package com.zhbit.xuexin.sys.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.common.utils.SecurityUtil;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.StudentsService;
import com.zhbit.xuexin.sys.service.LoginLogService;
import com.zhbit.xuexin.sys.service.SystemService;
import com.zhbit.xuexin.sys.service.UserAuthorityService;
import com.zhbit.xuexin.sys.service.UserService;
import com.zhbit.xuexin.sys.utils.TreeUtil;

/**
 * 
 * 系统模块
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6730434273313510704L;

	private String employNo;// 用户编号
	private String password;// 用户密码
	private Integer userType;// 用户类型

	HttpSession session = getSession();

	@Resource
	private UserService userService;
	@Resource
	private UserAuthorityService userAuthorityService;
	@Resource
	private SystemService systemService;
	@Resource
	private LoginLogService loginLogService;
	@Resource
	private StudentsService studentsService;

	/**
	 * 进入登录页面
	 * 
	 * @return
	 */
	public String goLogin() {
		return "goLogin";
	}

	/**
	 * 左侧菜单
	 * 
	 * @return
	 */
	public String leftMenu() {
		return "leftMenu";
	}

	/**
	 * 显示内容界面
	 * 
	 * @return
	 */
	public String mainBody() {
		return "mainBody";
	}

	/**
	 * 显示内容界面
	 * 
	 * @return
	 */
	public String main() {
		return "main";
	}

	// 登录方法
	public void login() {
		Map<String, Object> map = Const.getJsonMap();
		int isSuccess = 1;// 1-登录失败 0-登录成功
		User user = null;
		try {
			if (userType == 1 || userType == 3) {// 教师用户
				if ("".equals(employNo) || "".equals(password)) {
					addActionError("用户名或者密码不能为空！");
					map.put("code", Const.CODE_FAIL);
				} else {
					user = userService.getUserByNo(employNo);
					if (user == null
							|| !SecurityUtil.GetMD5Code(password).equals(
									user.getPassword())) {
						addActionError("用户名或者密码错误！");
						map.put("code", Const.CODE_FAIL);
						map.put("msg", "用户名或者密码错误！");
					} else {
						isSuccess = 0;
						session.setAttribute(Const.SESSION_USER, user);
						// 用户全部权限，用于权限拦截
						List<Authority> list = userAuthorityService
								.getUserAuthorityByNo(employNo);
						session.setAttribute("userAuthorities", list);
						// 用户的菜单权限
						List<Authority> menulist = userAuthorityService
								.getUserAuthorityOfMenuByNo(employNo);
						// 将菜单级别的权限处理
						TreeUtil tatju = new TreeUtil(menulist);
						// 先将权限信息构造成树形结构
						// 将树形结构转换成json数据，并放入session
						session.setAttribute("treeMenu", tatju.getTreeMenu());
						systemService.initLogin(session, user);
						List<Role> roles = (List<Role>) session.getAttribute(Const.SESSION_ROLE);// 获取用户归属角色
						session.setAttribute("isAdmin", isAdmin(roles));
						session.setAttribute("isStu", false);
						map.put("code", Const.CODE_SUCCESS);
					}
				}
			} else {
				if ("".equals(employNo) || "".equals(password)) {
					addActionError("用户名或者密码不能为空！");
					map.put("code", Const.CODE_FAIL);
					
				} else {
					user = studentsService.getStuToUser(employNo);

					if (user == null
							|| !SecurityUtil.GetMD5Code(password).equals(
									user.getPassword())) {
						addActionError("用户名或者密码错误！");
						map.put("code", Const.CODE_FAIL);
						map.put("msg", "用户名或者密码错误！");
					} else {
						isSuccess = 0;
						session.setAttribute(Const.SESSION_USER, user);
						session.setAttribute(Const.SESSION_STUDENT,
								studentsService.getStudentByNo(employNo));
						session.setAttribute("userAuthorities", Const.STU_AUTH);
						// 将菜单级别的权限处理
						TreeUtil tatju = new TreeUtil(Const.STU_MENU);
						// 先将权限信息构造成树形结构
						// 将树形结构转换成json数据，并放入session
						session.setAttribute("treeMenu", tatju.getTreeMenu());
						systemService.initStuLogin(session);
						session.setAttribute("isAdmin", false);
						session.setAttribute("isStu", true);
						map.put("code", Const.CODE_SUCCESS);

					}
				}
			}
			String parentOrgId = user == null ? "" : user.getParentOrgId();
			loginLogService.saveLog(employNo, getRequest().getRemoteAddr(),
					isSuccess, parentOrgId);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", Const.CODE_FAIL);
			loginLogService.saveLog(employNo, getRequest().getRemoteAddr(),
					isSuccess, "");
		}
		OutUtil.outJson(map, getResponse());
	}
	/**
	 * 判断是否含有管理员角色
	 * @param roles
	 * @return
	 * 	余锡鸿 2017/4/8
	 */
	private boolean isAdmin(List<Role> roles){
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				if (role.getRoleId().equals(Const.admin_roleId)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 登出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String logout() throws IOException {
		HttpServletRequest req = getRequest();
		if (session.getAttribute(Const.SESSION_USER) != null) {
			session.removeAttribute(Const.SESSION_USER);
		}
		req.getSession().invalidate();
		getResponse().sendRedirect("loginAction_goLogin.action");
		return null;
	}

	public String getEmployNo() {
		return employNo;
	}

	public void setEmployNo(String employNo) {
		this.employNo = employNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
