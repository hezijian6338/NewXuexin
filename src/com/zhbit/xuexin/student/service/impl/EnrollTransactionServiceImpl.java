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
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.EnrollTransaction;
import com.zhbit.xuexin.domain.StudentDutys;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.EnrollTransactionDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.EnrollTransactionService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("enrollTransactionService")
@Transactional(readOnly = true)
public class EnrollTransactionServiceImpl implements EnrollTransactionService {
    private Logger logger = LoggerFactory.getLogger(EnrollTransactionServiceImpl.class);

    @Autowired
    @Qualifier("enrollTransactionDao")
    private EnrollTransactionDao dao;

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
    public Page<EnrollTransaction> getList(Page<EnrollTransaction> page) {
        return dao.getList(page);
    }
    /**
     * @Title: getDateExist
     * @Description: TODO(通过学生异动的具体信息info来获取对象。根据学号和年级判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
    public  int getDateExist(EnrollTransaction info)
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
     * @throws ParseException
     */
    @Override
    @Transactional(readOnly = false)
	public int save(EnrollTransaction info, String userId, String tanDate,
			String hanDate, String canDate) throws ParseException {
		if (info != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 2017/4/17 余锡鸿 验证学籍异动模块中新增保存界面的学号跟学号对应的姓名是否一致
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
			if (existResult == 0) {
				if (tanDate != null && !"".equals(tanDate)) {
					info.setTansactiondate(df.parse(tanDate));
				}
				if (hanDate != null && !"".equals(hanDate)) {
					info.setHandledate(df.parse(hanDate));
				}
				if (canDate != null && !"".equals(canDate)) {
					info.setCanceldate(df.parse(canDate));
				}
				info.setCreateTime(new Date());
				info.setCreator(userId);
				info.setStuId(stu.getStuId());
				dao.save(info);
				return 1;
			} else {
				return 3;
			}
		} else {
			return 0;
		}
	}

