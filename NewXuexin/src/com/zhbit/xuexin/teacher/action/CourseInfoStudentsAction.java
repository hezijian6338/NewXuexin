package com.zhbit.xuexin.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.ExcelErrorInfo;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.service.CourseInfoStudentsService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:34:19
 * @version 1.0
 */
@Controller("courseInfoStudentsAction")
@Scope("prototype")
public class CourseInfoStudentsAction extends BaseAction implements ModelDriven<CourseInfoStudents> {

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

	private File excel;// 导入文件

	private String excelFileName;// 导入文件名

	private String coursecodeno;

	private String academicyear;

	private String semester;

	private String selectedcourseno;
	@Resource
	private CourseInfoStudentsService service;

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

	/**
	 * @Title: viewDetail
	 * @Description: TODO(详情页面)
	 * @author liangriyu
	 * @date 2015-12-20 下午4:40:02
	 * @return
	 * @return String
	 */
	public String viewDetail() {
		return "viewDetail";
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

	public String viewVerify() {
		return "viewVerify";
	}

	/**
	 * 获取列表
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			User user = getSessionUser();
			Page<CourseInfoStudents> page2 = new Page<CourseInfoStudents>(req);
			Boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
			if (!Boolean.TRUE.equals(isAdmin)) {
				page2.getParas().put("employNo", user.getEmployNo());
			}
			Page<CourseInfoStudents> page = service.getList(page2);

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
	 * @Title: save
	 * @Description: TODO(新增信息。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:20:51
	 * @return void
	 */
	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {

			int state = service.save(info, getSessionUser().getUserId());
			//
			if (state == 1) {
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: update
	 * @Description: TODO(修改信息。)
	 * @author liangriyu
	 * @date 2016-3-10 上午1:37:56
	 * @return void
	 */
	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			CourseInfoStudents oldInfo = service.getCourseInfoStudentsByid(info.getId());
			System.out.println(info.getStuname());
			int state = service.update(info, oldInfo);
			//
			if (state == 1) {
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 
	 * 
	 * @Title: delete
	 * @Description: TODO(删除)
	 * @author 梁日宇
	 * @date 2015-12-21 下午10:09:27
	 * @return void
	 */
	public void delete() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			service.delete(ids);
			map.put("status", Const.CODE_SUCCESS);
			map.put("message", "删除成功");
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("message", "删除失败");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getCourseInfoStudents
	 * @Description: TODO(获取对象信息。)
	 * @author liangriyu
	 * @date 2016-3-11 上午2:34:01
	 * @return void
	 */
	public void getCourseInfoStudents() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			CourseInfoStudents info = service.getCourseInfoStudentsByid(sid);
			map.put("info", info);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: importFile
	 * @Description: TODO(导入学生信息)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:19:18
	 * @return void
	 */
	public void importFile() {
		try {

			int resultCode = 0;
			int[] count = { 0, 0, 0, 0, 0, 0 };
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(Const.SESSION_USER);

					count = service.importFile(excel, user, suffix);
					if (count[0] < 0) {
						resultCode = Const.CODE_UNKOWN_ERR;
					}
				} else {// 格式不符
					resultCode = 1;
				}
			}

			StringBuffer result = new StringBuffer();
			result.append("<script type=\"text/javascript\">");
			result.append("var resultCode=").append(resultCode).append(";");
			result.append("var importCount=").append(count[0]).append(";");
			result.append("var insertCount=").append(count[1]).append(";");
			result.append("var updateCount=").append(count[2]).append(";");
			result.append("var existCount=").append(count[3]).append(";");
			result.append("var studentNoExist=").append(count[4]).append(";");
			result.append("var courseCodeNoExist=").append(count[5]).append(";");
			result.append("</script>");
			getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
		}
	}

	public void verifyFile() throws IOException {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
					List<ExcelErrorInfo> errorInfos = service.verifyFile(excel, suffix);
					map.put("status", Const.CODE_SUCCESS);
					map.put("info", errorInfos);
				} else {// 格式不符
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "格式不正确");
				}
			}
		} catch (Exception e) {
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器端发生异常");
		}
		StringBuffer result = new StringBuffer();
		result.append("<script type=\"text/javascript\">");
		result.append("var result=").append(OutUtil.outJson(map)).append(";");
		result.append("</script>");
		getResponse().getWriter().print(new String(result.toString().getBytes("UTF-8"), "ISO8859-1"));

	}
	public void exportImportScoreTemplate() {
		InputStream in = null;
		try {
			getResponse().setContentType("application/octet-stream; charset=utf-8");
			getResponse().setHeader("Content-Disposition",
					"attachment;filename=" + new String("学期成绩总表导入模板.xlsx".getBytes("GBK"), "ISO8859-1"));

			String templatePath = ServletActionContext.getServletContext().getRealPath(File.separator) + File.separator
					+ "template" + File.separator + "importScore.xlsx";
			System.out.println(templatePath);
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
	public void importScoreFile(){
		try {
			int resultCode = 0;
			int[] count = { 0, 0, 0, 0 };
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(Const.SESSION_USER);
					System.out.println("导入模块数据" + academicyear + " " + semester + " " + coursecodeno);
					count = service.importScoreFile(excel,  suffix);
					if (count[0] < 0 && count[3] != 4) {
						resultCode = Const.CODE_UNKOWN_ERR;
					} else {
						if (count[3] == 4) {
							resultCode = 4;
						}
					}
				} else {// 格式不符
					resultCode = 1;
				}
			}
			System.out.println(Arrays.toString(count));
			StringBuffer result = new StringBuffer();
			result.append("<script type=\"text/javascript\">");
			result.append("var resultCode=").append(resultCode).append(";");
			result.append("var totalCount=").append(count[0]).append(";");
			result.append("var updateCount=").append(count[1]).append(";");
			result.append("var insertCount=").append(count[2]).append(";");
			result.append("var errorCount=").append(count[3]).append(";");
			result.append("var normalcount=").append(count[4]).append(";");
			result.append("var resitcount=").append(count[5]).append(";");
			result.append("var repaircount=").append(count[6]).append(";");
			result.append("</script>");
			getResponse().getWriter().print(result.toString());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void importPositiveTestScoreFile() {
		try {

			int resultCode = 0;
			int[] count = { 0, 0, 0, 0 };
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(Const.SESSION_USER);
					System.out.println("导入模块数据" + academicyear + " " + semester + " " + coursecodeno);
					count = service.importPositiveTestScoreFile(excel, user, suffix, academicyear, semester,
							coursecodeno, selectedcourseno);
					if (count[0] < 0 && count[3] != 4) {
						resultCode = Const.CODE_UNKOWN_ERR;
					} else {
						if (count[3] == 4) {
							resultCode = 4;
						}
					}
				} else {// 格式不符
					resultCode = 1;
				}
			}

			StringBuffer result = new StringBuffer();
			result.append("<script type=\"text/javascript\">");
			result.append("var resultCode=").append(resultCode).append(";");
			result.append("var importCount=").append(count[0]).append(";");
			result.append("var insertCount=").append(count[1]).append(";");
			result.append("var updateCount=").append(count[2]).append(";");
			result.append("</script>");
			getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
		}
	}
	
	public void importResitTestScoreFile() {
		try {

			int resultCode = 0;
			int[] count = { 0, 0, 0, 0 };
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(Const.SESSION_USER);
					count = service.importResitTestScoreFile(excel, user, suffix, academicyear, semester, coursecodeno,
							selectedcourseno);
					if (count[0] < 0 && count[3] != 4) {
						resultCode = Const.CODE_UNKOWN_ERR;
					} else {
						if (count[3] == 4) {
							resultCode = 4;
						}
					}
				} else {// 格式不符
					resultCode = 1;
				}
			}

			StringBuffer result = new StringBuffer();
			result.append("<script type=\"text/javascript\">");
			result.append("var resultCode=").append(resultCode).append(";");
			result.append("var importCount=").append(count[0]).append(";");
			result.append("var insertCount=").append(count[1]).append(";");
			result.append("var updateCount=").append(count[2]).append(";");
			result.append("</script>");
			getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
		}
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
					"attachment;filename=" + new String("开课课程学生选课及成绩明细表导入模板.xlsx".getBytes("GBK"), "ISO8859-1"));

			String templatePath = ServletActionContext.getServletContext().getRealPath(File.separator) + File.separator
					+ "template" + File.separator + "CourseInfoStudents.xlsx";
			System.out.println(templatePath);
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
			ExportExcelVO vo = service.exportExcelList(new Page<CourseInfoStudents>(req));
			ExcelUtil.exportArray(getResponse(), vo.getTitle(), vo.getHeadTitle(), vo.getDataList());
		} catch (Exception err) {
			log.error("导出文件写出结果时出异常", err);
			err.printStackTrace();
		}
	}

	/**
	 * @Title: getCourseDetail
	 * @Description: TODO(获取学生成绩明细。)
	 * @author liangriyu
	 * @date 2016-3-11 上午2:34:01
	 * @return void
	 */
	public void getCourseDetail() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			List<Object[]> list = service.getCourseDetail(info.getStudentno());
			map.put("result", list);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
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

	public String getCoursecodeno() {
		return coursecodeno;
	}

	public void setCoursecodeno(String coursecodeno) {
		this.coursecodeno = coursecodeno;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public String getSelectedcourseno() {
		return selectedcourseno;
	}

	public void setSelectedcourseno(String selectedcourseno) {
		this.selectedcourseno = selectedcourseno;
	}

}
