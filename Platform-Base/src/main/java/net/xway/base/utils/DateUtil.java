package net.xway.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import net.xway.base.Constant;

public class DateUtil {
	
	public static final int ONEDAY_MS = 1 * 24 * 60 * 60 * 1000 - 1;  

	public static String minute2gmt(Integer offset) {
		if (offset != null) {
			int m = Math.abs( offset ) % 60;
			offset = - offset / 60;
			return "GMT"+ (offset > 0 ? "+"+offset : offset ) + ":" + ( m == 0 ? "00" : (m < 10 ? "0"+m : m) ) ;
		}
		return null;
	}

	public static Date inc(Date date) {
		return date == null ? null : new Date(date.getTime() + ONEDAY_MS);
	}
	
	public static Date parse(String date, String format, TimeZone zone) throws ParseException {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(zone);
			Date d = sdf.parse(date);
			if (d.getTime() < Constant.TEN_THOUSAND_YEAR) {
				return d;
			}
		}
		return null;
	}

	public static Date parseDate(String date, String format, TimeZone zone) throws ParseException {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setTimeZone(zone);
			Date d = sdf.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE,0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			d = c.getTime();
			if (d.getTime() < Constant.TEN_THOUSAND_YEAR) {
				return d;
			}
		}
		return null;
	}

}
