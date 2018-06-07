package com.zhbit.xuexin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.RoleAuthority;
import com.zhbit.xuexin.sys.dao.AuthorityDao;
import com.zhbit.xuexin.sys.dao.RoleAuthorityDao;
import com.zhbit.xuexin.sys.dao.RoleDao;
import com.zhbit.xuexin.sys.dao.SystemDao;
import com.zhbit.xuexin.sys.service.RoleAuthorityService;

/**
 * 
 * 角色权限信息管理接口实现
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
@Service("roleAuthorityService")
@Transactional(readOnly=true)
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    @Autowired
    @Qualifier("roleAuthorityDao")
    private RoleAuthorityDao roleAuthorityDao;
    @Autowired
    @Qualifier("authorityDao")
    private AuthorityDao authrityDao;
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
    @Autowired
    @Qualifier("systemDao")
    private SystemDao systemDao;

    /**
     * 通过角色id获取权限列表信息
     * 
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<Authority> getAuthoritiesByRoleId(String roleId) {
        return roleAuthorityDao.getAuthoritiesByRoleId(roleId);
    }

    /**
     * @Title: saveRoleAuthority
     * @Description: TODO(保存角色权限。)
     * @author 梁日宇
     * @date 2015-12-28 下午9:18:31
     * @param roleId
     * @param aids
     * @return void
     */
    @Override
    @Transactional(readOnly=false)
    public void saveRoleAuthority(String roleId, String aids) {
        roleAuthorityDao.deleteRoleAuthorityByRoleId(roleId);
        if(aids!=null && !"".equals(aids)){
            String[] authIds = aids.trim().split(",");
            for(String authId:authIds){
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setAuthority(authrityDao.getAuthorityById(authId));
                roleAuthority.setRole(roleDao.getRoleById(roleId));
                roleAuthorityDao.saveRoleAuthority(roleAuthority);
            }
        }
        Const.role_links = systemDao.getRoleLink();
    }

}
