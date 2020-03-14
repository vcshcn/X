package net.xway.platform.system.dto;

public class Attachment extends BaseDTO {

	private static final long serialVersionUID = -2665962743619136058L;
	
	private Integer attachmentid;
	private String filename;
	private String extname;
	private long size;
	private String realpath;
	
	public Integer getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(Integer attachmentid) {
		this.attachmentid = attachmentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getExtname() {
		return extname;
	}
	public void setExtname(String extname) {
		this.extname = extname;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getRealpath() {
		return realpath;
	}
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	
}
