package com.zjzcn.helper.query.filer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjz
 * @version [v1.0.0, 2012-7-8]
 */
public class WhereFilter implements Filter {

	private String propertyName;
	private Object value1;
	private Object value2;
	private Op op;

	private List<Object> paramList = new ArrayList<Object>();

	public WhereFilter(String propertyName, Op op) {
		this(propertyName, null, null, op);
	}

	public WhereFilter(Object value, Op op) {
		this(null, value, null, op);
	}

	public WhereFilter(Object value1, Object value2, Op op) {
		this(null, value1, value2, op);
	}

	public WhereFilter(String propertyName, Object value, Op op) {
		this(propertyName, value, null, op);
	}

	public WhereFilter(String propertyName, Object value1, Object value2, Op op) {
		this.propertyName = propertyName;
		this.value1 = value1;
		this.value2 = value2;
		this.op = op;
	}

	public String toQueryString() {
		StringBuilder hqlBuilder = new StringBuilder();

		switch (op) {
		case EQ: {
			hqlBuilder.append(propertyName).append("=?");
			paramList.add(value1);
			break;
		}
		case NE: {
			hqlBuilder.append(propertyName).append("<>?");
			paramList.add(value1);
			break;
		}
		case LIKE: {
			hqlBuilder.append(propertyName).append(" like ?");
			paramList.add(value1);
			break;
		}
		case LIKE_START: {
			hqlBuilder.append(propertyName).append(" like ?");
			paramList.add(value1 + "%");
			break;
		}
		case LIKE_END: {
			hqlBuilder.append(propertyName).append(" like ?");
			paramList.add("%" + value1);
			break;
		}
		case LIKE_ANYWHERE: {
			hqlBuilder.append(propertyName).append(" like ?");
			paramList.add("%" + value1 + "%");
			break;
		}
		case GT: {
			hqlBuilder.append(propertyName).append(">?");
			paramList.add(value1);
			break;
		}
		case LT: {
			hqlBuilder.append(propertyName).append("<?");
			paramList.add(value1);
			break;
		}
		case LE: {
			hqlBuilder.append(propertyName).append("<=?");
			paramList.add(value1);
			break;
		}
		case GE: {
			hqlBuilder.append(propertyName).append(">=?");
			paramList.add(value1);
			break;
		}
		case BETWEEN: {
			hqlBuilder.append(propertyName).append(" between ? and ?");
			paramList.add(value1);
			paramList.add(value2);
			break;
		}
		case IN: {
			Object[] values = (Object[]) value1;
			int len = values.length;

			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < len - 1; i++) {
				buf.append("?, ");
				paramList.add(values[i]);
			}
			buf.append("?");
			paramList.add(values[len - 1]);

			hqlBuilder.append(propertyName).append(" in (").append(buf).append(')');
			break;
		}
		case NOT_IN: {
			Object[] values = (Object[]) value1;
			int len = values.length;

			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < len - 1; i++) {
				buf.append("?, ");
				paramList.add(values[i]);
			}
			buf.append("?");
			paramList.add(values[len - 1]);

			hqlBuilder.append(propertyName).append(" not in (").append(buf).append(')');
			break;
		}
		case IS_NULL: {
			hqlBuilder.append(propertyName).append(" is null");
			break;
		}
		case IS_NOT_NULL: {
			hqlBuilder.append(propertyName).append(" is not null");
			break;
		}
		default: {
		}
		}
		int i = 0;
		for (int idx = 0; idx != -1; i++) {
			idx = hqlBuilder.indexOf("?", idx + 1);
			if (idx != -1) {
				hqlBuilder.insert(idx + 1, i);
			}
		}
		if (paramList.size() != i - 1) {
			throw new RuntimeException("Hql ? number is " + (i - 1) + ", paramList.size is " + paramList + ", hql:"
					+ hqlBuilder);
		}
		return hqlBuilder.toString();
	}

	public List<Object> getParamList() {
		return paramList;
	}

	public enum Op {
		EQ, NE, LIKE, LIKE_START, LIKE_END, LIKE_ANYWHERE, GT, LT, LE, GE, BETWEEN, IN, NOT_IN, IS_NULL, IS_NOT_NULL
	}

	public static void main(String[] args) {
		StringBuffer hqlBuilder = new StringBuffer();
		hqlBuilder.append("dddddddd");
		int i = 0;
		for (int idx = 0; idx != -1; i++) {
			idx = hqlBuilder.indexOf("?", idx + 1);
			if (idx != -1) {
				hqlBuilder.insert(idx + 1, i);
			}
		}

		System.out.println(hqlBuilder + "" + i);
	}

}
