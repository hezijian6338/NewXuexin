package com.zhbit.xuexin.sys.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.sys.dao.UserAuthorityDao;

/**
 * 
 * 权限基础信息管理持久化接口实现
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
@Repository("userAuthorityDao")
public class UserAuthorityDaoImpl implements UserAuthorityDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * 通过用户编号获取用户的所有权限
     * @param employNo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Authority> getUserAuthorityByNo(String employNo) {
        String sql = "SELECT * FROM TB_SYS_AUTHORITY AUTH WHERE AUTH.AUTHORITY_ID IN"
                + "(SELECT RA.AUTHORITY_ID FROM TB_SYS_ROLE_AUTHORITY RA WHERE RA.ROLE_ID IN(SELECT UR.ROLE_ID FROM TB_SYS_USER_ROLE UR WHERE UR.USER_ID IN(SELECT US.USER_ID FROM TB_SYS_USER US WHERE US.EMPLOY_NO=?)))";
        SQLQuery q = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .addEntity(Authority.class);
        q.setParameter(0, employNo);
        List<Authority> authorities = q.list();
        return authorities;
    }

    /**
     * 通过用户编号获取用户菜单级权限
     * @param employNo 用户编号
     * @return
     * 
     */
    @SuppressWarnings("unchecked")
    public List<Authority> getUserAuthorityOfMenuByNo(String employNo) {
        String sql = "SELECT * FROM TB_SYS_AUTHORITY AUTH WHERE AUTH.AUTHORITY_TYPE=? AND"
                + " AUTH.AUTHORITY_ID IN(SELECT RA.AUTHORITY_ID FROM TB_SYS_ROLE_AUTHORITY RA WHERE RA.ROLE_ID IN(SELECT UR.ROLE_ID FROM TB_SYS_USER_ROLE UR WHERE UR.USER_ID IN(SELECT US.USER_ID FROM TB_SYS_USER US WHERE US.EMPLOY_NO=?))) order by AUTH.MENU_NO";
        SQLQuery q = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .addEntity(Authority.class);
        q.setParameter(0, 0);//0代表菜单权限
        q.setParameter(1, employNo);
        List<Authority> authorities = q.list();
        return authorities;
    }

}
