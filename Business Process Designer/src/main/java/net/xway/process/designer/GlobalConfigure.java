package net.xway.process.designer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Locale;
import java.util.Properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;

public class GlobalConfigure {

	public static IntegerProperty WIDTH ;
	public static IntegerProperty HEIGHT;
	public static SimpleObjectProperty<Background> diagramPaneBackground ;

	private static Properties ps;
	
	public static void setJdbcType(String jdbcType) {
		ps.setProperty("jdbcType", jdbcType);
	}
	
	public static void setJdbcDriverPath(String jdbcType, String jdbcDriverPath) {
		ps.setProperty(jdbcType+".jdbcDriverPath", jdbcDriverPath);
	}

	public static void setJdbcDriver(String jdbcType, String jdbcDriver) {
		ps.setProperty(jdbcType+".jdbcDriver", jdbcDriver);
	}

	public static void setJdbcDriverHost(String jdbcType, String jdbcDriverHost) {
		ps.setProperty(jdbcType+".jdbcDriverHost", jdbcDriverHost);
	}

	public static void setJdbcDriverPort(String jdbcType, int jdbcDriverPort) {
		ps.setProperty(jdbcType+".jdbcDriverPort", jdbcDriverPort+"");
	}

	public static void setJdbcDriverUser(String jdbcType, String jdbcDriverUser) {
		ps.setProperty(jdbcType+".jdbcDriverUser", jdbcDriverUser);
	}

	public static void setJdbcDriverPassword(String jdbcType, String jdbcDriverPassword) {
		ps.setProperty(jdbcType+".jdbcDriverPassword", jdbcDriverPassword);
	}

	public static void setJdbcDriverDatabase(String jdbcType, String jdbcDriverDatabase) {
		ps.setProperty(jdbcType+".jdbcDriverDatabase", jdbcDriverDatabase);
	}

	public static void setLocale(Locale locale) {
		ps.setProperty("locale", locale.getLanguage() + "_" + locale.getCountry());
	}

	
	
	public static String[] getJdbcAllTypes() { 
		return readStringArray(ps, "jdbcAllTypes", "MariaDB");
	}
	
	public static String getJdbcType() {
		return ps.getProperty("jdbcType", "MariaDB");
	}
	
	public static String getJdbcDriverPath(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverPath");
	}
	
	public static String getJdbcDriver(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriver", "org.mariadb.jdbc.Driver");
	}
	
	public static String getJdbcDriverHost(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverHost", "localhost");
	}
	
	public static Integer getJdbcDriverPort(String jdbcType) {
		return readeInteger(ps, jdbcType+".jdbcDriverPort", "3306");
	}
	
	public static String getJdbcDriverUser(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverUser", "root");
	}
	
	public static String getJdbcDriverPassword(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverPassword", "mysql");
	}
	
	public static String getJdbcDriverDatabase(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverDatabase", "XWAY");
	}
	
	public static String getJdbcDriverURLTemplate(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverURLTemplate", "jdbc:mariadb://%s:%d/%s?characterEncoding=UTF-8&useUnicode=true");
	}
	
	public static String getJdbcDriverTestSQL(String jdbcType) {
		return ps.getProperty(jdbcType + ".jdbcDriverTestSQL", "SELECT 1");
	}

	public static String getJdbcDriverURL(String jdbcType) {
		return String.format(getJdbcDriverURLTemplate(jdbcType), getJdbcDriverHost(jdbcType), getJdbcDriverPort(jdbcType), getJdbcDriverDatabase(jdbcType) );
	}
	
	public static Locale getLocale() {
		String s = ps.getProperty("locale");
		if (s == null) {
			return Locale.getDefault();
		}
		return new Locale(s.substring(0, 2), s.substring(3,5));
	}

	
	public static void reload() {
		try {
			reloadFile();
		} 
		catch (IOException e) {
			reloadDefault();
		}
		
		WIDTH = new SimpleIntegerProperty(7);
		HEIGHT = new SimpleIntegerProperty(7);
		diagramPaneBackground = new SimpleObjectProperty<>(new Background(new BackgroundImage(new DotBackgroundImage(), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
	}
	
	public static void reloadFile() throws IOException {
		try (
			Reader reader = new BufferedReader(new FileReader("config.properties"));
		)
		{
			ps = new Properties();
			ps.load(reader);
			
		}
	}
	
	public static void reloadDefault() {
		String jdbcType = "MariaDB";
		ps = new Properties();
		ps.setProperty("locale", Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry());
		ps.setProperty("jdbcAllTypes", jdbcType);
		ps.setProperty("jdbcType", jdbcType);
		ps.setProperty(jdbcType+".jdbcDriverPath", "");
		ps.setProperty(jdbcType+".jdbcDriver", "org.mariadb.jdbc.Driver");
		ps.setProperty(jdbcType+".jdbcDriverHost", "localhost");
		ps.setProperty(jdbcType+".jdbcDriverPort", "3306");
		ps.setProperty(jdbcType+".jdbcDriverUser", "root");
		ps.setProperty(jdbcType+".jdbcDriverPassword", "mysql");
		ps.setProperty(jdbcType+".jdbcDriverDatabase", "XWAY");
		ps.setProperty(jdbcType+".jdbcDriverURLTemplate", "jdbc:mariadb://%s:%d/%s?characterEncoding=UTF-8&useUnicode=true");
		ps.setProperty(jdbcType+".jdbcDriverTestSQL", "SELECT 1");
	}
	
	private static String[] readStringArray(Properties p, String key, String df) {
		String value = p.getProperty(key, df);
		return value != null ? value.split(",") : null;
	}
	
	private static Integer readeInteger(Properties p, String key, String df) {
		String value = p.getProperty(key, df);
		return Integer.valueOf(value);
	}
	
	public static void saveFile() throws IOException {
		try (
				Writer writer = new BufferedWriter(new FileWriter("config.properties"));
		)
		{
			ps.store(writer, "Business Process Designer 1.0");
		}
	}

    static class DotBackgroundImage extends WritableImage {
    	
		public DotBackgroundImage() {
			super(WIDTH.get(), HEIGHT.get());
			for (int i=0; i<WIDTH.get(); i++) {
				for (int j=0; j<HEIGHT.get(); j++) {
					this.getPixelWriter().setColor(i, j, Color.WHITE);
				}
			}
			getPixelWriter().setColor(WIDTH.get()/2, HEIGHT.get()/2, Color.BLACK);
		}
    	
    }
	
	static {
		reload();
	}

}