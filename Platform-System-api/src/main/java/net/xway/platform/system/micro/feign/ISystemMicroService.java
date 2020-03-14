package net.xway.platform.system.micro.feign;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

public interface ISystemMicroService {

	/*
	 * Account
	 */
	@GetMapping("/findAccountByLoginname")
	public ResponseEntity<Login> findAccountByLoginname(@RequestParam("loginname") String loginname);
	
	@PostMapping("/matchAccountPassword")
	public ResponseEntity<Boolean> matchAccountPassword(@RequestBody Login account, @RequestParam("password") String password);
	
	@GetMapping("/saveProfilePassword")
	public ResponseEntity<Void> saveProfilePassword(@RequestParam("userid") int userid, @RequestParam("password") String password);
	
	/*
	 * User
	 */
	@GetMapping("/findUserByUserID")
	public ResponseEntity<User> findUserByUserID(@RequestParam("userid") int userid);
	
	@PostMapping("/saveProfile")
	public ResponseEntity<Void> saveProfile(@RequestBody Map<String, Object> json);
	
	@PostMapping("/saveProfileCustomize")
	public ResponseEntity<Void> saveProfileCustomize(@RequestBody Customize customize);
	
	/*
	 * Employee 
	 */
	@GetMapping("/findEmployeeByUserID")
	public ResponseEntity<Employee> findEmployeeByUserID(@RequestParam("userid") int userid);
	@PostMapping("/queryEmployeePage")
	public ResponseEntity<PageResult<Employee>> queryEmployeePage(@RequestBody Query query);
	
	/*
	 * Role
	 */
	@PostMapping("/queryRolePage")
	public ResponseEntity<PageResult<Role>> queryRolePage(@RequestBody Query query);
	@GetMapping("/findRoleByRoleID")
	public ResponseEntity<Role> findRoleByRoleID(@RequestParam("roleid") int roleid);
	
	/*
	 * Privilege
	 */
	@GetMapping("/findPrivilegeByPrivilegeID")
	public ResponseEntity<Privilege> findPrivilegeByPrivilegeID(int privilegeid);
	@GetMapping("/findAllPrivilege")
	public ResponseEntity<List<Privilege>> findAllPrivilege();

	
	/*
	 * Department
	 */
	@PostMapping("/queryDepartmentPage")
	public ResponseEntity<PageResult<Department>> queryDepartmentPage(@RequestBody Query query);
	@GetMapping("/findDepartmentByDepartmentID")
	public ResponseEntity<Department> findDepartmentByDepartmentID(@RequestParam("departmentid") int departmentid);
	
	/*
	 * Org
	 */
	@PostMapping("/queryOrgPage")
	public ResponseEntity<PageResult<Org>> queryOrgPage(@RequestBody Query query);
	@GetMapping("/findOrgByOrgID")
	public ResponseEntity<Org> findOrgByOrgID(@RequestParam("orgid") int orgid);
	
	/*
	 * View
	 */
	@GetMapping("/findViewByName")
	public ResponseEntity<View> findViewByName(@RequestParam("viewname") String viewname);
	
	/*
	 * Field
	 */
	@GetMapping("/findUserTableField")
	public ResponseEntity<List<Field>> findUserTableField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid);
	@GetMapping("/findUserDisplayField")
	public ResponseEntity<List<Field>> findUserDisplayField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid);
	@GetMapping("/findUserNotDisplayField")
	public ResponseEntity<List<Field>> findUserNotDisplayField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid);
	@GetMapping("/saveUserExcludeField")
	public ResponseEntity<Void> saveUserExcludeField(@RequestParam("userid") int userid, @RequestParam("viewid") int viewid, @RequestParam("fieldid") int[] fieldid);
	
	/*
	 * Country
	 */
	@GetMapping("/findCountryByCountryID")
	public ResponseEntity<Country> findCountryByCountryID(@RequestParam("countryid") int countryid);
	@GetMapping("/findAllCountry")
	public ResponseEntity<List<Country>> findAllCountry();

	/*
	 * Local
	 */
	@GetMapping("/findAllLocale")
	public ResponseEntity<List<Locale>> findAllLocale();
	
	/*
	 * TimeZone
	 */
	@GetMapping("/findAllTimeZone")
	public ResponseEntity<List<TimeZone>> findAllTimeZone();
	@GetMapping("/findTimeZoneByTimeZoneID")
	public ResponseEntity<TimeZone> findTimeZoneByTimeZoneID(@RequestParam("timezoneid") int timezoneid);
	
	/*
	 * Resource
	 */
	@GetMapping("/findResourceByLink")
	public ResponseEntity<Resource> findResourceByLink(@RequestParam("name") String name);
	
	/*
	 * Menu
	 */
	@GetMapping("/findMenuByMenuID")
	public ResponseEntity<Menu> findMenuByMenuID(@RequestParam("menuid") int menuid);
	@GetMapping("/findMenuByRoleID")
	public ResponseEntity<List<Menu>> findMenuByRoleID(@RequestParam("roleid") int roleid);
	@GetMapping("/findAllMenu")
	public ResponseEntity<List<Menu>> findAllMenu();
	@GetMapping("/findAllMenuTree")
	public ResponseEntity<List<Menu>> findAllMenuTree();
	
	/*
	 * ToolBox
	 */
	@GetMapping("/findAllToolBox")
	public ResponseEntity<List<ToolBox>> findAllToolBox();
	@GetMapping("/findSelectedToolBoxByUserID")
	public ResponseEntity<List<ToolBox>> findSelectedToolBoxByUserID(@RequestParam("userid") int userid);
	
}
