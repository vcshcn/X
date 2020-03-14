package net.xway.platform.app.system.utils;


import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.xway.base.Constant;
import net.xway.platform.system.dto.User;

/**
 * User Utils
 * @author cc
 *
 */
public class UserUtil {

//	public static getUserDateFormat(HttpSession session) {
//		
//	}
	
//	/**
//	 * Get Current User Date Format
//	 * @param session HttpSession
//	 * @return Date Format
//	 */
//	public static String getUserDateFormat(HttpSession session) {
//		User user = (User) session.getAttribute(User.SESSION_KEY);
//		String format = user.getCustomize().getDateformat();
//		if (format == null) {
//			format = Constant.USER_DEFAULT_DATE_FORMAT;
//		}
//		return format;
//	}
//
//	public static String getUserTimeFormat(HttpSession session) {
//		User user = (User) session.getAttribute(User.SESSION_KEY);
//		String format = user.getCustomize().getTimeformat();
//		if (format == null) {
//			format = Constant.USER_DEFAULT_TIME_FORMAT;
//		}
//		return format;
//	}
//
//	public static String getUserDateTimeFormat(HttpSession session) {
//		User user = (User) session.getAttribute(User.SESSION_KEY);
//		String format = user.getCustomize().getDatetimeformat();
//		if (format == null) {
//			format = Constant.USER_DEFAULT_DATETIME_FORMAT;
//		}
//		return format;
//	}
	
	public static User getUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (User) session.getAttribute(User.SESSION_KEY);
		}
		return null;
	}

	public static void setUserToSession(HttpServletRequest request, User user) {
		HttpSession session =request.getSession();
		user.setMaxInactiveInterval(session.getMaxInactiveInterval());
		session.setAttribute(User.SESSION_KEY, user);
		
		// get & put user local
		if (user.getLocale() != null) {
			session.setAttribute(Constant.USER_SESSION_LOCALE_KEY, user.getLocale().getLocale());
		}
		else {
			session.setAttribute(Constant.USER_SESSION_LOCALE_KEY, request.getLocale());
		}
		
		// update browser local
		Locale locale = (Locale) session.getAttribute(Constant.USER_SESSION_LOCALE_KEY);
		String htmllocale = locale.getLanguage() + "_" + locale.getCountry();
		switch (htmllocale) {
			case "zh" : htmllocale = "zh_CN"; break ;
			case "zh_CN":
			case "zh_TW":
			case "en_US": break;
			default: htmllocale = "en_US";
		}
		session.setAttribute(Constant.BROWSER_SESSION_LOCALE_KEY, htmllocale);
		
		// get & put user timezone
		if (user.getTimezone() != null) {
			session.setAttribute(Constant.USER_SESSION_TIMEZONE_KEY, user.getTimezone().getTimeZone());
		}
		else {
			String gmt = (String) session.getAttribute(Constant.BROWSER_SESSION_TIMEZONE_KEY);
			TimeZone zone = null;
			if (gmt != null ) {
				zone = TimeZone.getTimeZone(gmt);
			}
			else {
				zone = TimeZone.getDefault();
			}
			session.setAttribute(Constant.USER_SESSION_TIMEZONE_KEY, zone);
		}
		
		// get & put user country
		if (user.getCountry() != null) {
			session.setAttribute(Constant.USER_SESSION_COUNTRY_KEY, user.getCountry());
		}
		else {
			session.removeAttribute(Constant.USER_SESSION_COUNTRY_KEY);
		}
		
		// put env
		session.setAttribute(Constant.BROWSER_SESSION_TIMEZONE_KEY, user.getEnv().getBrowserOffset());
		session.setAttribute(Constant.BROWSER_SESSION_WIDTHE_KEY, user.getEnv().getScreenWidth());
		session.setAttribute(Constant.BROWSER_SESSION_HEIGHT_KEY, user.getEnv().getScreenHeight());
		session.setAttribute(Constant.BROWSER_SESSION_COLOR_KEY, user.getEnv().getBrowserColorDepth());
		session.setAttribute(Constant.BROWSER_SESSION_DPI_KEY, user.getEnv().getBrowserDPI());
		session.setAttribute(Constant.USER_SESSION_LOGINTIME_KEY,  user.getEnv().getLoginTime());

	}
	
}
