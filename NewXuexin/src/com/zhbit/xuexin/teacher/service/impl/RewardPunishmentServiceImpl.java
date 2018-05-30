package com.zhbit.xuexin.teacher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.teacher.dao.RewardPunishmentDao;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;
import com.zhbit.xuexin.teacher.service.RewardPunishmentService;

@Service("tearewardPunishmentService")
@Transactional(readOnly = true)
public class RewardPunishmentServiceImpl implements RewardPunishmentService{
	private Logger logger = LoggerFactory
			.getLogger(RewardPunishmentServiceImpl.class);
	
	@Autowired
	@Qualifier("tearewardPunishmentDao")
	private RewardPunishmentDao dao;
	
	@Autowired
	@Qualifier("teacherInfoDao")
	private TeacherInfoDao teacherInfoDao;
	
	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao orgDao;
	@Override
	public Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page) {
		return dao.getList(page);
	}

}
