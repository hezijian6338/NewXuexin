package com.zhbit.xuexin.student.action;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.StuscoreconfDetailService;
import com.zhbit.xuexin.student.service.StuscoreconfMasterService;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

@Controller("stuscoreconfDetailAction")
@Scope("prototype")
public class StuscoreconfDetailAction extends BaseAction {

	@Resource
	private StuscoreconfDetailService stuscoreconfDetailService;
	@Resource
	private StuscoreconfMasterService stuscoreconfMasterService;
	private StuscoreconfDetail sscd;
	private String ids;
	private String coursestuId;
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

	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			// 检查参数
			if (checkRetakeflagAndScore(sscd)) {
				if (null != sscd && !StringUtils.isBlank(sscd.getMasterId())
						&& !StringUtils.isBlank(coursestuId)) {
					StuscoreconfMaster stuscoreconfMaster = stuscoreconfMasterService
							.get(sscd.getMasterId());
					CourseInfoStudents cs = stuscoreconfDetailService
							.getCourseInfoStudentsByCourseId(coursestuId);
					if (null != stuscoreconfMaster && null != cs) {
						// 判断该学生是否选了该课程
						if (stuscoreconfDetailService.checkCourseIsSelected(
								stuscoreconfMaster.getStudentno(), cs.getId())) {
							// 判断学年学期是否匹配
							if (stuscoreconfMaster.getAcademicyear().equals(
									cs.getAcademicYear())
									&& stuscoreconfMaster.getTerm().equals(
											cs.getTerm())) {
								// 判断是否存在
								if (!stuscoreconfDetailService.isExist(
										sscd.getMasterId(), cs.getCoursename())) {
									sscd.setCoursecode(cs.getCoursecode());
									sscd.setCoursename(cs.getCoursename());
									sscd.setEmployNo(cs.getEmployNo());
									sscd.setEmployName(cs.getEmployName());
									sscd.setCourseType(cs.getCourseType());
									sscd.setSelectedCourseNo(cs.getSelectedCoureNo());
									sscd.setCreateTime(new Date());
									sscd.setCreator(user.getUserId());
									stuscoreconfDetailService.save(sscd);
									map.put("status", Const.CODE_SUCCESS);
								} else {
									map.put("status", Const.CODE_FAIL);
									map.put("msg", "该考试成绩记录已存在.");
								}
							} else {
								map.put("status", Const.CODE_FAIL);
								map.put("msg", "该学生课程学年学期不匹配.");
							}
						} else {
							map.put("status", Const.CODE_FAIL);
							map.put("msg", "该学生未选择该课程.");
						}

					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "参数异常.");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "参数异常.");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "参数异常.");
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	private boolean checkRetakeflagAndScore(StuscoreconfDetail sscd) {
		if (null != sscd) {
			String scoreReg = "^(优秀|良好|中等|及格|不及格|旷考|100|0|[1-9]\\d?|([A-E](\\+|-)?))$";
			String retakeflag = sscd.getRetakeflag();
			String finalScore = sscd.getFinalscore();
			String resitscore = sscd.getResitscore();
			if (null != retakeflag && null != finalScore) {
				if (retakeflag.matches("^[01]$")
						&& finalScore.matches(scoreReg)) {
					if ("1".equals(retakeflag)) {
						return resitscore != null
								&& resitscore.matches(scoreReg);
					}
					sscd.setResitscore(null);
					return true;
				}
			}
		}
		return false;
	}

