package com.zhbit.xuexin.sys.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.domain.UserRole;
import com.zhbit.xuexin.sys.dao.UserRoleDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2015-12-16 下午9:01:53
 *@version 1.0
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao{

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * @Title: saveUserRole
     * @Description: TODO(保存用户角色)
     * @author 梁日宇
     * @date 2015-12-16 下午8:59:32
     * @param userRole
     * @return void
     */
    @Override
    public void saveUserRole(UserRole userRole) {
        hibernateTemplate.save(userRole);
    }

    /**
     * 
     * 
     * @Title: deleteUserRole
     * @Description: TODO(删除用户角色关联表的信息)
     * @author 梁日宇
     * @date 2015-12-20 下午11:49:12
     * @param UserRole
     * @return void
     */
    @Override
    public void deleteUserRole(UserRole userRole) { 
        hibernateTemplate.delete(userRole);
    }
    

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
    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> getUserRolesByUserId(String userId) {
        String sql = "select ID,ROLE_ID,USER_ID from TB_SYS_USER_ROLE where USER_ID = ?";
        SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(UserRole.class);
        query.setParameter(0, userId);
        List<UserRole> list = query.list();
        return list;
    }

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
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUserByRoleId(String roleId) {
        String sql = "select * from TB_SYS_USER u where u.USER_ID in (select ur.USER_ID from TB_SYS_USER_ROLE ur where ur.ROLE_ID=?)";
        SQLQuery query= hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(User.class);
        query.setParameter(0, roleId);
        return query.list();
    }

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
    @Override
    public void deleteUserRoleByRoleId(String roleId) {
        String sql = "delete from TB_SYS_USER_ROLE ur where ur.ROLE_ID=?";
        SQLQuery query= hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, roleId);
        query.executeUpdate();
    }
}
