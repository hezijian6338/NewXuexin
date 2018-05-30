package com.zhbit.xuexin.sys.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.RoleAuthority;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.SystemDao;

/**
 *系统初始化持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-1-1 下午10:35:41
 *@version 1.0
 */
@Repository("systemDao")
public class SystemDaoImpl implements SystemDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * @Title: getRoleLink
     * @Description: TODO(获取角色对应的操作链接 roleId->List[link]。)
     * @author 梁日宇
     * @date 2016-1-1 下午10:57:18
     * @return
     * @return Map<String,List<String>>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, List<String>> getRoleLink() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        String sql = "SELECT T1.ROLE_ID FROM TB_SYS_ROLE T1";
        SQLQuery q = getSession().createSQLQuery(sql);
        List<Object> roleIds = q.list();
        if(roleIds.size()>0){
            for(Object roleId:roleIds){
                String reaultSQL = "SELECT T1.URL FROM TB_SYS_AUTHORITY T1,TB_SYS_ROLE_AUTHORITY T2 WHERE T1.AUTHORITY_ID=T2.AUTHORITY_ID AND T1.URL<>'#' AND T2.ROLE_ID=?";
                SQLQuery qrs = getSession().createSQLQuery(reaultSQL);
                qrs.setParameter(0, String.valueOf(roleId));
                List<String> links = qrs.list();
                System.out.println("---------------------links="+roleId+":"+links.size());
                map.put(String.valueOf(roleId), links);
            }
            
        }
        return map;
    }

    /**
     * @Title: getUserRoles
     * @Description: TODO(获取当前用户的归属角色。)
     * @author 梁日宇
     * @date 2016-1-2 上午12:13:02
     * @param user
     * @return List<Role>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getUserRoles(User user) {
        String sql = "select * from TB_SYS_ROLE where ROLE_ID in (select ROLE_ID from TB_SYS_USER_ROLE where USER_ID = ?)";
        SQLQuery query = getSession().createSQLQuery(sql)
                .addEntity(Role.class);
        query.setParameter(0, user.getUserId());
        List<Role> roles = query.list();
        return roles;
    }
    
    @SuppressWarnings("unchecked")
    public List<Role> getUserRoles2() {
        String sql = "select * from TB_SYS_ROLE where ROLE_NAME=?";
        SQLQuery query = getSession().createSQLQuery(sql)
                .addEntity(Role.class);
        query.setParameter(0, Const.student_roleName);
        List<Role> roles = query.list();
        return roles;
    }

    /**
     * @Title: initAdminRoleAuthority
     * @Description: TODO(初始化管理员角色权限。)
     * @author 梁日宇
     * @date 2016-1-3 上午12:32:01
     * @return void
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initAdminRoleAuthority() {
        String sqlDel = "delete from TB_SYS_ROLE_AUTHORITY where ROLE_ID = ?";
        SQLQuery qDel = getSession().createSQLQuery(sqlDel);
        qDel.setParameter(0, Const.admin_roleId);
        qDel.executeUpdate();
        String sql= "select * from TB_SYS_AUTHORITY";
        SQLQuery q = getSession().createSQLQuery(sql).addEntity(Authority.class);
        List<Authority> auths = q.list();
        Role role = hibernateTemplate.get(Role.class, Const.admin_roleId);
        for(Authority authority:auths){
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setAuthority(authority);
            roleAuthority.setRole(role);
            hibernateTemplate.save(roleAuthority);
            getSession().flush();//及时保存到数据库
            //System.out.println("====================="+authority.getAuthorityName());
        }
    }

    /**
     * @Title: getUserAuthorities
     * @Description: TODO(通过用户id获取用户所有权限。)
     * @author 梁日宇
     * @date 2016-1-3 下午10:30:41
     * @return
     * @return List<Authority>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Authority> getUserAuthorities(String userId) {
        String sql = "SELECT * FROM TB_SYS_AUTHORITY AUTH WHERE AUTH.AUTHORITY_ID IN"
                + "(SELECT RA.AUTHORITY_ID FROM TB_SYS_ROLE_AUTHORITY RA WHERE RA.ROLE_ID IN(SELECT UR.ROLE_ID FROM TB_SYS_USER_ROLE UR WHERE UR.USER_ID = ?))";
        SQLQuery q = getSession().createSQLQuery(sql)
                .addEntity(Authority.class);
        q.setParameter(0, userId);
        List<Authority> authorities = q.list();
        return authorities;
    }

    /**
     * @Title: getStudentAuthorities
     * @Description: TODO(获取学生角色权限。)
     * @author liangriyu
     * @date 2016-3-1 下午10:39:16
     * @return
     * @return List<Authority>
     */
    @Override
    public List<Authority> getStudentAuthorities() {
        String sql = "SELECT * FROM TB_SYS_AUTHORITY auth WHERE AUTH.AUTHORITY_ID IN(SELECT RAUTH.AUTHORITY_ID FROM TB_SYS_ROLE_AUTHORITY RAUTH WHERE RAUTH.ROLE_ID IN(SELECT R.ROLE_ID FROM TB_SYS_ROLE R WHERE R.ROLE_NAME=?))";
        SQLQuery q = getSession().createSQLQuery(sql)
                .addEntity(Authority.class);
        q.setParameter(0, Const.student_roleName);
        @SuppressWarnings("unchecked")
        List<Authority> authorities = q.list();
        return authorities;
    }

    /**
     * @Title: getStudentMenu
     * @Description: TODO(获取学生角色菜单。)
     * @author liangiryu
     * @date 2016-3-1 下午10:40:17
     * @return
     * @return List<Authority>
     */
    @Override
    public List<Authority> getStudentMenu() {
        String sql = "SELECT * FROM TB_SYS_AUTHORITY auth WHERE AUTH.AUTHORITY_TYPE=0 AND AUTH.AUTHORITY_ID IN(SELECT RAUTH.AUTHORITY_ID FROM TB_SYS_ROLE_AUTHORITY RAUTH WHERE RAUTH.ROLE_ID IN(SELECT R.ROLE_ID FROM TB_SYS_ROLE R WHERE R.ROLE_NAME=?))";
        SQLQuery q = getSession().createSQLQuery(sql)
                .addEntity(Authority.class);
        q.setParameter(0, Const.student_roleName);
        @SuppressWarnings("unchecked")
        List<Authority> authorities = q.list();
        return authorities;
    }

    /**
     * @Title: getStuRoles
     * @Description: TODO(获取学生角色。)
     * @author liangriyu
     * @date 2016-3-2 上午12:11:07
     * @return
     * @return List<Role>
     */
    @Override
    public List<Role> getStuRoles() {
        String sql = "SELECT * FROM TB_SYS_ROLE R WHERE R.ROLE_NAME=?";
        SQLQuery q = getSession().createSQLQuery(sql)
                .addEntity(Role.class);
        q.setParameter(0, Const.student_roleName);
        @SuppressWarnings("unchecked")
        List<Role> roles = q.list();
        return roles;
    }

    /**
     * @Title: getOrgs
     * @Description: TODO(获取机构列表。)
     * @author liangriyu
     * @date 2016-3-6 下午11:45:18
     * @return
     * @return List<Organization>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Organization> getOrgs() {
        String hql = "From "+Organization.class.getName();
        hibernateTemplate.find(hql);
        return hibernateTemplate.find(hql);
    }

	

}
