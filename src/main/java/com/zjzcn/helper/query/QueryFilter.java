package com.zjzcn.helper.query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zjzcn.helper.query.filer.OrderbyFilter;
import com.zjzcn.helper.query.filer.PageFilter;
import com.zjzcn.helper.query.filer.Filter;
import com.zjzcn.helper.query.filer.WhereFilter;

/**
 * @author zhangjz
 * @version [v1.0.0, 2012-7-8]
 */
public class QueryFilter {

	private List<Filter> filers = new LinkedList<Filter>();

	private QueryFilter() {
		// NOOP
	}

	public static QueryFilter newFilter() {
		return new QueryFilter();
	}

	/************************ where start *************************/
	/**
	 * 匹配全部
	 */
	public QueryFilter like(String propertyName, String value) {
		if (isNotBlank(value)) {
			Filter filter = new WhereFilter(propertyName, value.trim(), WhereFilter.Op.LIKE);
			filers.add(filter);
		}
		return this;
	}

	/**
	 * 匹配开始
	 */
	public QueryFilter likeStart(String propertyName, String value) {
		if (isNotBlank(value)) {
			Filter filter = new WhereFilter(propertyName, value.trim(), WhereFilter.Op.LIKE_START);
			filers.add(filter);
		}
		return this;
	}

	/**
	 * 匹配结尾
	 */
	public QueryFilter likeEnd(String propertyName, String value) {
		if (isNotBlank(value)) {
			Filter filter = new WhereFilter(propertyName, value.trim(), WhereFilter.Op.LIKE_END);
			filers.add(filter);
		}
		return this;
	}

	/**
	 * 匹配中间
	 */
	public QueryFilter likeAnywhere(String propertyName, String value) {
		if (isNotBlank(value)) {
			Filter filter = new WhereFilter(propertyName, value.trim(), WhereFilter.Op.LIKE_ANYWHERE);
			filers.add(filter);
		}
		return this;
	}

	/**
	 * 相等
	 */
	public QueryFilter eq(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.EQ);
		filers.add(filter);
		return this;
	}

	/**
	 * 不等
	 */
	public QueryFilter ne(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.NE);
		filers.add(filter);
		return this;
	}

	/**
	 * 大于
	 */
	public QueryFilter gt(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.GT);
		filers.add(filter);
		return this;
	}

	/**
	 * 小于
	 */
	public QueryFilter lt(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.LT);
		filers.add(filter);
		return this;
	}

	/**
	 * 小于等于
	 */
	public QueryFilter le(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.LE);
		filers.add(filter);
		return this;
	}

	/**
	 * 大于等于
	 */
	public QueryFilter ge(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			}
			value = ((String) value).trim();
		}

		Filter filter = new WhereFilter(propertyName, value, WhereFilter.Op.GE);
		filers.add(filter);
		return this;
	}

	/**
	 * 范围
	 */
	public QueryFilter between(String propertyName, Object value1, Object value2) {
		if (value1 == null || value2 == null) {
			return this;
		}

		Filter filter = new WhereFilter(propertyName, value1, value2, WhereFilter.Op.BETWEEN);
		filers.add(filter);
		return this;
	}

	/**
	 * sql中的in
	 */
	public QueryFilter in(String propertyName, Object[] values) {
		if (values == null || values.length == 0) {
			return this;
		}

		Filter filter = new WhereFilter(propertyName, values, WhereFilter.Op.IN);
		filers.add(filter);
		return this;
	}

	/**
	 * sql中的not in
	 */
	public QueryFilter notIn(String propertyName, Object[] values) {
		if (values == null || values.length == 0) {
			return this;
		}

		Filter filter = new WhereFilter(propertyName, values, WhereFilter.Op.NOT_IN);
		filers.add(filter);
		return this;
	}

	public QueryFilter isNull(String propertyName) {
		Filter filter = new WhereFilter(propertyName, WhereFilter.Op.IS_NULL);
		filers.add(filter);
		return this;
	}

	public QueryFilter isNotNull(String propertyName) {
		Filter filter = new WhereFilter(propertyName, WhereFilter.Op.IS_NOT_NULL);
		filers.add(filter);
		return this;
	}

	/************************ order by start *************************/
	public QueryFilter orderByAsc(String propertyName) {
		Filter filter = new OrderbyFilter(propertyName, "asc");
		filers.add(filter);
		return this;
	}

	public QueryFilter orderByDesc(String propertyName) {
		Filter filter = new OrderbyFilter(propertyName, "desc");
		filers.add(filter);
		return this;
	}

	/************************ page start *************************/
	public QueryFilter page(int pageNo, int pageSize) {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = Page.DEFLAUT_PAGE_SIZE;
		}

		Filter filter = new PageFilter(pageNo, pageSize);
		filers.add(filter);
		return this;
	}

	/************************ page start *************************/
	public QueryFilter page(Page<?> page) {
		if (isNotBlank(page.getOrderProperty()) && isNotBlank(page.getOrderDirection())) {
			Filter filter = new OrderbyFilter(page.getOrderProperty(), page.getOrderDirection());
			filers.add(filter);
		}

		Filter filter = new PageFilter(page);
		filers.add(filter);
		return this;
	}

	/************************ utils start *************************/
	public List<Filter> getFilters() {
		return filers;
	}

	public void clear() {
		filers.clear();
	}

	public Object[] handleFilters(Class<?> clazz) {
		StringBuilder hqlBuilder = new StringBuilder();
		List<Object> paramList = new ArrayList<Object>();

		StringBuilder whereBuilder = new StringBuilder();
		StringBuffer orderbyBuilder = new StringBuffer();
		Page<?> page = null;
		// 遍历参数，组合where和参数
		for (Filter filter : filers) {
			if (filter instanceof WhereFilter) {
				WhereFilter whereFilter = (WhereFilter) filter;
				whereBuilder.append(" and ").append(whereFilter.toQueryString());
				paramList.addAll(whereFilter.getParamList());
			} else if (filter instanceof OrderbyFilter) {
				orderbyBuilder.append(filter.toQueryString()).append(", ");
			} else if (filter instanceof PageFilter) {
				page = ((PageFilter) filter).getPage();
			}
		}
		hqlBuilder.append(" from ").append(getShortClassName(clazz.getName())).append(" where 1=1")
				.append(whereBuilder).append(orderbyBuilder.length() > 0 ? " order by " : " ")
				.append(StringUtils.removeEnd(orderbyBuilder.toString(), ", "));
		int i = 0;
		for (int idx = 0; idx != -1; i++) {
			idx = hqlBuilder.indexOf("?", idx + 1);
			if (idx != -1) {
				hqlBuilder.insert(idx + 1, i);
			}
		}

		return new Object[] { hqlBuilder.toString(), paramList, page };

	}

	/************************ private *************************/
	private static boolean isBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	private static boolean isNotBlank(String str) {
		int length;

		if ((str == null) || ((length = str.length()) == 0)) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 将包名+类名转换成类名
	 * 
	 * @param className
	 * @return
	 */
	private static String getShortClassName(String className) {
		if (className == null) {
			return "";
		}
		if (className.length() == 0) {
			return "";
		}

		int lastDotIdx = className.lastIndexOf('.');
		int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
		String out = className.substring(lastDotIdx + 1);
		if (innerIdx != -1) {
			out = out.replace('$', '.');
		}
		return out;
	}
}
