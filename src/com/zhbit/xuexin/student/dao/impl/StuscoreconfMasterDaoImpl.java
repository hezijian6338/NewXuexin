package com.zhbit.xuexin.student.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.dao.impl.BaseDaoImpl;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.dao.StuscoreconfMasterDao;
@Repository("stuscoreconfMasterDao")
public class StuscoreconfMasterDaoImpl extends BaseDaoImpl<StuscoreconfMaster> implements StuscoreconfMasterDao{

	@Override
	public void deleteDetailByMasterId(String id) {
		String hql = "DELETE StuscoreconfDetail s WHERE s.masterId=?";
		Query query = createQuery(hql, id);
		query.executeUpdate();
	}

}
