package com.zhbit.xuexin.teacher.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.dto.StuScoreQuery;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.teacher.service.StuScoreExportService;

@Controller("stuScoreExportAction")
public class StuScoreExportAction extends BaseAction {

	private StuScoreQuery query;
	@Resource
	private StuScoreExportService scoreExportService;

	public String index() {
		return "index";
	}
	
	public String viewImport() {
		return "viewImport";
	}

	// http://localhost:8080/NewXuexin/tea/stuScoreExportAction_exportScore.action?query.academicYear=2016-2017&query.term=1&query.grade=2016
	public void exportScore() throws IOException {
		if (null == query || StringUtils.isBlank(query.getGrade()) || StringUtils.isBlank(query.getAcademicYear())
				|| StringUtils.isBlank(query.getTerm())) {
			getResponse().getWriter().write(new String("参数异常".getBytes("GBK"), "iso8859-1"));
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(query.getAcademicYear()).append("-").append(query.getTerm()).append("-");
		if (StringUtils.isNotBlank(query.getOrgName())) {
			sb.append(query.getOrgName());
		}
		if (StringUtils.isNotBlank(query.getGrade())) {
			sb.append(query.getGrade()).append("级");
		}
		if (StringUtils.isNotBlank(query.getClassname())) {
			sb.append(query.getClassname());
		}
		sb.append("学生成绩汇总.zip");

		try {
			String downName = new String(sb.toString().getBytes("GBK"), "iso8859-1");
			HttpServletResponse response = getResponse();
			response.setHeader("content-disposition", "attachment;filename=" + downName);
			response.setContentType("application/octet-stream; charset=utf-8");
			scoreExportService.exportScore(query, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listParam() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			map.put("status", Const.CODE_SUCCESS);
			map.put("orgName", scoreExportService.listOrgName());
			map.put("classname", scoreExportService.listClassname());
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public StuScoreQuery getQuery() {
		return query;
	}

	public void setQuery(StuScoreQuery query) {
		this.query = query;
	}

}
