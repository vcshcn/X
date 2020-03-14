package net.xway.platform.system.dto;

import java.util.Date;

public class Environment {

	private Date loginTime;
	private Date lastActiveTime;
	private String lastActiveAction;
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
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getBrowserColorDepth() {
		return browserColorDepth;
	}
	public void setBrowserColorDepth(String browserColor) {
		this.browserColorDepth = browserColor;
	}
	public String getBrowserDPI() {
		return browserDPI;
	}
	public void setBrowserDPI(String browserDPI) {
		this.browserDPI = browserDPI;
	}
	public String getBrowserAgent() {
		return browserAgent;
	}
	public void setBrowserAgent(String browserAgent) {
		this.browserAgent = browserAgent;
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
	public String getBrowserOffset() {
		return browserOffset;
	}
	public void setBrowserOffset(String browserOffset) {
		this.browserOffset = browserOffset;
	}
	public Date getLastActiveTime() {
		return lastActiveTime;
	}
	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}
	public String getLastActiveAction() {
		return lastActiveAction;
	}
	public void setLastActiveAction(String lastActiveAction) {
		this.lastActiveAction = lastActiveAction;
	}
	
	
}
