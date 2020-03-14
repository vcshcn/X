package net.xway.platform.system.dto;

import java.util.Date;
import java.util.List;

public class View  extends BaseDTO {

	private static final long serialVersionUID = 7525117152344650733L;
	private Integer viewid;
	private String name;
	private String label;
	private String type;
	private String tableName;
	private String className;
	private String serviceName;
	private String serviceInterface;
	private String serviceImpl;
	private Date modifiedTime;
	private List<Field> fields;
	
	public Integer getViewid() {
		return viewid;
	}
	public void setViewid(Integer viewid) {
		this.viewid = viewid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceInterface() {
		return serviceInterface;
	}
	public void setServiceInterface(String serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	public String getServiceImpl() {
		return serviceImpl;
	}
	public void setServiceImpl(String serviceImpl) {
		this.serviceImpl = serviceImpl;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	
}
