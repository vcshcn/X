package net.xway.platform.system.dto;

public class Bookmark extends BaseDTO  {

	private static final long serialVersionUID = -8049778018447663548L;
	private Integer bookmarkid;
	private String name;
	private String link;
	
	public Integer getBookmarkid() {
		return bookmarkid;
	}
	public void setBookmarkid(Integer bookmarkid) {
		this.bookmarkid = bookmarkid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
