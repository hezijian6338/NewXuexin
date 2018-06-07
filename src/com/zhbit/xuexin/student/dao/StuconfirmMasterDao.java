package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.dao.IBaseDao;
import com.zhbit.xuexin.domain.StuconfirmMaster;

public interface StuconfirmMasterDao extends IBaseDao<StuconfirmMaster> {

	void deleteDetailByMasterId(String id);

}
