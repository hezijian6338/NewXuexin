package com.zhbit.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.zhbit.test.dao.IStuDao;
import com.zhbit.xuexin.common.dao.impl.BaseDaoImpl;
import com.zhbit.xuexin.domain.Students;
@Repository("stuDao")
public class StuDaoImpl extends BaseDaoImpl<Students> implements IStuDao{
	
}
