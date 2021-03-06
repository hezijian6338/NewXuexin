package com.zhbit.xuexin.teacher.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.domain.User;

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

	/**
	 * 
	* @Title: exportExcelList   
	* @Description: TODO(导出。)   
	* @param @param page
	* @param @return    设定文件   
	* @return ExportExcelVO    返回类型  
	* @date 2018-6-3 下午10:40:37
	* @author 林敬凯
	* @throws
	 */
	ExportExcelVO exportExcelList(Page<TeaRewardPunishment> page);

	/**
	 * 
	* @Title: importFile   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param excel
	* @param @param user
	* @param @param suffix
	* @param @return    设定文件   
	* @return int[]    返回类型  
	* @date 2018-6-4 上午8:42:18
	* @author 林敬凯
	* @throws
	 */
	int[] importFile(File excel, User user, String suffix);
}
