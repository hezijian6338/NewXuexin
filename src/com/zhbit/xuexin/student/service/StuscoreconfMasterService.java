package com.zhbit.xuexin.student.service;

import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.IBaseService;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfMaster;

import java.util.List;

public interface StuscoreconfMasterService extends IBaseService<StuscoreconfMaster> {
	/**
	 * 根据学号返回导学信息
	 * @Title: getLearningGuidStudentsListByStuno
	 * @Description: TODO
	 * @param studentno
	 * @return
	 * @return: LearningGuidStudentsList
	 */
	LearningGuidStudentsList getLearningGuidStudentsListByStuno(String studentno);
	/**
	 * 根据查询助手查询导出
	 * @Title: exportExcelList
	 * @Description: TODO
	 * @param qh
	 * @return
	 * @return: ExportExcelVO
	 */
	ExportExcelVO exportExcelList(StuscoreconfMaster sscm);
	
	/**
	 * 返回该学生所选课程
	 * @Title: getStuCoursesByAcademicyearAndTerm
	 * @Description: TODO
	 * @param studentno
	 * @return
	 * @return: List<CourseInfo>
	 */
	List<CourseInfoStudents> getCourseInfoStudentsByStuno(String studentno);

    /**
     * 根据学年和学期返回该学生所选课程
     * @param studentno
     * @param academicyear
     * @param term
     * @return
     */
	List<CourseInfoStudents> getCourseInfoStudentsByStunoAndAcademicyearTerm(String studentno,String academicyear,String term);
	
	/**
	 * 删除多个
	 * @Title: deleteMultiple
	 * @Description: TODO
	 * @param ids
	 * @return: void
	 */
	void deleteMultiple(String ids);
	/**
	 * 删除自己
	 * @Title: deleteSelfMultiple
	 * @Description: TODO
	 * @param self
	 * @param ids
	 * @return: void
	 */
	void deleteSelfMultiple(Students self, String ids);
	/**
	 * 判断该学年学期记录是否存在
	 * @Title: isExist
	 * @Description: TODO
	 * @param studentno
	 * @param academicyear
	 * @param term
	 * @return
	 * @return: boolean
	 */
	boolean isExist(String studentno, String academicyear, String term);
	

}
