
package com.zhbit.xuexin.sys.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.RoleAuthority;
import com.zhbit.xuexin.sys.dao.RoleAuthorityDao;

/**
 * 对角色权限表的持久接口实现
 * @author 梁日宇
 *
 */
@Repository("roleAuthorityDao")
public class RoleAuthorityDaoImpl implements RoleAuthorityDao{

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernatetemple;
	
	public Session getSession() {
        return hibernatetemple.getSessionFactory().getCurrentSession();
    }

	/**
	 * 通过角色id获取权限列表信息
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
    public List<Authority> getAuthoritiesByRoleId(String roleId) {
		String sql = "select * from TB_SYS_AUTHORITY auth where auth.AUTHORITY_ID in (select ru.AUTHORITY_ID from TB_SYS_ROLE_AUTHORITY ru where ru.ROLE_ID =?)";
		SQLQuery query= hibernatetemple.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Authority.class);
		query.setParameter(0, roleId);
		List<Authority> listAuthority = query.list();
		return listAuthority;
	}

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
    @Override
    public void deleteRoleAuthorityByRoleId(String roleId) {
        String sql = "delete from TB_SYS_ROLE_AUTHORITY ru where ru.ROLE_ID =?";
        SQLQuery query= hibernatetemple.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, roleId);
        query.executeUpdate();
    }

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
    @Override
    public void saveRoleAuthority(RoleAuthority roleAuthority) {
        hibernatetemple.save(roleAuthority); 
        getSession().flush();//及时保存到数据库
    }

}
