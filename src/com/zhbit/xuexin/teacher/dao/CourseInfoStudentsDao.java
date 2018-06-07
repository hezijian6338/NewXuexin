package com.zhbit.xuexin.teacher.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.CourseInfoStudents;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface CourseInfoStudentsDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<CourseInfoStudents>
     */
    Page<CourseInfoStudents> getList(Page<CourseInfoStudents> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(CourseInfoStudents info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(CourseInfoStudents info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(CourseInfoStudents info);

    /**
     * @Title: getCourseInfoStudentsByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return CourseInfoStudents
     */
    CourseInfoStudents getCourseInfoStudentsByid(String id);

    /**
     * @Title: getCourseDetail
     * @Description: TODO(通过学号获取课程及成绩明细。)
     * @author lry
     * @date 2016-5-12 下午10:12:36
     * @param studentno
     * @return
     * @return List<Object[]>
     */
    List<Object[]> getCourseDetail(String studentno);
/**
 * 
 *@author 许彩开 email:1121836563@qq.com
 *@date 创建时间：2017年4月14日 上午10:19:07
 *@Description:TODO(通过学号查询是否存在该学号)
 *@param id
 *@return
 */
    public int getCourseInfoStudentsByid_exist(String id);
 
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年4月20日 下午7:29:18
     *@Description:TODO(通过学号，课程代码查询是否已存在)
     *@param id
     *@return
     */
    public int getCourseInfoStudentsByT_COURSEINFO_STUDENTS_exist(String id,String courseCode);
    public int getCourseInfoStudentsByT_COURSEINFO_STUDENTS_selected_exist(String id,String selectedCourseNo);

    public List<CourseInfoStudents> getCourseInfoStudentNeedUpdateScore(String academicYear,String semester,String courseNo,String employNo);
    
    /**
     * 根据学年学期学号查询选课信息
     * @Title: listByStuNoAndAcademicYearAndTerm
     * @Description: TODO
     * @param stuNo
     * @param academicYear
     * @param trem
     * @return
     * @return: List<CourseInfoStudents>
     */
    List<CourseInfoStudents> listByStuNoAndAcademicYearAndTerm(String stuNo,String academicYear,String term);
    
    CourseInfoStudents getByStuNoAndCourseCodeAndAcademicYearAndTerm(String stuNo,String courseCode,String academicYear,String term);
    
    /**
     * 清除缓存
     */
    void flush();
    
    void saveOrUpdate(CourseInfoStudents cs);
}
