package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.teacher.dao.CourseInfoDao;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;
import com.zhbit.xuexin.teacher.service.CourseInfoService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("CourseInfoService")
@Transactional(readOnly = true)
public class CourseInfoServiceImpl implements CourseInfoService {
    private Logger logger = LoggerFactory.getLogger(CourseInfoServiceImpl.class);

    @Autowired
    @Qualifier("CourseInfoDao")
    private CourseInfoDao dao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;
    
    @Autowired
    @Qualifier("teacherInfoDao")
    private TeacherInfoDao teacherInfoDao;

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
    public Page<CourseInfo> getList(Page<CourseInfo> page) {
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
    public int save(CourseInfo info, String userId) {
        if (info != null) {
            CourseInfo ci = dao.getCourseInfoBySelectedcourseno(info.getSelectedcourseno());
            if (ci != null) {// 
                return 0;
            }
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
     * @return CourseInfo
     */
    @Override
    public CourseInfo getCourseInfoByid(String id) {
        return dao.getCourseInfoByid(id);
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
    public void update(CourseInfo info, CourseInfo oldInfo) {
        if (info != null) {
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
                CourseInfo info = dao.getCourseInfoByid(id);
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
            int i=1;
            while (rowIterator.hasNext()) {
                try {
                    row = rowIterator.next();
                   
                    if(!ExcelUtil.getCellValue(row.getCell(6)).equals("")){
                    importCount++;
                    CourseInfo ci =dao.getCourseInfoBySelectedcourseno(ExcelUtil.getCellValue(row.getCell(6))); 
                    System.out.println("这次的选课课程号为"+ExcelUtil.getCellValue(row.getCell(6)));
//                    String sourceStr=ExcelUtil.getCellValue(row.getCell(4));
//                    String [] sourceStrArray=sourceStr.split(",");
//                    int teacherInfoCount=0;
//                    for (int j = 0; j < sourceStrArray.length; j++) {
//                    	TeacherInfo teacherInfo = teacherInfoDao.getTeacherInfoByNo(sourceStrArray[i]);
//                    	if (teacherInfo==null) {
//							teacherInfoCount++;
//						}
//                    	
//					}
                    
                    if (ci != null) {// 
                        updateCount++;
                    }
                  
                    else {
                    	System.out.println("这次的选课课程号为"+ExcelUtil.getCellValue(row.getCell(6)));
                    	System.out.println("这是第几条"+i);
                    	System.out.println(ExcelUtil.getCellValue(row.getCell(0))+"---"+ExcelUtil.getCellValue(row.getCell(1))+"---"+ExcelUtil.getCellValue(row.getCell(2))+"---"+ExcelUtil.getCellValue(row.getCell(3))+"---"+ExcelUtil.getCellValue(row.getCell(4))+"---"+ExcelUtil.getCellValue(row.getCell(5))+"---"+ExcelUtil.getCellValue(row.getCell(6)));
                    	i++;
                        CourseInfo info = new CourseInfo();
                        info.setCoursecode(ExcelUtil.getCellValue(row.getCell(0)));
                        info.setCoursename(ExcelUtil.getCellValue(row.getCell(1)));
                        info.setAcademicyear(ExcelUtil.getCellValue(row.getCell(2)));
                        if(!ExcelUtil.getCellValue(row.getCell(3)).equals(""))
                        info.setTerm(ExcelUtil.getCellValue(row.getCell(3)));
                        if(!ExcelUtil.getCellValue(row.getCell(4)).equals(""))
                        info.setEmployNo(ExcelUtil.getCellValue(row.getCell(4)));
                        if(!ExcelUtil.getCellValue(row.getCell(5)).equals(""))
                        info.setEmployName(ExcelUtil.getCellValue(row.getCell(5)));
                        if(!ExcelUtil.getCellValue(row.getCell(6)).equals(""))
                        info.setSelectedcourseno(ExcelUtil.getCellValue(row.getCell(6)));
                        if(!ExcelUtil.getCellValue(row.getCell(7)).equals(""))
                        info.setCoursetype(ExcelUtil.getCellValue(row.getCell(7)));
                        if(!ExcelUtil.getCellValue(row.getCell(8)).equals(""))
                        info.setTotalhours(Integer.parseInt(ExcelUtil.getCellValue(row.getCell(8))));
                        if(!ExcelUtil.getCellValue(row.getCell(9)).equals(""))
                        info.setLabhours(Integer.parseInt(ExcelUtil.getCellValue(row.getCell(9))));
                        if(!ExcelUtil.getCellValue(row.getCell(10)).equals(""))
                        info.setClassinfo(ExcelUtil.formatTransition(ExcelUtil.getCellValue(row.getCell(10))));
                        
                        if(!ExcelUtil.getCellValue(row.getCell(11)).equals(""))
                        info.setLimitstudentnum(Integer.parseInt(ExcelUtil.getCellValue(row.getCell(11))));
                        if(!ExcelUtil.getCellValue(row.getCell(12)).equals(""))
                        info.setStudentnum(Integer.parseInt(ExcelUtil.getCellValue(row.getCell(12))));
                        if(!ExcelUtil.getCellValue(row.getCell(13)).equals(""))
                        info.setCredit(Double.valueOf(ExcelUtil.getCellValue(row.getCell(13))));
                        if(!ExcelUtil.getCellValue(row.getCell(14)).equals(""))
                        info.setBelongto(ExcelUtil.getCellValue(row.getCell(14)));
                        if(!ExcelUtil.getCellValue(row.getCell(15)).equals(""))
                        info.setMemo((ExcelUtil.getCellValue(row.getCell(15))));

                        info.setCreateTime(new Date());
                        info.setCreator(user.getUserId());
                        dao.save(info);
                        insertCount++;
                        if (insertCount%100==0) {
							dao.flush();
						}
                    }
                    
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
     * @Title: exportExcelList
     * @Description: TODO(导出。)
     * @author lry
     * @date 2016-3-14 上午12:59:51
     * @param page
     * @return
     * @return ExportExcelVO
     */
    @Override
    public ExportExcelVO exportExcelList(Page<CourseInfo> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<CourseInfo> pageResult = dao.getList(page);
        List<CourseInfo> list = pageResult.getResult();
        // 设置表头
        String[] title = { "课程代码", "课程名称", "学年", "学期", "教师工号", "教师姓名", "选课课号", "课程性质", "总学时", "实验学时", "教学班组成", "限选人数","选课人数",
                "学分", "课程归属", "备注" };
        // 设置文件名
        String filename = "开课课程信息表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            CourseInfo info = list.get(i);
            String[] str = new String[16];
            str[0] = ExcelUtil.setValue(info.getCoursecode());
            str[1] = ExcelUtil.setValue(info.getCoursename());
            str[2] = ExcelUtil.setValue(info.getAcademicyear());
            str[3] = ExcelUtil.setValue(info.getTerm());
            str[4] = ExcelUtil.setValue(info.getEmployNo());
            str[5] = ExcelUtil.setValue(info.getEmployName());
            str[6] = ExcelUtil.setValue(info.getSelectedcourseno());
            str[7] = ExcelUtil.setValue(info.getCoursetype());
            str[8] = ExcelUtil.setValue(info.getTotalhours());
            str[9] = ExcelUtil.setValue(info.getLabhours());
            str[10] = ExcelUtil.setValue(info.getClassinfo());
            str[11] = ExcelUtil.setValue(info.getLimitstudentnum());
            str[12] = ExcelUtil.setValue(info.getStudentnum());
            str[13] = ExcelUtil.setValue(info.getCredit());
            str[14] = ExcelUtil.setValue(info.getBelongto());
            str[15] = ExcelUtil.setValue(info.getMemo());
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

	@Override
	public Page<Map<String,String>> getSemesterAndYearList(Page<Map<String,String>> page, User user) {
		return dao.getSemesterAndYearList(page, user.getEmployNo());
	}

	@Override
	public Page<CourseInfo> getSemesterCourseList(Page<CourseInfo> page, User user, String academicYear,
			String semester) {
		return dao.getSemesterCourseList(page, user.getEmployNo(), academicYear, semester);
	}

 

}
