package net.xway.platform.app.system.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.xway.base.Constant;
import net.xway.platform.app.system.service.IAccountService;
import net.xway.platform.app.system.service.IResourceService;
import net.xway.platform.app.system.utils.UserUtil;
import net.xway.platform.system.dto.Privilege;
import net.xway.platform.system.dto.Resource;
import net.xway.platform.system.dto.User;

@Component
public class PrivilegeInterceptor extends HandlerInterceptorAdapter  {

	private static Log log = LogFactory.getLog(PrivilegeInterceptor.class);

	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IAccountService accountService;

	private boolean allow(Resource resource, HttpServletRequest request) {
		Privilege privilege = resource.getPrivilege();
		if (privilege == null) {
			return true;
		}
		
		User user = UserUtil.getUserFromSession(request);

		if (user == null) {
			return false;
		}
		
		return accountService.canAccess(user, resource);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {
		String name = getRequestPath(request);
		Resource action = resourceService.findResourceByLink(name);
		
		if (action == null) {
			if (log.isWarnEnabled()) {
				log.warn("Resource " + name + " Not Found.");
			}
			response.sendError(HttpServletResponse.SC_NOT_FOUND, name + Constant.ACTION_EXTENDNAME);
			return false;
		}
		
		if (allow(action, request) == false) {
			if (log.isDebugEnabled()) {
				log.debug(name + " No Privilege To Access!");
			}

			String r = request.getHeader("x-requested-with");
			if ("XMLHttpRequest".equals(r)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			return false;
		}

		request.setAttribute(Constant.ACTION_NAME, name);
		request.setAttribute(Constant.ACTION_QUERYSTRING, request.getQueryString());
		request.setAttribute(Constant.ACTION_ADDRESS, name + Constant.ACTION_EXTENDNAME + (request.getQueryString() != null && request.getQueryString().length()> 0 ? "?"+request.getQueryString() : ""));
		request.setAttribute(Constant.ACTION_LABEL, action.getLabel());
		if (request.getSession(false) != null) {
			request.getSession().setAttribute(Constant.USER_SESSION_LAST_ACTIVETIME_KEY, new Date());
		}
		return super.preHandle(request, response, handler);
	}
	
	private String getRequestPath(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if (uri != null && uri.lastIndexOf("/")>=0) {
			uri = uri.substring(uri.lastIndexOf("/")+1);
			uri = uri.replace(Constant.ACTION_EXTENDNAME, "");
		}
		return uri;
	}

}
