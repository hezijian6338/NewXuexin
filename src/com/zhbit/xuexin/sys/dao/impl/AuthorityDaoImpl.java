package com.zhbit.xuexin.sys.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.sys.dao.AuthorityDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-27 下午8:36:58
 * @version 1.0
 */
@Repository("authorityDao")
public class AuthorityDaoImpl implements AuthorityDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * 
     * 
     * @Title: getAllAuthorities
     * @Description: TODO(获取所有的权限。)
     * @author 梁日宇
     * @date 2015-12-27 下午8:55:09
     * @return
     * @throws Exception
     * @return List<Authority>
     */
    @SuppressWarnings("unchecked")
    public List<Authority> getAllAuthorities() {
        return hibernateTemplate.find("From Authority");
    }

    /**
     * 
     * 
     * @Title: getAuthorityById
     * @Description: TODO(通过id获取权限信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午9:40:06
     * @return
     * @return Authority
     */
    @Override
    public Authority getAuthorityById(String authId) {
        return hibernateTemplate.get(Authority.class, authId);
    }

    /**
     * 
     * 
     * @Title: getList
     * @Description: TODO(获取权限树表。)
     * @author 梁日宇
     * @date 2015-12-29 下午9:47:32
     * @param authId
     * @param authorityName
     * @param authorityType
     * @param isSearch
     *            是否为条件查询 0-否，1-是
     * @return
     * @return List<Authority>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Authority> getList(String authId, String authorityName, Integer authorityType, Integer isSearch) {
        StringBuffer sb = new StringBuffer();
        sb.append("From Authority auth where 1=1 ");
        List<Object> ps = new ArrayList<Object>();
        if (isSearch == 1) {
            if (authorityName != null && !"".equals(authorityName)) {
                sb.append(" and auth.authorityName").append(" like ?");
                ps.add("%" + authorityName + "%");
            }
            if (authorityType != null) {
                System.out.println("-------------authorityType="+authorityType);
                sb.append(" and auth.authorityType").append(" = ?");
                ps.add(authorityType);
            }
        }
        else {
            if (authId == null) {
                sb.append(" and auth.parentId=?");
                ps.add(Const.defult_Pid);
            }
            else {
                sb.append(" and auth.parentId=?");
                ps.add(authId);
            }
        }
        String hql = sb.toString();
        Query q = getSession().createQuery(hql);
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        return q.list();
    }

    /**
     * 
     * 
     * @Title: getChildren
     * @Description: TODO(通过权限id获取子权限列表。)
     * @author 梁日宇
     * @date 2015-12-29 下午10:10:52
     * @param authId
     * @return
     * @return List<Authority>
     */
    @SuppressWarnings("unchecked")
    public List<Authority> getChildren(String authId) {
        String hql = "From Authority where parentId='" + authId + "'";
        List<Authority> list = hibernateTemplate.find(hql);
        return list;
    }

    /**
     * @Title: saveAuthority
     * @Description: TODO(保存权限。)
     * @author 梁日宇
     * @date 2015-12-30 下午11:24:40
     * @param auth
     * @return void
     */
    @Override
    public void saveAuthority(Authority auth) {
        hibernateTemplate.save(auth);      
    }

    /**
     * @Title: updateAuthority
     * @Description: TODO(修改权限。)
     * @author 梁日宇
     * @date 2015-12-30 下午11:24:40
     * @param auth
     * @return void
     */
    @Override
    public void updateAuthority(Authority auth) {
        hibernateTemplate.update(auth);
    }

    /**
     * @Title: deleteAuthority
     * @Description: TODO(删除权限。)
     * @author 梁日宇
     * @date 2016-1-1 上午12:19:06
     * @param authority
     * @return void
     */
    @Override
    public void deleteAuthority(Authority authority) {
        hibernateTemplate.delete(authority);
    }

    /**
     * @Title: getPage
     * @Description: TODO(获取权限分页列表。)
     * @author liangriyu
     * @date 2016-1-10 下午12:00:23
     * @param page
     * @return
     * @return Page<Authority>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<Authority> getPage(Page<Authority> page) {
        String hqlResult = "From " +Authority.class.getName()+" auth ";
        String hqlCount = "select count(auth)";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
//            String key = item.next();
//            if ("authorityName".equals(key)) {
//                sb.append(" and auth.").append(key).append(" like ?");
//                ps.add("%" + page.getParas().get(key) + "%");
//            }
//            if("companyId".equals(key)){
//                sb.append(" and auth.company.").append(key).append(" = ?");
//                ps.add(page.getParas().get(key));
//            }
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
                .createQuery(hqlResult + sb.toString() + " order by auth.parentIds");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        //q.setFirstResult(page.getFirst());
        //q.setMaxResults(page.getRows());
        List<Authority> list = q.list();
        page.setResult(list);
        return page;
    }

	@Override
	public Authority getAuthorityByNameAndTypeAndparentId(String authorityName,
			Integer authorityType, String parentId) {
		String hql = "From Authority where authorityName=? and authorityType=? and parentId=?";
		List<Authority> list = hibernateTemplate.find(hql, authorityName, authorityType, parentId);
		return list.isEmpty() ? null : list.get(0);
	}

}
