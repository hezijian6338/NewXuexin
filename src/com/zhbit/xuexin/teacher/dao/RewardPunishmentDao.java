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
	/**
	 * 
	* @Title: getTeaRewardPunishmentByid   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param id
	* @param @return    设定文件   
	* @return TeaRewardPunishment    返回类型  
	* @date 2018-5-31 下午5:45:12
	* @author 林敬凯
	* @throws
	 */
	TeaRewardPunishment getTeaRewardPunishmentByid(String id);
	/**
	 * 
	* @Title: update   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 下午6:08:15
	* @author 林敬凯
	* @throws
	 */
	void update(TeaRewardPunishment info);

	/**
	 * 
	* @Title: delete   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 下午11:05:34
	* @author 林敬凯
	* @throws
	 */
	void delete(TeaRewardPunishment info);
	/**
	 * 
	* @Title: getTeaRewardPunishmentByNo   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param cellValue
	* @param @return    设定文件   
	* @return TeaRewardPunishment    返回类型  
	* @date 2018-6-4 上午10:06:05
	* @author 林敬凯
	* @throws
	 */
	TeaRewardPunishment getTeaRewardPunishmentByNo(String employNo);

}
