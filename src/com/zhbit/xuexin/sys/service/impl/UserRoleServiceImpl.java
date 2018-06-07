package com.zhbit.xuexin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.domain.UserRole;
import com.zhbit.xuexin.sys.dao.RoleDao;
import com.zhbit.xuexin.sys.dao.UserDao;
import com.zhbit.xuexin.sys.dao.UserRoleDao;
import com.zhbit.xuexin.sys.service.UserRoleService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-24 下午9:08:17
 * @version 1.0
 */
@Service("userRoleService")
@Transactional(readOnly = true)
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    @Qualifier("userRoleDao")
    private UserRoleDao userRoleDao;
    
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;
    
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

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
    @Override
    public List<User> getUserByRoleId(String roleId) {
        return userRoleDao.getUserByRoleId(roleId);
    }

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
    @Override
    @Transactional(readOnly=false)
    public void saveUserRole(String uids, String roleId) {
        String[] userIds = uids.split(",");
        //清除旧数据再保存     
        userRoleDao.deleteUserRoleByRoleId(roleId);
        Role role = roleDao.getRoleById(roleId);
        if(userIds!=null){
            for(String userId:userIds){
                UserRole userRole = new UserRole();
                userRole.setRole(role);
                userRole.setUser(userDao.getUserById(userId));
                userRoleDao.saveUserRole(userRole);
            }
        }
    }
}
