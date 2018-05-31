package com.zhbit.xuexin.teacher.service;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeaRewardPunishment;

public interface RewardPunishmentService {


	Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page);

	public int save(TeaRewardPunishment info, String userId, String happenedDateStr,
			String rewardDateStr, Page<TeaRewardPunishment> page)throws Exception;
	
	TeaRewardPunishment getTeaRewardPunishmentByid(String id);
	
	/**
	 * 
	* @Title: update   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info
	* @param @param oldInfo
	* @param @param happenedDateStr
	* @param @param rewardDateStr    设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 下午5:57:01
	* @author 林敬凯
	* @throws
	 */
	void update(TeaRewardPunishment info, TeaRewardPunishment oldInfo,
			String happenedDateStr, String rewardDateStr)throws Exception;

	/**
	 * 
	* @Title: delete   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param ids    设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 下午9:28:33
	* @author 林敬凯
	* @throws
	 */
	void delete(String ids);
}
