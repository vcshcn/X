package net.xway.platform.system.dto;

import java.util.Date;
import java.util.List;

import net.xway.base.Constant;


public class User  extends BaseDTO {

	private static final long serialVersionUID = 1234567890L;
	public static final char TYPE_EMPLOYEE = 'E';
	public static final char TYPE_CUSTOMER = 'C';
	
	public final static String SESSION_KEY = "user";
	
	private Integer userid;
	private char type;
	private String name;
	private Gender gender;
	private String nickname;
	private Date birthday;
	private String tel;
	private String mobile;
	private String address;
	private String zipcode;
	private String email;
	private String homepage;
	private String photo;
	private String aboutme; 
	
	private Login login;
	private Role role;
	private Country country;
	private Locale locale;
	private TimeZone timezone;
	private Customize customize;
	
	private List<ToolBox> toolboxs;
	private Environment env;
	
	private int maxInactiveInterval;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Integer getAge() {
		if (birthday != null) {
			Date now = new Date();
			long diff = now.getTime() - birthday.getTime();
			diff = diff / (1 * 24 * 60 * 60 * 1000); // convert day
			return (int) (diff % 365);
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getPhoto() {
		if (photo == null) {
			return Constant.USER_DEFAULT_PHOTO;
		}
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public TimeZone getTimezone() {
		return timezone;
	}
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Customize getCustomize() {
		return customize;
	}
	public void setCustomize(Customize customize) {
		this.customize = customize;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<ToolBox> getToolboxs() {
		return toolboxs;
	}
	public void setToolboxs(List<ToolBox> toolboxs) {
		this.toolboxs = toolboxs;
	}
	public String getAboutme() {
		return aboutme;
	}
	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}
	public Environment getEnv() {
		return env;
	}
	public void setEnv(Environment env) {
		this.env = env;
	}
	public int getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	public void setMaxInactiveInterval(int maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

}
