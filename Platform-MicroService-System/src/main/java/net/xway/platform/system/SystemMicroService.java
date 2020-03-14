package net.xway.platform.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.Constant;
import net.xway.base.database.PageResult;
import net.xway.base.database.Query;
import net.xway.base.utils.PagingUtil;
import net.xway.platform.system.dto.Country;
import net.xway.platform.system.dto.Customize;
import net.xway.platform.system.dto.Department;
import net.xway.platform.system.dto.Employee;
import net.xway.platform.system.dto.Field;
import net.xway.platform.system.dto.Locale;
import net.xway.platform.system.dto.Login;
import net.xway.platform.system.dto.Menu;
import net.xway.platform.system.dto.Org;
import net.xway.platform.system.dto.Privilege;
import net.xway.platform.system.dto.Resource;
import net.xway.platform.system.dto.Role;
import net.xway.platform.system.dto.TimeZone;
import net.xway.platform.system.dto.ToolBox;
import net.xway.platform.system.dto.User;
import net.xway.platform.system.dto.View;
import net.xway.platform.system.micro.feign.ISystemMicroService;
import net.xway.platform.system.service.IAccountService;
import net.xway.platform.system.service.ICountryService;
import net.xway.platform.system.service.IDataViewService;
import net.xway.platform.system.service.IDepartmentService;
import net.xway.platform.system.service.IEmployeeService;
import net.xway.platform.system.service.ILocaleService;
import net.xway.platform.system.service.IMenuService;
import net.xway.platform.system.service.IOrgService;
import net.xway.platform.system.service.IPrivilegeService;
import net.xway.platform.system.service.IResourceService;
import net.xway.platform.system.service.IRoleService;
import net.xway.platform.system.service.ITimeZoneService;
import net.xway.platform.system.service.IToolBoxService;

@RestController
public class SystemMicroService implements ISystemMicroService {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPrivilegeService privilegeService;
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private ILocaleService localeService;
	@Autowired
	private ITimeZoneService timeZoneService;
	@Autowired
	private IDataViewService dataViewService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IToolBoxService toolBoxService;
	
	@GetMapping("/name")
	public String getName() {
		return Constant.SYSTEM_NAME;
	}
	
	@GetMapping("/version")
	public String getVersion() {
		return Constant.SYSTEM_VERSION;
	}
	
	/*
	 * Account
	 */
	@GetMapping("/findAccountByLoginname")
	public ResponseEntity<Login> findAccountByLoginname(String loginname) {
		Login account = accountService.findAccountByLoginname(loginname);
		return ResponseEntity.ok(account);
	}
	
	@PostMapping("/matchAccountPassword")
	public ResponseEntity<Boolean> matchAccountPassword(@RequestBody Login account, @RequestParam("password") String password)  {
		boolean match = accountService.matchAccountPassword(account, password);
		return ResponseEntity.ok(match);
	}
	
	@GetMapping("/saveProfilePassword")
	public ResponseEntity<Void> saveProfilePassword(@RequestParam("userid") int userid, @RequestParam("password") String password) {
		accountService.saveProfilePassword(userid, password);
		return ResponseEntity.ok(null);
	}
	
