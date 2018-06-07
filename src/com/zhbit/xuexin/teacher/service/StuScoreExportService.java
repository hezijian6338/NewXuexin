package com.zhbit.xuexin.teacher.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.zhbit.xuexin.common.dto.StuScoreQuery;

public interface StuScoreExportService {
	/**
	 * 根据查询条件导出
	 * @Title: exportScoreByPage
	 * @Description: TODO
	 * @param page
	 * @return: void
	 */
	void exportScore(StuScoreQuery query,OutputStream out);
	
	
	List<Map<String,String>> listOrgName();
	List<Map<String,String>> listClassname();
}
