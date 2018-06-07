package com.zhbit.xuexin.teacher.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.AttendanceMaster;
import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:18
 * @version 1.0
 */
public interface AttendanceMasterService {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<AttendanceMaster> getList(Page<AttendanceMaster> page);

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
    int save(AttendanceMaster info, String userId);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return AttendanceMaster
     */
    AttendanceMaster getAttendanceMasterByid(String id);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @return void
     */
    void update(AttendanceMaster info, AttendanceMaster oldInfo);

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
     * @param sourceFile
     * @return
     * @return int[]
     */
    int[] importFile(File excel, User user, String suffix, String sourceFile);

    /**
     * @Title: removeFile
     * @Description: TODO(源文件名称。)
     * @author lry
     * @date 2016-4-10 上午1:26:06
     * @param sourceFile
     * @return void
     */
    void removeFile(String sourceFile);

    /**
     * @Title: uploadAttachment
     * @Description: TODO(上传附件。)
     * @author 梁日宇
     * @date 2016-4-11 下午8:36:00
     * @param sid
     * @param attachment
     * @param suffix
     * @return void
     */
    void uploadAttachment(String sid, File attachment, String suffix) throws Exception;

    /**
     * @Title: delteFile
     * @Description: TODO(删除附件。)
     * @author lry
     * @date 2016-4-12 上午12:52:40
     * @param sid
     * @param attachmentFileName
     * @return void
     */
    void delteFile(String sid, String attachmentFileName);

}
