
package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.User;

/**
 *导学学生名单持久化接口
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午12:54:32
 *@version 1.0
 */
public interface LearningGuidStudentsListDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @param user 
     * @param isAdmin 
     * @return
     * @return Page<LearningGuidStudentsList>
     */
    Page<LearningGuidStudentsList> getList(Page<LearningGuidStudentsList> page,User user,boolean isAdmin);
    /**
     * @Title: save
     * @Description: TODO(保存新增。)
     * @author lry
     * @date 2016-3-15 下午7:45:51
     * @param info
     * @return
     * @return int
     */
    void save(LearningGuidStudentsList info);
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
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-15 下午7:46:33
     * @param info
     * @return void
     */
    void update(LearningGuidStudentsList info);
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param info
     * @return void
     */
    void delete(LearningGuidStudentsList info);
    
    LearningGuidStudentsList getLearningGuidStudentsListByStuId(String studentno);

}