	public void saveSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscd) {
				StuscoreconfMaster sscm = stuscoreconfMasterService.get(sscd
						.getMasterId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				// System.out.println(sscm.getStatus());
				// 判断当前主表能不能被修改
				if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
						|| Const.SCORECONF_STATUS_RESET
								.equals(sscm.getStatus())) {
					// 判断当前主表信息是否属于该学生
					if (null != self && null != sscm
							&& self.getStudentno().equals(sscm.getStudentno())) {
						this.save();
					}
				}

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
			if (checkRetakeflagAndScore(sscd)) {
				StuscoreconfDetail old = stuscoreconfDetailService.get(sscd
						.getId());
				if (null != old) {
					old.setRetakeflag(sscd.getRetakeflag());
					old.setFinalscore(sscd.getFinalscore());
					old.setResitscore(sscd.getResitscore());
					old.setMemo(sscd.getMemo());
					stuscoreconfDetailService.update(old);
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

	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			// 检查参数
			if (checkRetakeflagAndScore(sscd)) {
				StuscoreconfDetail old = stuscoreconfDetailService.get(sscd
						.getId());
				if (null != old) {
					StuscoreconfMaster sscm = stuscoreconfMasterService.get(old
							.getMasterId());
					Students self = (Students) getSession().getAttribute(
							Const.SESSION_STUDENT);
					// 判断当前主表能不能被修改
					if (Const.SCORECONF_STATUS_NEW.equals(sscm.getStatus())
							|| Const.SCORECONF_STATUS_RESET.equals(sscm
									.getStatus())) {
						// 判断当前主表信息是否属于该学生
						if (null != self
								&& null != sscm
								&& self.getStudentno().equals(
										sscm.getStudentno())) {
							old.setRetakeflag(sscd.getRetakeflag());
							old.setFinalscore(sscd.getFinalscore());
							old.setResitscore(sscd.getResitscore());
							old.setMemo(sscd.getMemo());
							stuscoreconfDetailService.update(old);
							map.put("status", Const.CODE_SUCCESS);

						}
					}
				}
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
				stuscoreconfDetailService.deleteMultiple(ids);
				map.put("status", Const.CODE_SUCCESS);
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
				stuscoreconfDetailService.deleteSelfMultiple(self, ids);
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
			if (null != sscd && !StringUtils.isBlank(sscd.getMasterId())) {
				QueryHelper qh = new QueryHelper();
				qh.setFrom(StuscoreconfDetail.class, "s");
				qh.setWhere("s.masterId=?", sscd.getMasterId());
				qh.setOrder("s.coursename", QueryHelper.ASC);
//				qh.setOrder("s.retakeflag", QueryHelper.ASC);
				PageResult pageResult = stuscoreconfDetailService
						.queryPageResult(qh, page, rows);
				map.put("status", Const.CODE_SUCCESS);
				map.put("total", pageResult.getCount());
				map.put("rows", pageResult.getData());
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void listSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscd && !StringUtils.isBlank(sscd.getMasterId())) {
				StuscoreconfMaster stuscoreconfMaster = stuscoreconfMasterService
						.get(sscd.getMasterId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != self
						&& null != stuscoreconfMaster
						&& self.getStudentno().equals(
								stuscoreconfMaster.getStudentno())) {
					QueryHelper qh = new QueryHelper();
					qh.setFrom(StuscoreconfDetail.class, "s");
					qh.setWhere("s.masterId=?", sscd.getMasterId());
					qh.setOrder("s.coursename", QueryHelper.ASC);
//					qh.setOrder("s.retakeflag", QueryHelper.ASC);
					PageResult pageResult = stuscoreconfMasterService
							.queryPageResult(qh, page, rows);
					map.put("status", Const.CODE_SUCCESS);
					map.put("total", pageResult.getCount());
					map.put("rows", pageResult.getData());
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void get() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != sscd && !StringUtils.isBlank(sscd.getId())) {
				sscd = stuscoreconfDetailService.get(sscd.getId());
				map.put("status", Const.CODE_SUCCESS);
				map.put("sscd", sscd);
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
			if (null != sscd && !StringUtils.isBlank(sscd.getId())) {
				sscd = stuscoreconfDetailService.get(sscd.getId());
				if (null != sscd) {
					StuscoreconfMaster stuscoreconfMaster = stuscoreconfMasterService
							.get(sscd.getMasterId());
					Students self = (Students) getSession().getAttribute(
							Const.SESSION_STUDENT);
					if (null != self
							&& null != stuscoreconfMaster
							&& self.getStudentno().equals(
									stuscoreconfMaster.getStudentno())) {
						sscd = stuscoreconfDetailService.get(sscd.getId());
						map.put("status", Const.CODE_SUCCESS);
						map.put("sscd", sscd);
					}
				}
			}

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");
		}
		OutUtil.outJson(map, getResponse());
	}

	public StuscoreconfDetail getSscd() {
		return sscd;
	}

	public void setSscd(StuscoreconfDetail sscd) {
		this.sscd = sscd;
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

	public String getCoursestuId() {
		return coursestuId;
	}

	public void setCoursestuId(String coursestuId) {
		this.coursestuId = coursestuId;
	}

}
