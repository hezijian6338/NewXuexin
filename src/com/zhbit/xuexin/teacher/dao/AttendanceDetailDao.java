package com.zhbit.xuexin.teacher.dao;

import java.util.List;

import org.hibernate.Session;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.AttendanceDetail;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface AttendanceDetailDao {

    Session getSession();

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<AttendanceDetail>
     */
    Page<AttendanceDetail> getList(Page<AttendanceDetail> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(AttendanceDetail info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(AttendanceDetail info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(AttendanceDetail info);

    /**
     * @Title: getAttendanceDetailByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return AttendanceDetail
     */
    AttendanceDetail getAttendanceDetailByid(String id);

    /**
     * @Title: deleteBySelectedcourseno
     * @Description: TODO(通过选课号来删除学生考勤明细信息。)
     * @author lry
     * @date 2016-4-11 下午7:40:17
     * @param selectedcourseno
     * @return void
     */
    void deleteBySelectedcourseno(String selectedcourseno);

    /**
     * @Title: getAttendanceTimes
     * @Description: TODO(通过选课号查询该课程的考勤时间)
     * @author LRY
     * @date 2016-4-24 下午11:43:52
     * @param selectedcourseno
     * @param time
     * @return
     * @return List<String>
     */
    List<String> getAttendanceTimes(String selectedcourseno);

    /**
     * @Title: getDetailList
     * @Description: TODO(通过选课号和考勤时间查询该日期考勤明细)
     * @author LRY
     * @date 2016-4-24 下午11:45:03
     * @param selectedcourseno
     * @param time
     * @return
     * @return Object[]
     */
    List<AttendanceDetail> getDetailList(String selectedcourseno, String time);
    /**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年5月9日 下午8:56:15
     *@Description:TODO()
     *@param selectedcoursenol
     *@return
     */
    int getSelectedcoursenol_exist(String selectedcoursenol);

}
