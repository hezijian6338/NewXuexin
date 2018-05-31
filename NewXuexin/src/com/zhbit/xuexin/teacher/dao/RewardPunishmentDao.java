package com.zhbit.xuexin.teacher.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeaRewardPunishment;

public interface RewardPunishmentDao {

	Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page);
	
	/**
	 * 
	* @Title: save   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 下午2:34:59
	* @author 林敬凯
	* @throws
	 */
	void save(TeaRewardPunishment info);

}
