package com.zhbit.xuexin.student.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.dto.StuQuery;
import com.zhbit.xuexin.common.dto.StuScoreQuery;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;

/**
 * 学生信息持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-1 下午3:25:12
 * @version 1.0
 */
public interface StudentsDao {

    /**
     * @Title: getStuToUser
     * @Description: TODO(通过学生编号获取信息封装成user对象。)
     * @author liangriyu
     * @date 2016-3-1 下午3:11:04
     * @return
     * @return User
     */
    User getStuToUser(String stuNo);

    /**
     * @Title: getList
     * @Description: TODO(获取学生用户列表。)
     * @author Administrator
     * @date 2016-3-3 上午2:00:33
     * @param page
     * @return
     * @return Page<Students>
     */
    Page<Students> getList(Page<Students> page);

    /**
     * @Title: getStudent
     * @Description: TODO(获取学生对象。)
     * @author liangriyu
     * @param stuId
     * @date 2016-3-6 上午10:47:02
     * @return
     * @return Students
     */
    Students getStudent(String stuId);
    
    /**
     * @Description: TODO(通过学号和姓名获取学生对象。)
     * @param stuNo
     * @param stuname
     * @return
     */
    public Students getStudentByNoAndName(String stuNo,String stuname);
    /**
     * @Title: getStudent
     * @Description: TODO(通过学号获取学生对象。)
     * @author liangriyu
     * @date 2016-3-6 上午10:47:02
     * @param stuNo
     *            学号
     * @return
     * @return Students
     */
    Students getStudentByNo(String stuNo);

    /**
     * @Title: saveStudents
     * @Description: TODO(保存学生信息。)
     * @author liangriyu
     * @date 2016-3-7 上午12:08:44
     * @param students
     * @return void
     */
    void saveStudents(Students students);

    /**
     * @Title: updateStudents
     * @Description: TODO(修改学生信息。)
     * @author liangriyu
     * @date 2016-3-10 上午1:45:05
     * @param students
     * @return void
     */
    void updateStudents(Students students);

    /**
     * @Title: deleteStudents
     * @Description: TODO(删除学生信息。)
     * @author liangiryu
     * @date 2016-3-10 上午2:07:12
     * @param students
     * @return void
     */
    void deleteStudents(Students students);

    List<String> queryPhotoPath(String sql);
    
    /**
     * @Title: getStudent
     * @Description: TODO(通过身份证号获取学生对象。)
     * @author liangriyu
     * @date 2016-3-6 上午10:47:02
     * @param idcardno
     *            身份证号
     * @return
     * @return Students
     */
    Students getStudentByIdcardNo(String idcardno);
    /**
     * 根据学院id获取学院名称
     * @Title: getOrgNameByOrgId
     * @Description: TODO
     * @param orgId
     * @return
     * @return: String
     */
	String getOrgNameByOrgId(String orgId);
	/**
	 * 清除缓存
	 * @Title: flush
	 * @Description: TODO
	 * @return: void
	 */
	void flush();
	
	
	
	List<String> listOrgName();
	List<String> listClassname();
	
	List<Students> getStudentsByStuQuery(StuQuery query);
	
}
