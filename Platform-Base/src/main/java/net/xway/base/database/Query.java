package net.xway.base.database;

import java.util.Map;


public class Query extends java.util.HashMap<String, Object> {

	private static final long serialVersionUID = -153188016011930502L;
	
	public static final String DEFAULT_QUERY_NAME = "query";
	public static final String PAGENO_NAME = "pageNO";
	public static final String PAGESIZE_NAME = "pageSize";
	public static final String OFFSET_NAME = "OFFSET";
	public static final String ROWS_NAME = "ROWS";

	public final static Integer DEFAULT_PAGENO = Integer.valueOf(1);
	public final static Integer DEFAULT_PAGESIZE = Integer.valueOf(15);
	
	private Integer pageNO;
	private Integer pageSize;
	
	
	public Query() {
		this(DEFAULT_PAGENO);
	}

	public Query(Integer pageNO) {
		this(pageNO, DEFAULT_PAGESIZE);
	}
	
	public Query(Integer pageNO, Integer pageSize) {
		this(pageNO, pageSize, null);
	}

	public Query(Integer pageNO, Integer pageSize, Map<String, Object> params) {
		if (params != null) {
			putAll(params);
		}
		if (pageNO == null) {
			pageNO = DEFAULT_PAGENO;
		}
		if (pageSize == null) {
			pageSize = DEFAULT_PAGESIZE;
		}
		
		this.pageNO = pageNO;
		this.pageSize = pageSize;
		
		put(PAGENO_NAME, pageNO);
		put(PAGESIZE_NAME, pageSize);
	}

	@Override
	public Object put(String key, Object value) {
		if ( PAGESIZE_NAME.equals(key) && value instanceof Integer) {
			pageSize = (Integer) value;
			compute(DataBaseType.MySQL);
		}
		if (PAGENO_NAME.equals(key) && value instanceof Integer  && ((Integer)value).intValue() > 0 ) {
			pageNO = (Integer) value;
			compute(DataBaseType.MySQL);
		}
		
		if (value == null
		|| (value instanceof String && ((String)value).isEmpty())
		) {
			return null;
		}

		return super.put(key, value);			
	}
	
	public int getPageSize() {
		return (Integer) get(PAGESIZE_NAME);
	}
	
	public int getPageNO() {
		return (Integer) get(PAGENO_NAME);
	}

	public int getOffset() {
		return (Integer) get(OFFSET_NAME);
	}
	
	public int getRows() {
		return (Integer) get(ROWS_NAME);
	}

	public void compute(DataBaseType type) {
		int offset = 0 ;
		int rows = 0 ;

		switch (type) {
			case MySQL:
			case MariaDB:
				offset = (pageNO - 1) * pageSize ;
				rows = pageSize ;
				break ;
			case SQLServer:
				offset = (pageNO - 1) * pageSize + 1 ;
				rows = offset + pageSize - 1;
				break ;
			case Oracle: 
				offset = (pageNO - 1) * pageSize + 1;
				rows = offset + pageSize - 1;
				break; 
			case PostgreSQL:
				
		}

		put(OFFSET_NAME, Integer.valueOf(offset));
		put(ROWS_NAME, Integer.valueOf(rows));
	}
	
	public boolean isReserveKey(String key) {
		return DEFAULT_QUERY_NAME.equals(key) || PAGENO_NAME.equals(key) || PAGESIZE_NAME.equals(key) || OFFSET_NAME.equals(key) || ROWS_NAME.equals(key);
	}
	
}
