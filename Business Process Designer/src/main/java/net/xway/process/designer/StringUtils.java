package net.xway.process.designer;

public class StringUtils {

	public static String repeat(String s, int times) {
		for (int i=0; i<times; i++) {
			s = s.concat(s);
		}
		return s;
	}
}
