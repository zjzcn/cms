package com.zjzcn.helper.query.filter;

import com.zjzcn.helper.query.Page;

/**
 * @author zhangjz
 * @version [v1.0.0, 2012-7-8]
 */
public class PageFilter implements Filter {

	private int pageNo;

	private int pageSize;

	private Page<?> page;

	public PageFilter(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public PageFilter(Page<?> page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String toQueryString() {
		if (page != null) {
			return (page.getOrderProperty() + " " + page.getOrderDirection());
		}
		return "";
	}

	public <T> Page<?> getPage() {
		if(page == null) {
			return new Page<T>(pageNo, pageSize);
		}
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

}
