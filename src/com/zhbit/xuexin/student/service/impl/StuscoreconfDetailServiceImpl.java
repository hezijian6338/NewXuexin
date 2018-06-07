package com.zhbit.xuexin.student.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.service.impl.BaseServiceImpl;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.dao.StuscoreconfDetailDao;
import com.zhbit.xuexin.student.dao.StuscoreconfMasterDao;
import com.zhbit.xuexin.student.service.StuscoreconfDetailService;

@Service("stuscoreconfDetailService")
@Transactional(readOnly = true)
public class StuscoreconfDetailServiceImpl extends
		BaseServiceImpl<StuscoreconfDetail> implements
		StuscoreconfDetailService {
	private StuscoreconfDetailDao stuscoreconfDetailDao;
	@Resource
	private StuscoreconfMasterDao stuscoreconfMasterDao;

	@Resource
	public void setStuscoreconfDetailDao(
			StuscoreconfDetailDao stuscoreconfDetailDao) {
		this.stuscoreconfDetailDao = stuscoreconfDetailDao;
		super.setBaseDao(stuscoreconfDetailDao);
	}

	@Override
	public CourseInfo getCourseInfoByCoursecode(String coursecode) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfo.class, "c");
		qh.setWhere("c.coursecode=?", coursecode);
		List<CourseInfo> ls = stuscoreconfDetailDao.query(qh);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public boolean checkCourseIsSelected(String studentno, String coursestuId) {
		QueryHelper qh = new QueryHelper();
		qh.setSelect("1");
		qh.setFrom(CourseInfoStudents.class, "cs");
		qh.setWhere("cs.studentno=?", studentno);
		qh.setWhereAND("cs.id=?", coursestuId);
		List<?> r = stuscoreconfDetailDao.query(qh);
		return r != null && r.size() > 0;
	}

	@Override
	public CourseInfoStudents getCourseInfoStudentsByCourseId(String courseId) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.id=?", courseId);
		List<CourseInfoStudents> ls = stuscoreconfDetailDao.query(qh);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteMultiple(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for (String id : sids) {
				stuscoreconfDetailDao.delete(id);
			}
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSelfMultiple(Students self, String ids) {
		if (null != ids && !"".equals(ids) && null != self) {
			String[] sids = ids.split(",");
			for (String id : sids) {
				StuscoreconfDetail old = stuscoreconfDetailDao.get(id);
				if (null != old) {
					StuscoreconfMaster sscm = stuscoreconfMasterDao
							.get(old.getMasterId());
					//判断当前表能否被删除
					if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
							|| Const.SCORECONF_STATUS_RESET.equals(sscm
									.getStatus())) {
						// 判断当前主表信息是否属于该学生
						if (null != self
								&& null != sscm
								&& self.getStudentno().equals(
										sscm.getStudentno())) {
							stuscoreconfDetailDao.delete(old);
						}
					}
				}
			}
		}
	}

	@Override
	public List<StuscoreconfDetail> getStuscoreconfDetailByMasterId(String id) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(StuscoreconfDetail.class, "s");
		qh.setWhere("s.masterId=?", id);
		return stuscoreconfDetailDao.query(qh);
	}

	@Override
	public boolean isExist(String masterid,String coursename) {
		QueryHelper qh = new QueryHelper();
		qh.setSelect("1");
		qh.setFrom(StuscoreconfDetail.class, "s");
		qh.setWhere("s.masterId=?", masterid);
		qh.setWhereAND("s.coursename=?", coursename);
		return !stuscoreconfDetailDao.query(qh).isEmpty();
	}

}
