package net.xway.platform.app.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class TimeInterceptor extends HandlerInterceptorAdapter {

	private boolean debug = false;
	private int threshold = 0;
	private ThreadLocal<Long> time = new ThreadLocal<>();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (debug == true && threshold > 0)
			time.set(System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		if (debug == true) {
			long start = time.get();
			long end = System.currentTimeMillis();
			if (threshold > 0 && end - start >= threshold) {
				
			}
		}
		
		super.afterCompletion(request, response, handler, ex);
	}

	@Value(value = "debug")
	public void setDebug(String debug) {
		this.debug = Boolean.parseBoolean(debug);
	}

	public void setThreshold(String s) {
		
	}
}
