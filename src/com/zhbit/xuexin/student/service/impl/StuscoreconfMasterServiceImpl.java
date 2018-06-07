package com.zhbit.xuexin.student.service.impl;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.impl.BaseServiceImpl;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.dao.StuscoreconfMasterDao;
import com.zhbit.xuexin.student.service.StuscoreconfMasterService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

@Service("stuscoreconfMasterService")
@Transactional(readOnly = true)
public class StuscoreconfMasterServiceImpl extends
        BaseServiceImpl<StuscoreconfMaster> implements
        StuscoreconfMasterService {
    private StuscoreconfMasterDao stuscoreconfMasterDao;

    @Resource
    public void setStuscoreconfMasterDao(
            StuscoreconfMasterDao stuscoreconfMasterDao) {
        this.stuscoreconfMasterDao = stuscoreconfMasterDao;
        super.setBaseDao(stuscoreconfMasterDao);
    }

    @Override
    public LearningGuidStudentsList getLearningGuidStudentsListByStuno(
            String studentno) {
        QueryHelper qh = new QueryHelper();
        qh.setFrom(LearningGuidStudentsList.class, "l");
        qh.setWhere("l.studentno=?", studentno);
        qh.setOrder("l.academicyear", QueryHelper.DESC);
        qh.setOrder("l.term", QueryHelper.DESC);
        List<LearningGuidStudentsList> ls = stuscoreconfMasterDao.query(qh);
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }

	@Override
	public ExportExcelVO exportExcelList(StuscoreconfMaster sscm) {
		ExportExcelVO vo = new ExportExcelVO();
		QueryHelper stuQh = new QueryHelper()
				.setFrom(Students.class, "s")
				.setWhere("s.studentno=?", sscm.getStudentno());
		List<Students> stus = stuscoreconfMasterDao.query(stuQh);
		QueryHelper dQh = new QueryHelper()
				.setFrom(StuscoreconfDetail.class,"sd")
				.setWhere("sd.masterId=?", sscm.getId())
				.setOrder("sd.coursename", QueryHelper.ASC);
		List<StuscoreconfDetail> sds = stuscoreconfMasterDao.query(dQh);
		if (!stus.isEmpty()) {
			Students students = stus.get(0);
			//设置标题
			String title = String.format("%s学年第%s学期全程导学学生考试成绩备案表", sscm.getAcademicyear(),sscm.getTerm());
			//设置heads
			String[] heads = new String[6];
			heads[0] = sscm.getStuname();
			heads[1] = students.getSex();
			heads[2] = sscm.getStudentno();
			heads[3] = sscm.getClassname();
			heads[4] = sscm.getTelno();
			heads[5] = sscm.getTeachername();
			List<Object[]> data = new ArrayList<Object[]>(sds.size());
			for(StuscoreconfDetail sd : sds){
				String[] arrs = new String[5];
				arrs[0] = sd.getCoursecode() + "-" + sd.getCoursename();
				arrs[1]	= sd.getCourseType();
				arrs[2]	= sd.getFinalscore();
				arrs[3]	= sd.getResitscore();
				arrs[4]	= sd.getEmployName();
				data.add(arrs);
			}
			vo.setTitle(title);
			vo.setHeadTitle(heads);
			vo.setDataList(data);
		}
		return vo;
	}

    @Override
    public List<CourseInfoStudents> getCourseInfoStudentsByStuno(
            String studentno) {
        QueryHelper qh = new QueryHelper();
        qh.setFrom(CourseInfoStudents.class, "c");
        qh.setWhere("c.studentno=?", studentno);
        return stuscoreconfMasterDao.query(qh);
    }

    @Override
    public List<CourseInfoStudents> getCourseInfoStudentsByStunoAndAcademicyearTerm(String studentno, String academicyear, String term) {
        QueryHelper qh = new QueryHelper();
        qh.setFrom(CourseInfoStudents.class, "cs");
        qh.setWhereAND("cs.studentno=?", studentno);
        qh.setWhereAND("cs.academicYear=?", academicyear);
        qh.setWhereAND("cs.term=?", term);
        return stuscoreconfMasterDao.query(qh);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMultiple(String ids) {
        if (ids != null && !"".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                stuscoreconfMasterDao.delete(id);
                stuscoreconfMasterDao.deleteDetailByMasterId(id);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteSelfMultiple(Students self, String ids) {
        if (null != ids && !"".equals(ids) && null != self) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                StuscoreconfMaster s = stuscoreconfMasterDao.get(id);
                if (null != s && self.getStudentno().equals(s.getStudentno())) {
                    if (Const.SCORECONF_STATUS_NEW.equals(s.getStatus())
                            || Const.SCORECONF_STATUS_RESET.equals(s
                            .getStatus())) {
                        stuscoreconfMasterDao.delete(s);
                    }
                }
            }
        }
    }

	@Override
	public boolean isExist(String studentno, String academicyear, String term) {
		QueryHelper qh = new QueryHelper().setSelect("1")
			.setFrom(StuscoreconfMaster.class, "s")
			.setWhere("s.studentno=?", studentno)
			.setWhereAND("s.academicyear=?", academicyear)
			.setWhereAND("s.term=?", term);
		List<Object> list = stuscoreconfMasterDao.query(qh);
		return list != null && !list.isEmpty();
	}
}
