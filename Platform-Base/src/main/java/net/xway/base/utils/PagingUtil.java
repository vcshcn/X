package net.xway.base.utils;


import net.xway.base.database.Query;

public class PagingUtil {
	
	public static Query create() {
		return create(null);
	}
	
	public static Query create(Integer pageSize) {
		Query query = new Query();		
		if (pageSize != null) {
			query.put(Query.PAGESIZE_NAME,  pageSize);
		}
	
		return query;
	}

}
