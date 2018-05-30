package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LoanScholarship;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.LoanScholarshipDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.LoanScholarshipService;
import com.zhbit.xuexin.sys.dao.OrganizationDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-21 上午12:22:41
 * @version 1.0
 */
@Service("loanScholarshipService")
@Transactional(readOnly = true)
public class LoanScholarshipServiceImpl implements LoanScholarshipService {

    private Logger logger = LoggerFactory.getLogger(LoanScholarshipServiceImpl.class);

    @Autowired
    @Qualifier("loanScholarshipDao")
    private LoanScholarshipDao dao;

    @Autowired
    @Qualifier("organizationDao")
    private OrganizationDao orgDao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

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
    public Page<LoanScholarship> getList(Page<LoanScholarship> page) {
        return dao.getList(page);
    }
    /**
     * @Title: getDateExist
     * @Description: TODO(通过学生贷款的具体信息info来获取对象。根据学号和年级判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
    public  int getDateExist(LoanScholarship info)
    {
        return dao.getDateExist(info);
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
    public int save(LoanScholarship info, String userId, String repaydateStr, String modifydateStr) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNo(info.getStudentno());
            int existResult = getDateExist(info);
            if (stu == null) {
                return 2;
            }
            if (!stu.getStuname().equals(info.getStuname())) {
				return 2;
			}
            if(!stu.getIdcardno().equals(info.getIdcardno())){
 				return 4;
 			}
            if(existResult == 0){ 
            	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            Organization org = orgDao.getOrganizationById(info.getOrgId());
	            info.setOrgName(org.getOrgName());
	            try {
	                if (repaydateStr != null && !"".equals(repaydateStr)) {
	                    info.setRepaydate(df.parse(repaydateStr));
	                }
	                if (modifydateStr != null && !"".equals(modifydateStr)) {
	                    info.setModifydate(df.parse(modifydateStr));
	                }
	            }
	            catch(ParseException e) {
	                e.printStackTrace();
	                return 0;
	            }
	            info.setCreateTime(new Date());
	            info.setCreator(userId);
	            info.setStuId(stu.getStuId());
	            dao.save(info);
	            return 1;
            }else {
				return 3;
			}
        }
        else {
            return 0;
        }
    }

    /**
     * @Title: getCountryScholarshipByid
     * @Description: TODO(获取对象。)
     * @author lry
     * @date 2016-3-19 下午10:01:29
     * @param csId
     * @return
     * @return CountryScholarship
     */
    @Override
    public LoanScholarship getLoanScholarshipByid(String id) {
        return dao.getLoanScholarshipByid(id);
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
    public int update(LoanScholarship info, LoanScholarship oldInfo, String repaydateStr, String modifydateStr) {
        if (info != null) {
        	Students stu = studentsDao.getStudentByNo(info.getStudentno());
 			if (stu == null) {
 				return 2;
 			}
 			if (!stu.getStuname().equals(info.getStuname())) {
 				return 2;
 			}
 			if(!stu.getIdcardno().equals(info.getIdcardno())){
 				return 3;
 			}
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
            info.setStuId(oldInfo.getStuId());
            try {
                if (repaydateStr != null && "".equals(repaydateStr)) {
                    info.setRepaydate(df.parse(repaydateStr));
                }
                if (modifydateStr != null && "".equals(modifydateStr)) {
                    info.setModifydate(df.parse(modifydateStr));
                }
            }
            catch(ParseException e) {
                e.printStackTrace();
            }
            dao.update(info);
            return 1;
        }else {
			return 0;
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
                LoanScholarship info = dao.getLoanScholarshipByid(id);
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
    public int[] importFile(File excel, User user, String suffix) {
        Workbook wb = null;
        FileInputStream in = null;
        int importCount = 0;// 成功导入的总条数
        int insertCount = 0;// 导入新增的总条数、
        int infoIsNullCount = 0;// 导入文件的某条记录在基础数据没有数据，比如没有该学生或者老师基本信息
        int existCount=0;//导入文件中的数据跟数据库已存在的相同记录的重复数
        int exceptionCount=0;//导入文件中的异常数据的条数
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
            return new int[] { -1, insertCount, infoIsNullCount };
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
                    if (row.getCell(3) != null && !"".equals(row.getCell(3).getStringCellValue())) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(row.getCell(3).getStringCellValue());
                        if (stu == null) {// 存在学生信息才导入
                            infoIsNullCount++;
                        }
                        else {
                            LoanScholarship info = new LoanScholarship();
                            info.setOrgName(row.getCell(0).getStringCellValue());
                            if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue())) {
                                info.setOrgId(Const.ORG_ID_MAP.get(row.getCell(2).getStringCellValue()));
                            }
                            info.setMajor(row.getCell(1).getStringCellValue());
                            info.setClassname(row.getCell(2).getStringCellValue());
                            info.setStudentno(row.getCell(3).getStringCellValue());
                            info.setStuname(row.getCell(4).getStringCellValue());
                            String sex = "0";
                            if (row.getCell(5) != null && "女".equals(row.getCell(5).getStringCellValue())) {
                                sex = "1";
                            }
                            info.setSex(sex);
                            info.setIdcardno(row.getCell(6).getStringCellValue());
                            info.setGrade(row.getCell(7).getStringCellValue());
                            info.setLoanamount(row.getCell(8).getNumericCellValue());
                            info.setAcademicyear(row.getCell(9).getStringCellValue());
                            info.setTerm(row.getCell(10).getStringCellValue());
                            if (row.getCell(11) != null)
                                info.setCensoredflag(row.getCell(11).getStringCellValue().equals("是") ? "Y" : "N");
                            if (row.getCell(12) != null)
                                info.setRefusereason(row.getCell(12).getStringCellValue());
                            if (row.getCell(13) != null && !row.getCell(13).getStringCellValue().equals("")) {
                                info.setMemo(row.getCell(13).getStringCellValue());
                            }
                            info.setCreateTime(new Date());
                            info.setStuId(stu.getStuId());
                            int existResult = getDateExist(info);
                            if(existResult==0){
	                            dao.save(info);
	                            insertCount++;
                            }else{
                            	existCount++;
                            }
                        }
                    }
                }
                catch(Exception e) {
                	exceptionCount++;
                    msg = (row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "")
                            + (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, infoIsNullCount ,existCount ,exceptionCount};
    }

