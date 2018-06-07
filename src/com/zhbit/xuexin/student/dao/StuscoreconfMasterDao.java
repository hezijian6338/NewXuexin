package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.dao.IBaseDao;
import com.zhbit.xuexin.domain.StuscoreconfMaster;

public interface StuscoreconfMasterDao extends IBaseDao<StuscoreconfMaster>{
	/**
	 * 根据主表id删除从表
	 * @Title: deleteDetailByMasterId
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	void deleteDetailByMasterId(String id);

}
