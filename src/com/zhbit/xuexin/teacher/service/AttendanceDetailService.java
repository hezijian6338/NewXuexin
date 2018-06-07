package com.zhbit.xuexin.teacher.service;

import java.io.File;
import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.AttendanceDetail;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.vo.AttendanceDetailVO;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:33:18
 *@version 1.0
 */
public interface AttendanceDetailService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<AttendanceDetail> getList(Page<AttendanceDetail> page);
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param userId
     *            创建者
     * @param attendanceTimeStr 
     * @return
     * @return int
     */
    int save(AttendanceDetail info, String userId, String attendanceTimeStr);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return AttendanceDetail
     */
    AttendanceDetail getAttendanceDetailByid(String id);
    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo 
     * @param attendanceTimeStr 
     * @return void
     */
    void update(AttendanceDetail info, AttendanceDetail oldInfo, String attendanceTimeStr);
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
    * @Description: TODO(通过选课号获取该课程考勤。) 
    * @author LRY 
    * @date 2016-4-24 下午11:59:13 
    * @param selectedcourseno
    * @return 
    * @return List<AttendanceDetailVO>
     */
    List<AttendanceDetailVO> getDetailList(String selectedcourseno);
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
