package net.xway.platform.app.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xway.base.controller.JsonResult;
import net.xway.platform.app.system.service.IDataViewService;
import net.xway.platform.system.dto.Field;
import net.xway.platform.system.dto.User;
import net.xway.platform.system.dto.View;

@RestController
public class DataViewController {

	@Autowired
	private IDataViewService dataViewService;
	
	@RequestMapping("/ViewDataView")
	public JsonResult<View> findDataViewByName(String viewname, HttpSession session) {
		User user = (User) session.getAttribute(User.SESSION_KEY);
		View view = dataViewService.findViewByName(viewname);
		List<Field> f = dataViewService.findUserTableField(user.getUserid(), view.getViewid());
		view.setFields(f);
		return JsonResult.newInstance(view);		
	}
	
//	@RequestMapping("/FindUserTableField")
//	public JsonResult<List<Field>> findUserTableField(int viewid, HttpSession session) {
//		User user = (User) session.getAttribute(User.SESSION_KEY);
//		List<Field> f = dataViewService.findUserTableField(user.getUserid(), viewid);
//		
//		return JsonResult.newInstance(f);		
//	}

	@RequestMapping("/ModifyTableColumn")
	public JsonResult<Object[]> findUserTableColumn(String viewname, HttpSession session) {
		User user = (User) session.getAttribute(User.SESSION_KEY);
		View v = dataViewService.findViewByName(viewname);

		List<Field> avail = dataViewService.findUserDisplayField(user.getUserid(), v.getViewid());
		List<Field> disp = dataViewService.findUserNotDisplayField(user.getUserid(), v.getViewid());

		return JsonResult.newInstance(new Object[] {avail, disp});
	}
	
	@RequestMapping("/SaveTableColumn")
	public JsonResult<Void>  SaveTableColumn(int viewid, int[] fieldid, HttpSession session) {
		if (fieldid == null) {
			fieldid = new int[0];
		}
		User user = (User) session.getAttribute(User.SESSION_KEY);
		dataViewService.saveUserExcludeField(user.getUserid(), viewid, fieldid);
		return JsonResult.newInstance();
	}
}
