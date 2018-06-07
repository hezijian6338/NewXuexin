package com.zhbit.xuexin.student.service;

import java.util.List;

import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.IBaseService;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.StuconfirmMaster;
import com.zhbit.xuexin.domain.Students;

public interface StuconfirmMasterService extends IBaseService<StuconfirmMaster> {


	void deleteMultiple(String ids);

	void deleteSelfMultiple(Students self, String ids);

	ExportExcelVO exportExcelList(StuconfirmMaster scfm);
	
	/**
	 * 根据学生学号得到已选课程的信息，未用到
	 * @param studentno
	 * @return
	 */

	List<CourseInfoStudents> getCourseInfoStudentsByStuno(String studentno);
	
	/**
	 * 根据学生学号查到导学名单，从而可以得到导师信息
	 * @param studentno
	 * @return
	 */

	LearningGuidStudentsList getLearningGuidStudentsListByStuno(String studentno);

	/**
	 * HZJ
	 * 添加根据学生当前学年学期获取课程列表函数
	 * 2017.4.10
	 * 
	 */

	List<CourseInfo> getCoursesByStuTermAndAcayear(String term ,String acayear ,String classname) ;
	/**
	 * HZJ
	 * 添加根据学生学号查询学生的当前年级的单一信息，解决URL传递中文参数乱码问题
	 * 2017.4.11
	 * 
	 */
	
	List<Students> getStudentClassnameByStuno(String stuno) ;
	
	boolean isExist(String studentno, String academicyear, String term);
	
	List<CourseInfo> getCourseTypeList() ;
	
}
