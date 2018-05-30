package com.zhbit.xuexin.student.service;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.IBaseService;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.StuconfirmDetail;
import com.zhbit.xuexin.domain.Students;

public interface StuconfirmDetailService extends IBaseService<StuconfirmDetail> {

	CourseInfo getCourseInfoByCoursecode(String coursecode);

	boolean checkCourseIsSelected(String studentno, String coursestuId);

	CourseInfoStudents getCourseInfoStudentsByCourseId(String couseId);
	
	CourseInfo getCourseInfoByCourseId(String courseId) ;
	
	void deleteMultipe(String ids);

	void deleteSelfMultiple(Students self, String ids);

	boolean isExist(String masterid,String coursename);
	
	/**
	 * 通过MasterID来获取从表的信息列表
	 * @param id
	 * @return
	 */
	
	List<StuconfirmDetail> getStuconfirmDetailByMasterId(String id);

	ExportExcelVO exportExcelList(QueryHelper qh);


}
