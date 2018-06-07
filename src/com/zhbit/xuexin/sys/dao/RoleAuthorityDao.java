package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.RoleAuthority;

/**
 * 对角色权限表的持久接口
 * 
 * @author 梁日宇
 * 
 */
public interface RoleAuthorityDao {

    /**
     * 通过角色id获取权限列表信息
     * 
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Authority> getAuthoritiesByRoleId(String roleId);

    /**
     * 
     * 
     * @Title: deleteRoleAuthorityByRoleId
     * @Description: TODO(通过角色id删除该角色权限。)
     * @author 梁日宇
     * @date 2015-12-28 下午9:26:40
     * @param roleId
     * @return void
     */
    public void deleteRoleAuthorityByRoleId(String roleId);

    /**
     * 
     * 
     * @Title: saveRoleAuthority
     * @Description: TODO(保存角色权限)
     * @author 梁日宇
     * @date 2015-12-28 下午10:19:44
     * @param roleAuthority
     * @return void
     */
    public void saveRoleAuthority(RoleAuthority roleAuthority);

}
