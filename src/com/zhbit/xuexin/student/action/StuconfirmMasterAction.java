package com.zhbit.xuexin.student.action;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.PageResult;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.*;
import com.zhbit.xuexin.student.service.LearningGuidStudentsListService;
import com.zhbit.xuexin.student.service.StuconfirmDetailService;
import com.zhbit.xuexin.student.service.StuconfirmMasterService;
import com.zhbit.xuexin.student.service.StudentsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("stuconfirmMasterAction")
@Scope("prototype")
public class StuconfirmMasterAction extends BaseAction {
	private static final long serialVersionUID = -4683255814357236686L;
	@Resource
	private StuconfirmMasterService stuconfirmMasterServiceservice;
	@Resource
	private StudentsService studentsService;
	@Resource
	private StuconfirmDetailService stuconfirmDetailService;
	@Resource
	private LearningGuidStudentsListService learningGuidStudentsListService;

	private StuconfirmMaster scfm;

	private int page = 1;
	private int rows = 10;

	public String ids;

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
	 * HZJ 保存函数（保存按钮调用等） 设置并保存主表中的数据
	 * 
	 */

	public void save() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			if (null == scfm) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", " 传入参数有错误");
			}
			// 获取学生个人的信息表
			Students student = studentsService.getStudentByNo(scfm
					.getStudentno());

			// 用学生学号来得到导学名单的信息
			LearningGuidStudentsList learningGuidStudentsList = learningGuidStudentsListService
					.getLearningGuidStudentsListByStuId(student.getStudentno());

			// 判断是否存在
			if (stuconfirmMasterServiceservice.isExist(scfm.getStudentno(),
					scfm.getAcademicyear(), scfm.getTerm())) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "该记录已存在!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			
			// 传入各种参数到scfm主表中
			scfm.setStuId(student.getStuId());
			scfm.setStuname(student.getStuname());

			if (null != learningGuidStudentsList) {
				// 传入学生导师的信息（名称和工号）
				scfm.setTeachername(learningGuidStudentsList.getTeachername());
				scfm.setTeacherno(learningGuidStudentsList.getTeacherno());
			}
			scfm.setOrgId(student.getOrgId());
			scfm.setOrgName(student.getOrgName());
			String mobileno = student.getMobileno();
			// 如果学生表中学生号码存在多个,就截取第一个
			if (mobileno != null && mobileno.length() > 11) {
				mobileno = mobileno.substring(0, 11);
			}
			scfm.setTelno(mobileno);
			scfm.setGrade(student.getGrade());

			// 由于学生信息表中没有当前学生的学期和学年的信息，只能从jsp传入的信息中导入
			scfm.setAcademicyear(scfm.getAcademicyear());
			scfm.setTerm(scfm.getTerm());

			scfm.setMajor(student.getMajor());
			scfm.setClassname(student.getClassname());
			scfm.setCreateTime(new Date());
			scfm.setCreator(user.getUserId());

			scfm.setJck(scfm.getJck());
			scfm.setJckyx(scfm.getJckyx());

			scfm.setBxk(scfm.getBxk());
			scfm.setBxkyx(scfm.getBxkyx());

			scfm.setZyk(scfm.getZyk());
			scfm.setZykyx(scfm.getZykyx());

			scfm.setTsbxkyx(scfm.getTsbxkyx());

			scfm.setTsxxkyx(scfm.getTsxxkyx());

			scfm.setKlyxk(scfm.getKlyxk());

			scfm.setOthercourse(scfm.getOthercourse());
			scfm.setOthercredit(scfm.getOthercredit());

			// 设置状态为_新建_
			scfm.setStatus(Const.CONFIRM_STATUS_NEW);

			// 调用save()函数保存当前主表scfm
			StuconfirmMaster newscfm = stuconfirmMasterServiceservice
					.save(scfm);

			map.put("status", Const.CODE_SUCCESS);
			map.put("newscfm", newscfm);

		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * HZJ 保存学生个人信息函数
	 * 
	 */
	public void saveSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {

			// 找到自己
			Students self = (Students) getSession().getAttribute(
					Const.SESSION_STUDENT);

			// 判断学生自己的信息和scfm主表是否为空，并且检查当前学生的学号和主表的学生学号是否一样（检查是否同一个学生）
			if (null != scfm && null != self
					&& self.getStudentno().equals(scfm.getStudentno())) {
				this.save();
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入参数有误!");
				OutUtil.outJson(map, getResponse());
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
			OutUtil.outJson(map, getResponse());
		}
	}

	/**
	 * HZJ 更新函数，编辑等按钮使用
	 * 
	 */

	public void update() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm) {

				// 把当前主表的信息保存到中转表中
				StuconfirmMaster old = stuconfirmMasterServiceservice.get(scfm
						.getId());

				if (null != old) {
					old.setAcademicyear(scfm.getAcademicyear());
					old.setTerm(scfm.getTerm());
					stuconfirmMasterServiceservice.update(old);
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入参数有误!");
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
	 * HZJ 更新学生自己的信息
	 * 
	 */

	public void updateSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm) {

				// 取得当前学生自己的信息
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);

				// 把当前主表的信息保存到中转位置
				StuconfirmMaster old = stuconfirmMasterServiceservice.get(scfm
						.getId());

				// 检查中转位置是否为空和学生自己的信息是否为空，并且检查当前主表的学生是否和当前登录的学生相同
				if (null != old && null != self
						&& self.getStudentno().equals(old.getStudentno())) {

					// 检查状态是否为新建状态或者重置
					if (Const.CONFIRM_STATUS_NEW.equals(old.getStatus())
							|| Const.CONFIRM_STATUS_RESET.equals(old
									.getStatus())) {

						// 传入jsp中的参数
						old.setAcademicyear(scfm.getAcademicyear());
						old.setTerm(scfm.getTerm());
						old.setJck(scfm.getJck());
						old.setJckyx(scfm.getJckyx());
						old.setBxk(scfm.getBxk());
						old.setBxkyx(scfm.getBxkyx());
						old.setZyk(scfm.getZyk());
						old.setZykyx(scfm.getZykyx());
						old.setTsbxkyx(scfm.getTsbxkyx());
						old.setTsxxkyx(scfm.getTsxxkyx());
						old.setKlyxk(scfm.getKlyxk());
						old.setOthercourse(scfm.getOthercourse());
						old.setOthercredit(scfm.getOthercredit());

						stuconfirmMasterServiceservice.update(old);
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

	/**
	 * HZJ 删除
	 * 
	 */

	public void delete() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				stuconfirmMasterServiceservice.deleteMultiple(ids);
				map.put("status", Const.CODE_SUCCESS);
				map.put("message", "删除成功");
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "没有指明id");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("message", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	public void deleteSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != ids) {
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				stuconfirmMasterServiceservice.deleteSelfMultiple(self, ids);
				map.put("status", Const.CODE_SUCCESS);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "未指明id");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * HZJ 获取列表
	 * 
	 */

	public void list() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			QueryHelper qh = new QueryHelper();
			qh.setFrom(StuconfirmMaster.class, "s");
			User user = getSessionUser();

			// 判定登录人员是老师还是学生
			if ("1".equals(user.getUserType())) {
				qh.setWhere("s.studentno = ?", user.getEmployNo());
			} else {
				boolean isAdmin = (Boolean) getSession()
						.getAttribute("isAdmin");
				if (!isAdmin) {
					qh.setWhere("s.teacherno = ?", user.getEmployNo());
				}
			}

			if (null != scfm) {
				if (!StringUtils.isBlank(scfm.getStuname())) {
					qh.setWhere("s.stuname like ?", scfm.getStuname() + "%");
				}
				if (!StringUtils.isBlank(scfm.getStudentno())) {
					qh.setWhere("s.studentno like ?", scfm.getStudentno() + "%");
				}
				if (!StringUtils.isBlank(scfm.getGrade())) {
					qh.setWhere("s.grade = ?", scfm.getGrade());
				}
				if (!StringUtils.isBlank(scfm.getAcademicyear())) {
					qh.setWhere("s.academicyear like ?", scfm.getAcademicyear()
							+ "%");
				}
				if (!StringUtils.isBlank(scfm.getTerm())) {
					qh.setWhere("s.term = ?", scfm.getTerm());
				}
				if (!StringUtils.isBlank(scfm.getStatus())) {
					qh.setWhere("s.status = ?", scfm.getStatus());
				}
			}
			qh.setOrder("s.studentno", QueryHelper.ASC);
			PageResult queryPageResult = stuconfirmMasterServiceservice
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

	/**
	 * Title: exportExcelList Description: 导出按钮调用函数
	 */
	public void exportExcelList() {
		try {
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (self.getStudentno().equals(scfm.getStudentno())) {
					ExportExcelVO vo = stuconfirmMasterServiceservice
							.exportExcelList(scfm);
					ExcelUtil.exportForConfirm(getResponse(), vo.getTitle(),
							vo.getHeadTitle(), vo.getDataList());
				}
			}
		} catch (Exception err) {
			log.error("导出文件写出结果时出异常", err);
			err.printStackTrace();
		}
	}

	/**
	 * HZJ 获取scfm主表自己
	 * 
	 */

	public void get() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				map.put("status", Const.CODE_SUCCESS);
				map.put("scfm", scfm);
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * HZJ 获取从表信息并且验证学生信息是否对应当前的主表信息
	 * 
	 */

	public void getSelf() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != scfm && null != self
						&& self.getStudentno().equals(scfm.getStudentno())) {
					map.put("status", Const.CODE_SUCCESS);
					map.put("scfm", scfm);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入参数有错!");
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
	 * HZJ 刷新状态信息
	 * 
	 */

	public void updateStatus() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm && !StringUtils.isBlank(scfm.getStatus())) {
				StuconfirmMaster old = stuconfirmMasterServiceservice.get(scfm
						.getId());
				String status = scfm.getStatus();
				if (!Const.CONFIRM_STATUS_NEW.equals(status)
						|| !Const.CONFIRM_STATUS_RESET.equals(status)
						|| !Const.CONFIRM_STATUS_SUBMIT.equals(status)
						|| !Const.CONFIRM_STATUS_VERIFY.equals(status)) {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入参数有误!");
				} else {
					old.setStatus(status);
					stuconfirmMasterServiceservice.update(old);
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
	 * HZJ 提交按钮的调用函数
	 * 
	 */

	public void submit() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != scfm && null != self
						&& self.getStudentno().equals(scfm.getStudentno())) {

					// 判定状态是否为新建或者重置状态
					if (Const.CONFIRM_STATUS_NEW.equals(scfm.getStatus())
							|| Const.CONFIRM_STATUS_RESET.equals(scfm
									.getStatus())) {

						List<StuconfirmDetail> sds = stuconfirmDetailService
								.getStuconfirmDetailByMasterId(scfm.getId());
						if (null != sds && !sds.isEmpty()) {

							// 判断存在数据和不为空的时候，提交成功，更改状态
							scfm.setStatus(Const.CONFIRM_STATUS_SUBMIT);

							stuconfirmMasterServiceservice.update(scfm);
							map.put("status", Const.CODE_SUCCESS);
						} else {
							map.put("status", Const.CODE_FAIL);
							map.put("msg", "该记录为空 请添加");
						}
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许确认状态为新建的记录!");
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入参数有误!");
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
	 * HZJ 重置按钮的使用函数
	 * 
	 */

	public void reset() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				if (isAdmin
						|| (null != scfm && user.getEmployNo().equals(
								scfm.getTeacherno()))) {
					// 只能重置是提交了的信息
					if (Const.CONFIRM_STATUS_SUBMIT.equals(scfm.getStatus())
							|| Const.CONFIRM_STATUS_NEW
									.equals(scfm.getStatus())) {

						// 更新当前的状态
						scfm.setStatus(Const.CONFIRM_STATUS_RESET);

						stuconfirmMasterServiceservice.update(scfm);
						map.put("status", Const.CODE_SUCCESS);
					} else {
						map.put("status", Const.CODE_FAIL);
						map.put("msg", "只允许重置状态为提交的记录!");
					}
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的参数有误!");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常.");

		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * HZJ 确认状态
	 * 
	 */

	public void verify() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			User user = getSessionUser();
			boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				if (isAdmin
						|| (null != scfm && user.getEmployNo().equals(
								scfm.getTeacherno()))) {
					if (null != scfm) {

						// 检查状态是否为新建或者提交
						if (Const.CONFIRM_STATUS_SUBMIT
								.equals(scfm.getStatus())
								|| Const.CONFIRM_STATUS_NEW.equals(scfm
										.getStatus())) {

							// 设置状态为确认状态
							scfm.setStatus(Const.CONFIRM_STATUS_VERIFY);

							// 传入当前确认的老师的工号和名字
							scfm.setTeacherno(user.getEmployNo());
							scfm.setTeachername(user.getEmployName());

							// 传入当期确认的时间
							scfm.setConfirmdate(new Date());

							stuconfirmMasterServiceservice.update(scfm);
							map.put("status", Const.CODE_SUCCESS);
						} else {
							map.put("status", Const.CODE_FAIL);
							map.put("msg", "只允许确认状态为提交的记录!");
						}
					}
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "传入参数有误!");
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
			List<Students> classname = stuconfirmMasterServiceservice
					.getStudentClassnameByStuno(scfm.getStudentno());
			String classnameString = classname.get(0).getClassname();
			if (null != scfm) {
				scfm = stuconfirmMasterServiceservice.get(scfm.getId());
				if (null != scfm) {
					// List<CourseInfoStudents> infos =
					// stuconfirmMasterServiceservice
					// .getCourseInfoStudentsByStuno(scfm.getStudentno());
					List<CourseInfo> infos = stuconfirmMasterServiceservice
							.getCoursesByStuTermAndAcayear(scfm.getTerm(),
									scfm.getAcademicyear(), classnameString);
					map.put("infos", infos);
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
	 * HZJ 根据学生自己的学年学期和班级获取当前学生可选的课程名字
	 * 
	 */

	public void listCoursesSelf() {
		Map<String, Object> map = Const.getJsonMap();
		scfm = stuconfirmMasterServiceservice.get(scfm.getId());
		// 根据学生的学号获取学生在student表中的信息列表
		List<Students> classname = stuconfirmMasterServiceservice
				.getStudentClassnameByStuno(scfm.getStudentno());

		// 获取当前学生的班级信息
		String classnameString = classname.get(0).getClassname();
		try {
			if (null != scfm) {
				Students self = (Students) getSession().getAttribute(
						Const.SESSION_STUDENT);
				if (null != self) {
					// List<CourseInfoStudents> infos =
					// stuconfirmMasterServiceservice
					// .getCourseInfoStudentsByStuno(self.getStudentno());
					List<CourseInfo> infos = stuconfirmMasterServiceservice
							.getCoursesByStuTermAndAcayear(scfm.getTerm(),
									scfm.getAcademicyear(), classnameString);
					
					List<CourseInfo> ct = stuconfirmMasterServiceservice.getCourseTypeList() ;
					
					map.put("ct", ct);
					map.put("infos", infos);
					map.put("status", Const.CODE_SUCCESS);
				}
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
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
			Students student = studentsService.getStudentByNo(scfm
					.getStudentno());
			if (null == student) {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "传入的参数有误!");
				OutUtil.outJson(map, getResponse());
				return;
			}
			scfm.setStuId(student.getStuId());
			scfm.setStuname(student.getStuname());
			scfm.setOrgId(student.getOrgId());
			scfm.setOrgName(student.getOrgName());
			scfm.setMajor(student.getMajor());
			scfm.setClassname(student.getClassname());
			String mobileno = student.getMobileno();
			// 如果学生表中学生号码存在多个,就截取第一个
			if (mobileno != null && mobileno.length() > 11) {
				mobileno = mobileno.substring(0, 11);
			}
			scfm.setTelno(mobileno);
			scfm.setGrade(student.getGrade());
			LearningGuidStudentsList learningGuidStudentsList = stuconfirmMasterServiceservice
					.getLearningGuidStudentsListByStuno(student.getStudentno());
			if (null != learningGuidStudentsList) {
				scfm.setTeachername(learningGuidStudentsList.getTeachername());
				scfm.setTeacherno(learningGuidStudentsList.getTeacherno());
			}
			scfm.setStatus("未保存");
			map.put("status", Const.CODE_SUCCESS);
			map.put("scfm", scfm);
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
				scfm = new StuconfirmMaster();
				scfm.setStudentno(self.getStudentno());
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

	public void creditSelectvalue() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			
		} catch (Exception err) {

		}
		OutUtil.outJson(map, getResponse());
	}

	public StuconfirmMaster getScfm() {
		return scfm;
	}

	public void setScfm(StuconfirmMaster scfm) {
		this.scfm = scfm;
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
