package com.zhbit.xuexin.student.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.StuQuery;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.StudentsService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-2 上午11:20:47
 * @version 1.0
 */
@Controller("studentsAction")
@Scope("prototype")
public class StudentsAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODO(简单说明是做什么的。)
	 */
	private static final long serialVersionUID = 575040700322763761L;

	private File stuFile;

	private String stuFileFileName;

	private String stuId;

	private String studentno;

	private String stuname;

	private String sex;

	private String birthday;

	private String politicalstatus;

	private String nation;

	private String nativeplace;

	private String fromPlace;

	private String idcardno;

	private String orgId;

	private String orgName;

	private String department;

	private String major;

	private String majorfield;

	private String majorcategories;

	private String cultivatedirection;

	private String classname;

	private Integer educationsystem;

	private Integer schoolinglength;

	private String acceptancedate;

	private String middleschool;

	private String mobileno;

	private String familytelno;

	private String postcode;

	private String travelrange;

	private String address;

	private String skill;

	private String selfdescription;

	private String awards;

	private String schoolstatus;

	private String dqszj;

	private String photopath;

	private String graduateflag;

	private String alumniflag;

	private String createTime;

	private String creator;

	private String password;

	private int page;// 当前页

	private int rows;// 页面大小

	private String ids;// 用户ids

	private File photo;// 头像

	private String photoFileName;// 导入文件名

	private String passwordOld;

	@Resource
	private StudentsService studentsService;

	/**
	 * 
	 * 
	 * @Title: base
	 * @Description: TODO(学生基础信息页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String base() {
		return "base";
	}

	/**
	 * 
	 * 
	 * @Title: viewList
	 * @Description: TODO(管理用户列表页面)
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
	 * 
	 * 
	 * @Title: viewEdit
	 * @Description: TODO(修改页面)
	 * @author Administrator
	 * @date 2015-12-20 下午4:40:02
	 * @return
	 * @return String
	 */
	public String viewEdit() {
		return "viewEdit";
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
	
	public String viewExport() {
		return "viewExport";
	}

	/**
	 * @Title: viewDetail
	 * @Description: TODO(详情页面。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:14:27
	 * @return
	 * @return String
	 */
	public String viewDetail() {
		return "viewDetail";
	}

	/**
	 * @Title: viewUpdatePhoto
	 * @Description: TODO(上传头像页面。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:14:27
	 * @return
	 * @return String
	 */
	public String viewUpdatePhoto() {
		return "viewUpdatePhoto";
	}
	private StuQuery query;
	
	public StuQuery getQuery() {
		return query;
	}

	public void setQuery(StuQuery query) {
		this.query = query;
	}
	//http://localhost:8080/NewXuexin/stu/studentsAction_export.action?query.grade=2016
	public void export() throws Exception{
		if (null == query || StringUtils.isBlank(query.getGrade())	) {
			getResponse().getWriter().write(new String("参数异常".getBytes("GBK"), "iso8859-1"));
			return;
		}
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(query.getOrgName())) {
			sb.append(query.getOrgName());
		}
		if (StringUtils.isNotBlank(query.getGrade())) {
			sb.append(query.getGrade()).append("级");
		}
		if (StringUtils.isNotBlank(query.getClassname())) {
			sb.append(query.getClassname());
		}
		sb.append("学生基本信息.zip");

		try {
			String downName = new String(sb.toString().getBytes("GBK"), "iso8859-1");
			HttpServletResponse response = getResponse();
			response.setHeader("content-disposition", "attachment;filename=" + downName);
			response.setContentType("application/octet-stream; charset=utf-8");
			studentsService.export(query, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void listParam() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			map.put("status", Const.CODE_SUCCESS);
			map.put("orgName", studentsService.listOrgName());
			map.put("classname", studentsService.listClassname());
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}
	
	/**
	 * 获取所有用户
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			Page<Students> page = studentsService.getList(new Page<Students>(
					req));
			//
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
	 * 
	 * @Title: getSelf
	 * @Description: 获取自己的信息
	 * @return: void
	 */
	public void getSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			Page<Students> page = studentsService.getSelf(new Page<Students>(
					req), stu);
			//
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
	 * @Title: getStudent
	 * @Description: TODO(获取用户信息。)
	 * @author liangiryu
	 * @date 2016-3-9 下午11:13:42
	 * @return void
	 */
	public void getStudent() {
		Map<String, Object> map = Const.getJsonMap();
		try {

			Students stu = studentsService.getStudent(stuId);
			//
			map.put("info", stu);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public void getStudentSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students self = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			Students stu = studentsService.getStudent(self.getStuId());
			map.put("info", stu);
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
			int[] count = { 0, 0, 0 };
			if (stuFile != null) {
				String suffix = stuFileFileName.substring(stuFileFileName
						.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix)
						|| ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(
							Const.SESSION_USER);
					count = studentsService.importStudents(stuFile, user,
							suffix);
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
			getResponse().setContentType(
					"application/octet-stream; charset=utf-8");
			getResponse().setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String("学生信息导入模板.xls".getBytes("GBK"),
									"ISO8859-1"));

			String templatePath = ApplicationUtil.getInstance()
					.getProjectPath()
					+ File.separator
					+ "template"
					+ File.separator + "Students.xls";
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
			ExportExcelVO vo = studentsService
					.exportExcelList(new Page<Students>(req));
			ExcelUtil.exportArray(getResponse(), vo.getTitle(),
					vo.getHeadTitle(), vo.getDataList());
		} catch (Exception err) {
			log.error("导出文件写出结果时出异常", err);
			err.printStackTrace();
		}
	}

	/**
	 * @Title: save
	 * @Description: TODO(新增学生信息。)
	 * @author Administrator
	 * @date 2016-3-9 下午11:20:51
	 * @return void
	 */
	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			orgName = studentsService.getOrgNameByOrgId(orgId);
			if (!StringUtils.isBlank(orgName)) {
				studentsService.saveStudents(studentno, stuname, sex,
						df.parse(birthday), politicalstatus, nation,
						nativeplace, fromPlace, idcardno, orgId, orgName,
						department, major, majorfield, majorcategories,
						cultivatedirection, classname, educationsystem,
						schoolinglength, df.parse(acceptancedate),
						middleschool, mobileno, familytelno, postcode,
						travelrange, address, skill, selfdescription, awards,
						schoolstatus, dqszj, photopath, graduateflag,
						alumniflag, getSessionUser().getUserId());
				//
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "学院不存在!");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: update
	 * @Description: TODO(修改学生基本信息。)
	 * @author liangriyu
	 * @date 2016-3-10 上午1:37:56
	 * @return void
	 */
	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			orgName = studentsService.getOrgNameByOrgId(orgId);
			if (!StringUtils.isBlank(orgName)) {
				studentsService.updateStudents(stuId, studentno, stuname, sex,
						df.parse(birthday), politicalstatus, nation,
						nativeplace, fromPlace, idcardno, orgId, orgName,
						department, major, majorfield, majorcategories,
						cultivatedirection, classname, educationsystem,
						schoolinglength, df.parse(acceptancedate),
						middleschool, mobileno, familytelno, postcode,
						travelrange, address, skill, selfdescription, awards,
						schoolstatus, dqszj, photopath, graduateflag,
						alumniflag);
				//
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "学院不存在!");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 
	 * @Title: updateSelf
	 * @Description: 修改自己信息
	 * @return: void
	 */
	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Students student = studentsService.getStudent(stuId);
		Students self = (Students) getSession().getAttribute(
				Const.SESSION_STUDENT);
		if (student != null
				&& self.getStudentno().equals(student.getStudentno())) {
			try {
				orgName = studentsService.getOrgNameByOrgId(orgId);
				if (!StringUtils.isBlank(orgName)) {
					studentsService.updateStudents(stuId, self.getStudentno(),
							self.getStuname(), sex, df.parse(birthday),
							politicalstatus, nation, nativeplace, fromPlace,
							idcardno, orgId, orgName, department, major,
							majorfield, majorcategories, cultivatedirection,
							classname, educationsystem, schoolinglength,
							df.parse(acceptancedate), middleschool, mobileno,
							familytelno, postcode, travelrange, address, skill,
							selfdescription, awards, schoolstatus, dqszj,
							photopath, graduateflag, alumniflag);
					//
					getSession().setAttribute(Const.SESSION_STUDENT,
							studentsService.getStudent(self.getStuId()));
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "学院不存在!");
				}
			} catch (Exception err) {
				err.printStackTrace();
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "服务器异常");
			}
		} else {
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "只能修改自己的信息");
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
			studentsService.deleteStudents(ids);
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
	 * @Title: updatePhoto
	 * @Description: TODO(跟新头像。)
	 * @author liangriyu
	 * @date 2016-3-11 上午2:34:01
	 * @return void
	 */
	public void updatePhoto() {
		int code = 0;
		try {
			if (photo != null && null != photoFileName) {
				photoFileName = photoFileName.toLowerCase();
				if (photo.length() > 1024 * 1024) {
					code = 2;
				} else if (!photoFileName.matches(Const.PHOTO_REG)) {
					code = 1;
				} else {
					String suffix = photoFileName.substring(photoFileName
							.lastIndexOf("."));
					studentsService.updatePhoto(studentno, photo, suffix);
				}
			}
		} catch (Exception e) {
			log.error("上传附件件出错", e);
			code = 3;
		}
		StringBuffer result = new StringBuffer();
		result.append("<script type=\"text/javascript\">");
		result.append("var resultCode=").append(code).append(";");
		result.append("</script>");
		try {
			getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateSelfPhoto() {
		int code = 0;
		try {
			if (photo != null && null != photoFileName) {
				photoFileName = photoFileName.toLowerCase();
				if (photo.length() > 1024 * 1024) {
					code = 2;
				} else if (!photoFileName.matches(Const.PHOTO_REG)) {
					code = 1;
				} else {
					String suffix = photoFileName.substring(photoFileName
							.lastIndexOf("."));
					Students self = (Students) getSession().getAttribute(
							Const.SESSION_STUDENT);
					studentsService.updatePhoto(self.getStudentno(), photo,
							suffix);
				}
			}
		} catch (Exception e) {
			log.error("上传附件件出错", e);
			code = 3;
		}
		StringBuffer result = new StringBuffer();
		result.append("<script type=\"text/javascript\">");
		result.append("var resultCode=").append(code).append(";");
		result.append("</script>");
		try {
			getResponse().getWriter().print(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @Title: getPhotoFile
	 * @Description: TODO(获取照片。)
	 * @author LRY
	 * @date 2016-4-27 上午12:58:13
	 * @return void
	 */
	public void getPhotoFile() {
		try {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath(Const.studentPhoto);
			Students stu = studentsService.getStudentByNo(studentno);
			String ppath = stu.getPhotopath();
			if (ppath != null && !"".equals(ppath)) {
				OutUtil.outOpenFile(realPath + File.separator + ppath,
						getResponse());
			} else {
				String defaultPhotoPath = ServletActionContext
						.getServletContext().getRealPath(
								Const.DEFAULT_PHOTO_PATH);
				OutUtil.outOpenFile(defaultPhotoPath, getResponse());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSelfPhotoFile() {
		try {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath(Const.studentPhoto);
			Students self = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			Students stu = studentsService.getStudentByNo(self.getStudentno());
			String ppath = stu.getPhotopath();
			if (ppath != null && !"".equals(ppath)) {
				OutUtil.outOpenFile(realPath + File.separator + ppath,
						getResponse());
			} else {
				String defaultPhotoPath = ServletActionContext
						.getServletContext().getRealPath(
								Const.DEFAULT_PHOTO_PATH);
				OutUtil.outOpenFile(defaultPhotoPath, getResponse());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: viewList
	 * @Description: TODO(学生成绩列表页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String detailCourse() {
		return "detailCourse";
	}

	/**
	 * @Title: detailPolical
	 * @Description: TODO(学生党团关系明细页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String detailPolical() {
		return "detailPolical";
	}

	/**
	 * @Title: detailLoan
	 * @Description: TODO(学生贷款明细页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String detailLoan() {
		return "detailLoan";
	}

	/**
	 * @Title: detailExperience
	 * @Description: TODO(学生学习经历明细页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String detailExperience() {
		return "detailExperience";
	}

	/**
	 * @Title: updatePwd
	 * @Description: TODO(修改密码。)
	 * @author LRY
	 * @date 2016-5-18 下午11:23:24
	 * @return void
	 */
	public void updatePwd() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			int rs = studentsService.updatePwd(password, passwordOld, stuId);
			if (rs == 0) {
				map.put("status", Const.CODE_SUCCESS);
				map.put("message", "修改成功");
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("message", "密码不正确");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("message", "修改失败");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: viewPwd
	 * @Description: TODO(修改学生密码页面)
	 * @author 梁日宇
	 * @date 2015-12-14 下午8:55:25
	 * @return
	 * @return String
	 */
	public String viewPwd() {
		return "viewPwd";
	}

	public File getStuFile() {
		return stuFile;
	}

	public void setStuFile(File stuFile) {
		this.stuFile = stuFile;
	}

	public String getStuFileFileName() {
		return stuFileFileName;
	}

	public void setStuFileFileName(String stuFileFileName) {
		this.stuFileFileName = stuFileFileName;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPoliticalstatus() {
		return politicalstatus;
	}

	public void setPoliticalstatus(String politicalstatus) {
		this.politicalstatus = politicalstatus;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajorfield() {
		return majorfield;
	}

	public void setMajorfield(String majorfield) {
		this.majorfield = majorfield;
	}

	public String getMajorcategories() {
		return majorcategories;
	}

	public void setMajorcategories(String majorcategories) {
		this.majorcategories = majorcategories;
	}

	public String getCultivatedirection() {
		return cultivatedirection;
	}

	public void setCultivatedirection(String cultivatedirection) {
		this.cultivatedirection = cultivatedirection;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getEducationsystem() {
		return educationsystem;
	}

	public void setEducationsystem(Integer educationsystem) {
		this.educationsystem = educationsystem;
	}

	public Integer getSchoolinglength() {
		return schoolinglength;
	}

	public void setSchoolinglength(Integer schoolinglength) {
		this.schoolinglength = schoolinglength;
	}

	public String getAcceptancedate() {
		return acceptancedate;
	}

	public void setAcceptancedate(String acceptancedate) {
		this.acceptancedate = acceptancedate;
	}

	public String getMiddleschool() {
		return middleschool;
	}

	public void setMiddleschool(String middleschool) {
		this.middleschool = middleschool;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getFamilytelno() {
		return familytelno;
	}

	public void setFamilytelno(String familytelno) {
		this.familytelno = familytelno;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTravelrange() {
		return travelrange;
	}

	public void setTravelrange(String travelrange) {
		this.travelrange = travelrange;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSelfdescription() {
		return selfdescription;
	}

	public void setSelfdescription(String selfdescription) {
		this.selfdescription = selfdescription;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getSchoolstatus() {
		return schoolstatus;
	}

	public void setSchoolstatus(String schoolstatus) {
		this.schoolstatus = schoolstatus;
	}

	public String getDqszj() {
		return dqszj;
	}

	public void setDqszj(String dqszj) {
		this.dqszj = dqszj;
	}

	public String getPhotopath() {
		return photopath;
	}

	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}

	public String getGraduateflag() {
		return graduateflag;
	}

	public void setGraduateflag(String graduateflag) {
		this.graduateflag = graduateflag;
	}

	public String getAlumniflag() {
		return alumniflag;
	}

	public void setAlumniflag(String alumniflag) {
		this.alumniflag = alumniflag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPasswordOld() {
		return passwordOld;
	}

	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

}
