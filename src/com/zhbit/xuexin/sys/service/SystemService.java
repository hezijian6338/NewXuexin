package com.zhbit.xuexin.sys.service;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.domain.User;

/**
 * 系统初始化业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-1-1 下午6:01:51
 * @version 1.0
 */
public interface SystemService {

    /**
     * @Title: initLogin
     * @Description: TODO(用户登录时初始化。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    void initLogin(HttpSession session,User user);
    
    /**
     * @Title: initSystem
     * @Description: TODO(初始化系统常用数据映射。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    void initSystem();
    
    /**
     * @Title: initAdminRoleAuthority
     * @Description: TODO(初始化管理员角色权限。)
     * @author 梁日宇
     * @date 2016-1-3 上午12:32:01
     * @return void
     */
    void initAdminRoleAuthority();
    
    /**
     * @Title: initRoleLink
     * @Description: TODO(初始化角色对应的操作链接 roleId->List[link]。)
     * @author 梁日宇
     * @date 2016-1-1 下午10:57:18
     * @return void
     */
    void initRoleLink();

    /**
     * @Title: initStuLogin
     * @Description: TODO(学生用户登录时初始化。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    void initStuLogin(HttpSession session);
}
