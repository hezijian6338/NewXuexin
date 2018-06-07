package com.zhbit.xuexin.student.action;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.StudentsService;
import com.zhbit.xuexin.student.service.StuscoreconfDetailService;
import com.zhbit.xuexin.student.service.StuscoreconfMasterService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller("stuscoreconfMasterAction")
@Scope("prototype")
public class StuscoreconfMasterAction extends BaseAction {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4683255814357236686L;
	@Resource
	private StuscoreconfMasterService stuscoreconfMasterService;
	@Resource
	private StuscoreconfDetailService stuscoreconfDetailService;
	@Resource
	private StudentsService studentsService;

	private StuscoreconfMaster sscm;

	private String ids;
	// 分页参数
	private int page = 1;
	private int rows = 10;

	public String index() {
		return "index";
	}

	public String viewAdd() {
		return "viewAdd";
	}

	public String viewEdit() {
		return "viewEdit";
	}

	public String viewInfo() {
		return "viewInfo";
	}

	public String viewDetail() {
		return "viewDetail";
	}

	/**
	 * 保存主表
	 *
	 * @Title: save
	 * @Description: TODO
	 * @return: void
	 */
	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			// 检查参数
			if (!checkSscmAcademicyearAndTerm(sscm)) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "学年或学期格式不正确!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			Students student = studentsService.getStudentByNo(sscm
					.getStudentno());
			if (null == student) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的学号有误!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			// 判断是否存在
			if (stuscoreconfMasterService.isExist(sscm.getStudentno(),
					sscm.getAcademicyear(), sscm.getTerm())) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "该记录已存在!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			sscm.setStuId(student.getStuId());
			sscm.setStuname(student.getStuname());
			sscm.setOrgId(student.getOrgId());
			sscm.setOrgName(student.getOrgName());
			sscm.setMajor(student.getMajor());
			sscm.setClassname(student.getClassname());
			String mobileno = student.getMobileno();
			// 如果学生表中学生号码存在多个,就截取第一个
			if (mobileno != null && mobileno.length() > 11) {
				mobileno = mobileno.substring(0, 11);
			}
			sscm.setTelno(mobileno);
			sscm.setGrade(student.getGrade());
			LearningGuidStudentsList ll = stuscoreconfMasterService
					.getLearningGuidStudentsListByStuno(student.getStudentno());
			if (null != ll) {
				sscm.setTeacherno(ll.getTeacherno());
				sscm.setTeachername(ll.getTeachername());
			}

			sscm.setCreateTime(new Date());
			sscm.setCreator(user.getUserId());
			sscm.setStatus(Const.SCORECONF_STATUS_NEW);
			StuscoreconfMaster newsscm = stuscoreconfMasterService.save(sscm);

			map.put("status", Const.CODE_SUCCESS);
			map.put("newsscm", newsscm);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 检查学年学期是否正确
	 *
	 * @param sscm
	 * @return
	 * @Title: checkSscmAcademicyearAndTerm
	 * @Description: TODO
	 * @return: boolean
	 */
	private boolean checkSscmAcademicyearAndTerm(StuscoreconfMaster sscm) {
		if (null != sscm) {
			String reg = "^20[0-9]{2}-20[0-9]{2}$";
			String academicyear = sscm.getAcademicyear();
			String term = sscm.getTerm();
			if (null != academicyear && academicyear.matches(reg)) {
				char per = academicyear.charAt(3);
				char next = academicyear.charAt(8);
				if ((next - per) == 1) {
					if ("1".equals(term) || "2".equals(term)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 学生保存自己的主表
	 *
	 * @Title: saveSelf
	 * @Description: TODO
	 * @return: void
	 */
	public void saveSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students self = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			if (null != sscm && null != self
					&& self.getStudentno().equals(sscm.getStudentno())) {
				this.save();
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的参数有误!");
				OutUtil.outJson(map, getResponse());
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
			OutUtil.outJson(map, getResponse());
		}
	}

	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			// 检查参数
			if (checkSscmAcademicyearAndTerm(sscm)) {
				StuscoreconfMaster old = stuscoreconfMasterService.get(sscm
						.getId());
				if (null != old) {
					old.setAcademicyear(sscm.getAcademicyear());
					old.setTerm(sscm.getTerm());
					stuscoreconfMasterService.update(old);
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "学年或学期格式不正确!");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {

			// 检查学年学期格式
			if (checkSscmAcademicyearAndTerm(sscm)) {
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				StuscoreconfMaster old = stuscoreconfMasterService.get(sscm
						.getId());
				if (null != old && null != self
						&& self.getStudentno().equals(old.getStudentno())) {
					if (Const.SCORECONF_STATUS_NEW.equals(old.getStatus())
							|| Const.SCORECONF_STATUS_RESET.equals(old
									.getStatus())) {
						old.setAcademicyear(sscm.getAcademicyear());
						old.setTerm(sscm.getTerm());
						stuscoreconfMasterService.update(old);
						map.put("status", Const.CODE_SUCCESS);
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许修改状态为新建或重置的记录!");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "学年或学期格式不正确!");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void delete() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				stuscoreconfMasterService.deleteMultiple(ids);
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "未指明id!");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void deleteSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				stuscoreconfMasterService.deleteSelfMultiple(self, ids);
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "未指明id!");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void list() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			QueryHelper qh = new QueryHelper();
			qh.setFrom(StuscoreconfMaster.class, "s");
			User user = getSessionUser();
			if ("1".equals(user.getUserType())) {
				qh.setWhere("s.studentno=?", user.getEmployNo());
			} else {
				boolean isAdmin = (Boolean) getSession()
						.getAttribute("isAdmin");
				if (!isAdmin) {
					qh.setWhere("s.teacherno=?", user.getEmployNo());
				}
			}
			if (null != sscm) {
				if (!StringUtils.isBlank(sscm.getStuname())) {
					qh.setWhere("s.stuname like ?", sscm.getStuname() + "%");
				}
				if (!StringUtils.isBlank(sscm.getStudentno())) {
					qh.setWhere("s.studentno like ?", sscm.getStudentno() + "%");
				}
				if (!StringUtils.isBlank(sscm.getGrade())) {
					qh.setWhere("s.grade=?", sscm.getGrade());
				}
				if (!StringUtils.isBlank(sscm.getAcademicyear())) {
					qh.setWhere("s.academicyear=?", sscm.getAcademicyear());
				}
				if (!StringUtils.isBlank(sscm.getTerm())) {
					qh.setWhere("s.term=?", sscm.getTerm());
				}
				if (!StringUtils.isBlank(sscm.getStatus())) {
					qh.setWhere("s.status=?", sscm.getStatus());
				}

			}
			qh.setOrder("s.studentno", QueryHelper.ASC);
			PageResult queryPageResult = stuscoreconfMasterService
					.queryPageResult(qh, page, rows);
			map.put("total", queryPageResult.getCount());
			map.put("rows", queryPageResult.getData());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void exportExcelList() {
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != sscm
						&& self.getStudentno().equals(sscm.getStudentno())) {
					ExportExcelVO vo = stuscoreconfMasterService
							.exportExcelList(sscm);
					if (null != vo) {
						ExcelUtil.exportForScore(getResponse(), vo.getTitle(),
								vo.getHeadTitle(), vo.getDataList());
					}
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			log.error("导出文件写出结果时出异常", err);
			try {
				getResponse().getOutputStream().write("导出文件异常..".getBytes("UTF-8"));
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void get() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				map.put("status", Const.CODE_SUCCESS);
				map.put("sscm", sscm);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void getSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != sscm && null != self
						&& self.getStudentno().equals(sscm.getStudentno())) {
					map.put("status", Const.CODE_SUCCESS);
					map.put("sscm", sscm);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void updateStatus() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm && !StringUtils.isBlank(sscm.getStatus())) {
				StuscoreconfMaster old = stuscoreconfMasterService.get(sscm
						.getId());
				String status = sscm.getStatus();
				if (!Const.SCORECONF_STATUS_NEW.equals(status)
						|| !Const.SCORECONF_STATUS_RESET.equals(status)
						|| !Const.SCORECONF_STATUS_SUBMIT.equals(status)
						|| !Const.SCORECONF_STATUS_VERIFY.equals(status)) {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				} else {
					old.setStatus(status);
					stuscoreconfMasterService.update(old);
					map.put("status", Const.CODE_SUCCESS);
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void submit() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != sscm && null != self
						&& self.getStudentno().equals(sscm.getStudentno())) {
					if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
							|| Const.SCORECONF_STATUS_RESET.equals(sscm
									.getStatus())) {
						List<StuscoreconfDetail> sds = stuscoreconfDetailService
								.getStuscoreconfDetailByMasterId(sscm.getId());
						if (null != sds && !sds.isEmpty()) {
							sscm.setStatus(Const.SCORECONF_STATUS_SUBMIT);
							stuscoreconfMasterService.update(sscm);
							map.put("status", Const.CODE_SUCCESS);
						} else {
							map.put("status", Const.CODE_FAIL);
							map.put("msg", "该表成绩记录为空,请添加成绩记录!");
						}
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许提交状态为新建或重置的记录!");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void reset() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				// 校验该信息是否属于该老师
				if (isAdmin
						|| (null != sscm && user.getEmployNo().equals(
								sscm.getTeacherno()))) {
					if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
							|| Const.SCORECONF_STATUS_SUBMIT.equals(sscm
									.getStatus())) {
						sscm.setStatus(Const.SCORECONF_STATUS_RESET);
						stuscoreconfMasterService.update(sscm);
						map.put("status", Const.CODE_SUCCESS);
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许重置状态为新建或提交的记录!");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void verify() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				// 校验该信息是否属于该老师
				if (isAdmin
						|| (null != sscm && user.getEmployNo().equals(
								sscm.getTeacherno()))) {
					if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
							|| Const.SCORECONF_STATUS_SUBMIT.equals(sscm
									.getStatus())) {
						sscm.setStatus(Const.SCORECONF_STATUS_VERIFY);
						// sscm.setTeacherno(user.getEmployNo());
						// sscm.setTeachername(user.getEmployName());
						sscm.setConfirmdate(new Date());
						stuscoreconfMasterService.update(sscm);
						map.put("status", Const.CODE_SUCCESS);
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许确认状态为新建或提交的记录!");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入的参数有误!");
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void listCourses() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				if (null != sscm) {
					List<CourseInfoStudents> infos = stuscoreconfMasterService
							.getCourseInfoStudentsByStunoAndAcademicyearTerm(
									sscm.getStudentno(),
									sscm.getAcademicyear(), sscm.getTerm());
					map.put("infos", infos);
					map.put("status", Const.CODE_SUCCESS);
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	public void listCoursesSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscm) {
				sscm = stuscoreconfMasterService.get(sscm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != self && null != sscm) {
					List<CourseInfoStudents> infos = stuscoreconfMasterService
							.getCourseInfoStudentsByStunoAndAcademicyearTerm(
									sscm.getStudentno(),
									sscm.getAcademicyear(), sscm.getTerm());
					map.put("infos", infos);
					map.put("status", Const.CODE_SUCCESS);
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 新建时回显数据
	 *
	 * @Title: save
	 * @Description: TODO
	 * @return: void
	 */
	public void load() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students student = studentsService.getStudentByNo(sscm
					.getStudentno());
			if (null == student) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的参数有误!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			sscm.setStuId(student.getStuId());
			sscm.setStuname(student.getStuname());
			sscm.setOrgId(student.getOrgId());
			sscm.setOrgName(student.getOrgName());
			sscm.setMajor(student.getMajor());
			sscm.setClassname(student.getClassname());
			String mobileno = student.getMobileno();
			// 如果学生表中学生号码存在多个,就截取第一个
			if (mobileno != null && mobileno.length() > 11) {
				mobileno = mobileno.substring(0, 11);
			}
			sscm.setTelno(mobileno);
			sscm.setGrade(student.getGrade());
			LearningGuidStudentsList ll = stuscoreconfMasterService
					.getLearningGuidStudentsListByStuno(student.getStudentno());
			if (null != ll) {
				sscm.setTeacherno(ll.getTeacherno());
				sscm.setTeachername(ll.getTeachername());
			}
			sscm.setStatus("未保存");
			map.put("status", Const.CODE_SUCCESS);
			map.put("sscm", sscm);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 学生新建时回显数据
	 *
	 * @Title: saveSelf
	 * @Description: TODO
	 * @return: void
	 */
	public void loadSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Students self = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);
			if (null != self) {
				sscm = new StuscoreconfMaster();
				sscm.setStudentno(self.getStudentno());
				this.load();
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的参数有误!");
				OutUtil.outJson(map, getResponse());
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
			OutUtil.outJson(map, getResponse());
		}
	}

	public StuscoreconfMaster getSscm() {
		return sscm;
	}

	public void setSscm(StuscoreconfMaster sscm) {
		this.sscm = sscm;
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

}
