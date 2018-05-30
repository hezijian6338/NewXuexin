package com.zhbit.xuexin.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.zhbit.xuexin.common.dao.IBaseDao;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.QueryHelper;

/**
 * Created by zouxiang on 2016/8/1.
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	@Resource
	protected SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T save(T obj) {
		getSession().save(obj);
		return obj;
	}

	@Override
	public void update(T obj) {
		getSession().update(obj);
	}

	@Override
	public void delete(Serializable id) {
		getSession().delete(get(id));
	}
	
	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public T get(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> listAll() {
		return getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	@Override
	public void flushAndClearSession() {
		getSession().flush();
		getSession().clear();
	}

	@Override
	public <E> List<E> query(String hql, Object... args) {
		return createQuery(hql, args).list();
	}

	@Override
	public <E> List<E> query(QueryHelper queryHelper) {
		return createQuery(queryHelper).list();
	}

	@Override
	public PageResult queryPageResult(QueryHelper queryHelper, int curPage,
			int pageSize) {
		return queryPageResult(queryHelper.getHQL(), curPage, pageSize,
				queryHelper.getArgs().toArray());
	}

	@Override
	public PageResult queryPageResult(String hql, int curPage, int pageSize,
			Object... args) {
		Query query = createQuery(hql, args);
		ScrollableResults scroll = query.scroll();
		scroll.last();
		int maxNum = scroll.getRowNumber() + 1;
		if (curPage < 1) {
			curPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		query.setFirstResult((curPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		List data = query.list();
		return new PageResult(data, curPage, maxNum, pageSize);
	}

	/**
	 * 快速的创建一个Query对象
	 * 
	 * @Title: createQuery
	 * @Description: TODO
	 * @param hql
	 * @param args
	 * @return
	 * @return: Query
	 */
	protected Query createQuery(String hql, Object... args) {
		Query query = getSession().createQuery(hql);
		if (null != args) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query;
	}

	/**
	 * 快速的创建一个Query对象
	 * 
	 * @Title: createQuery
	 * @Description: TODO
	 * @param hql
	 * @param args
	 * @return
	 * @return: Query
	 */
	protected Query createQuery(QueryHelper queryHelper) {
		return createQuery(queryHelper.getHQL(), queryHelper.getArgs()
				.toArray());
	}
}
