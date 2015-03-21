package com.zjzcn.util.query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.zjzcn.util.StringUtils;
import com.zjzcn.util.query.condition.OrderCondition;
import com.zjzcn.util.query.condition.PageCondition;
import com.zjzcn.util.query.condition.QueryCondition;
import com.zjzcn.util.query.condition.WhereCondition;

/**
 * @author zhangjz
 * @version [v1.0.0, 2012-7-8]
 */
public class Condition {
	private List<QueryCondition> conditionList = new LinkedList<QueryCondition>();

	private Condition() {
		// NOOP
	}

	public static Condition newCondition() {
		return new Condition();
	}

	/************************ where start *************************/
	/**
	 * 匹配全部
	 */
	public Condition like(String propertyName, String value) {
		if (isNotBlank(value)) {
			QueryCondition conditon = new WhereCondition(propertyName,
					value.trim(), WhereCondition.Op.LIKE);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 匹配开始
	 */
	public Condition likeStart(String propertyName, String value) {
		if (isNotBlank(value)) {
			QueryCondition conditon = new WhereCondition(propertyName,
					value.trim(), WhereCondition.Op.LIKE_START);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 匹配结尾
	 */
	public Condition likeEnd(String propertyName, String value) {
		if (isNotBlank(value)) {
			QueryCondition conditon = new WhereCondition(propertyName,
					value.trim(), WhereCondition.Op.LIKE_END);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 匹配中间
	 */
	public Condition likeAnywhere(String propertyName, String value) {
		if (isNotBlank(value)) {
			QueryCondition conditon = new WhereCondition(propertyName,
					value.trim(), WhereCondition.Op.LIKE_ANYWHERE);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 相等
	 */
	public Condition eq(String propertyName, Object value) {
		if (value == null) {
			return this;
		}
		if (value instanceof String) {
			if (isBlank((String) value)) {
				return this;
			} else {
				value = ((String) value).trim();
			}
		}

		QueryCondition conditon = new WhereCondition(propertyName, value,
				WhereCondition.Op.EQ);
		conditionList.add(conditon);
		return this;
	}

	/**
	 * 不等
	 */
	public Condition ne(String propertyName, Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (isBlank((String) value)) {
					return this;
				} else {
					value = ((String) value).trim();
				}
			}

			QueryCondition conditon = new WhereCondition(propertyName, value,
					WhereCondition.Op.NE);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 大于
	 */
	public Condition gt(String propertyName, Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (isBlank((String) value)) {
					return this;
				} else {
					value = ((String) value).trim();
				}
			}

			QueryCondition conditon = new WhereCondition(propertyName, value,
					WhereCondition.Op.GT);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 小于
	 */
	public Condition lt(String propertyName, Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (isBlank((String) value)) {
					return this;
				} else {
					value = ((String) value).trim();
				}
			}

			QueryCondition conditon = new WhereCondition(propertyName, value,
					WhereCondition.Op.LT);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 小于等于
	 */
	public Condition le(String propertyName, Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (isBlank((String) value)) {
					return this;
				} else {
					value = ((String) value).trim();
				}
			}

			QueryCondition conditon = new WhereCondition(propertyName, value,
					WhereCondition.Op.LE);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 大于等于
	 */
	public Condition ge(String propertyName, Object value) {
		if (value != null) {
			if (value instanceof String) {
				if (isBlank((String) value)) {
					return this;
				} else {
					value = ((String) value).trim();
				}
			}

			QueryCondition conditon = new WhereCondition(propertyName, value,
					WhereCondition.Op.GE);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * 范围
	 */
	public Condition between(String propertyName, Object value1, Object value2) {
		if (value1 != null && value2 != null) {
			QueryCondition conditon = new WhereCondition(propertyName, value1,
					value2, WhereCondition.Op.BETWEEN);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * sql中的in
	 */
	public Condition in(String propertyName, Object[] values) {
		if (values != null && values.length > 0) {
			QueryCondition conditon = new WhereCondition(propertyName, values,
					WhereCondition.Op.IN);
			conditionList.add(conditon);
		}
		return this;
	}

	/**
	 * sql中的not in
	 */
	public Condition notIn(String propertyName, Object[] values) {
		if (values != null && values.length > 0) {
			QueryCondition conditon = new WhereCondition(propertyName, values,
					WhereCondition.Op.NOT_IN);
			conditionList.add(conditon);
		}
		return this;
	}

	public Condition isNull(String propertyName) {
		QueryCondition conditon = new WhereCondition(propertyName,
				WhereCondition.Op.IS_NULL);
		conditionList.add(conditon);
		return this;
	}

	public Condition isNotNull(String propertyName) {
		QueryCondition conditon = new WhereCondition(propertyName,
				WhereCondition.Op.IS_NOT_NULL);
		conditionList.add(conditon);
		return this;
	}

	/************************ order by start *************************/
	public Condition orderByAsc(String propertyName) {
		QueryCondition conditon = new OrderCondition(propertyName, true);
		conditionList.add(conditon);
		return this;
	}

	public Condition orderByDesc(String propertyName) {
		QueryCondition conditon = new OrderCondition(propertyName, false);
		conditionList.add(conditon);
		return this;
	}

	/************************ page start *************************/
	public Condition page(Integer pageNo, Integer pageSize) {
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}

		QueryCondition conditon = new PageCondition(pageNo, pageSize);
		conditionList.add(conditon);
		return this;
	}

	/************************ pageBean start *************************/
	public Condition page(PageBean<?> pageBean) {
		QueryCondition conditon = new PageCondition(pageBean);
		conditionList.add(conditon);
		return this;
	}

	/************************ utils start *************************/
	public List<QueryCondition> getConditionList() {
		return conditionList;
	}

	public void clear() {
		conditionList.clear();
	}

	public Object[] handleCondition(Class<?> clazz) {
		StringBuilder hqlBuf = new StringBuilder();

		List<Object> paramList = new ArrayList<Object>();

		// 获取类名
		String className = Condition.getShortClassName(clazz.getName());
		hqlBuf.append(" from ").append(className).append(" where 1=1");

		// 遍历参数，组合where和参数
		for (QueryCondition cond : conditionList) {
			if (cond instanceof WhereCondition) {
				WhereCondition where = (WhereCondition) cond;

				hqlBuf.append(" and ").append(where.toQueryString());
				paramList.addAll(where.getParamList());
			}
		}

		// 遍历参数，组合order by
		String orderby = "";
		StringBuffer orderbyAfter = new StringBuffer();
		for (QueryCondition cond : conditionList) {
			if (cond instanceof PageCondition) {
				PageCondition page = (PageCondition) cond;
				if (page.getPageBean() != null
						&& StringUtils.isNotBlank(page.getPageBean()
								.getOrderProperty())) {
					orderby = " order by ";
					orderbyAfter.append(cond.toQueryString());
					break;
				}
			} else if (cond instanceof OrderCondition) {
				orderby = " order by ";

				orderbyAfter.append(cond.toQueryString()).append(", ");
			}
		}

		hqlBuf.append(orderby).append(orderbyAfter);

		String hql = hqlBuf.toString();

		if (hql.endsWith(", ")) {
			hql = hql.substring(0, hql.lastIndexOf(","));
		}

		PageBean<?> pageBean = null;
		for (QueryCondition cond : conditionList) {
			if (cond instanceof PageCondition) {
				PageCondition page = (PageCondition) cond;

				if (page.getPageBean() != null) {
					pageBean = page.getPageBean();
				} else {
					pageBean = new PageBean<Object>(page.getPageNo(),
							page.getPageSize());
				}
			}
		}

		return new Object[] { hql, paramList, pageBean };
	}

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
		int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0
				: lastDotIdx + 1);
		String out = className.substring(lastDotIdx + 1);
		if (innerIdx != -1) {
			out = out.replace('$', '.');
		}
		return out;
	}
}
