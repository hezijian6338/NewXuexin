package com.zhbit.xuexin.teacher.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.domain.TeacherInfo;
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
	@Override
	@Transactional(readOnly = false)
	public int save(TeaRewardPunishment info, String userId,
			String happenedDateStr, String rewardDateStr,
			Page<TeaRewardPunishment> page) throws ParseException {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<TeaRewardPunishment> pageResult = dao.getList(page);
		List<TeaRewardPunishment> list = pageResult.getResult();
		if(info != null) {
			TeacherInfo tea = teacherInfoDao.getTeacherInfoByNoAndName(info.getEmployno(),info.getEmployname());
			if(tea == null) {
				return 0;
			} else {
				for (int i = 0; i < list.size(); i++) {
					TeaRewardPunishment info_old = list.get(i);
					if(!info_old.getEmployname().equals(info.getEmployname())) {
						return 0;
					}
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Organization org = orgDao.getOrganizationById(info.getOrgId());
			info.setOrgName(org.getOrgName());
			Organization org2 = orgDao.getOrganizationById(info.getRpOrgId());
			info.setRpOrgName(org2.getOrgName());
			if (happenedDateStr != null && !"".equals(happenedDateStr)) {
				info.setHappenedDate(df.parse(happenedDateStr));
			}
			if (rewardDateStr != null && !"".equals(rewardDateStr)) {
				info.setRewardDate(df.parse(rewardDateStr));
			}
			info.setCreateTime(new Date());
			info.setCreator(userId);
			info.setId(tea.getId());
			dao.save(info);
			return 1;
		} else {
			return 0;
		}
		
	}
	/**
	 * 
	* @Title: getTeaRewardPunishmentByid   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param id
	* @param @return    设定文件   
	* @date 2018-5-31 下午5:41:17
	* @author 林敬凯
	* @throws
	 */
	@Override
	public TeaRewardPunishment getTeaRewardPunishmentByid(String id) {
		// TODO Auto-generated method stub
		return dao.getTeaRewardPunishmentByid(id);
	}
	/**
	 * 
	* @Title: update   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info
	* @param @param oldInfo
	* @param @param happenedDateStr
	* @param @param rewardDateStr    设定文件   
	* @date 2018-5-31 下午5:57:35
	* @author 林敬凯
	* @throws
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(TeaRewardPunishment info, TeaRewardPunishment oldInfo,
			String happenedDateStr, String rewardDateStr) throws Exception {
		if(info != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Organization org = orgDao.getOrganizationById(info.getOrgId());
			info.setOrgName(org.getOrgName());
			Organization org2 = orgDao.getOrganizationById(info.getRpOrgId());
			info.setRpOrgName(org2.getOrgName());
			if (happenedDateStr != null && !"".equals(happenedDateStr)) {
				info.setHappenedDate(df.parse(happenedDateStr));
			}
			if (rewardDateStr != null && !"".equals(rewardDateStr)) {
				info.setRewardDate(df.parse(rewardDateStr));
			}
			info.setEmployname(oldInfo.getEmployname());
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			info.setId(oldInfo.getId());
			dao.update(info);
		}
		
	}
	/**
	 * 
	* @Title: delete   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param ids    设定文件   
	* @date 2018-5-31 下午9:28:54
	* @author 林敬凯
	* @throws
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for(String id : sids) {
				TeaRewardPunishment info = dao.getTeaRewardPunishmentByid(id);
				dao.delete(info);
			}
		}
		
	}

}
