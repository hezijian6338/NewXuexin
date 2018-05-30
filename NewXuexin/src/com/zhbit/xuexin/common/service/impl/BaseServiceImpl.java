package com.zhbit.xuexin.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.dao.IBaseDao;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.service.IBaseService;
import com.zhbit.xuexin.common.utils.QueryHelper;

/**
 * Created by zouxiang on 2016/8/1.
 */
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	private IBaseDao<T> baseDao;

	protected void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	@Transactional(readOnly = false)
	public T save(T obj) {
		baseDao.save(obj);
		return obj;
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T obj) {
		baseDao.update(obj);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Serializable id) {
		baseDao.delete(id);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public T get(Serializable id) {
		return baseDao.get(id);
	}

	@Override
	public List<T> listAll() {
		return baseDao.listAll();
	}

	@Override
	public <E> List<E> query(QueryHelper queryHelper) {
		return baseDao.query(queryHelper);
	}

	@Override
	public <E> List<E> query(String hql, Object... args) {
		return baseDao.query(hql, args);
	}

	@Override
	public PageResult queryPageResult(QueryHelper queryHelper, int curPage,
			int pageSize) {
		return baseDao.queryPageResult(queryHelper, curPage, pageSize);
	}

	@Override
	public PageResult queryPageResult(String hql, int curPage, int pageSize,
			Object... args) {
		return baseDao.queryPageResult(hql, curPage, pageSize, args);
	}

}
