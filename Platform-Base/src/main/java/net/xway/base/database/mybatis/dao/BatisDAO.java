package net.xway.base.database.mybatis.dao;

import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import net.xway.base.database.DataUpdateDirtyException;
import net.xway.base.database.IBaseDAO;
import net.xway.base.database.PageResult;
import net.xway.base.database.Parameters;
import net.xway.base.database.Query;

public abstract class BatisDAO<T> extends SqlSessionDaoSupport implements IBaseDAO<T>  {
	
	protected String prefix = "";

	public BatisDAO() {
		for (Class<?> klass : getClass().getInterfaces()) {
			if (IBaseDAO.class.isAssignableFrom(klass)) {
				prefix = klass.getName() + ".";
			}
		}
	}

	public BatisDAO(Class<T> klass) {
		prefix = klass.getName() + ".";
	}

	public Parameters Parameters() {
		return  new Parameters();
	}
	
	
	// for 
	public <E> E uniqueObjectResult(String statement) {
		return getSqlSession().selectOne(statement);
	}

	public <E> E uniqueObjectResult(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public <E> E uniqueObjectResult(String statement, Parameters parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public <E> List<E> selectObjectList(String statement) {
		return getSqlSession().selectList(statement);
	}

	public <E> List<E> selectObjectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	public <E> List<E> selectObjectList(String statement, Parameters parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	
	@Override
	public PageResult<T> findPage(Query query) {
		int count = selectCount(prefix+"findCount", query);
		List<T> results = Collections.emptyList();
		
		if (count > 0) {
			// test pageno is large max page
			int pagesize = query.getPageSize();
			int maxpage = (count % pagesize == 0 ? count / pagesize : count / pagesize + 1);
			if (count > 0 && query.getPageNO() > maxpage) {
				query.put(Query.PAGENO_NAME, maxpage);
			}
			results = selectList(prefix+"findPage", query);
		}
		return new PageResult<T>(query, results, count);
	}
	
	@Override
	public List<T> findAll() {
		return getSqlSession().selectList(prefix+"findAll");
	}
	
	// find one
	@Override
	public T findByPrimaryKey(int pk) {
		 return getSqlSession().selectOne(prefix+"findByPrimaryKey", pk);
	}
	
	// count
	public int selectCount(String statement) {
		return getSqlSession().selectOne(statement);
	}

	public int selectCount(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public int selectCount(String statement, Parameters parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	// select one 
	public T uniqueResult(String statement) {
		return getSqlSession().selectOne(statement);
	}

	public T uniqueResult(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	public T uniqueResult(String statement, Parameters parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}
	
	// find list	
	public List<T> selectList(String statement) {
		return getSqlSession().selectList(statement);
	}
	
	public List<T> selectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	public List<T> selectList(String statement, Parameters parameter) {
		return getSqlSession().selectList(statement, parameter);
	}

	// insert
	@Override
	public int insert(T t) {
		int rows = getSqlSession().insert(prefix+"insert", t);
		if (rows != 1) {
			throw new DataUpdateDirtyException(t);
		}
		return rows;
	}

	public int insert(String statement, Parameters parameter) {
		return getSqlSession().insert(statement, parameter);
	}

	// update
	@Override
	public int update(T t) {
		int rows = getSqlSession().update(prefix+"update", t);
		if (rows != 1) {
			throw new DataUpdateDirtyException(t);
		}
		return rows;
	}
	
	public int update(String statement, Object parameter) {
		return getSqlSession().update(prefix+statement, parameter);
	}

	public int update(String statement, Parameters parameter) {
		return getSqlSession().update(prefix+statement, parameter);
	}

	// delete
	@Override
	public int delete(int pk) {
		return getSqlSession().delete(prefix+"delete", pk); 
	}

	public int delete(String statement) {
		return getSqlSession().delete(statement);
	}

	public int delete(String statement, Object parameter) {
		return getSqlSession().delete(statement, parameter);
	}

	public int delete(String statement, Parameters parameter) {
		return  getSqlSession().delete(statement, parameter);
	}

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

}
