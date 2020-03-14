package net.xway.base.database.mybatis.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import net.xway.base.database.DataBaseType;
import net.xway.base.database.Query;

@Intercepts({@Signature(type=Executor.class,method="query",args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class }) })
public class PagingInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement stat = (MappedStatement) invocation.getArgs()[0];
		Object args = invocation.getArgs()[1];
		
		if (args instanceof Query) {
			Query query = (Query) args;
			if ( stat.getDatabaseId() != null) {
				throw new java.lang.UnsupportedOperationException();
			}
			else {
				query.compute(DataBaseType.MySQL); // ?? how to databaseid
			}
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