	/*
	 * User
	 */
	@GetMapping("/findUserByUserID")
	public ResponseEntity<User> findUserByUserID(@RequestParam("userid") int userid) {
		User user = accountService.findUserByUserID(userid);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/saveProfile")
	public ResponseEntity<Void> saveProfile(@RequestBody Map<String, Object> json) {
		int userid = (Integer) json.get("userid");
		String nickname = (String) json.get("nickname");
		String tel = (String) json.get("tel");
		String mobile = (String) json.get("mobile");
		Date birthday = json.get("birthday") != null ? new Date((long)json.get("birthday")) : null;
		String email = (String) json.get("email");
		String homepage = (String) json.get("homepage");
		String zipcode = (String) json.get("zipcode");
		String address = (String) json.get("address");
		Integer countryid = (Integer)json.get("countryid");
		Integer localeid = (Integer)json.get("localeid");
		Integer timezoneid = (Integer) json.get("timezoneid");
		accountService.saveProfile(userid, nickname, tel, mobile, birthday, email, homepage, zipcode, address, countryid, localeid, timezoneid);
		return ResponseEntity.ok(null);
	}

	@PostMapping("/saveProfileCustomize")
	public ResponseEntity<Void> saveProfileCustomize(@RequestBody Customize customize) {
		accountService.saveProfileCustomize(customize);
		return ResponseEntity.ok(null);
	}

	/*
	 * Employee
	 */
	@PostMapping("/queryEmployeePage")
	public ResponseEntity<PageResult<Employee>> queryEmployeePage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		PageResult<Employee> result = employeeService.queryEmployeePage(query);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findEmployeeByUserID")
	public ResponseEntity<Employee> findEmployeeByUserID(@RequestParam("userid") int userid) {
		Employee e = employeeService.findEmployeeByUserID(userid);
		return ResponseEntity.ok(e);
	}

	/*
	 * Role
	 */
	@PostMapping("/queryRolePage")
	public ResponseEntity<PageResult<Role>> queryRolePage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		PageResult<Role> result = roleService.queryRolePage(query);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findRoleByRoleID")
	public ResponseEntity<Role> findRoleByRoleID(@RequestParam("roleid") int roleid) {
		Role role = roleService.findRoleByRoleID(roleid);
		return ResponseEntity.ok(role);
	}
	
	/*
	 * Department
	 */
	@PostMapping("/queryDepartmentPage")
	public ResponseEntity<PageResult<Department>> queryDepartmentPage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		PageResult<Department> result = departmentService.queryDepartmentPage(query);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findDepartmentByDepartmentID")
	public ResponseEntity<Department> findDepartmentByDepartmentID(@RequestParam("departmentid") int departmentid) {
		Department department = departmentService.findDepartmentByDepartmentID(departmentid);
		return ResponseEntity.ok(department);
	}

	/*
	 * Org
	 */
	@PostMapping("/queryOrgPage")
	public ResponseEntity<PageResult<Org>> queryOrgPage(@RequestBody(required = false) Query query) {
		if (query == null) {
			query = PagingUtil.create();
		}
		PageResult<Org> result = orgService.queryOrgPage(query);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/findOrgByOrgID")
	public ResponseEntity<Org> findOrgByOrgID(@RequestParam("orgid") int orgid) {
		Org org = orgService.findOrgByOrgID(orgid);
		return ResponseEntity.ok(org);
	}

	/*
	 * Privilege
	 */
	@GetMapping("/findPrivilegeByPrivilegeID")
	public ResponseEntity<Privilege> findPrivilegeByPrivilegeID(int privilegeid) {
		Privilege privilege = privilegeService.findPrivilegeByPrivilegeID(privilegeid);
		return ResponseEntity.ok(privilege);
	}
	
	@GetMapping("/findAllPrivilege")
	public ResponseEntity<List<Privilege>> findAllPrivilege() {
		List<Privilege> ps = privilegeService.findAllPrivilege();
		return ResponseEntity.ok(ps);
	}

	/*
	 * View
	 */
	@GetMapping("/findViewByName")
	public ResponseEntity<View> findViewByName(String viewname) {
		View view = dataViewService.findViewByName(viewname);
		return ResponseEntity.ok(view);
	}
	
	/*
	 * Field
	 */
	@GetMapping("/findUserTableField")
	public ResponseEntity<List<Field>> findUserTableField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid) {
		List<Field> fields = dataViewService.findUserTableField(userid, viewid);
		return ResponseEntity.ok(fields);
	}

	@GetMapping("/findUserDisplayField")
	public ResponseEntity<List<Field>> findUserDisplayField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid) {
		List<Field> fields = dataViewService.findUserDisplayField(userid, viewid);
		return ResponseEntity.ok(fields);
	}
	
