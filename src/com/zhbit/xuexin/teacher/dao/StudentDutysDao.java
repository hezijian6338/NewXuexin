package com.zhbit.xuexin.teacher.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.StudentDutys;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface StudentDutysDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<StudentDutys>
     */
    Page<StudentDutys> getList(Page<StudentDutys> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(StudentDutys info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(StudentDutys info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(StudentDutys info);

    /**
     * @Title: getStudentDutysByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return StudentDutys
     */
    StudentDutys getStudentDutysByid(String id);

    /**
     * @Title: getStudentDutysByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return StudentDutys
     */
    StudentDutys getStudentDutysByNo(String employNo);
    /**
     * @Title: getStudentDutysExist
     * @Description: TODO(通过id获取对象。通过学生具体信息info来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/20 下午7:47:45
     * @param info
     * @return
     * @return list.size()
     * 
     */
    public  int getStudentDutysExist(StudentDutys info);
    
}
