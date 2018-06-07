package com.zhbit.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.test.dao.IStuDao;
import com.zhbit.test.service.IStuService;
import com.zhbit.xuexin.common.service.impl.BaseServiceImpl;
import com.zhbit.xuexin.domain.Students;
@Service("stuService")
@Transactional(readOnly=true)
public class StuServiceImpl extends BaseServiceImpl<Students> implements IStuService {
	private IStuDao stuDao;
	@Resource
	public void setStuDao(IStuDao stuDao) {
		this.stuDao = stuDao;
		super.setBaseDao(stuDao);
	}
	
	
}
