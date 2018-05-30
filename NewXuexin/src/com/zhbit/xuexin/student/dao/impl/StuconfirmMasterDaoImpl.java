package com.zhbit.xuexin.student.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.dao.impl.BaseDaoImpl;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.StuconfirmMaster;
import com.zhbit.xuexin.student.dao.StuconfirmMasterDao;

@Repository("StuconfirmMasterDao")
public class StuconfirmMasterDaoImpl  extends BaseDaoImpl<StuconfirmMaster> implements StuconfirmMasterDao {

	/**
	 * 
	 * 根据MasterID删除从表信息
	 */
	@Override
	public void deleteDetailByMasterId(String id) {
		String hql = "DELETE StuconfirmDetail s WHERE s.masterId=?";
		Query query = createQuery(hql,id);
		query.executeUpdate();
		
	}

}
