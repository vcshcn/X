package net.xway.process.designer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class AppUtils {

	public static Connection getConnection() throws MalformedURLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String jdbcType = GlobalConfigure.getJdbcType();
		return getConnection(GlobalConfigure.getJdbcDriverPath(jdbcType), GlobalConfigure.getJdbcDriver(jdbcType), GlobalConfigure.getJdbcDriverURL(jdbcType), GlobalConfigure.getJdbcDriverUser(jdbcType), GlobalConfigure.getJdbcDriverPassword(jdbcType));
	}
	
	public static Connection getConnection(String path, String klass, String url, String user, String password) throws MalformedURLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		try {
			return DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			URLClassLoader loader = new URLClassLoader(new URL[] {Paths.get(path).toUri().toURL() });
			Driver driver =  (Driver) Class.forName(klass, true, loader).getConstructor().newInstance();
			
			DriverManager.registerDriver(new DriverWrapper(driver));
			return DriverManager.getConnection(url, user, password);
		}
	}
}


class DriverWrapper implements Driver {
	
	private Driver driver;
	
	DriverWrapper(Driver driver) {
		this.driver = driver;
	}

	DriverWrapper(String jdbcJarFilePath, String driverClass) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		URL url = new File(jdbcJarFilePath).toURI().toURL();
		URLClassLoader classloader = new URLClassLoader(new URL[] {url}, getClass().getClassLoader());
		Class<?> jdbcClass = Class.forName(driverClass, true, classloader);
		this.driver = (Driver) jdbcClass.getConstructor().newInstance();
	}
	
	public boolean acceptsURL(String u) throws SQLException {
		return this.driver.acceptsURL(u);
	}
	public Connection connect(String u, Properties p) throws SQLException {
		return this.driver.connect(u, p);
	}
	public int getMajorVersion() {
		return this.driver.getMajorVersion();
	}
	public int getMinorVersion() {
		return this.driver.getMinorVersion();
	}
	public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
		return this.driver.getPropertyInfo(u, p);
	}
	public boolean jdbcCompliant() {
		return this.driver.jdbcCompliant();
	}
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return this.driver.getParentLogger();
	}
}
