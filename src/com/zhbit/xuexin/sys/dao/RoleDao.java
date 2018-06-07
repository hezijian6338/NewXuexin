package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Role;

/**
 * 角色管理持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-16 下午9:09:51
 * @version 1.0
 */
public interface RoleDao {

    /**
     * @Title: getRoleById
     * @Description: TODO(获取角色实体)
     * @author 梁日宇
     * @date 2015-12-16 下午9:13:35
     * @param roleId
     * @return
     * @return Role
     */
    public Role getRoleById(String roleId);

    /**
     * @Title: getRolesByComId
     * @Description: TODO(通过公司id获取角色列表)
     * @author Administrator
     * @date 2015-12-17 下午11:33:58
     * @param companyId
     * @return
     * @return List<Role>
     */
    public List<Role> getRolesByComId(int companyId);

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
    public Page<Role> getRoleList(Page<Role> page);

    /**
     * 
     * 
     * @Title: updateRole
     * @Description: TODO(修改角色信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:45:19
     * @param role
     * @return void
     */
    public void updateRole(Role role);

    /**
     * 
     * 
     * @Title: saveRole
     * @Description: TODO(保存角色信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:45:19
     * @param role
     * @return void
     */
    public void saveRole(Role role);

    /**
     * 
     * 
     * @Title: deleteRole
     * @Description: TODO(删除角色。)
     * @author 梁日宇
     * @date 2015-12-29 上午12:19:25
     * @param role
     * @return void
     */
    public void deleteRole(Role role);

    /**
     * @Title: getRoles
     * @Description: TODO(获取所有角色列表。)
     * @author liangriyu
     * @date 2016-2-20 下午8:37:37
     * @return
     * @return List<Role>
     */
    public List<Role> getRoles();
}
