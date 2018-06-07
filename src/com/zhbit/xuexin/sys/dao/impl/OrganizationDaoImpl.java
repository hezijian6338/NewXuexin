package com.zhbit.xuexin.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.OrganizationDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-20 下午7:30:46
 * @version 1.0
 */
@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	/**
	 * @Title: getOrganizations
	 * @Description: 获取组织机构列表
	 * @author liangriyu
	 * @date 2016-2-20 下午7:23:24
	 * @return
	 * @return List<Organization>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizations() {
		return hibernateTemplate.find("From Organization");
	}

	/**
	 * @Title: getOrganizationById
	 * @Description: TODO(通过id获取组织机构。)
	 * @author liangriyu
	 * @date 2016-2-20 下午9:17:00
	 * @param orgId
	 * @return
	 * @return Organization
	 */
	@Override
	public Organization getOrganizationById(String orgId) {
		return hibernateTemplate.get(Organization.class, orgId);
	}
	
//    /**
//     * @Title: getOrganizationByName
//     * @Description: TODO(通过id获取组织机构。)
//     * @author lianhaowen
//     * @date 2017-6-3 下午9:17:00
//     * @param orgName
//     * @return
//     * @return Organization
//     */
//    public Organization getOrganizationByName(String orgName){
//    	String hql = "From Organization org where org.orgName= ?";
//        List<Organization> list = (List<Organization>) hibernateTemplate.find(hql, new Object[] {orgName});
//        return list.isEmpty() ? null : list.get(0);
//    	
//    }


	/**
	 * @Title: getList
	 * @Description: TODO(异步获取机构列表。)
	 * @author liangriyu
	 * @date 2016-2-21 下午3:51:35
	 * @param id
	 * @return
	 * @return List<Organization>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getList(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("From Organization org where 1=1 ");
		List<Object> ps = new ArrayList<Object>();
		if (id == null || "".equals(id)) {
			sb.append(" and org.parentId=?");
			ps.add(Const.defult_Pid);
		} else {
			sb.append(" and org.parentId=?");
			ps.add(id);
		}
		String hql = sb.toString();
		Query q = getSession().createQuery(hql);
		for (int i = 0; i < ps.size(); i++) {
			q.setParameter(i, ps.get(i));
		}
		return q.list();
	}

	/**
	 * @Title: getChildren
	 * @Description: TODO(通过id获取下级列表。)
	 * @author liangriyu
	 * @date 2016-2-21 下午3:56:13
	 * @param orgId
	 * @return
	 * @return List<Organization>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getChildren(String orgId) {
		String hql = "From Organization where parentId='" + orgId + "'";
		List<Organization> list = hibernateTemplate.find(hql);
		return list;
	}

	/**
	 * @Title: saveOrganization
	 * @Description: TODO(新增组织机构。)
	 * @author liangriyu
	 * @date 2016-2-21 下午8:43:59
	 * @param org
	 * @return void
	 */
	@Override
	public void saveOrganization(Organization org) {
		hibernateTemplate.save(org);
	}

	/**
	 * @Title: updateOrganization
	 * @Description: TODO(新增组织机构。)
	 * @author liangriyu
	 * @date 2016-2-21 下午8:43:59
	 * @param org
	 * @return void
	 */
	@Override
	public void updateOrganization(Organization org) {
		hibernateTemplate.update(org);
	}

	@Override
	public List<User> orgToUser(String orgId) {
		return null;
	}

	@Override
	public void deleteOrganization(Organization organization) {
		hibernateTemplate.delete(organization);
	}

	@Override
	public Organization getOrgByNameAndParentId(String orgName, String parentId) {
		String hql = "From Organization where orgName=? and parentId=?";
		List<Organization> list = hibernateTemplate
				.find(hql, orgName, parentId);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Organization getOrgByName(String orgName) {
		String hql = "From Organization where orgName=?";
		List<Organization> list = hibernateTemplate
				.find(hql, orgName);
		return list.isEmpty() ? null : list.get(0);
	}
}
