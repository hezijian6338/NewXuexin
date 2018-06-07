package com.zhbit.xuexin.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.sys.dao.RoleDao;
import com.zhbit.xuexin.sys.service.RoleService;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2015-12-17 下午11:35:21
 *@version 1.0
 */
@Service("roleService")
@Transactional(readOnly=true)
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
    
    /**
     * @Title: getRolesByComId
     * @Description: TODO(通过公司id获取角色列表)
     * @author 梁日宇
     * @date 2015-12-17 下午11:33:58
     * @param companyId
     * @return
     * @return List<Role>
     */
//    @Override
//    public List<Role> getRolesByComId(int companyId) {
//        return roleDao.getRolesByComId(companyId);
//    }

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
    @Override
    public Page<Role> getRoleList(Page<Role> page) {      
        return roleDao.getRoleList(page);
    }

    @Override
    public Role getRoleById(String roleId) {
        return roleDao.getRoleById(roleId);
    }

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
    @Override
    @Transactional(readOnly=false)
    public void updateRole(String roleId, String roleNo, String roleName, String memo) {
        Role role = roleDao.getRoleById(roleId);
        role.setRoleNo(roleNo);
        role.setRoleName(roleName);
        role.setMemo(memo);
        roleDao.updateRole(role);
    }

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
     * @return void
     */
    @Override
    @Transactional(readOnly=false)
    public void saveRole(String roleNo, String roleName, String memo) {
        Role role = new Role();
        role.setRoleNo(roleNo);
        role.setRoleName(roleName);
        role.setMemo(memo);
        role.setCreateTime(new Date());
        roleDao.saveRole(role);
    }

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
    @Override
    @Transactional(readOnly = false)
    public void deleteRole(String ids) {
        if(ids!=null){
            String[] rids = ids.split(",");
            for(String roleId:rids){
                Role role = roleDao.getRoleById(roleId);
                roleDao.deleteRole(role);
            }   
        }   
    }

    /**
     * @Title: getRoles
     * @Description: TODO(获取所有角色列表。)
     * @author liangriyu
     * @date 2016-2-20 下午8:37:37
     * @return
     * @return List<Role>
     */
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

}
