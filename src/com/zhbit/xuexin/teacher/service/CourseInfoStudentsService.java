package com.zhbit.xuexin.teacher.service;

import java.io.File;
import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.ExcelErrorInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.User;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:33:18
 *@version 1.0
 */
public interface CourseInfoStudentsService {
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<CourseInfoStudents> getList(Page<CourseInfoStudents> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @return
     * @return int
     */
    int save(CourseInfoStudents info, String userId);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return CourseInfoStudents
     */
    CourseInfoStudents getCourseInfoStudentsByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo 
     * @return void
     */
    int update(CourseInfoStudents info, CourseInfoStudents oldInfo);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-15 下午10:46:26
     * @param ids
     * @return void
     */
    void delete(String ids);
    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-15 下午10:47:48
     * @param excel
     * @param user
     * @param suffix 
     * @return
     * @return int[]
     */
    int[] importFile(File excel, User user, String suffix);
    /**
     * @Title: importPositiveTestScoreFile
     * @Description: TODO(导入成绩)
     * @author lry
     * @date 2016-3-15 下午10:47:48
     * @param excel
     * @param user
     * @param suffix 
     * @return
     * @return int[]
     */
    int[] importPositiveTestScoreFile(File excel, User user, String suffix,String academicyear,String semester,String coursecode,String selectedcourseno);
    
	int[] importResitTestScoreFile(File excel, User user, String suffix,String academicyear,String semester,String coursecode,String selectedcourseno);
    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    ExportExcelVO exportExcelList(Page<CourseInfoStudents> page);
    
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
     * 核对数据
     * @Title: verifyFile
     * @Description: TODO
     * @return
     * @return: List<ExcelErrorInfo>
     */
    List<ExcelErrorInfo> verifyFile(File excel, String suffix) throws Exception;
    
    
    int[] importScoreFile(File excel, String suffix) throws Exception;
  
}
