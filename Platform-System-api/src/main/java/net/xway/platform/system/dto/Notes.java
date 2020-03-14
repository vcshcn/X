package net.xway.platform.system.dto;

import java.util.Date;

public class Notes {

	private Integer notesid;
	private String title;
	private String content;
	private User user;
	private Date createtime;
	
	public Integer getNotesid() {
		return notesid;
	}
	public void setNotesid(Integer notesid) {
		this.notesid = notesid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
