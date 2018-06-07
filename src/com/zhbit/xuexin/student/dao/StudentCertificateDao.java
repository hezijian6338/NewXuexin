package com.zhbit.xuexin.student.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.StudentCertificate;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-6-2 上午1:02:04
 *@version 1.0
 */
public interface StudentCertificateDao {
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<StudentCertificate>
     */
    Page<StudentCertificate> getList(Page<StudentCertificate> page);

    /**
     * @Title: save
     * @Description: TODO(保存信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    void save(StudentCertificate info);

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    void update(StudentCertificate info);

    /**
     * @Title: delete
     * @Description: TODO(删除信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    void delete(StudentCertificate info);

    /**
     * @Title: getStudentCertificateByid
     * @Description: TODO(通过id获取对象信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return StudentCertificate
     */
    StudentCertificate getStudentCertificateByid(String id);

}
