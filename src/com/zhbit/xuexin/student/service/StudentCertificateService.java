package com.zhbit.xuexin.student.service;

import java.io.File;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.StudentCertificate;
import com.zhbit.xuexin.domain.User;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-6-2 上午1:01:17
 *@version 1.0
 */
public interface StudentCertificateService {

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    Page<StudentCertificate> getList(Page<StudentCertificate> page);

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
    int save(StudentCertificate info, String userId);

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return StudentCertificate
     */
    StudentCertificate getStudentCertificateByid(String id);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @return void
     */
    int update(StudentCertificate info, StudentCertificate oldInfo);

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
     * @Title: uploadFile
     * @Description: TODO(上传附件。)
     * @author 梁日宇
     * @date 2016-4-11 下午8:36:00
     * @param sid
     * @param file
     * @param suffix
     * @return void
     */
    void uploadFile(String sid, File file, String suffix) throws Exception;

    /**
     * @Title: delteFile
     * @Description: TODO(删除附件。)
     * @author lry
     * @date 2016-4-12 上午12:52:40
     * @param sid
     * @param fileName
     * @return void
     */
    void delteFile(String sid, String fileName);

}
