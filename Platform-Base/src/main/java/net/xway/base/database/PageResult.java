package net.xway.base.database;

import java.util.List;

public class PageResult<T> {

	private Query query;
	private int count;
	private List<T> results;
	private int pageNO;
	private int pageSize;
	private int pageCount;
	
	public PageResult() {
	}
	
	public PageResult(Query query, List<T> results, int count) {
		this(query, results, count, (Integer) query.get(Query.PAGENO_NAME), (Integer) query.get(Query.PAGESIZE_NAME));
	}
	
	public PageResult(Query query, List<T> results, int count, int pageNO, int pageSize) {
		this.query = query;
		this.results = results;
		this.count = count;
		this.pageNO = pageNO;
		this.pageSize = pageSize;
		if (count % pageSize == 0) {
			pageCount = count / pageSize;
		}
		else {
			pageCount = count / pageSize + 1;
		}
	}
	
	public Query getQuery() {
		return query;
	}

	public List<T> getResults() {
		return results;
	}
	public int getPageNO() {
		return pageNO;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getFirstPage() {
		return count > 0 ? 1 : 0;
	}
	public int getLastPage() {
		return pageCount;
	}
	public int getPrevPage() {
		return pageNO > 1 ? pageNO-1 : 1;
	}
	public int getNextPage() {
		return pageNO < pageCount ? pageNO+1 : pageCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getCount() {
		return count;
	}
	
}
