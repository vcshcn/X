package net.xway.platform.app.system.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.base.utils.DateUtil;
import net.xway.platform.app.system.service.AccountLockException;
import net.xway.platform.app.system.service.IAccountService;
import net.xway.platform.app.system.service.ITimeZoneService;
import net.xway.platform.app.system.service.NoSuchAccountException;
import net.xway.platform.app.system.service.PasswordNotMatchException;
import net.xway.platform.app.system.utils.UserUtil;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Employee;
import net.xway.platform.system.dto.Environment;
import net.xway.platform.system.dto.TimeZone;
import net.xway.platform.system.dto.User;

@RestController
public class AccountController  {
	
	private Log log = LogFactory.getLog(AccountController.class);
	
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ITimeZoneService timezoneService;
	
	@RequestMapping("/Login")
	public JsonResult<Employee> login(String loginname, String password, HttpServletRequest request) {
		JsonResult<Employee> result = new JsonResult<>();
		if (loginname == null || loginname.trim().length() == 0) {
			result.put(1, "loginname is empty");
		}
		if (password == null || password.trim().length() == 0) {
			result.put(2, "password is empty");
		}
		if (result.hasErrors()) {
			return result;
		}
		
		try {
			Employee user = (Employee)accountService.login(loginname, password);
			//---------------------------------------------------------
			// put user in session
			Environment env = collectEnv(request); // collect environment
			user.setEnv(env);

			UserUtil.setUserToSession(request, user);
			
			result.setObject(user);
		}
		catch (NoSuchAccountException e) {
			result.put(3, "No Such Account");
		}
		catch (PasswordNotMatchException e) {
			result.put(4, "Password Not Match");
		}
		catch (AccountLockException e) {
			result.put(5, "Account Lock");
		}
		catch (Exception e) {
			log.error("User[loginname=" + loginname + ",password="+password + "] unknown exception", e);
			result.put(-1, e);
		}
		return result;
	}
	
	@RequestMapping("/Logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@RequestMapping("/SaveProfileInformation")
	public JsonResult<User> saveProfile(@RequestBody Map<String, Object> json, HttpServletRequest request, HttpSession session) throws ParseException {
		TimeZone timezone = null;
		if (json.get("timezoneid") != null) {
			timezone = timezoneService.findTimeZoneByTimeZoneID((Integer)json.get("timezoneid"));
		}
		
		User u = (User) session.getAttribute(User.SESSION_KEY);
		Integer userid = u.getUserid();
		String nickname = (String) json.get("nickname");
		String tel = (String) json.get("tel");
		String mobile = (String) json.get("mobile");
		Date birthday = null;// DateUtil.parseDate((String)json.get("birthday"), u.getCustomize().getDateformat() , timezone!=null ? timezone.getTimeZone() : u.getEnv().getBrowserLocal());
		String email = (String) json.get("email");
		String homepage = (String) json.get("homepage");
		String zipcode = (String) json.get("zipcode");
		String address = (String) json.get("address");
		Integer countryid = (Integer)json.get("countryid");
		Integer localeid = (Integer)json.get("localeid");
		Integer timezoneid = (Integer) json.get("timezoneid");
	
		accountService.saveProfile(userid, nickname, tel, mobile, birthday, email, homepage, zipcode, address, countryid, localeid, timezoneid);
		User user = accountService.findUserByUserID(userid);
		user.setEnv(u.getEnv());
		UserUtil.setUserToSession(request, user);
		
		return new JsonResult<>(user);
	}
	
	@RequestMapping("/SaveProfilePassword")
	public JsonResult<Void> saveProfilePassword(@RequestBody Map<String, String> params, HttpSession session) {
		User user = (User) session.getAttribute(User.SESSION_KEY);
		String password = params.get("password");
		if (password != null && "******".equals(password) ==false) {
			accountService.saveProfilePassword(user.getUserid(), password);
		}
		return new JsonResult<>(null);
	}
	
	@RequestMapping("/SaveProfileCustomize")
	public JsonResult<User> saveProfileCustomize(@RequestBody Customize c, HttpSession session) {
		User user = (User) session.getAttribute(User.SESSION_KEY);
		c.setCustomizeid(user.getUserid());
		accountService.saveProfileCustomize(c);
		user.setCustomize(c);
		return new JsonResult<>(user);
	}
	
	private Environment collectEnv(HttpServletRequest request) {
		Environment env = new Environment();
		env.setLoginTime(new Date());
		env.setLastActiveTime(env.getLoginTime());
		env.setClientIP(request.getRemoteHost());
		env.setServerIP(request.getServerName());
		env.setBrowserColorDepth(request.getParameter("color"));
		env.setBrowserDPI(request.getParameter("dpi"));
		env.setScreenWidth(request.getParameter("width"));
		env.setScreenHeight(request.getParameter("height"));
		env.setBrowserAgent(request.getHeader("user-agent"));
		env.setBrowserLocal(request.getHeader("accept-language"));
		env.setBrowserReferer(request.getHeader("referer"));
		env.setLatitude(request.getParameter("latitude"));
		env.setLongitude(request.getParameter("longitude"));
		if ( request.getParameter("offset") != null) {
			String _offset = request.getParameter("offset");
			try {
				int i = Integer.parseInt(_offset);
				env.setBrowserOffset(DateUtil.minute2gmt(i));
			}
			catch (NumberFormatException e) { }
		}
		
		return env;
	}

}