    /**
     * @Description: TODO(通过id获取对象实体。)
     * @author lry
     * @date 2016-3-20 下午9:55:52
     * @param id
     * @return
     * @return EnrollTransaction
     */
    @Override
    public EnrollTransaction getEnrollTransactionByid(String id) {
        return dao.getEnrollTransactionByid(id);
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
	public int update(EnrollTransaction info, EnrollTransaction oldInfo,
			String tanDate, String hanDate, String canDate)
			throws ParseException {
		if (info != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
			
			if (tanDate != null && !"".equals(tanDate)) {
					info.setTansactiondate(df.parse(tanDate));
			}
			if (hanDate != null && !"".equals(hanDate)) {
					info.setHandledate(df.parse(hanDate));
			}
			if (canDate != null && !"".equals(canDate)) {
					info.setCanceldate(df.parse(canDate));
			}
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			info.setStuId(oldInfo.getStuId());
			dao.update(info);
			return 1;
		} else {
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
                EnrollTransaction info = dao.getEnrollTransactionByid(id);
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
                    if (row.getCell(2) != null && !"".equals(row.getCell(2).getStringCellValue())) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(row.getCell(2).getStringCellValue());
                        if (stu == null) {// 存在学生信息才导入
                            infoIsNullCount++;
                        }
                        else {
                            EnrollTransaction info = new EnrollTransaction();
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            info.setTransactionno(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setTansactionclass(ExcelUtil.getCellValue(row.getCell(1)));
                            info.setStudentno(ExcelUtil.getCellValue(row.getCell(2)));
                            info.setStuname(ExcelUtil.getCellValue(row.getCell(3)));
                            info.setSex(ExcelUtil.getCellValue(row.getCell(4)) == "男" ? "0" : "1");
                            info.setProcesssymbols(ExcelUtil.getCellValue(row.getCell(5)));
                            info.setTansactiontype(ExcelUtil.getCellValue(row.getCell(6)));
                            info.setTansactionreason(ExcelUtil.getCellValue(row.getCell(7)));
                            if (row.getCell(8) != null && !row.getCell(8).getStringCellValue().equals(""))
                                info.setTansactiondate(df.parse(row.getCell(8).getStringCellValue()));
                            if (row.getCell(9) != null && !row.getCell(9).getStringCellValue().equals(""))
                                info.setHandledate(df.parse(row.getCell(9).getStringCellValue()));
                            if (row.getCell(10) != null && !row.getCell(10).getStringCellValue().equals(""))
                                info.setCanceldate(df.parse(row.getCell(10).getStringCellValue()));
                            info.setTansactionmemo(row.getCell(11) == null ? "" : row.getCell(11).getStringCellValue());
                            info.setZxqschool(ExcelUtil.getCellValue(row.getCell(12)));
                            info.setZxqgrade(ExcelUtil.getCellValue(row.getCell(13)));
                            info.setZxqmajor(ExcelUtil.getCellValue(row.getCell(14)));
                            info.setYdqcollege(ExcelUtil.getCellValue(row.getCell(15)));
                            info.setYdqdepartment(ExcelUtil.getCellValue(row.getCell(16)));
                            info.setYdqmajor(ExcelUtil.getCellValue(row.getCell(17)));
                            info.setYdqlength(ExcelUtil.getCellValue(row.getCell(18)));
                            info.setYdqmajorfield(ExcelUtil.getCellValue(row.getCell(19)));
                            info.setYdqcultivatedirection(ExcelUtil.getCellValue(row.getCell(20)));
                            info.setYdqclassname(ExcelUtil.getCellValue(row.getCell(21)));
                            info.setYdqschoolstatus(ExcelUtil.getCellValue(row.getCell(22)));
                            info.setZchschool(ExcelUtil.getCellValue(row.getCell(23)));
                            info.setZchgrade(ExcelUtil.getCellValue(row.getCell(24)));
                            info.setZchmajor(ExcelUtil.getCellValue(row.getCell(25)));
                            info.setYdhcollege(ExcelUtil.getCellValue(row.getCell(26)));
                            info.setYdhdepartment(ExcelUtil.getCellValue(row.getCell(27)));
                            info.setYdhmajor(ExcelUtil.getCellValue(row.getCell(28)));
                            info.setYdhlength(ExcelUtil.getCellValue(row.getCell(29)));
                            info.setYdhmajorfield(ExcelUtil.getCellValue(row.getCell(30)));
                            info.setYdhcultivatedirection(ExcelUtil.getCellValue(row.getCell(31)));
                            info.setYdhschoolstatus(ExcelUtil.getCellValue(row.getCell(32)));
                            info.setYdqgrade(ExcelUtil.getCellValue(row.getCell(33)));
                            info.setYdhgrade(ExcelUtil.getCellValue(row.getCell(34)));
                            info.setAcademicyear(ExcelUtil.getCellValue(row.getCell(35)));
                            info.setTerm(ExcelUtil.getCellValue(row.getCell(36)));
                            info.setOperator(ExcelUtil.getCellValue(row.getCell(37)));
                            info.setOperatortime(ExcelUtil.getCellValue(row.getCell(38)));
                            // if (row.getCell(39) != null)
                            info.setYdqinschool(ExcelUtil.getCellValue(row.getCell(39)) == "否" ? "N" : "Y");
                            // if (row.getCell(40) != null)
                            info.setYdhinschool(ExcelUtil.getCellValue(row.getCell(40)) == "否" ? "N" : "Y");
                            info.setYdqmajorcode(ExcelUtil.getCellValue(row.getCell(41)));
                            info.setYdhmajorcode(ExcelUtil.getCellValue(row.getCell(42)));
                            // if (row.getCell(43) != null)
                            info.setYdqisregiste(ExcelUtil.getCellValue(row.getCell(43)) == "否" ? "N" : "Y");
                            // if (row.getCell(44) != null)
                            info.setYdhisregiste(ExcelUtil.getCellValue(row.getCell(44)) == "否" ? "N" : "Y");
                            info.setMemo(ExcelUtil.getCellValue(row.getCell(45)));
                            info.setYdqeducation(ExcelUtil.getCellValue(row.getCell(46)));
                            info.setYdheducation(ExcelUtil.getCellValue(row.getCell(47)));
                            info.setYdqmajorcategory(ExcelUtil.getCellValue(row.getCell(48)));
                            info.setYdhmajorcategory(ExcelUtil.getCellValue(row.getCell(49)));
                            info.setYdresult(ExcelUtil.getCellValue(row.getCell(50)));
                            info.setStudentcategory(ExcelUtil.getCellValue(row.getCell(51)));
                            info.setExaminateno(ExcelUtil.getCellValue(row.getCell(52)));
                            info.setIdcardno(ExcelUtil.getCellValue(row.getCell(53)));

                            info.setCreateTime(new Date());// ==========
                            info.setCreator(user.getUserId());// ==========
                            info.setStuId(stu.getStuId());// ==============
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
                    msg = (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
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
     */
    @Override
    public ExportExcelVO exportExcelList(Page<EnrollTransaction> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<EnrollTransaction> pageResult = dao.getList(page);
        List<EnrollTransaction> list = pageResult.getResult();
        // 设置表头
        String[] title = { "异动序号", "异动后行政班", "学号", "姓名", "性别", "处理文号", "异动类别", "异动原因", "异动时间", "行文时间", "撤销时间", "异动说明",
                "转学前学校名称", "转学前所在年级", "转学前专业", "异动前学院", "异动前系", "异动前专业", "异动前学制", "异动前专业方向", "异动前培养方向", "异动前行政班",
                "异动前学籍转态", "转出后学校名称", "转出后年级", "转出后专业", "异动后学院", "异动后系", "异动后专业", "异动后学制", "异动后专业方向", "异动后培养方向",
                "异动后学籍状态", "异动前所在年级", "异动后所在年级", "学年", "学期", "操作人", "操作日期", "异动前是否在校", "异动后是否在校", "异动前专业代码", "异动后专业代码",
                "异动前是否注册", "异动后是否注册", "备注", "异动前学历层次", "异动后学历层次", "异动前专业类别", "异动后专业类别", "异动结果", "学生类别", "考生号", "身份证号" };
        // 设置文件名
        String filename = "学籍异动管理表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            EnrollTransaction info = list.get(i);
            String[] str = new String[54];
            str[0] = info.getTransactionno() == null ? "" : info.getTransactionno();
            str[1] = info.getTansactionclass() == null ? "" : info.getTansactionclass();
            str[2] = info.getStudentno() == null ? "" : info.getStudentno();
            str[3] = info.getStuname() == null ? "" : info.getStuname();
            str[4] = info.getSex() == "0" ? "男" : "女";
            str[5] = info.getProcesssymbols() == null ? "" : info.getProcesssymbols();
            str[6] = info.getTansactiontype() == null ? "" : info.getTansactiontype();
            str[7] = info.getTansactionreason() == null ? "" : info.getTansactionreason();
            str[8] = info.getTansactiondate() == null ? "" : df.format(info.getTansactiondate());
            str[9] = info.getHandledate() == null ? "" : df.format(info.getHandledate());
            str[10] = info.getCanceldate() == null ? "" : df.format(info.getCanceldate());
            str[11] = info.getTansactionmemo() == null ? "" : info.getTansactionmemo();
            str[12] = info.getZxqschool() == null ? "" : info.getZxqschool();
            str[13] = info.getZxqgrade() == null ? "" : info.getZxqgrade();
            str[14] = info.getZxqmajor() == null ? "" : info.getZxqmajor();
            str[15] = info.getYdqcollege() == null ? "" : info.getYdqcollege();
            str[16] = info.getYdqdepartment() == null ? "" : info.getYdqdepartment();
            str[17] = info.getYdqmajor() == null ? "" : info.getYdqmajor();
            str[18] = info.getYdqlength() == null ? "" : info.getYdqlength();
            str[19] = info.getYdqmajorfield() == null ? "" : info.getYdqmajorfield();
            str[20] = info.getYdqcultivatedirection() == null ? "" : info.getYdqcultivatedirection();
            str[21] = info.getYdqclassname() == null ? "" : info.getYdqclassname();
            str[22] = info.getYdqschoolstatus() == null ? "" : info.getYdqschoolstatus();
            str[23] = info.getZchschool() == null ? "" : info.getZchschool();
            str[24] = info.getZchgrade() == null ? "" : info.getZchgrade();
            str[25] = info.getZchmajor() == null ? "" : info.getZchmajor();
            str[26] = info.getYdhcollege() == null ? "" : info.getYdhcollege();
            str[27] = info.getYdhdepartment() == null ? "" : info.getYdhdepartment();
            str[28] = info.getYdhmajor() == null ? "" : info.getYdhmajor();
            str[29] = info.getYdhlength() == null ? "" : info.getYdhlength();
            str[30] = info.getYdhmajorfield() == null ? "" : info.getYdhmajorfield();
            str[31] = info.getYdhcultivatedirection() == null ? "" : info.getYdhcultivatedirection();
            str[32] = info.getYdhschoolstatus() == null ? "" : info.getYdhschoolstatus();
            str[33] = info.getYdqgrade() == null ? "" : info.getYdqgrade();
            str[34] = info.getYdhgrade() == null ? "" : info.getYdhgrade();
            str[35] = info.getAcademicyear() == null ? "" : info.getAcademicyear();
            str[36] = info.getTerm() == null ? "" : info.getTerm();
            str[37] = info.getOperator() == null ? "" : info.getOperator();
            str[38] = info.getOperatortime() == null ? "" : info.getOperatortime();
            str[39] = info.getYdqinschool() == "N" ? "否" : "是";
            str[40] = info.getYdhinschool() == "N" ? "否" : "是";
            str[41] = info.getYdqmajorcode() == null ? "" : info.getYdqmajorcode();
            str[42] = info.getYdhmajorcode() == null ? "" : info.getYdhmajorcode();
            str[43] = info.getYdqisregiste() == "N" ? "否" : "是";
            str[44] = info.getYdhisregiste() == "N" ? "否" : "是";
            str[45] = info.getMemo() == null ? "" : info.getMemo();
            str[46] = info.getYdqeducation() == null ? "" : info.getYdqeducation();
            str[47] = info.getYdheducation() == null ? "" : info.getYdheducation();
            str[48] = info.getYdqmajorcategory() == null ? "" : info.getYdqmajorcategory();
            str[49] = info.getYdhmajorcategory() == null ? "" : info.getYdhmajorcategory();
            str[50] = info.getYdresult() == null ? "" : info.getYdresult();
            str[51] = info.getStudentcategory() == null ? "" : info.getStudentcategory();
            str[52] = info.getExaminateno() == null ? "" : info.getExaminateno();
            str[53] = info.getIdcardno() == null ? "" : info.getIdcardno();

            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

}
