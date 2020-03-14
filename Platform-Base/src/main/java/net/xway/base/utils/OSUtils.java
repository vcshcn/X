package net.xway.base.utils;

import java.text.DateFormat;
import java.util.Locale;

public class OSUtils {

	private static DateFormat defaultDateFormat ;
	
	static {
		String l = System.getProperty("user.language");		
		if (l != null) {
			Locale locale = new Locale(l);
			defaultDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		}
	}
	
	public static DateFormat getDefaultDateFormat() {
		return defaultDateFormat;
	}
}
