package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.User;

/**
 * 导学学生名单业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午12:56:32
 * @version 1.0
 */
public interface LearningGuidStudentsListService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @param user1 
     * @param isAdmin 
     * @return
     * @return Page<PoliticalStatus>
     */
    Page<LearningGuidStudentsList> getList(Page<LearningGuidStudentsList> page,User user, boolean isAdmin);

    /**
     * @Title: save
     * @Description: TODO(保存新增。)
     * @author lry
     * @date 2016-3-15 下午7:45:51
     * @param studentno
     * @param stuname
     * @param academicyear
     * @param term
     * @param classname
     * @param teachername
     * @param userId
     * @return
     * @return int
     */
    int save(String studentno, String stuname, String academicyear, String term, String classname, String teachername,
            String userId);

    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-15 下午7:46:33
     * @param id
     * @param studentno
     * @param stuname
     * @param academicyear
     * @param term
     * @param classname
     * @param teachername
     * @param teacherno 
     * @return void
     */
   int update(String id, String studentno, String stuname, String academicyear, String term, String classname,
            String teachername, String teacherno);

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    void delete(String ids);

    /**
     * @Title: getLearningGuidStudentsListById
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-15 下午7:47:45
     * @param id
     * @return
     * @return LearningGuidStudentsList
     */
    LearningGuidStudentsList getLearningGuidStudentsListById(String id);

    
    
    /**
     * @Title: getLearningGuidStudentsList_exist
     * @Description: TODO(通过id获取对象。通过学生学号student,导师姓名teachername，学年academicyear，学期term来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/1 下午7:47:45
     * @param studentno、teachername、acadicyear、term
     * @return
     * @return list.size()
     */
    int getLearningGuidStudentsListExist(String studentno,String classname,
    		String teachername, String academicyear, String term);
    
    
    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-14 上午12:59:25
     * @param excel
     * @param user
     * @param suffix 
     * @return
     * @return int[]
     */
    int[] importFile(File excel, User user, String suffix);

    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    ExportExcelVO exportExcelList(Page<LearningGuidStudentsList> page,User user, boolean isAdmin);
    
    LearningGuidStudentsList getLearningGuidStudentsListByStuId(String studentno);
}