    /**
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     * 2017/5/1 余锡鸿 解决学生贷款模块点击导出出现白屏的bug
     */
    @Override
    public ExportExcelVO exportExcelList(Page<LoanScholarship> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<LoanScholarship> pageResult = dao.getList(page);
        List<LoanScholarship> list = pageResult.getResult();
        // 设置表头
        String[] title = { "学院名称", "专业", "班级", "学号", "姓名", "性别", "身份证号", "年级", "贷款金额", "学年", "学期", "是否通过专业学院资格审核",
                "被拒绝原因", "备注(是否还贷)" };
        // 设置文件名
        String filename = "贷款表格";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            LoanScholarship info = list.get(i);
            String[] str = new String[14];
            str[0] = info.getOrgName() == null ? "" : info.getOrgName();
            str[1] = info.getMajor() == null ? "" : info.getMajor();
            str[2] = info.getClassname() == null ? "" : info.getClassname();
            str[3] = info.getStudentno();
            str[4] = info.getStuname();
            String sex = "男";
            if ("1".equals(info.getSex())) {
                sex = "女";
            }
            str[5] = sex;
            str[6] = info.getIdcardno() == null ? "" : info.getIdcardno();
            str[7] = info.getGrade() == null ? "" : info.getGrade();
            str[8] = (info.getLoanamount() == null ? "" : info.getLoanamount()).toString();
            str[9] = info.getAcademicyear() == null ? "" : info.getAcademicyear();
            str[10] = info.getTerm() == null ? "" : info.getTerm();
            str[11] = info.getCensoredflag().equals("Y") ? "是" : "否";
            str[12] = info.getRefusereason() == null ? "" : info.getRefusereason();
            str[13] = info.getMemo() == null ? "" : info.getMemo();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

}
