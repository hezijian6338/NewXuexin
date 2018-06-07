package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.LearningGuidStudentsListDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.LearningGuidStudentsListService;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;

/**
 * 导学学生名单业务层
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午12:56:55
 * @version 1.0
 */
@Service("learningGuidStudentsListService")
@Transactional(readOnly = true)
public class LearningGuidStudentsListServiceImpl implements LearningGuidStudentsListService {

    private Logger logger = LoggerFactory.getLogger(LearningGuidStudentsListServiceImpl.class);

    @Autowired
    @Qualifier("learningGuidStudentsListDao")
    private LearningGuidStudentsListDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

    @Autowired
    @Qualifier("teacherInfoDao")
    private TeacherInfoDao teacherInfoDao;

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<LearningGuidStudentsList>
     */
    @Override
    public Page<LearningGuidStudentsList> getList(Page<LearningGuidStudentsList> page,User user,boolean isAdmin) {
        return dao.getList(page,user,isAdmin);
    }

    /**
     * @Title: save
     * @Description: TODO(保存新增。)
     * @author lry
     * @date 2016-3-15 下午7:45:51
     * @param studentno
     * @param stuname
     * @param academicyear
     * @param term
     * @param classname
     * @param teachername
     * @param userId
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(String studentno, String stuname, String academicyear, String term, String classname,
            String teachername, String userId) {
        Students stu = studentsDao.getStudentByNo(studentno);
        LearningGuidStudentsList info = new LearningGuidStudentsList();
		int existResult = getLearningGuidStudentsListExist(
				studentno.replaceAll(" ", ""), classname.replaceAll(" ", ""),
				teachername.replaceAll(" ", ""),
				academicyear.replaceAll(" ", ""), term.replaceAll(" ", ""));
        //2017/3/29 余锡鸿 验证导学名单模块中新增保存界面的学号跟学号对应的姓名是否一致
		//2017/4/19 余锡鸿 新增导学名单模块中新增保存界面错误提示新信息
        if(!stu.getStuname().equals(stuname)){
        	return 0;	
        }
        if(teacherInfoDao.getTeacherInfoByName(teachername)==null){
        	return 3;//导师姓名错误
        }
		if (stu != null && existResult == 0) {
			info.setStuId(stu.getStuId());
			info.setStudentno(studentno);
			info.setStuname(stuname);
			info.setAcademicyear(academicyear);
			info.setTerm(term);
			info.setClassname(classname);
			info.setTeacherno(teacherInfoDao.getTeacherInfoByName(teachername)
					.getEmployNo());
			info.setTeachername(teachername);
			info.setCreator(userId);
			info.setCreateTime(new Date());
			dao.save(info);
			return 1;
		} else if (existResult > 0) {
			return 2;//数据库已存在相同记录
		} else {
			return 0;
		}
    }

    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-15 下午7:46:33
     * @param id
     * @param studentno
     * @param stuname
     * @param academicyear
     * @param term
     * @param classname
     * @param teachername
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public int update(String id, String studentno, String stuname, String academicyear, String term, String classname,
            String teachername, String teacherno) {
    	//2017/4/19 余锡鸿 新增导学名单模块中编辑保存界面错误提示新信息
    	Students stu = studentsDao.getStudentByNo(studentno);
    	if(!stu.getStuname().equals(stuname)){
        	return 0;	
        }
        if(teacherInfoDao.getTeacherInfoByName(teachername)==null){
        	return 3;//导师姓名错误
        }
    	int existResult = getLearningGuidStudentsListExist(
				studentno.replaceAll(" ", ""), classname.replaceAll(" ", ""),
				teachername.replaceAll(" ", ""),
				academicyear.replaceAll(" ", ""), term.replaceAll(" ", ""));
    	if(stu != null &&existResult==0){
	        LearningGuidStudentsList info = dao.getLearningGuidStudentsListById(id);
	        info.setStudentno(studentno);
	        info.setStuname(stuname);
	        info.setAcademicyear(academicyear);
	        info.setTerm(term);
	        info.setClassname(classname);
	        info.setTeacherno(teacherInfoDao.getTeacherInfoByName(teachername).getEmployNo());
	        info.setTeachername(teachername);
	        dao.update(info);
	        return 1;
    	}else if (existResult > 0) {
			return 2;//数据库已存在相同记录
		} else{
    		return 0;
    	}
    }

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String ids) {
        if (ids != null || "".equals(ids)) {
            String[] sids = ids.split(",");
            for (String id : sids) {
                LearningGuidStudentsList info = dao.getLearningGuidStudentsListById(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: getLearningGuidStudentsListById
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-15 下午7:47:45
     * @param id
     * @return
     * @return LearningGuidStudentsList
     */
    @Override
    public LearningGuidStudentsList getLearningGuidStudentsListById(String id) {
        return dao.getLearningGuidStudentsListById(id);
    }

