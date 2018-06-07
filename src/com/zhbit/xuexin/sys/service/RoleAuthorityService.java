package com.zhbit.xuexin.sys.service;

import java.util.List;

import com.zhbit.xuexin.domain.Authority;

/**
 * 
 * 角色权限信息管理接口
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
public interface RoleAuthorityService {

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
     * @Title: saveRoleAuthority
     * @Description: TODO(保存角色权限。)
     * @author 梁日宇
     * @date 2015-12-28 下午9:18:31
     * @param roleId
     * @param aids
     * @return void
     */
    public void saveRoleAuthority(String roleId, String aids);

}
