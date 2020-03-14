package net.xway.platform.system.dto;

import java.util.Date;

public class UserAlarmLog {

	private String userAlarmLogId;
	private String userLoginLogId;
	private String action;
	private String httpMethod;
	private String fileName;
	private String className;
	private String methodName;
	private Integer lineNumber;
	private String message;
	private Date createTime;
	
	public UserAlarmLog() { }
	
	public UserAlarmLog(String userLoginLogId, String action, String httpMethod, Throwable cause) {
		this.userLoginLogId = userLoginLogId;
		this.action = action;
		this.httpMethod = httpMethod;
		StackTraceElement element = cause.getStackTrace()[0];
		fileName = element.getFileName();
		className = element.getClassName();
		methodName = element.getMethodName();
		lineNumber = element.getLineNumber();
		message = cause.getLocalizedMessage();
		createTime = new Date();
	}
	
	public String getUserAlarmLogId() {
		return userAlarmLogId;
	}
	public void setUserAlarmLogId(String userAlarmLogId) {
		this.userAlarmLogId = userAlarmLogId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Integer getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(Integer lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUserLoginLogId() {
		return userLoginLogId;
	}

	public void setUserLoginLogId(String userLoginLogId) {
		this.userLoginLogId = userLoginLogId;
	}
	
	
}
