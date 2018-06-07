package com.zhbit.xuexin.sys.action;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.service.UserService;
import com.zhbit.xuexin.sys.vo.UserVO;

/**
 * 用户管理Action
 * 
 * @ClassName: UserAction
 * @Description: TODO(用户action类)
 * @author 梁日宇
 * @date 2015-12-16 下午9:31:57
 * @version 1.0
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = -9177240967591205763L;

    private String userId;

    private String orgId;

    private String employNo;

    private String employName;

    private Date createTime;

    private String sex;

    private String tell;

    private Integer status;

    private String address;

    private String email;

    private String password;

    private String passwordOld;

    private String userType;

    private String roleIds;// 角色ids

    private String timeForm;

    private String timeTo;

    private int page;// 当前页

    private int rows;// 页面大小

    private String deptIds;// 部门id

    private String roleId;

    private String ids;// 用户ids

    @Resource
    private UserService userService;

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
     * 
     * 
     * @Title: viewAdd
     * @Description: TODO(新增页面)
     * @author Administrator
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
     * 
     * 
     * @Title: viewTab
     * @Description: TODO(tab页面)
     * @author Lry
     * @date 2015-12-20 下午4:40:24
     * @return
     * @return String
     */
    public String viewTab() {
        return "viewTab";
    }

    /**
     * 获取所有用户
     * 
     * @author 梁日宇
     * @return
     */
    public void getUserList() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Page<UserVO> page = userService.getUserList(new Page<UserVO>(req));
            //
            map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * @Title: save
     * @Description: TODO(保存用户)
     * @author 梁日宇
     * @date 2015-12-15 下午11:16:49
     * @return void
     */
    public void save() {
        Map<String, Object> map = Const.getJsonMap();
        try {
        	User user = userService.getUserByNo(employNo);
        	if(null == user){
	            // System.out.println("--"+employName+"=eret"+roleIds+"="+companyId);
	            userService.saveUser(employNo, employName, sex, tell, status, address, email, orgId, roleIds, userType);
	            map.put("status", Const.CODE_SUCCESS);
	            map.put("message", "保存成功");
        	}else{
        		map.put("status", Const.CODE_FAIL);
                map.put("message", "该编号已存在!");
        	}
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "保存失败");
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * 
     * 
     * @Title: getUser
     * @Description: TODO(获取用户对象。)
     * @author 梁日宇
     * @date 2015-12-21 下午10:08:31
     * @return void
     */
    public void getUser() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            UserVO userVO = userService.getUserVOById(userId);
            getRequest().setAttribute("user", userVO);
            map.put("info", userVO);
            map.put("status", 0);
            map.put("message", "获取用户成功");
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", 9999);
            map.put("message", "获取用户失败");
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * 
     * 
     * @Title: edit
     * @Description: TODO(编辑用户)
     * @author 梁日宇
     * @date 2015-12-21 下午10:09:01
     * @return void
     */
    public void edit() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            // System.out.println("--"+employName+"=eret"+roleIds+"="+companyId);
            userService.updateUser(userId, employNo, employName, sex, tell, status, address, email, orgId, roleIds,
                    userType);
            map.put("status", Const.CODE_SUCCESS);
            map.put("message", "修改成功");
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "修改失败");
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * 
     * 
     * @Title: delete
     * @Description: TODO(删除用户)
     * @author 梁日宇
     * @date 2015-12-21 下午10:09:27
     * @return void
     */
    public void delete() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            userService.deleteUser(ids);
            map.put("status", Const.CODE_SUCCESS);
            map.put("message", "删除成功");
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "删除失败");
        }
        OutUtil.outJson(map, getResponse());
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
        	if(null != password && password.length() >=6){
	            User user = getSessionUser();
	            int rs = userService.updatePwd(password,passwordOld,user);
	            if(rs==0){
	                map.put("status", Const.CODE_SUCCESS);
	                map.put("message", "修改成功,请重新登录!");
	            }else{
	                map.put("status", Const.CODE_FAIL);
	                map.put("message", "密码不正确");
	            }
        	}else{
        		map.put("status", Const.CODE_FAIL);
                map.put("message", "新密码不能小于6位");
        	}
            
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "修改失败");
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * 
     * 
     * @Title: pwd
     * @Description: TODO(修改密码页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String pwd() {
        return "pwd";
    }
    
    
    /**
     * @Title: pwd2
     * @Description: TODO(列表修改密码页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String pwd2() {
        return "pwd2";
    }
    
    /**
     * @Title: updatePwd
     * @Description: TODO(列表修改密码。)
     * @author LRY
     * @date 2016-5-18 下午11:23:24
     * @return void
     */
    public void updatePwd2() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            User user = userService.getUserById(userId);
            int rs = userService.updatePwd2(password,user);
            if(rs==0){
                map.put("status", Const.CODE_SUCCESS);
                map.put("message", "修改成功");
            }else{
                map.put("status", Const.CODE_FAIL);
                map.put("message", "密码不正确");
            }
            
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "修改失败");
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * 获取所有用户
     * 
     * @author 梁日宇
     * @return
     */
    public void getUsers() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Page<UserVO> page = userService.getUsers(new Page<UserVO>(req));
            //
            map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEmployNo() {
        return employNo;
    }

    public void setEmployNo(String employNo) {
        this.employNo = employNo;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getTimeForm() {
        return timeForm;
    }

    public void setTimeForm(String timeForm) {
        this.timeForm = timeForm;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
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

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

}