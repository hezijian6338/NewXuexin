package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.StuScoreCarify;
import com.zhbit.xuexin.teacher.dao.CourseInfoDao;
import com.zhbit.xuexin.teacher.dao.StuScoreCarifyDao;
import com.zhbit.xuexin.teacher.service.StuScoreCarifyService;
 
@Service("StuScoreCarifyService")
@Transactional(readOnly = true)
public class StuScoreCarifyServiceImpl implements StuScoreCarifyService{
	@Autowired
	@Qualifier("StuScoreCarifyDao")
	private StuScoreCarifyDao stuScoreCarifyDao;
	
	@Autowired
	@Qualifier("CourseInfoDao")
	private CourseInfoDao courseInfoDao;
	
	@Override
	public Page<StuScoreCarify> getStuScoreCarifyList(Page<StuScoreCarify> page, String teano, String selectCourseNo) {	 
		return stuScoreCarifyDao.getStuScoreCarifyList(page, teano, selectCourseNo);
	}
	
	@Override
	public Page<StuScoreCarify> getStuScoreCarifyListByGuideTeacher(Page<StuScoreCarify> page, String teano) {
		return stuScoreCarifyDao.getStuScoreCarifyListByGuideTeacher(page, teano);
	}
	
	@Override
	public Page<StuScoreCarify> getStuScoreCarifyListByStudentInfo(Page<StuScoreCarify> page,String year,String term,String studentno){
		return stuScoreCarifyDao.getStuScoreCarifyListByStudentInfo(page, year, term,studentno);
	}
	
	
	@Override
	public ExportExcelVO exportExcelList(Page<StuScoreCarify> page,String teano, String selectCourseNo) {
		page.setPage(1);
		page.setRows(300000);
		// 获取查询结果数据集
		Page<StuScoreCarify> pageResult = stuScoreCarifyDao.getStuScoreCarifyList(page, teano, selectCourseNo);
		List<StuScoreCarify> list = pageResult.getResult();
		// 设置表头
		String[] title = { "选课课号","课程名称","学号", "姓名","老师导入总评", "学生录入总评", "导师导入补考", "学生录入补考", "正考备注","补考备注"};
		// 设置文件名
		String filename = "学生选课成绩纠错表";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			StuScoreCarify info = list.get(i);
			String[] str = new String[10];
			str[0] = ExcelUtil.setValue(info.getSelectedcourseno());
			str[1] = ExcelUtil.setValue(info.getCourseName());
			str[2] = ExcelUtil.setValue(info.getStudentno());
			str[3] = ExcelUtil.setValue(info.getStuname());
			str[4] = ExcelUtil.setValue(info.getTendscore());
			str[5] = ExcelUtil.setValue(info.getFinalscore());
			str[6] = ExcelUtil.setValue(info.getTresitscore());
			str[7] = ExcelUtil.setValue(info.getResitscore());
			str[8] = ExcelUtil.setValue(info.getMemo());
			str[9] = ExcelUtil.setValue(info.getResitmemo()); 
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}
	private void setSheetValue(HSSFSheet sheet,int rowIndex,int colIndex,String value)
	{
		sheet.getRow(rowIndex).getCell(colIndex).setCellValue(value);
	}
	
	private void writeCourseInfoOnExcelHeader(HSSFSheet sheet,String selectCourseNo){
		if(selectCourseNo == null){
			return ;
		}
		else{
			CourseInfo courseInfo = courseInfoDao.getCourseInfoBySelectedcourseno(selectCourseNo);			
			setSheetValue(sheet,1,0,"课程号："+courseInfo.getCoursecode());
			setSheetValue(sheet,1,2,"开课对象："+courseInfo.getOrgName());
			setSheetValue(sheet,1,6,"课程名称："+courseInfo.getCoursename());
			setSheetValue(sheet,2,0,"开课老师："+courseInfo.getEmployName());
			setSheetValue(sheet,2,2,"选课课号："+selectCourseNo);
			setSheetValue(sheet,2,6,"开课学期："+courseInfo.getAcademicyear()
												+"-"+courseInfo.getTerm());
			setSheetValue(sheet,2,9,"学时："+courseInfo.getTotalhours());
		
		}		
	}
	private void setStuScoreCarifyCellStyle(HSSFSheet sheet,HSSFCellStyle style,int startRowNo,int rowNumber,int colNumber){
		for(int i=startRowNo;i<rowNumber+startRowNo;i++){
			HSSFRow row = sheet.getRow(i)==null?sheet.createRow(i):sheet.getRow(i);
			for(int j=0;j<colNumber;j++){
				(row.getCell(j)==null?row.createCell(j):row.getCell(j)).setCellStyle(style);
			} 
		}	
	}
	
	private HSSFCellStyle getStuScoreCarifyCellStyle(HSSFWorkbook wb){
		HSSFCellStyle style = wb.createCellStyle(); 
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 9);
		style.setFont(font);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
		return style;
	}
	
	private void writeStuScoreCarifyInfoToExcel(HSSFSheet sheet,List<StuScoreCarify> info,int startRow){
		for(StuScoreCarify item:info){
			setSheetValue(sheet,startRow,0,(startRow-3)+"");
			setSheetValue(sheet,startRow,1,item.getClassname());
			setSheetValue(sheet,startRow,2,item.getStudentno());
			setSheetValue(sheet,startRow,4,item.getStuname());
			setSheetValue(sheet,startRow,5,item.getFinalscore());
			setSheetValue(sheet,startRow,6,item.getResitscore());
			setSheetValue(sheet,startRow,7,item.getTendscore());
			setSheetValue(sheet,startRow,8,item.getTresitscore());
			setSheetValue(sheet,startRow,9,item.getMemo());
			setSheetValue(sheet,startRow,10,item.getResitmemo());
			startRow++;
		}
	}
	
	@Override
	public HSSFWorkbook exportExcelList(File outputFile,Page<StuScoreCarify> page,String teano, String selectCourseNo) {
		page.setPage(1);
		page.setRows(300000);
		Page<StuScoreCarify> pageResult = stuScoreCarifyDao.getStuScoreCarifyList(page, teano, selectCourseNo);
		List<StuScoreCarify> list = pageResult.getResult();
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		try {
			InputStream templeExcelInputStream = new FileInputStream(outputFile);
			wb = new HSSFWorkbook(templeExcelInputStream);
			sheet = wb.getSheetAt(0);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		final int rowNoStartWriteStuScoreCarify = 4;
		final int StuScoreCarifyInfoColNum = 10;
		if(!list.isEmpty()){
			writeCourseInfoOnExcelHeader(sheet,selectCourseNo);
			HSSFCellStyle style = getStuScoreCarifyCellStyle(wb);
			setStuScoreCarifyCellStyle(sheet, style,
										rowNoStartWriteStuScoreCarify,list.size(), StuScoreCarifyInfoColNum);
			writeStuScoreCarifyInfoToExcel(sheet, list,
					rowNoStartWriteStuScoreCarify);
		}
		else{
			writeCourseInfoOnExcelHeader(sheet, selectCourseNo);
		}
		return wb;
	}


}
