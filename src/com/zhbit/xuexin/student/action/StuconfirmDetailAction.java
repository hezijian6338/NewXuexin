package com.zhbit.xuexin.student.action;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.*;
import com.zhbit.xuexin.student.service.StuconfirmDetailService;
import com.zhbit.xuexin.student.service.StuconfirmMasterService;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.CDATA;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("stuconfirmDetailAction")
@Scope("prototype")
public class StuconfirmDetailAction extends BaseAction {

	/**
	 * 
	 */
	@Resource
	private StuconfirmDetailService stuconfirmDeatilService;
	@Resource
	private StuconfirmMasterService stuconfirmMasterService;

	private StuconfirmDetail scfd;

	private String ids;
	private String coursestuId;
	private String predate;
	private String stunum;

	private int page = 1;
	private int rows = 10;

	/**
	 * Title: setStunum Description: 接收jsp中传入的学生学号
	 * 
	 * @param stunum
	 */
	public void setStunum(String stunum) {
		this.stunum = stunum;
	}

	/**
	 * Title: getStunum Description: 获取传入的学生学号
	 * 
	 * @return
	 */
	public String getStunum() {
		return this.stunum;
	}

	/**
	 * Title: setPredate Description: 接收jsp中传入需要转换的日期
	 * 
	 * @param predate
	 */
	public void setPredate(String predate) {
		this.predate = predate;
	}

	/**
	 * Title: getPredate Description: 获取传入的日期
	 * 
	 * @return
	 */
	public String getPredate() {
		return this.predate;
	}

	public StuconfirmDetail getScfd() {
		return scfd;
	}

