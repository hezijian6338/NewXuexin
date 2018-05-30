/**   
 * Copyright (c) 1997-2016 Creawor All Rights Reserved
 * 地址: 珠海市高新区南方软件园B6栋2楼
 * 
 * 该软件是广东创我科技有限公司(下面简称创我科技)保密的信息和专利。
 * 非创我科技授权和许可，你不得披露该保密信息和不得使用它。 
 *
 * @Title: FamilyInfoServiceImpl.java 
 * @author Administrator
 * @Description: TODO(简单说明这个文件是做什么的。) 
 * @date 2016-3-10 下午1:15:04 
 * @version V1.0   
 */
package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

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
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.FamilyInfoDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.FamilyInfoService;

/**
 * 学生家庭信息业务层
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-10 下午1:15:04
 * @version 1.0
 */
@Service("familyInfoService")
@Transactional(readOnly = true)
public class FamilyInfoServiceImpl implements FamilyInfoService {

    private Logger logger = LoggerFactory.getLogger(FamilyInfoServiceImpl.class);

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

    @Autowired
    @Qualifier("familyInfoDao")
    private FamilyInfoDao dao;

    /**
     * @Title: getList
     * @Description: TODO(分页获取学生家庭信息。)
     * @author liangriyu
     * @date 2016-3-10 下午11:58:02
     * @param page
     * @return
     * @return Page<FamilyInfo>
     */
    @Override
    public Page<FamilyInfo> getList(Page<FamilyInfo> page) {
        return dao.getList(page);
    }

    /**
     * @Title: importExcel
     * @Description: TODO(导入学生家庭信息。)
     * @author liangriyu
     * @date 2016-3-6 上午10:35:19
     * @param stuFile
     *            文件对象
     * @param suffix
     *            文件名（去后缀）
     * @param user
     *            当前用户
     * @return
     * @return int[]
     */
    @Override
    @Transactional(readOnly = false)
    public int[] importExcel(File excel, User user, String suffix) {
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
            String msg = null;

            while (rowIterator.hasNext()) {
                try {
                    row = rowIterator.next();
                    if (!ExcelUtil.getCellValue(row.getCell(0)).equals("")) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(ExcelUtil.getCellValue(row.getCell(0)));
                        if (stu == null) {// 存在学生信息才导入
                            updateCount++;
                        }
                        else {
                            FamilyInfo info = new FamilyInfo();
                            info.setStuId(stu.getStuId());
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
                            info.setName(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setCompany(ExcelUtil.getCellValue(row.getCell(3)));
                            info.setTelno(ExcelUtil.getCellValue(row.getCell(4)));
//                            String postcode = "";
//                            if (!"无".equals(ExcelUtil.getCellValue(row.getCell(5)))) {
//                                postcode = row.getCell(5).getStringCellValue();
//                            }
                            info.setPostcode(ExcelUtil.getCellValue(row.getCell(5)));
                            info.setPoliticalstatus(ExcelUtil.getCellValue(row.getCell(6)));

                            info.setCall("父亲");
                            info.setCreateTime(new Date());
                            info.setCreator(user.getUserId());
                            dao.save(info);

                            FamilyInfo info2 = new FamilyInfo();
                            info2.setStuId(stu.getStuId());
                            info2.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
                            info2.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
                            info2.setName(ExcelUtil.getCellValue(row.getCell(7)));
                            info2.setCompany(ExcelUtil.getCellValue(row.getCell(8)));
                            info2.setTelno(ExcelUtil.getCellValue(row.getCell(9)));
//                            String postcode2 = "";
//                            if (row.getCell(10) != null && !"无".equals(row.getCell(10).getStringCellValue())) {
//                                postcode2 = row.getCell(10).getStringCellValue();
//                            }
                            info2.setPostcode(ExcelUtil.getCellValue(row.getCell(10)));
                            info2.setPoliticalstatus(ExcelUtil.getCellValue(row.getCell(11)));

                            info2.setCall("母亲");
                            info2.setCreateTime(new Date());
                            info2.setCreator(user.getUserId());
                            dao.save(info2);

                            insertCount++;
                        }
                    }
                }
                catch(Exception e) {
                    msg = (ExcelUtil.getCellValue(row.getCell(0)))
                            + (ExcelUtil.getCellValue(row.getCell(0)));
                    logger.error("读取导入的文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, updateCount };
    }

    /**
     * @Title: save
     * @Description: TODO(新增家庭信息。)
     * @author lry
     * @date 2016-3-18 上午2:59:35
     * @param studentno
     * @param stuname
     * @param call
     * @param name
     * @param politicalstatus
     * @param jobduty
     * @param company
     * @param companyaddress
     * @param telno
     * @param postcode
     * @param userId
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(String studentno, String stuname, String call, String name, String politicalstatus, String jobduty,
            String company, String companyaddress, String telno, String postcode, String userId) {
        Students stu = studentsDao.getStudentByNo(studentno);
        FamilyInfo info = new FamilyInfo();
        if (stu != null) {
            info.setStuId(stu.getStuId());
            info.setStudentno(studentno);
            info.setStuname(stuname);
            info.setCall(call);
            info.setName(name);
            info.setPoliticalstatus(politicalstatus);
            info.setTelno(telno);
            info.setJobduty(jobduty);
            info.setCompany(company);
            info.setCompanyaddress(companyaddress);
            info.setPostcode(postcode);
            info.setCreator(userId);
            info.setCreateTime(new Date());
            dao.save(info);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Title: update
     * @Description: TODO(修改家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:00:07
     * @param id
     * @param studentno
     * @param stuname
     * @param call
     * @param name
     * @param politicalstatus
     * @param jobduty
     * @param company
     * @param companyaddress
     * @param telno
     * @param postcode
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void update(String id, String studentno, String stuname, String call, String name, String politicalstatus,
            String jobduty, String company, String companyaddress, String telno, String postcode) {
        FamilyInfo info = dao.getFamilyInfo(id);
        info.setStuname(stuname);
        info.setCall(call);
        info.setName(name);
        info.setPoliticalstatus(politicalstatus);
        info.setTelno(telno);
        info.setJobduty(jobduty);
        info.setCompany(company);
        info.setCompanyaddress(companyaddress);
        info.setPostcode(postcode);
        dao.update(info);
    }

    /**
     * @Title: delete
     * @Description: TODO(删除家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:00:39
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String ids) {
        if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                FamilyInfo info = dao.getFamilyInfo(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: getFamilyInfo
     * @Description: TODO(通过id获取家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:01:12
     * @param id
     * @return
     * @return FamilyInfo
     */
    @Override
    public FamilyInfo getFamilyInfo(String id) {
        return dao.getFamilyInfo(id);
    }

	@Override
	public Page<FamilyInfo> getSelf(Page<FamilyInfo> page,Students stu) {
		
		return dao.getSelf(page,stu);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteFamilyInfo(FamilyInfo info) {
		dao.delete(info);
	}
    
    

}