    /**
     * @Title: getLearningGuidStudentsList_exist
     * @Description: TODO(通过id获取对象。通过学生学号student,导师姓名teachername，学年academicyear，学期term来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/1 下午7:47:45
     * @param studentno、teachername、acadicyear、term
     * @return
     * @return list.size()
     */
    public  int getLearningGuidStudentsListExist(String studentno,String classname,
    		String teachername, String academicyear, String term)
    {
        return dao.getLearningGuidStudentsListExist(studentno,classname,
        		teachername, academicyear,term);
    }
    
    
    /**
     * @Title: importFile
     * @Description: TODO(导入。)
     * @author lry
     * @date 2016-3-14 上午12:59:25
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
        int dataNullCount=0;//导入文件中某些字段是否含有空值
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
                    //2017/4/13 余锡鸿 解决导入导学名单导入模板时字段值含有空格问题
                    for(int i=0;i<=5;i++){
                    	row.getCell(i).setCellType(1);
	                    if(row.getCell(i)==null||row.getCell(i).equals("")){
							return new int[] { importCount, insertCount, infoIsNullCount, 1,existCount};
						}
	                    row.getCell(i).setCellType(1);
                    }
					String[] infoExcle = { 
							row.getCell(0).getStringCellValue(),
							row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(),
							row.getCell(3).getStringCellValue(),
							row.getCell(4).getStringCellValue(),
							row.getCell(5).getStringCellValue() };
                    for(int i=0;i<=5;i++){
						infoExcle[i]=infoExcle[i].replaceAll(" ","");
                    }
                    if (row.getCell(0) != null && !"".equals(infoExcle[0])) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(infoExcle[0]);
                       //判断 存在学生信息才能成功导入数据
                        if (stu == null) {
                            infoIsNullCount++;
                        }
                      //2017/3/28 余锡鸿 解决导入导学名单导入模板时发生的服务器数据异常问题和服务器无反应问题
                      //2017/3/29 余锡鸿 解决修复导入导学名单导入模板中新增保存界面中保存失败或者学号不存在问题
                      //2017/4/01 余锡鸿 解决不可导入数据库中已存在的重复数据
                      //2017/4/10 余锡鸿 限制到入文件的所有字段都不能为空
                        else {
                      
							int existResult = getLearningGuidStudentsListExist(
									infoExcle[0], infoExcle[1], infoExcle[3],
									infoExcle[4], infoExcle[5]);
							LearningGuidStudentsList info = new LearningGuidStudentsList();
                            if(existResult==0&&dataNullCount==0){
	                            info.setStudentno(infoExcle[0]);
	                            info.setClassname(infoExcle[1]);
	                            info.setStuname(infoExcle[2]);
	                            info.setTeachername(infoExcle[3]);
	                            info.setTeacherno(teacherInfoDao.getTeacherInfoByName(infoExcle[3])
	                                    .getEmployNo());
	                            info.setAcademicyear(infoExcle[4]);
	                            info.setTerm(infoExcle[5]);
	                            info.setStuId(stu.getStuId());
	                            info.setCreateTime(new Date());
	                            dao.save(info);
	                            insertCount++;
                            }
                            else{
                            	existCount++;
                            }
                        }
                    }
                }
                catch(Exception e) {
                	infoIsNullCount++;
                    msg = (row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "")
                            + (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
                    logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
                }
            }
        }
        return new int[] { importCount, insertCount, infoIsNullCount, dataNullCount ,existCount};
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
    public ExportExcelVO exportExcelList(Page<LearningGuidStudentsList> page,User user, boolean isAdmin) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<LearningGuidStudentsList> pageResult = dao.getList(page,user,isAdmin);
        List<LearningGuidStudentsList> list = pageResult.getResult();
        // 设置表头
        String[] title = { "学号", "专业班级", "姓名", "导学老师", "学年", "学期" };
        // 设置文件名
        String filename = "教师导学名单";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            String[] str = new String[6];
            str[0] = list.get(i).getStudentno();
            str[1] = list.get(i).getClassname();
            str[2] = list.get(i).getStuname();
            str[3] = list.get(i).getTeachername();
            str[4] = list.get(i).getAcademicyear() == null ? "" : list.get(i).getAcademicyear();
            str[5] = list.get(i).getTerm() == null ? "" : list.get(i).getTerm();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }
    
    @Override
    public LearningGuidStudentsList getLearningGuidStudentsListByStuId(String studentno) {
        return dao.getLearningGuidStudentsListByStuId(studentno);
    }
}
