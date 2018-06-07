package com.zhbit.xuexin.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.StuScoreCarify;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.service.CourseInfoService;
import com.zhbit.xuexin.teacher.service.CourseInfoStudentsService;
import com.zhbit.xuexin.teacher.service.StuScoreCarifyService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:34:19
 * @version 1.0
 */
@Controller("teaScoreConfirmAction")
@Scope("prototype")
public class TeaScoreConfirmAction extends BaseAction implements ModelDriven<CourseInfoStudents> {

	/**
	 * @Fields serialVersionUID : TODO(简单说明是做什么的。)
	 */
	private static final long serialVersionUID = -5329106811365077799L;

	private CourseInfoStudents info;

	@Override
	public CourseInfoStudents getModel() {
		if (info == null) {
			info = new CourseInfoStudents();
		}

		return info;
	}

	private String sid;

	private int page;// 当前页

	private int rows;// 页面大小

	private String ids;

	private String academicyear;

	private String semester;

	private String selectcourseno;

	private String stuno;
	
	private File excel;// 导入文件

	private String excelFileName;// 导入文件名

	@Resource
	private CourseInfoStudentsService courseInfoStudentsService;

	@Resource
	private CourseInfoService courseInfoService;

	@Resource
	private StuScoreCarifyService stuScoreCarifyService;

	/**
	 * @Title: viewList
	 * @Description: TODO(管理列表页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String index() {
		return "index";
	}

	/**
	 * @Title: viewDetail
	 * @author lianhaowen
	 * @date 2017-4-22
	 * @return String
	 */
	public String viewDetail() {
		return "viewDetail";

	}

	public String viewDetailAll() {
		return "viewDetailAll";

	}

	/**
	 * @Title: viewAdd
	 * @Description: TODO(新增页面)
	 * @author liangriyu
	 * @date 2015-12-20 下午4:39:40
	 * @return
	 * @return String
	 */
	public String viewAdd() {
		return "viewAdd";
	}

	/**
	 * @Title: viewEdit
	 * @Description: TODO(修改页面)
	 * @author liangriyu
	 * @date 2015-12-20 下午4:40:02
	 * @return
	 * @return String
	 */
	public String viewEdit() {
		return "viewEdit";
	}

	public String viewAllDetail() {
		return "viewAllDetail";
	}

	/**
	 * @Title: viewImport
	 * @Description: TODO(导入页面。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:14:27
	 * @return
	 * @return String
	 */
	public String viewImport() {
		return "viewImport";
	}

	public void getMainList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			Page<Map<String, String>> page = new Page<Map<String, String>>(req);
			User user = getSessionUser();
			page = courseInfoService.getSemesterAndYearList(page, user);
			System.out.println("action查询的个数：" + page.getResult());
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 获取列表
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getSemesterCourseList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			User user = getSessionUser();
			Page<CourseInfo> page = new Page<CourseInfo>(req);
			page = courseInfoService.getSemesterCourseList(page, user, academicyear, semester);
			System.out.println("学年" + academicyear + " 学期" + semester);
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 获取列表
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getCheckScoreList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			User user = getSessionUser();
			Page<StuScoreCarify> page = new Page<StuScoreCarify>(req);
			page = stuScoreCarifyService.getStuScoreCarifyList(page, user.getEmployNo(), selectcourseno);
			System.out.println("选课课号" + selectcourseno);
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public void getCheckScoreListByStudentInfo() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			User user = getSessionUser();
			Page<StuScoreCarify> page = new Page<StuScoreCarify>(req);
			page = stuScoreCarifyService.getStuScoreCarifyListByStudentInfo(page, academicyear, semester, stuno);
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public void getCheckScoreListByGuideTeacher() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			User user = getSessionUser();
			Page<StuScoreCarify> page = new Page<StuScoreCarify>(req);
			page = stuScoreCarifyService.getStuScoreCarifyListByGuideTeacher(page, user.getEmployNo());
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: exportTemplate
	 * @Description: TODO 导出模板excel文件
	 * @author YG Tan
	 * @date 2016年1月27日 下午2:36:19
	 * @return void
	 */
	public void exportTemplate() {
		InputStream in = null;
		try {
			getResponse().setContentType("application/octet-stream; charset=utf-8");
			getResponse().setHeader("Content-Disposition",
					"attachment;filename=" + new String("教师导入成绩模板.xls".getBytes("GBK"), "ISO8859-1"));

			// String templatePath =
			// ApplicationUtil.getInstance().getProjectPath() + File.separator +
			// "template"
			// + File.separator + "CourseInfo.xlsx";
			String templatePath = ServletActionContext.getServletContext().getRealPath(File.separator) + File.separator
					+ "template" + File.separator + "TeaScoreConfirm.xls";

			// String
			// templatePath="L:\\myEclipseworkspace\\newxuexin\\WebRoot\\template\\CourseInfo.xlsx";
			System.out.println(templatePath);
			System.out.println(ApplicationUtil.getInstance().getProjectPath());
			File temFile = new File(templatePath);

			byte[] bytes = new byte[0xffff];
			in = new FileInputStream(temFile);
			ServletOutputStream out = getResponse().getOutputStream();
			int b = 0;
			while ((b = in.read(bytes, 0, 0xffff)) > 0) {
				out.write(bytes, 0, b);
			}
			out.flush();
		} catch (Exception e) {
			log.error("导出模板文件出错", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("关闭导出模板文件的输入流出错", e);
				}
			}
		}
	}

	/**
	 * @Title: exportExcelList
	 * @Description: TODO(将查询结果导出到Excel表。)
	 * @author liangriyu
	 * @date 2016年1月12日 下午2:50:23
	 * @return void
	 */
	public void exportExcelList() {
		try {
			HttpServletRequest req = getRequest();
			getResponse().setContentType("application/octet-stream; charset=utf-8");
			getResponse().setHeader("Content-Disposition",
					"attachment;filename=" + new String("教师成绩纠错模板.xls".getBytes("GBK"), "ISO8859-1"));
			String TemplateFilePath = ServletActionContext.getServletContext().getRealPath(File.separator) + "template"
					+ File.separator + "StuScoreCarify.xls";
			System.out.println(TemplateFilePath);
			System.out.println(ApplicationUtil.getInstance().getProjectPath());
			File outputTemplateFile = new File(TemplateFilePath);
			User user = getSessionUser();
			Page<StuScoreCarify> page = new Page<StuScoreCarify>(req);
			HSSFWorkbook wb = stuScoreCarifyService.exportExcelList(outputTemplateFile, page, user.getEmployNo(),
					selectcourseno);
			ServletOutputStream responseOutputStream = getResponse().getOutputStream();
			wb.write(responseOutputStream);
			responseOutputStream.flush();
		} catch (Exception err) {
			log.error("导出文件写出结果时出异常", err);
			err.printStackTrace();
		}
	}

	public CourseInfoStudents getInfo() {
		return info;
	}

	public void setInfo(CourseInfoStudents info) {
		this.info = info;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getSelectcourseno() {
		return selectcourseno;
	}

	public void setSelectcourseno(String selectcourseno) {
		this.selectcourseno = selectcourseno;
	}

	public String getStuno() {
		return stuno;
	}

	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
}
