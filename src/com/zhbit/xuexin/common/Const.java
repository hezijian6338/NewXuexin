package com.zhbit.xuexin.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.zhbit.xuexin.common.domain.vo.AuthorityTypeVO;
import com.zhbit.xuexin.common.utils.PropUtil;
import com.zhbit.xuexin.domain.Authority;

/**
 * 
 * 常量和常量方法定义
 * 
 * @author 梁日宇
 * @version 1.0
 */
public class Const {
	
	// ------------------------系统常用常量
	
	public static final String SESSION_USER = "session_user";//当前用户session
	public static final String SESSION_STUDENT = "session_student";//当前学生session
	public static final String SESSION_ROLE = "session_role";//当前用户所属角色session
	public static final String SESSION_URL_TYPE = "session_url_tpye";//链接所对应的操作类型权限
	public static final String CODE_SUCCESS = "ok"; // 成功
	public static final String CODE_FAIL = "fail"; // 失败
	public static final String DATA_EXIST="exist";//查询数据库中是否存下记录
	public static final int CODE_NO_SESSION = 5; // SESSION失效
	public static final int CODE_NO_POWER = 6; // 没有权限
	
	public static final int CODE_UNKOWN_ERR = 9999;
	
	public static final int CONST_STATE = 1;//启用
	public static final String CONST_STATE_STR = "1";//启用
	
	public static final String REQUEST_URL = "req_url"; // request保存用户的请求连接
	
	public static final String SCORECONF_STATUS_NEW = "新建";
	public static final String SCORECONF_STATUS_SUBMIT = "提交";
	public static final String SCORECONF_STATUS_VERIFY = "确认";
	public static final String SCORECONF_STATUS_RESET = "重置";
	
	// ------------------------动态权限控制存贮
    public static final List<String> FREE_LINK = new ArrayList<String>();// 无限制链接
    public static final List<String> ALL_LINK = new ArrayList<String>();// 所有链接
    public static final List<String> BASE_LINK = new ArrayList<String>();// 系统基础功能链接
    public static final Map<String, String> ENTRANCE_MODNAME_MAP = new HashMap<String, String>();// 入口链接->
    public static final Map<String, List<String>> ROLE_LINKS_MAP = new HashMap<String, List<String>>();// 角色代号->模块名称
    public static final Map<String, List<Authority>> ROLE_POWER_MAP = new HashMap<String, List<Authority>>();// 角色代号->权限对象
    public static final Map<String, String> LINK_MODULE_MAP = new HashMap<String, String>();// 链接->权限名
    public static final Map<String, Boolean> LINK_PAGE_MAP = new HashMap<String, Boolean>();// 链接->是否页面
    public static List<Authority> STU_AUTH = new ArrayList<Authority>();//学生权限
    public static List<Authority> STU_MENU = new ArrayList<Authority>();//学生菜单
    public static final Map<String, String> ORG_ID_MAP = new HashMap<String, String>();// 机构名称->机构id(方便导入时使用)
    
    public static final String ZKZNO_REG = "^\\w+$";//高考成绩准考证匹配正则
    public static final String PHOTO_REG = "^.+\\.(jpg|jpeg|png)$";//头像文件匹配正则
  //新建字段 Create by zengjianqi
  	public static final String CONFIRM_STATUS_NEW = "新建" ;
  	public static final String CONFIRM_STATUS_RESET = "重置" ;
  	public static final String CONFIRM_STATUS_SUBMIT = "提交" ;
  	public static final String CONFIRM_STATUS_VERIFY = "确认" ;
	/**
	 * 保存当前访问的Action（权限路径）
	 */
	public static String currentAction;
	
	/**
	 * 默认父级id
	 */
	//public static int defult_Pid = -1;
	public static String defult_Pid = "defaultpid";
	
	/**
	 * 系统管理员角色id
	 */
	//public static int admin_roleId = 1;
	public static String admin_roleId = "adminroleid";
	public static String admin_roleno = "1001";
	
	/**
	 * 系统管理员id
	 */
	//public static int admin_userId = 1;
	public static String admin_userId = "adminuserid";
	
	public static String admin_userno = "admin";
	
	/**
	 * 学生角色名称
	 */
	public static String student_roleName = "学生";
	
	/**
	 * 默认密码
	 */
	public static String defult_password="666666";
	public static String stu_defult_password="888888";
	
	public static String[] user_type={"0","1","2"};//用户类型 0-教师，1-学生，3-其他
	
	/**
	 * 文件上传物理路径
	 */
	public static String student_attendance; //学生考勤登记表保存路径
	public static String student_photo; //学生头像保存路径
	public static String teacher_photo; //头像保存路径
	public static String student_certificate; //学生毕业相关证书附件保存路径
	public static String student_contest; //学生学科竞赛附件保存路径
	
	/**
	 * 文件上传服务器路径
	 */
	public static String studentPhoto="WEB-INF" + File.separator +"student_photo"; //学生头像保存路径
	public static String teacherPhoto="WEB-INF" + File.separator +"teacher_photo"; //头像保存路径
	public static String DEFAULT_PHOTO_PATH="photo" + File.separator + "default.jpg"; //头像保存路径
    
	/**
     * 获取角色对应的操作链接 roleId->List[link]
     */
    public static Map<String, List<String>> role_links = new HashMap<String, List<String>>();
    /**
     * 获取用户对应的角色 userId->List[roleId]
     */
    //public static Map<Integer, List<Integer>> user_roles = new HashMap<Integer, List<Integer>>();
	
    //权限类型常量
	public static Map<Integer, String> authTypeMap = new LinkedHashMap<Integer, String>();
	public static List<AuthorityTypeVO> authorityType = new ArrayList<AuthorityTypeVO>();
    
	
	
	/**
	 * 获取初始化Action Map
	 * 
	 * @return
	 */
	public static Map<String, Object> getJsonMap() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("status", CODE_FAIL);
		return map;
	}
	
	/**
     * 获取一个UUID
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	/**
	 * 初始化
	 */
	static {
	    Map<String, String> m = PropUtil.read();
	    student_attendance = m.get("STUDENT_ATTENDANCE");
		new File(student_attendance).mkdirs();
		student_photo = m.get("STUDENT_PHOTO");
        new File(student_photo).mkdirs();
        teacher_photo = m.get("TEACHER_PHOTO");
        new File(teacher_photo).mkdirs();
        student_certificate = m.get("STUDENT_CERTIFICATE");
        new File(student_certificate).mkdirs();
        student_contest = m.get("STUDENT_CONTEST");
        new File(student_contest).mkdirs();
		//权限类型初始化
		authTypeMap.put(0, "菜单");
		authTypeMap.put(1, "新增");
		authTypeMap.put(2, "修改");
		authTypeMap.put(3, "查询");
		authTypeMap.put(4, "删除");
		authTypeMap.put(5, "导入");
		authTypeMap.put(6, "导出");
		authTypeMap.put(7, "授权");
		for (Entry<Integer, String> entry : authTypeMap.entrySet()) {
		    AuthorityTypeVO vo =new AuthorityTypeVO();
		    vo.setTypeId(entry.getKey());
		    vo.setTypeName(entry.getValue());
		    authorityType.add(vo);
		  }
	}
}
