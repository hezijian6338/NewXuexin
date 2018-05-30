package com.zhbit.xuexin.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.QueryHelper;

/**
 * 
 * @ClassName: IBaseDao
 * @Description: 底层dao封装接口
 * @author: zouxiang
 * @date: 2017年3月24日 下午2:31:09
 * @param <T>
 */
public interface IBaseDao<T> {

	T save(T obj);

	void update(T obj);

	void delete(Serializable id);
	
	void delete(T entity);

	T get(Serializable id);

	/**
	 * 列出全部
	 * 
	 * @Title: listAll
	 * @Description: TODO
	 * @return
	 * @return: List<T>
	 */
	List<T> listAll();

	/**
	 * 刷新并且清除Session,避免Session缓存
	 * 
	 * @Title: flushAndClearSession
	 * @Description: TODO
	 * @return: void
	 */
	void flushAndClearSession();

	/**
	 * 根据hql语句查询
	 * 
	 * @Title: createQuery
	 * @Description: TODO
	 * @param hql
	 * @param args
	 * @return
	 * @return: List
	 */
	<E> List<E> query(String hql, Object... args);

	/**
	 * 根据查询助手查询
	 * 
	 * @Title: createQuery
	 * @Description: TODO
	 * @param queryHelper
	 * @return
	 * @return: List
	 */
	<E> List<E> query(QueryHelper queryHelper);

	/**
	 * 根据查询助手查询并分页
	 * 
	 * @Title: queryPageResult
	 * @Description: TODO
	 * @param queryHelper
	 * @param curPage
	 *            查询第几页
	 * @param pageSize
	 *            每页大小
	 * @return
	 * @return: PageResult
	 */
	PageResult queryPageResult(QueryHelper queryHelper, int curPage,
			int pageSize);

	/**
	 * 根据hql查询并分页
	 * 
	 * @Title: queryPageResult
	 * @Description: TODO
	 * @param hql
	 * @param curPage
	 *            查询第几页
	 * @param pageSize
	 *            每页大小
	 * @param args
	 * @return
	 * @return: PageResult
	 */
	PageResult queryPageResult(String hql, int curPage, int pageSize,
			Object... args);
}
