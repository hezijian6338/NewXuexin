package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.zhbit.xuexin.common.utils.DateUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.domain.AttendanceDetail;
import com.zhbit.xuexin.domain.AttendanceMaster;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.teacher.dao.AttendanceDetailDao;
import com.zhbit.xuexin.teacher.dao.AttendanceMasterDao;
import com.zhbit.xuexin.teacher.service.AttendanceMasterService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("attendanceMasterService")
@Transactional(readOnly = true)
public class AttendanceMasterServiceImpl implements AttendanceMasterService {
    private Logger logger = LoggerFactory.getLogger(AttendanceMasterServiceImpl.class);

    @Autowired
    @Qualifier("attendanceMasterDao")
    private AttendanceMasterDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

    @Autowired
    @Qualifier("attendanceDetailDao")
    private AttendanceDetailDao detailDao;

    @Autowired
    @Qualifier("organizationDao")
    private OrganizationDao orgDao;

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
    public Page<AttendanceMaster> getList(Page<AttendanceMaster> page) {
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
    public int save(AttendanceMaster info, String userId) {
        if (info != null) {
            AttendanceMaster ci = dao.getAttendanceMasterByCourseno(info.getSelectedcourseno());
            if (ci != null) {//
                return 0;
            }
            //许彩开	2017.04.18
           // info.setOrgId(Const.ORG_ID_MAP.get(info.getOrgName()));// 设置组织id
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
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
     * @return AttendanceMaster
     */
    @Override
    public AttendanceMaster getAttendanceMasterByid(String id) {
        return dao.getAttendanceMasterByid(id);
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
    public void update(AttendanceMaster info, AttendanceMaster oldInfo) {
        if (info != null) {
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
            dao.update(info);
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
                AttendanceMaster info = dao.getAttendanceMasterByid(id);
                removeFile(info.getSourceFile());// 删除源文件
                // 删除子表信息
                detailDao.deleteBySelectedcourseno(info.getSelectedcourseno());
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
    @SuppressWarnings("rawtypes")
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
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            Row row = null;
            rowIterator.next();// 第一行标题不读
            String selectedcourseno = null;
            String employNo = null;
            String academicyear = null;
            String term = null;
            Map<String, Integer> map = new HashMap<String, Integer>();// 记录考勤时间和列号
            try {
                row = rowIterator.next();// 第二行主表信息开始---------------------
//                 for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
//                 System.out.println("~~~~~~~~~~"+i+"~~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(i)));
//                 }
                if (ExcelUtil.getCellValue(row.getCell(9)).equals("")
                        || dao.getAttendanceMasterByCourseno(ExcelUtil.getCellValue(row.getCell(9))) != null) {
                    return new int[] { -2, insertCount, updateCount };
                }
                selectedcourseno = ExcelUtil.getCellValue(row.getCell(9));
                employNo = selectedcourseno.substring(selectedcourseno.lastIndexOf("-") - 6,
                        selectedcourseno.lastIndexOf("-") - 1);
                academicyear = ExcelUtil.getCellValue(row.getCell(2));
                term = ExcelUtil.getCellValue(row.getCell(4));
                AttendanceMaster master = new AttendanceMaster();
                master.setAcademicyear(academicyear);// 学年
                master.setTerm(term);
                master.setSelectedcourseno(selectedcourseno);
                master.setCoursename(ExcelUtil.getCellValue(row.getCell(21)));
                row = rowIterator.next();// 第三行主表信息开始-----------------------
                // for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                // System.out.println("~~~~~~~~~~"+i+"~~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(i)));
                // }
                master.setEmployName(ExcelUtil.getCellValue(row.getCell(2)));
                master.setEmployNo(employNo);
                if (!ExcelUtil.getCellValue(row.getCell(4)).equals("")) {
                    master.setOrgId(Const.ORG_ID_MAP.get(ExcelUtil.getCellValue(row.getCell(4))));// 设置组织id
                    master.setOrgName(ExcelUtil.getCellValue(row.getCell(4)));
                }
                master.setSchooltime(ExcelUtil.getCellValue(row.getCell(11)));
                master.setAddress(ExcelUtil.getCellValue(row.getCell(21)));
                master.setSourceFile(sourceFile);
                dao.save(master);
                row = rowIterator.next();
                row = rowIterator.next();// 获取考勤日期
//                for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
//                    System.out.println("~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(i))+"~~~~~~~gg~~~~");
//                }
                for (int i = 7; i < row.getPhysicalNumberOfCells(); i++) {
                    if (!ExcelUtil.getCellValue(row.getCell(i)).equals("")) {
                        System.out.println("~~~~~~~~~~"+i+"~~~~~~~~~~~"+ExcelUtil.getCellValue(row.getCell(i)));
                        map.put(ExcelUtil.getCellValue(row.getCell(i)), i);
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            String msg = null;
            while (rowIterator.hasNext()) {
                try {
                    row = rowIterator.next();
                    importCount++;
                    Students stu = studentsDao.getStudentByNo(row.getCell(2).getStringCellValue());
                    if (stu == null) {// 存在学生信息才导入
                        updateCount++;
                    }
                    else {
                        if (map.isEmpty()) {
                            AttendanceDetail info = new AttendanceDetail();
                            info.setSelectedcourseno(selectedcourseno);
                            info.setStuId(stu.getStuId());
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(3)));
                            info.setSex(ExcelUtil.getCellValue(row.getCell(4)).equals("男") ? "0" : "1");
                            info.setClassname(ExcelUtil.getCellValue(row.getCell(5)));
                            info.setCreateTime(new Date());
                            info.setCreator(user.getUserId());
                            detailDao.save(info);
                        }
                        else {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~"+map.size());
                            for (Iterator item = map.keySet().iterator(); item.hasNext();) {
                                String key = (String) item.next();
                                AttendanceDetail info = new AttendanceDetail();
                                info.setSelectedcourseno(selectedcourseno);
                                info.setStuId(stu.getStuId());
                                info.setStudentno(ExcelUtil.getCellValue(row.getCell(2)));
                                info.setStuname(ExcelUtil.getCellValue(row.getCell(3)));
                                info.setSex(ExcelUtil.getCellValue(row.getCell(4)).equals("男") ? "0" : "1");
                                info.setClassname(ExcelUtil.getCellValue(row.getCell(5)));
                                info.setCreateTime(new Date());
                                info.setCreator(user.getUserId());
                                info.setAttendanceTime(getAttendanceTime(academicyear, term, key));
                                info.setAttendanceStatus(ExcelUtil.getCellValue(row.getCell(map.get(key))));
                                System.out.println("=============="+info.getAttendanceTime()+"==="+info.getAttendanceStatus());
                                detailDao.save(info);
                            }
                        }
                        insertCount++;
                    }
                }
                catch(Exception e) {
                    msg = (ExcelUtil.getCellValue(row.getCell(0))) + (ExcelUtil.getCellValue(row.getCell(1)));
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, updateCount };
    }

    /**
     * @Title: getAttendanceTime
     * @Description: TODO(根据学年学期获取具体考勤年份。)
     * @author lry
     * @date 2016-4-6 上午1:51:44
     * @param academicyear
     * @param term
     * @param time
     * @return
     * @return Date
     */
    public Date getAttendanceTime(String academicyear, String term, String time) {
        String date = "";
        if (term.equals("1")) {
            date = academicyear.substring(0, academicyear.indexOf("-")) + "-" + time;
        }
        else {
            date = academicyear.substring(academicyear.indexOf("-") + 1) + "-" + time;
        }
        return DateUtil.formartStringToShortDate(date);
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
            String diskPath = Const.student_attendance;
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
    public void uploadAttachment(String sid, File attachment, String suffix) throws Exception {
        String file = Const.getUUID() + suffix;
        String savePath = Const.student_attendance + file;
        // 保存到物理路径
//        System.out.println("-----------savePath-----------" + savePath);
        FileOP.writeFile(savePath, attachment);
        AttendanceMaster info = dao.getAttendanceMasterByid(sid);
        String sourceFile = info.getSourceFile() + "," + file;
        info.setSourceFile(sourceFile);
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
        AttendanceMaster info = dao.getAttendanceMasterByid(sid);
        String sourceFile = info.getSourceFile().replace(attachmentFileName+",", "").replace(","+attachmentFileName, "");
        info.setSourceFile(sourceFile);
        dao.update(info);
    }
}
