package com.zhbit.xuexin.sys.dao;

import java.util.List;
import java.util.Map;

import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;

/**
 * 系统初始化持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-1-1 下午10:33:56
 * @version 1.0
 */
public interface SystemDao {

    /**
     * @Title: getRoleLink
     * @Description: TODO(获取角色对应的操作链接 roleId->List[link]。)
     * @author 梁日宇
     * @date 2016-1-1 下午10:57:18
     * @return Map<String,List<String>>
     */
    Map<String, List<String>> getRoleLink();

    /**
     * @Title: getUserRoles
     * @Description: TODO(获取当前用户的归属角色。)
     * @author 梁日宇
     * @date 2016-1-2 上午12:13:02
     * @param user
     * @return List<Role>
     */
    List<Role> getUserRoles(User user);
    
    List<Role> getUserRoles2();

    /**
     * @Title: initAdminRoleAuthority
     * @Description: TODO(初始化管理员角色权限。)
     * @author 梁日宇
     * @date 2016-1-3 上午12:32:01
     * @return void
     */
    void initAdminRoleAuthority();

    /**
     * @Title: getUserAuthorities
     * @Description: TODO(通过用户id获取用户所有权限。)
     * @author 梁日宇
     * @date 2016-1-3 下午10:30:41
     * @return
     * @return List<Authority>
     */
    List<Authority> getUserAuthorities(String userId);

    /**
     * @Title: getStudentAuthorities
     * @Description: TODO(获取学生角色权限。)
     * @author liangriyu
     * @date 2016-3-1 下午10:39:16
     * @return
     * @return List<Authority>
     */
    List<Authority> getStudentAuthorities();

    /**
     * @Title: getStudentMenu
     * @Description: TODO(获取学生角色菜单。)
     * @author liangiryu
     * @date 2016-3-1 下午10:40:17
     * @return
     * @return List<Authority>
     */
    List<Authority> getStudentMenu();

    /**
     * @Title: getStuRoles
     * @Description: TODO(获取学生角色。)
     * @author liangriyu
     * @date 2016-3-2 上午12:11:07
     * @return
     * @return List<Role>
     */
    List<Role> getStuRoles();

    /**
     * @Title: getOrgs
     * @Description: TODO(获取机构列表。)
     * @author liangriyu
     * @date 2016-3-6 下午11:45:18
     * @return
     * @return List<Organization>
     */
    List<Organization> getOrgs();
    
}
