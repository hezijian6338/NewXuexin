package com.zhbit.xuexin.sys.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.sys.dao.RoleDao;

/**
 *角色管理持久化实现
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2015-12-16 下午9:15:34
 *@version 1.0
 */
@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
    
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * @Title: getRoleById
     * @Description: TODO(获取角色实体)
     * @author 梁日宇
     * @date 2015-12-16 下午9:13:35
     * @param roleId
     * @return
     * @return Role
     */
    @Override
    public Role getRoleById(String roleId) {
        return hibernateTemplate.get(Role.class, roleId);
    }

    /**
     * @Title: getRolesByComId
     * @Description: TODO(通过公司id获取角色列表)
     * @author 梁日宇
     * @date 2015-12-17 下午11:33:58
     * @param companyId
     * @return
     * @return List<Role>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRolesByComId(int companyId) {
        String sql = "select * from TB_SYS_ROLE role where role.COMPANY_ID=?";
        SQLQuery sq = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Role.class);
        sq.setParameter(0, companyId);
        return sq.list();
    }

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
    @SuppressWarnings("unchecked")
    @Override
    public Page<Role> getRoleList(Page<Role> page) {
        String hqlResult = "From " +Role.class.getName()+" role ";
        String hqlCount = "select count(role)";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        sb.append(" and role.roleNo").append(" <> '").append(Const.admin_roleno).append("'");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("roleName".equals(key) || "roleNo".equals(key)) {
                sb.append(" and role.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if("companyId".equals(key)){
                sb.append(" and role.company.").append(key).append(" = ?");
                ps.add(page.getParas().get(key));
            }
        }
        // do count
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hqlResult + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by role.createTime");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<Role> list = q.list();
        page.setResult(list);
        return page;
    }

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
    @Override
    public void updateRole(Role role) {
        hibernateTemplate.update(role);
    }

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
    @Override
    public void saveRole(Role role) {
        hibernateTemplate.save(role);
    }

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
    @Override
    public void deleteRole(Role role) {
        hibernateTemplate.delete(role);
    }

    /**
     * @Title: getRoles
     * @Description: TODO(获取所有角色列表。)
     * @author liangriyu
     * @date 2016-2-20 下午8:37:37
     * @return
     * @return List<Role>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRoles() {
        return hibernateTemplate.find("From Role");
    }

}
