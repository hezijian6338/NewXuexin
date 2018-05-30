package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.domain.UserRole;

/**
 * 用户角色关联表持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-16 下午8:54:54
 * @version 1.0
 */

public interface UserRoleDao {

    /**
     * @Title: saveUserRole
     * @Description: TODO(保存用户角色)
     * @author 梁日宇
     * @date 2015-12-16 下午8:59:32
     * @param userRole
     * @return void
     */
    public void saveUserRole(UserRole userRole);

    /**
     * 
     * 
     * @Title: deleteuserRoleByUserId
     * @Description: TODO(通过用户id删除关联表的信息)
     * @author 梁日宇
     * @date 2015-12-20 下午11:49:12
     * @param userRole
     * @return void
     */
    public void deleteUserRole(UserRole userRole);

    /**
     * 
     * 
     * @Title: getUserRolesByUserId
     * @Description: TODO(根据用户id获取用户角色关联表)
     * @author 梁日宇
     * @date 2015-12-21 上午12:06:06
     * @param userId
     * @return
     * @return List<UserRole>
     */
    public List<UserRole> getUserRolesByUserId(String userId);

    /**
     * 
     * 
     * @Title: getUserByRoleId
     * @Description: TODO(通过关联表角色id获取用户列表)
     * @author 梁日宇
     * @date 2015-12-24 下午9:12:32
     * @param roleId
     * @return
     * @return List<User>
     */
    public List<User> getUserByRoleId(String roleId);

    /**
     * 
     * 
     * @Title: deleteUserRoleByRoleId
     * @Description: TODO(通过角色id删除用户角色。)
     * @author 梁日宇
     * @date 2015-12-24 下午10:29:50
     * @param roleId
     * @return void
     */
    public void deleteUserRoleByRoleId(String roleId);

}
