package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
import com.zhbit.xuexin.domain.StudentDutys;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.teacher.dao.StudentDutysDao;
import com.zhbit.xuexin.teacher.service.StudentDutysService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("studentDutysService")
@Transactional(readOnly = true)
public class StudentDutysServiceImpl implements StudentDutysService {
    private Logger logger = LoggerFactory.getLogger(StudentDutysServiceImpl.class);

    @Autowired
    @Qualifier("studentDutysDao")
    private StudentDutysDao dao;

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
    public Page<StudentDutys> getList(Page<StudentDutys> page) {
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
	public int save(StudentDutys info, String userId) {
		int existResult = getStudentDutysExist(info);
		if (info != null) {
			Students stu = studentsDao.getStudentByNo(info.getStudentno());
			if (stu == null) {
				return 0;
			}
			// 2017/4/20 余锡鸿 解决学生职务新增界面学号跟姓名不一致的问题
			if (!stu.getStuname().equals(info.getStuname())) {
				return 0;
			}
			//2017/4/20 余锡鸿 解决学生职务模块新增保存界面除去保存重复信息问题
			//2017/4/20 余锡鸿 增加学生职务模块新增保存界面的错误信息
			if (existResult == 0) {
				info.setStuId(stu.getStuId());
				dao.save(info);
				return 1;
			} else {
				return 2;
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
     * @return StudentDutys
     */
    @Override
    public StudentDutys getStudentDutysByid(String id) {
        return dao.getStudentDutysByid(id);
    }
    /**
     * @Title: getStudentDutysExist
     * @Description: TODO(通过id获取对象。通过学生具体信息info来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/20 下午7:47:45
     * @param info
     * @return
     * @return list.size()
     * 
     */
    public  int getStudentDutysExist(StudentDutys info)
    {
        return dao.getStudentDutysExist(info);
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
	public int update(StudentDutys info, StudentDutys oldInfo) {
		if (info != null) {
			Students stu = studentsDao.getStudentByNo(info.getStudentno());
			if (stu == null) {
				return 0;
			}
			// 2017/4/20 余锡鸿 解决学生职务编辑界面学号跟姓名不一致的问题
			if (!stu.getStuname().equals(info.getStuname())) {
				return 0;
			}
			// info.setCreateTime(oldInfo.getCreateTime());
			// info.setCreator(oldInfo.getCreator());
			//2017/4/20 余锡鸿 解决学生职务模块编辑界面除去保存重复信息问题
			//2017/4/20 余锡鸿 增加学生职务模块编辑界面的错误信息
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
                StudentDutys info = dao.getStudentDutysByid(id);
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
		int existCount = 0;// 导入文件中的数据跟数据库已存在的相同记录的重复数
		int exceptionCount=0;//导入文件中的异常数据的条数
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] { -1, insertCount, infoIsNullCount };
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
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
					String[] infoExcle = { row.getCell(0).getStringCellValue(),
							row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(),
							row.getCell(3).getStringCellValue(),
							row.getCell(4).getStringCellValue(),
							row.getCell(5).getStringCellValue(),
							row.getCell(6).getStringCellValue(),
							row.getCell(7).getStringCellValue(),
							row.getCell(8).getStringCellValue(),
							row.getCell(9).getStringCellValue() };
					for (int i = 0; i <= 9; i++) {
						infoExcle[i] = infoExcle[i].replaceAll(" ", "");
					}
					if (row.getCell(2) != null && !"".equals(infoExcle[2])) {

						importCount++;
						Students stu = studentsDao.getStudentByNo(row
								.getCell(2).getStringCellValue());
						if (stu == null) {// 存在学生信息才导入
							infoIsNullCount++;
						} else {	
							//2017/4/20 余锡鸿 解决学生职务模块导入文件除去重复信息问题
							//2017/4/20 余锡鸿 增加学生职务模块导入文件时的错误信息
							StudentDutys info = new StudentDutys();
							info.setStuId(stu.getStuId());
							info.setGrade(infoExcle[0]);
							info.setClassname(infoExcle[1]);
							info.setStudentno(infoExcle[2]);
							info.setStuname(infoExcle[3]);
							info.setSex(infoExcle[4].equals("男") ? "0" : "1");
							info.setDuty(infoExcle[5]);
							info.setTelno(infoExcle[6]);
							info.setShorttelno(infoExcle[7]);
							info.setAddress(infoExcle[8]);
							info.setMemo(infoExcle[9]);
							int existResult = getStudentDutysExist(info);
							if (existResult == 0) {
								dao.save(info);
								insertCount++;
							} else {
								existCount++;
							}
						}
					}
				} catch (Exception e) {
					exceptionCount++;
					msg = (row.getCell(0) != null ? row.getCell(0)
							.getStringCellValue() : "")
							+ (row.getCell(3) != null ? row.getCell(3)
									.getStringCellValue() : "");
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
				}
			}
		}
		return new int[] { importCount, insertCount, infoIsNullCount,
				existCount,exceptionCount };
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
    public ExportExcelVO exportExcelList(Page<StudentDutys> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<StudentDutys> pageResult = dao.getList(page);
        List<StudentDutys> list = pageResult.getResult();
        // 设置表头
        String[] title = { "年级", "班级", "学号", "姓名", "性别", "职务", "手机", "短号", "宿舍", "备注" };
        // 设置文件名
        String filename = "学生职务信息表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            StudentDutys info = list.get(i);
            String[] str = new String[10];
            str[0]=ExcelUtil.setValue(info.getGrade());
            str[1]=ExcelUtil.setValue(info.getClassname());
            str[2]=ExcelUtil.setValue(info.getStudentno());
            str[3]=ExcelUtil.setValue(info.getStuname());
            str[4]=ExcelUtil.setValue(info.getSex()).equals("0")?"男":"女";
            str[5]=ExcelUtil.setValue(info.getDuty());
            str[6]=ExcelUtil.setValue(info.getTelno());
            str[7]=ExcelUtil.setValue(info.getShorttelno());
            str[8]=ExcelUtil.setValue(info.getAddress());
            str[9]=ExcelUtil.setValue(info.getMemo());
            
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

}