	@GetMapping("/findUserNotDisplayField")
	public ResponseEntity<List<Field>> findUserNotDisplayField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid) {
		List<Field> fields = dataViewService.findUserNotDisplayField(userid, viewid);
		return ResponseEntity.ok(fields);
	}
	
	@GetMapping("/saveUserExcludeField")
	public ResponseEntity<Void> saveUserExcludeField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid, @RequestParam("fieldid") int[] fieldid) {
		dataViewService.saveUserExcludeField(userid, viewid, fieldid);
		return ResponseEntity.ok(null);
	}

	
	/*
	 * Country
	 */
	@GetMapping("/findCountryByCountryID")
	public ResponseEntity<Country> findCountryByCountryID(int countryid) {
		Country country = countryService.findCountryByCountryID(countryid);
		return ResponseEntity.ok(country);
	}
	
	@GetMapping("/findAllCountry")
	public ResponseEntity<List<Country>> findAllCountry() {
		List<Country> countrys = countryService.findAllCountry();
		return ResponseEntity.ok(countrys);
	}

	/*
	 * Local
	 */
	@GetMapping("/findAllLocale")
	public ResponseEntity<List<Locale>> findAllLocale() {
		List<Locale> locales = localeService.findAllLocale();
		return ResponseEntity.ok(locales);
	}
	
	/*
	 * TimeZone
	 */
	@GetMapping("/findAllTimeZone")
	public ResponseEntity<List<TimeZone>> findAllTimeZone() {
		List<TimeZone> zones = timeZoneService.findAllTimeZone();
		return ResponseEntity.ok(zones);
	}

	@GetMapping("/findTimeZoneByTimeZoneID")
	public ResponseEntity<TimeZone> findTimeZoneByTimeZoneID(int timezoneid) {
		TimeZone zone = timeZoneService.findTimeZoneByTimeZoneID(timezoneid);
		return ResponseEntity.ok(zone);
	}
	
	/*
	 * Resource
	 */
	@GetMapping("/findResourceByLink")
	public ResponseEntity<Resource> findResourceByLink(@RequestParam("name")  String name) {
		Resource resource = resourceService.findResourceByLink(name);
		return ResponseEntity.ok(resource);
	}

	/*
	 * Menu
	 */	
	@GetMapping("/findMenuByMenuID")
	public ResponseEntity<Menu> findMenuByMenuID(@RequestParam("menuid") int menuid) {
		Menu menu = menuService.findMenuByMenuID(menuid);
		return ResponseEntity.ok(menu);
	}
	
	@GetMapping("/findAllMenu")
	public ResponseEntity<List<Menu>> findAllMenu() {
		List<Menu> menus = menuService.findAllMenu();
		return ResponseEntity.ok(menus);
	}

	@GetMapping("/findMenuByRoleID")
	public ResponseEntity<List<Menu>> findMenuByRoleID(@RequestParam("roleid") int roleid) {
		List<Menu> menus = menuService.findMenuByRoleID(roleid);
		return ResponseEntity.ok(menus);
	}

	@GetMapping("/findAllMenuTree")
	public ResponseEntity<List<Menu>> findAllMenuTree() {
		List<Menu> menus = menuService.findAllMenuTree();
		return ResponseEntity.ok(menus);
	}

	/*
	 * ToolBox
	 */
	@GetMapping("/findAllToolBox")
	public ResponseEntity<List<ToolBox>> findAllToolBox() {
		List<ToolBox> tools = toolBoxService.findAllToolBox();
		return ResponseEntity.ok(tools);
	}
	
	@GetMapping("/findSelectedToolBoxByUserID")
	public ResponseEntity<List<ToolBox>> findSelectedToolBoxByUserID(@RequestParam("userid") int userid) {
		List<ToolBox> tools = toolBoxService.findSelectedToolBoxByUserID(userid);
		return ResponseEntity.ok(tools);
	}

}
