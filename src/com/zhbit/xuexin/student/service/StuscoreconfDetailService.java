package com.zhbit.xuexin.student.service;

import java.util.List;

import com.zhbit.xuexin.common.service.IBaseService;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;

public interface StuscoreconfDetailService extends IBaseService<StuscoreconfDetail> {
	/**
	 * 根据课程代码获取课程信息
	 * @Title: getCourseInfoByCoursecode
	 * @Description: TODO
	 * @param coursecode
	 * @return
	 * @return: CourseInfo
	 */
	CourseInfo getCourseInfoByCoursecode(String coursecode);
	/**
	 * 检查该学生是否有选该课程
	 * @Title: checkCourse
	 * @Description: TODO
	 * @param studentno
	 * @param coursecode
	 * @return
	 * @return: boolean
	 */
	boolean checkCourseIsSelected(String studentno, String coursestuId);
	/**
	 * 根据课程id获取课程信息
	 * @Title: getCourseInfoByCourseId
	 * @Description: TODO
	 * @param courseId
	 * @return
	 * @return: CourseInfo
	 */
	CourseInfoStudents getCourseInfoStudentsByCourseId(String courseId);
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
	 * 根据主表id查询
	 * @Title: getStuscoreconfDetailByMasterId
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<StuscoreconfDetail>
	 */
	List<StuscoreconfDetail> getStuscoreconfDetailByMasterId(String id);
	
	/**
	 * 检查该记录是否存在
	 * @Title: isExist
	 * @Description: TODO
	 * @param coursecode
	 * @param retakeflag
	 * @param masterid 
	 * @return
	 * @return: boolean
	 */
	boolean isExist(String masterid,String coursename);

}
