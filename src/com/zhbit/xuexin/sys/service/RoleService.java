package com.zhbit.xuexin.sys.service;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Role;

/**
 * 角色管理业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-17 下午11:32:09
 * @version 1.0
 */
public interface RoleService {

    /**
     * @Title: getRolesByComId
     * @Description: TODO(通过公司id获取角色列表)
     * @author 梁日宇
     * @date 2015-12-17 下午11:33:58
     * @param companyId
     * @return
     * @return List<Role>
     */

    /**
     * 
     * 
     * @Title: getRoleList
     * @Description: TODO(获取角色列表)
     * @author 梁日宇
     * @date 2015-12-22 上午12:10:44
     * @param page
     * @return
     * @return Page<Role>
     */
    Page<Role> getRoleList(Page<Role> page);

    /**
     * 
     * 
     * @Title: getRoleById
     * @Description: TODO(获取角色。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:08:39
     * @param roleId
     * @return
     * @return Role
     */
    Role getRoleById(String roleId);

    /**
     * 
     * 
     * @Title: updateRole
     * @Description: TODO(修改角色信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:41:10
     * @param roleId
     * @param roleNo
     * @param roleName
     * @param memo
     * @param companyId
     * @return void
     */
    void updateRole(String roleId, String roleNo, String roleName, String memo);

    /**
     * 
     * 
     * @Title: saveRole
     * @Description: TODO(保存角色信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:54:32
     * @param roleNo
     * @param roleName
     * @param memo
     * @param companyId
     * @return void
     */
    void saveRole(String roleNo, String roleName, String memo);

    /**
     * 
     * 
     * @Title: deleteRole
     * @Description: TODO(删除角色。)
     * @author 梁日宇
     * @date 2015-12-29 上午12:19:25
     * @param ids
     * @return void
     */
    void deleteRole(String ids);

    /**
     * @Title: getRoles
     * @Description: TODO(获取所有角色列表。)
     * @author liangriyu
     * @date 2016-2-20 下午8:37:37
     * @return
     * @return List<Role>
     */
    List<Role> getRoles();
}
