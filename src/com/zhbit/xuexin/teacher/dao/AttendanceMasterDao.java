package com.zhbit.xuexin.teacher.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.AttendanceMaster;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:48:33
 * @version 1.0
 */
public interface AttendanceMasterDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<AttendanceMaster>
     */
    Page<AttendanceMaster> getList(Page<AttendanceMaster> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(AttendanceMaster info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(AttendanceMaster info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(AttendanceMaster info);

    /**
     * @Title: getAttendanceMasterByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return AttendanceMaster
     */
    AttendanceMaster getAttendanceMasterByid(String id);

    /**
     * @Title: getAttendanceMasterByCourseno
     * @Description: TODO(通过选课号获取对象。)
     * @author lry
     * @date 2016-4-5 下午10:11:36
     * @param selectedcourseno
     * @return
     * @return AttendanceMaster
     */
    AttendanceMaster getAttendanceMasterByCourseno(String selectedcourseno);

}
