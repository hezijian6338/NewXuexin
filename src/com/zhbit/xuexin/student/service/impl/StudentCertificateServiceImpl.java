package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.domain.StudentCertificate;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentCertificateDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.StudentCertificateService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("studentCertificateService")
@Transactional(readOnly = true)
public class StudentCertificateServiceImpl implements StudentCertificateService {
    
    private Logger logger = LoggerFactory.getLogger(StudentCertificateServiceImpl.class);

    @Autowired
    @Qualifier("studentCertificateDao")
    private StudentCertificateDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

//    @Autowired
//    @Qualifier("organizationDao")
//    private OrganizationDao orgDao;

    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    @Override
    public Page<StudentCertificate> getList(Page<StudentCertificate> page) {
        return dao.getList(page);
    }

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
    @Override
    @Transactional(readOnly = false)
    public int save(StudentCertificate info, String userId) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNoAndName(info.getStudentno(), info.getStuname());
            if(stu==null){
                return 0;
            }
            info.setStuId(stu.getStuId());
            info.setCreateTime(new Date());
            info.setCreator(userId);
            dao.save(info);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return StudentCertificate
     */
    @Override
    public StudentCertificate getStudentCertificateByid(String id) {
        return dao.getStudentCertificateByid(id);
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     * @param oldInfo
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public int update(StudentCertificate info, StudentCertificate oldInfo) {
        if (info != null) {
        	Students stu = studentsDao.getStudentByNoAndName(info.getStudentno(), info.getStuname());
        	if(stu == null){
        		return 0;
        	}
            info.setStuId(oldInfo.getStudentno());
            info.setDocpath(oldInfo.getDocpath());
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
            dao.update(info);
            return 1;
        }
        else{
        	return 0 ;
        }
    }

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-15 下午10:46:26
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String ids) {
        if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                StudentCertificate info = dao.getStudentCertificateByid(id);
                removeFile(info.getDocpath());// 删除源文件
                // 删除子表信息
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-15 下午10:47:48
     * @param excel
     * @param user
     * @return
     * @return int[]
     */
    @Override
    @Transactional(readOnly = false)
    public int[] importFile(File excel, User user, String suffix, String sourceFile) {
        Workbook wb = null;
        FileInputStream in = null;
        int importCount = 0;// 成功导入的总条数
        int insertCount = 0;// 导入新增的总条数、
        int updateCount = 0;// 导入更新的总条数
        try {
            in = new FileInputStream(excel);
            if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
                wb = WorkbookFactory.create(in);
            }
            else {
                wb = new HSSFWorkbook(in);// 2007以下（2003）
            }
        }
        catch(Exception e) {
            logger.error("读取导入的文件出异常", e);
            return new int[] { -1, insertCount, updateCount };
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                    logger.error("读取导入的文件时关闭输入流异常", e);
                }
            }
        }
        if (wb != null) {
            
            
        }
        return new int[] { importCount, insertCount, updateCount };
    }


    /**
     * @Title: removeFile
     * @Description: TODO(源文件名称。)
     * @author lry
     * @date 2016-4-10 上午1:26:06
     * @param sourceFile
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void removeFile(String sourceFile) {
        if (sourceFile != null && !"".equals(sourceFile)) {
            String[] files = sourceFile.split(",");
            String diskPath = Const.student_certificate;
            for (String file : files) {
                // 删除物理路径对应的文件
                File delFile = new File(new File(diskPath), file);
                if (delFile.exists()) {
                    delFile.delete();
                }
            }
        }

    }

    /**
     * @Title: uploadAttachment
     * @Description: TODO(上传附件。)
     * @author 梁日宇
     * @date 2016-4-11 下午8:36:00
     * @param sid
     * @param suffix
     * @return void
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false)
    public void uploadFile(String sid, File attachment, String suffix) throws Exception {
        StudentCertificate info = dao.getStudentCertificateByid(sid);
        String file = info.getStudentno()+"_"+ suffix;
        removeFile(info.getDocpath());
        String savePath = Const.student_certificate + file;
        // 保存到物理路径
        FileOP.writeFile(savePath, attachment);
        info.setDocpath(file);
        dao.update(info);
    }

    /**
     * @Title: delteFile
     * @Description: TODO(删除附件。)
     * @author lry
     * @date 2016-4-12 上午12:52:40
     * @param sid
     * @param attachmentFileName
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delteFile(String sid, String attachmentFileName) {
        String diskPath = Const.student_attendance;
        // 删除物理路径对应的文件
        File delFile = new File(new File(diskPath), attachmentFileName);
        if (delFile.exists()) {
            delFile.delete();
        }
        StudentCertificate info = dao.getStudentCertificateByid(sid);
        String sourceFile = info.getDocpath().replace(attachmentFileName+",", "").replace(","+attachmentFileName, "");
        info.setDocpath(sourceFile);
        dao.update(info);
    }
}
