package net.xway.base.utils;

public class IPUtil {

	public static int ip2int(String ip) {
		String[] _ip = ip.split("\\.");
		if (_ip.length == 4) {
			return (Integer.parseInt(_ip[0]) << 24) + (Integer.parseInt(_ip[1]) << 16) + (Integer.parseInt(_ip[2]) << 8) + Integer.parseInt(_ip[3]);
		}
		return 0;
	}
	
	public static String int2ip(int ip) {
		return (ip>>>24) + "." + (ip >>> 16 & 0xff) + "." + (ip >>> 8 & 0xff) + "." + (ip & 0xff);
	}
}
