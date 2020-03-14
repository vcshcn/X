package net.xway.base.database.mybatis.interceptor;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(type=Executor.class,method="query",args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class }) })
public class TimerInterceptor implements Interceptor {

	private static Log log = LogFactory.getLog(TimerInterceptor.class);
	
	private int timeout = -1;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		long start = System.currentTimeMillis();
		Object r = invocation.proceed();
		long end = System.currentTimeMillis();
		
		if (timeout >=0 && end - start >= timeout) {
			MappedStatement stmt = (MappedStatement) invocation.getArgs()[0];
			String s = "Dev Mode, SQL " + stmt.getId() + " Timeout - " + (end-start) + "ms";
			System.err.println(s);
			if (log.isTraceEnabled()) {
				log.trace(s);
			}
			
		}
		return r;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		if (arg0.getProperty("timer") != null) {
			timeout = Integer.parseInt(arg0.getProperty("timer"));
		}
		
	}
	
}
