package com.zhbit.xuexin.sys.service;

import java.util.List;

import com.zhbit.xuexin.domain.User;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-24 下午9:07:02
 * @version 1.0
 */
public interface UserRoleService {

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
    List<User> getUserByRoleId(String roleId);

    /**
     * 
     * 
     * @Title: saveUserRole
     * @Description: TODO(保存用户角色。)
     * @author 梁日宇
     * @date 2015-12-24 下午10:12:31
     * @param uids
     * @param roleId
     * @return void
     */
    void saveUserRole(String uids, String roleId);

}
