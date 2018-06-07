package com.zhbit.xuexin.teacher.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.StuScoreCarify;

public interface StuScoreCarifyDao {
	public Page<StuScoreCarify> getStuScoreCarifyList(Page<StuScoreCarify> page,String teano,String selectCourseNo);
	public Page<StuScoreCarify> getStuScoreCarifyListByStudentInfo(Page<StuScoreCarify> page,String year,String term,String studentno);
	public Page<StuScoreCarify> getStuScoreCarifyListByGuideTeacher(Page<StuScoreCarify> page,String teano);

}
