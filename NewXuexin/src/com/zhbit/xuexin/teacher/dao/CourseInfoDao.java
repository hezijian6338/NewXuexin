package com.zhbit.xuexin.teacher.dao;

import java.util.Map;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface CourseInfoDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<CourseInfo>
     */
    Page<CourseInfo> getList(Page<CourseInfo> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(CourseInfo info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(CourseInfo info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(CourseInfo info);

    /**
     * @Title: getCourseInfoByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return CourseInfo
     */
    CourseInfo getCourseInfoByid(String id);

    /**
     * @Title: getCourseInfoByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return CourseInfo
     */
    CourseInfo getCourseInfoByNo(String employNo);

    /**
     * @Title: getCourseInfoByCode
     * @Description: TODO(通过课程代码获取开课信息。)
     * @author lry
     * @date 2016-3-29 上午1:51:52
     * @param coursecode
     * @return
     * @return CourseInfo
     */
    CourseInfo getCourseInfoByCode(String coursecode);
    
    /**
     * @Title: getCourseInfoByselectedcourseno
     * @Description: TODO(通过选课课号获取开课信息。)
     * @author lry
     * @date 2016-3-29 上午1:51:52
     * @param selectedcourseno
     * @return
     * @return CourseInfo
     */
    CourseInfo getCourseInfoBySelectedcourseno(String selectedCourseNo);
    CourseInfo getCourseInfoBySelectedcourseno(String selectedCourseNo,String academicYear,String semester,String teaNo);
 
    Page<Map<String,String>> getSemesterAndYearList(Page<Map<String,String>> page,String teaNo);
    Page<CourseInfo> getSemesterCourseList(Page<CourseInfo> page,String teaNo,String academicYear,String semester);
   
    /**
     * 清除缓存
     */
    void flush();
}
