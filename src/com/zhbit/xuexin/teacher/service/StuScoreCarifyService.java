package com.zhbit.xuexin.teacher.service;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.StuScoreCarify;

public interface StuScoreCarifyService {
	public Page<StuScoreCarify> getStuScoreCarifyList(Page<StuScoreCarify> page,String teano,String selectCourseNo);

	public Page<StuScoreCarify> getStuScoreCarifyListByStudentInfo(Page<StuScoreCarify> page,String year,String term,String studentno);
	
	public Page<StuScoreCarify> getStuScoreCarifyListByGuideTeacher(Page<StuScoreCarify> page,String teano);

	public ExportExcelVO exportExcelList(Page<StuScoreCarify> page, String employNo, String selectcourseno);

	public HSSFWorkbook exportExcelList(File outputFile,Page<StuScoreCarify> page,String teano, String selectCourseNo);
		
}
