package com.zhbit.xuexin.student.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.NceeScore;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.NceeScoreService;
import com.zhbit.xuexin.student.service.StudentsService;

/**
 *
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:34:19
 * @version 1.0
 */
@Controller("nceeScoreAction")
@Scope("prototype")
public class NceeScoreAction extends BaseAction implements
		ModelDriven<NceeScore> {

	/**
	 * @Fields serialVersionUID : TODO(简单说明是做什么的。)
	 */
	private static final long serialVersionUID = -5329106811365077799L;

	private NceeScore info;

	@Override
	public NceeScore getModel() {
		if (info == null) {
			info = new NceeScore();
		}
		return info;
	}

	private String pId;

	private int page;// 当前页

	private int rows;// 页面大小

	private String ids;

	private File excel;// 导入文件

	private String excelFileName;// 导入文件名

	private double sxScore;

	private double ywScore;

	private double yyScore;

	private double zhScore;

	@Resource
	private NceeScoreService service;

	@Resource
	private StudentsService studentsService;

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
			Page<NceeScore> page = service.getList(new Page<NceeScore>(req));
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
	 * 获取自己信息
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			Page<NceeScore> page = service.getSelf(new Page<NceeScore>(req),
					stu);
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
	 * @Title: save
	 * @Description: TODO(新增信息。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:20:51
	 * @return void
	 */
	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {

			if (null != info && null != info.getZkhNo()
					&& info.getZkhNo().matches(Const.ZKZNO_REG)) {
				int state = service.save(info, getSessionUser().getUserId(),
						sxScore, ywScore, yyScore, zhScore);
				//
				if (state == 1) {
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
				}
			} else {
				map.put("status", Const.CODE_FAIL);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public void saveSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			if (null != info && null != info.getZkhNo()
					&& info.getZkhNo().matches(Const.ZKZNO_REG)) {
				Students student = studentsService.getStudentByNo(stu
						.getStudentno());
				if (!StringUtils.isBlank(student.getIdcardno())) {
					info.setIdcardno(student.getIdcardno());
					info.setStuname(student.getStuname());
					int state = service.save(info,
							getSessionUser().getUserId(), sxScore, ywScore,
							yyScore, zhScore);
					if (state == 1) {
						map.put("status", Const.CODE_SUCCESS);
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "参数异常");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "请联系管理员录入身份证号!");
					
				}
			} else{
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "参数异常");
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
	 * @Description: TODO(修改信息。)
	 * @author liangriyu
	 * @date 2016-3-10 上午1:37:56
	 * @return void
	 */
	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != info && null != info.getZkhNo()
					&& info.getZkhNo().matches(Const.ZKZNO_REG)) {
				NceeScore oldInfo = service.getNceeScoreByid(info.getId());
				service.update(info, oldInfo);
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

	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null == info || null == info.getZkhNo()
					|| !info.getZkhNo().matches(Const.ZKZNO_REG)) {
				map.put("status", Const.CODE_FAIL);
				return;
			}
			NceeScore oldInfo = service.getNceeScoreByid(info.getId());
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			if (stu != null && oldInfo != null
					&& oldInfo.getStudentno().equals(stu.getStudentno())) {
				service.update(info, oldInfo);
				map.put("status", Const.CODE_SUCCESS);
			} else
				map.put("status", Const.CODE_FAIL);
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
	 * 删除自己的信息
	 * 
	 * @Title: deleteSelf
	 * @Description: TODO
	 * @return: void
	 */
	public void deleteSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			service.deleteSelf(ids, stu);
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
	 * @Title: getNceeScore
	 * @Description: TODO(获取对象信息。)
	 * @author liangriyu
	 * @date 2016-3-11 上午2:34:01
	 * @return void
	 */
	public void getNceeScore() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			NceeScore info = service.getNceeScoreByid(pId);
			map.put("info", info);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 只获取自己的信息
	 * 
	 * @Title: getNceeScore
	 * @Description: TODO
	 * @return: void
	 */
	public void getNceeScoreSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students stu = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			NceeScore info = service.getNceeScoreByid(pId);
			if (info != null && info.getStudentno().equals(stu.getStudentno())) {
				map.put("info", info);
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
			if (excel != null) {
				String suffix = excelFileName.substring(excelFileName
						.lastIndexOf("."));
				if (".xlsx".equalsIgnoreCase(suffix)
						|| ".xls".equalsIgnoreCase(suffix)) {
					User user = (User) getSession().getAttribute(
							Const.SESSION_USER);
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
							+ new String("学生高考入学成绩表导入模板.xls".getBytes("GBK"),
									"ISO8859-1"));

			String templatePath = ApplicationUtil.getInstance()
					.getProjectPath()
					+ File.separator
					+ "template"
					+ File.separator + "NceeScore.xls";
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
			ExportExcelVO vo = service
					.exportExcelList(new Page<NceeScore>(req));
			ExcelUtil.exportArray(getResponse(), vo.getTitle(),
					vo.getHeadTitle(), vo.getDataList(), false);
		} catch (Exception err) {
			log.error("导出文件写出结果时出异常", err);
			err.printStackTrace();
		}
	}

	public NceeScore getInfo() {
		return info;
	}

	public void setInfo(NceeScore info) {
		this.info = info;
	}

	public String getGsId() {
		return pId;
	}

	public void setGsId(String gsId) {
		this.pId = gsId;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public double getSxScore() {
		return sxScore;
	}

	public void setSxScore(double sxScore) {
		this.sxScore = sxScore;
	}

	public double getYwScore() {
		return ywScore;
	}

	public void setYwScore(double ywScore) {
		this.ywScore = ywScore;
	}

	public double getYyScore() {
		return yyScore;
	}

	public void setYyScore(double yyScore) {
		this.yyScore = yyScore;
	}

	public double getZhScore() {
		return zhScore;
	}

	public void setZhScore(double zhScore) {
		this.zhScore = zhScore;
	}

	public NceeScoreService getService() {
		return service;
	}

	public void setService(NceeScoreService service) {
		this.service = service;
	}

}
