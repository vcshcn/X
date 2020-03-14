package net.xway.base.database;

import java.util.List;

public interface IBaseDAO<T> {
	
	public default T findByPrimaryKey(int pk) {
		throw new UnsupportedOperationException();
	}
	
	public default List<T> findAll() {
		throw new UnsupportedOperationException();
	}

	public default PageResult<T> findPage(Query query) {
		throw new UnsupportedOperationException();
	}
	
	public default int insert(T t) {
		throw new UnsupportedOperationException();
	}

	public default int update(T t) {
		throw new UnsupportedOperationException();
	}

	public default int delete(int pk) {
		throw new UnsupportedOperationException();
	}

}
