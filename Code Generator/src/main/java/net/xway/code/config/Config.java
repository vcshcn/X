package net.xway.code.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import net.xway.code.database.IDataBase;
import net.xway.code.database.impl.MySQLDataBase;

public class Config {

	private static Config instance = new Config();
	private Config() {}
	public static Config getInstance() {
		return instance;
	}
		
	private IDataBase currentDataBase = new MySQLDataBase();
	private String jar = "";
	private String driver = "";
	private String url = "";
	private String username = "";
	private String password = "";
	
	private String baseDirectory = "";
	private String javaDirectory = "";
	private String webDirectory = "";
	private String sqlDirectory = "";
	private String docDirectory = "";
	
	public IDataBase getCurrentDataBase() {
		return currentDataBase; 
	}
	
	public void setCurrentDataBase(String name) {
		if (MySQLDataBase.NAME.equals(name)) {
			currentDataBase = new MySQLDataBase();
			return ;
		}
		
		throw new  java.lang.IllegalArgumentException(name);
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getJar() {
		return jar;
	}
	public void setJar(String jar) {
		this.jar = jar;
	}
	public String getBaseDirectory() {
		return baseDirectory;
	}
	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}
	public String getJavaDirectory() {
		return javaDirectory;
	}
	public void setJavaDirectory(String javaDirectory) {
		this.javaDirectory = javaDirectory;
	}
	public String getWebDirectory() {
		return webDirectory;
	}
	public void setWebDirectory(String webDirectory) {
		this.webDirectory = webDirectory;
	}
	public String getSqlDirectory() {
		return sqlDirectory;
	}
	public void setSqlDirectory(String sqlDirectory) {
		this.sqlDirectory = sqlDirectory;
	}
	public String getDocDirectory() {
		return docDirectory;
	}
	public void setDocDirectory(String docDirectory) {
		this.docDirectory = docDirectory;
	}

	public final static String FILENAME = "config.xway.properties";
	
	public void save(String directory) throws IOException {
		Properties ps = new Properties();
		ps.setProperty("database", currentDataBase.getName());
		ps.setProperty("jar", jar);
		ps.setProperty("driver", driver);
		ps.setProperty("url", url);
		ps.setProperty("username", username);
		ps.setProperty("password", password);
		ps.setProperty("baseDirectory", baseDirectory);
		ps.setProperty("javaDirectory", javaDirectory);
		ps.setProperty("webDirectory", webDirectory);
		ps.setProperty("sqlDirectory", sqlDirectory);
		ps.setProperty("docDirectory", docDirectory);

		File file = new File(directory, FILENAME);		
		try (
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
		) {
			ps.store(bw, "xway generate config");
		}
		
	}
	
	public void load(String directory) throws FileNotFoundException, IOException {
		Properties ps = new Properties();
		
		File file = new File(directory, FILENAME);		
		try (
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		) {
			ps.load(br);
		}
		
		setCurrentDataBase(ps.getProperty("database"));
		setJar(ps.getProperty("jar"));
		setDriver(ps.getProperty("driver"));
		setUrl(ps.getProperty("url"));
		setUsername(ps.getProperty("username"));
		setPassword(ps.getProperty("password"));
		setBaseDirectory(ps.getProperty("baseDirectory"));
		setJavaDirectory(ps.getProperty("javaDirectory"));
		setWebDirectory(ps.getProperty("webDirectory"));
		setSqlDirectory(ps.getProperty("sqlDirectory"));
		setDocDirectory(ps.getProperty("docDirectory"));
	}
	
	public static boolean testConnection(String jdbcJarFilePath, String driverClass, String url, String user, String password) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try (
			Connection conn =  getConnection(jdbcJarFilePath, driverClass, url, user, password);
		) {
			Statement stmt = conn.createStatement();
			stmt.executeQuery("SELECT 1");
			conn.close();
			return true;
		}
	}
	
	public Connection getConnection() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return getConnection(jar, driver, url, username, password);
	}
	
	private static Connection getConnection(String jdbcJarFilePath, String driverClass, String url, String user, String password) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		registerDriver(jdbcJarFilePath, driverClass, url);
		return  DriverManager.getConnection(url, user, password);
	}
	
	protected synchronized static Driver registerDriver(String jdbcJarFilePath, String driverClass, String url) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Driver d = null;
		try {
			d = DriverManager.getDriver(url);
		}
		catch (SQLException e) {
		}
		if (d == null) {
			d =  new DriverBridge(jdbcJarFilePath, driverClass);
			DriverManager.registerDriver(d);
		}
		return d;
	}

	private static class DriverBridge implements java.sql.Driver, AutoCloseable {

		private Driver driver;
		public DriverBridge(String jdbcJarFilePath, String driverClass) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			URL url = new File(jdbcJarFilePath).toURI().toURL();
			URLClassLoader classloader = new URLClassLoader(new URL[] {url}, getClass().getClassLoader());
			Class<?> jdbcClass = Class.forName(driverClass, true, classloader);
			driver = (Driver) jdbcClass.getConstructor().newInstance();
		}
		
		@Override
		public Connection connect(String url, Properties info) throws SQLException {
			return driver.connect(url, info);
		}

		@Override
		public boolean acceptsURL(String url) throws SQLException {
			return driver.acceptsURL(url);
		}

		@Override
		public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
			return driver.getPropertyInfo(url, info);
		}

		@Override
		public int getMajorVersion() {
			return driver.getMajorVersion();
		}

		@Override
		public int getMinorVersion() {
			return driver.getMinorVersion();
		}

		@Override
		public boolean jdbcCompliant() {
			return driver.jdbcCompliant();
		}

		@Override
		public Logger getParentLogger() throws SQLFeatureNotSupportedException {
			return driver.getParentLogger();
		}

		@Override
		public void close() throws Exception {
			DriverManager.deregisterDriver(this);
		}
		
	}

}
