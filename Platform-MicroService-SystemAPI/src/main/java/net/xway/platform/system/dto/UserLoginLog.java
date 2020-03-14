package net.xway.platform.system.dto;

import java.util.Date;


public class UserLoginLog {

	private String userLoginLogId;
	private String name;
	private Date loginTime;
	private Date lastActiveTime;
	private String clientIP;
	private String serverIP;
	private String browserLocal;
	private String browserOffset;
	private String browserReferer;
	private String screenWidth;
	private String screenHeight;
	private String browserAgent;
	private String browserColorDepth;
	private String browserDPI;
	private String latitude;
	private String longitude;
	
	public UserLoginLog() {	
	}
	
	public UserLoginLog(User user) {
		this.name = user.getName();
		this.lastActiveTime = user.getEnv().getLastActiveTime();
		this.loginTime = user.getEnv().getLoginTime();
		this.clientIP = user.getEnv().getClientIP();
		this.serverIP = user.getEnv().getServerIP();
		this.browserLocal = user.getEnv().getBrowserLocal();
		this.browserOffset = user.getEnv().getBrowserOffset();
		this.browserReferer = user.getEnv().getBrowserReferer();
		this.screenWidth = user.getEnv().getScreenWidth();
		this.screenHeight = user.getEnv().getScreenHeight();
		this.browserAgent = user.getEnv().getBrowserAgent();
		this.browserColorDepth = user.getEnv().getBrowserColorDepth();
		this.browserDPI = user.getEnv().getBrowserDPI();
		this.latitude = user.getEnv().getLatitude();
		this.longitude = user.getEnv().getLongitude();
		
	}

	public String getUserLoginLogId() {
		return userLoginLogId;
	}

	public void setUserLoginLogId(String userLoginLogId) {
		this.userLoginLogId = userLoginLogId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getBrowserLocal() {
		return browserLocal;
	}

	public void setBrowserLocal(String browserLocal) {
		this.browserLocal = browserLocal;
	}

	public String getBrowserOffset() {
		return browserOffset;
	}

	public void setBrowserOffset(String browserOffset) {
		this.browserOffset = browserOffset;
	}

	public String getBrowserReferer() {
		return browserReferer;
	}

	public void setBrowserReferer(String browserReferer) {
		this.browserReferer = browserReferer;
	}

	public String getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}

	public String getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}

	public String getBrowserAgent() {
		return browserAgent;
	}

	public void setBrowserAgent(String browserAgent) {
		this.browserAgent = browserAgent;
	}

	public String getBrowserColorDepth() {
		return browserColorDepth;
	}

	public void setBrowserColorDepth(String browserColorDepth) {
		this.browserColorDepth = browserColorDepth;
	}

	public String getBrowserDPI() {
		return browserDPI;
	}

	public void setBrowserDPI(String browserDPI) {
		this.browserDPI = browserDPI;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Date getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