	public void setScfd(StuconfirmDetail scfd) {
		this.scfd = scfd;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * Title: getCoursestuId Description: 接收jsp中传入的课程id，从而获取课程具体信息
	 * 
	 * @return
	 */
	public String getCoursestuId() {
		return coursestuId;
	}

	/**
	 * Title: setCoursestuId Description: 获取课程id
	 * 
	 * @param coursestuId
	 */
	public void setCoursestuId(String coursestuId) {
		this.coursestuId = coursestuId;
	}

	public int getCurPage() {
		return page;
	}

	public void setCurPage(int curPage) {
		this.page = curPage;
	}

	public int getPageSize() {
		return rows;
	}

	public void setPageSize(int pageSize) {
		this.rows = pageSize;
	}

	public String index() {
		return "index";
	}

	public String viewAdd() {
		return "viewAdd";
	}

	public String viewEdit() {
		return "viewEdit";
	}

	/**
	 * Title: ChangeType Description: 更改jsp传入的日期属性（String -> Date）
	 * 
	 * @param predate
	 * @return
	 * @throws ParseException
	 */
	public Date ChangeType(String predate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(predate);
		return date;
	}

	/**
	 * Title: save Description: 保存按钮调用方法
	 */
	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			
			StuconfirmMaster stuconfirmMaster = stuconfirmMasterService
					.get(scfd.getMasterId());
			
			// 更改了新的函数，根据课程信息表获取信息
			CourseInfo ci = stuconfirmDeatilService
					.getCourseInfoByCourseId(coursestuId);
			
			// 检查参数
			if (null != scfd && !StringUtils.isBlank(scfd.getMasterId())) {
				
				//检查是否曾经选过这个课程
				if (!stuconfirmDeatilService.checkCourseIsSelected(
						stuconfirmMaster.getId(), ci.getCoursecode())) {


					scfd.setCoursecode(ci.getCoursecode());
					scfd.setCoursename(ci.getCoursename());
					scfd.setCoursetype(scfd.getCoursetype());
					scfd.setPredate(ChangeType(this.predate));
					scfd.setIsselected(scfd.getIsselected());
					scfd.setCreateTime(new Date());
					scfd.setCreator(user.getUserId());
					stuconfirmDeatilService.save(scfd);
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
					//System.out.println("====================");
					map.put("msg", "已经存在该课程的选课信息.");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				System.out.println("====================");
				map.put("msg", "参数异常.");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: saveSelf Description: 保存学生自己信息调用的判断函数
	 */
	public void saveSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfd) {
				StuconfirmMaster scfm = stuconfirmMasterService.get(scfd
						.getMasterId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != self && null != scfm
						&& self.getStudentno().equals(scfm.getStudentno())) {
					this.save();
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
			OutUtil.outJson(map, getResponse());
		}
	}

	/**
	 * Title: update Description: 编辑的时候调用的更新函数
	 */
	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			if (null != scfd) {
				StuconfirmDetail old = stuconfirmDeatilService
						.get(scfd.getId());
				if (null != old) {
					old.setIsselected(scfd.getIsselected());
					old.setReasons(scfd.getReasons());
					old.setCoursetype(scfd.getCoursetype());
					old.setPredate(scfd.getPredate());
					stuconfirmDeatilService.update(old);
					map.put("status", Const.CODE_SUCCESS);
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: updateSelf Description: 编辑学生自己的信息的时候调用的函数
	 */
	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			// User user = getSessionUser();
			if (null != scfd) {
				StuconfirmDetail old = stuconfirmDeatilService
						.get(scfd.getId());
				if (null != old) {
					StuconfirmMaster scfm = stuconfirmMasterService.get(old
							.getMasterId());
					Students self = (Students) getSession().getAttribute(
							Const.SESSION_STUDENT);

					if (Const.CONFIRM_STATUS_NEW.equals(scfm.getStatus())
							|| Const.CONFIRM_STATUS_RESET.equals(scfm
									.getStatus())) {

						if (null != self
								&& null != scfm
								&& self.getStudentno().equals(
										scfm.getStudentno())) {
							old.setIsselected(scfd.getIsselected());
							old.setReasons(scfd.getReasons());
							old.setCoursetype(scfd.getCoursetype());
							old.setPredate(ChangeType(this.predate));
							stuconfirmDeatilService.update(old);
							map.put("status", Const.CODE_SUCCESS);
						}
					}
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: delete Description: 删除函数
	 */
	public void delete() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				stuconfirmDeatilService.deleteMultipe(ids);
				map.put("status", Const.CODE_SUCCESS);
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: deleteSelf Description: 删除学生自己的信息时候调用的函数
	 */
	public void deleteSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				stuconfirmDeatilService.deleteSelfMultiple(self, ids);
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "没指明id");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: list Description: 获取当前列表调用的方法
	 */
	public void list() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfd && !StringUtils.isBlank(scfd.getMasterId())) {
				QueryHelper qh = new QueryHelper();
				qh.setFrom(StuconfirmDetail.class, "s");
				qh.setWhere("s.masterId=?", scfd.getMasterId());
				PageResult pageResult = stuconfirmDeatilService
						.queryPageResult(qh, page, rows);
				map.put("status", Const.CODE_SUCCESS);
				map.put("total", pageResult.getCount());
				map.put("rows", pageResult.getData());
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * Title: listSelf Description: 加载学生自己信息调用的函数（从表）
	 */
	public void listSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfd && !StringUtils.isBlank(scfd.getMasterId())) {
				StuconfirmMaster stuconfirmMaster = stuconfirmMasterService
						.get(scfd.getMasterId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != self
						&& null != stuconfirmMaster
						&& self.getStudentno().equals(
								stuconfirmMaster.getStudentno())) {
					QueryHelper qh = new QueryHelper();
					qh.setFrom(StuconfirmDetail.class, "s");
					qh.setWhere("s.masterId=?", scfd.getMasterId());
					PageResult pageResult = stuconfirmDeatilService
							.queryPageResult(qh, page, rows);
					map.put("status", Const.CODE_SUCCESS);
					map.put("total", pageResult.getCount());
					map.put("rows", pageResult.getData());
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void get() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfd && !StringUtils.isBlank(scfd.getId())) {
				scfd = stuconfirmDeatilService.get(scfd.getId());
				map.put("status", Const.CODE_SUCCESS);
				map.put("scfd", scfd);
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void getSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfd && !StringUtils.isBlank(scfd.getId())) {
				scfd = stuconfirmDeatilService.get(scfd.getId());
				if (null != scfd) {
					StuconfirmMaster stuconfirmMaster = stuconfirmMasterService
							.get(scfd.getMasterId());
					Students self = (Students) getSession().getAttribute(
							Const.SESSION_STUDENT);
					if (null != self
							&& null != stuconfirmMaster
							&& self.getStudentno().equals(
									stuconfirmMaster.getStudentno())) {
						scfd = stuconfirmDeatilService.get(scfd.getId());
						map.put("status", Const.CODE_SUCCESS);
						map.put("scfd", scfd);
					}
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void exportTemplate() {
		InputStream in = null;
		try {
			getResponse().setContentType(
					"application/octet-stream; charset=utf-8");
			getResponse().setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String("学生确认表明细.xls".getBytes("GBK"),
									"ISO8859-1"));

			String templatePath = ApplicationUtil.getInstance()
					.getProjectPath()
					+ File.separator
					+ "template"
					+ File.separator + "StuconfirmDetail.xls";
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

}
