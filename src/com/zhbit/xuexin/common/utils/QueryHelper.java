package com.zhbit.xuexin.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询助手
 * 
 * @ClassName: QueryHelper
 * @Description: 利用此对象可以避免拼接Hql语句,配合BaseDao可以快速的完成查询 demo : QueryHelper
 *               queryHelper = new QueryHelper();
 *               queryHelper.setFrom(Course.class, "c");
 *               queryHelper.setWhere("c.teacher.code=?", teacher.getCode());
 * @author: zouxiang
 * @date: 2017年3月24日 下午2:39:46
 */
public class QueryHelper {
	public static String DESC = "DESC";
	public static String ASC = "ASC";
	private StringBuilder select = new StringBuilder(0);
	private StringBuilder form = new StringBuilder(0);
	private StringBuilder where = new StringBuilder(0);
	private StringBuilder order = new StringBuilder(0);
	private StringBuilder group = new StringBuilder(0);
	private StringBuilder having = new StringBuilder(0);
	private List<Object> args = new ArrayList<Object>(0);

	public QueryHelper setSelect(String select) {
		if (this.select.length() == 0) {
			this.select.append("SELECT ").append(select);
		} else {
			this.select.append("," + select);
		}
		return this;
	}

	public QueryHelper setFrom(Class<?> clazz, String alias) {
		if (this.form.length() == 0) {
			this.form.append(" FROM ").append(
					clazz.getSimpleName() + " AS " + alias);
		} else {
			this.form.append("," + clazz.getSimpleName() + " AS " + alias);
		}
		return this;
	}

	public QueryHelper setWhere(String sql, Object... args) {
		return setWhereAND(sql, args);
	}

	public QueryHelper setWhereAND(String sql, Object... args) {
		return setWhere(sql, " AND ", args);
	}

	public QueryHelper setWhereOR(String sql, Object... args) {
		return setWhere(sql, " OR ", args);
	}

	private QueryHelper setWhere(String sql, String s, Object... args) {
		if (this.where.length() == 0) {
			this.where.append(" WHERE ").append(sql);
		} else {
			this.where.append(s + sql);
		}
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				this.args.add(args[i]);
			}
		}
		return this;
	}

	public QueryHelper setHaving(String sql, Object... args) {
		return setHavingAND(sql, args);
	}

	public QueryHelper setHavingAND(String sql, Object... args) {
		return setHaving(sql, " AND ", args);
	}

	public QueryHelper setHavingOR(String sql, Object... args) {
		return setHaving(sql, " OR ", args);
	}

	private QueryHelper setHaving(String sql, String s, Object... args) {
		if (this.having.length() == 0) {
			this.having.append(" HAVING ").append(sql);
		} else {
			this.having.append(s + sql);
		}
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				this.args.add(args[i]);
			}
		}
		return this;
	}

	public QueryHelper setOrder(String sql, String order) {
		if (this.order.length() == 0) {
			this.order.append(" ORDER BY ").append(sql + " " + order);
		} else {
			this.order.append("," + sql + " " + order);
		}
		return this;
	}

	public QueryHelper setGroup(String sql) {
		if (this.group.length() != 0) {
			this.group = new StringBuilder(0);
		}
		this.group.append(" GROUP BY ").append(sql);
		return this;
	}

	public String getHQL() {
		return new StringBuilder().append(select).append(form).append(where)
				.append(group).append(having).append(order).toString();
	}

	public List<Object> getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return getHQL();
	}
}
