package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.User;

/**
 * 组织机构持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-20 下午7:26:52
 * @version 1.0
 */
public interface OrganizationDao {

    /**
     * @Title: getOrganizations
     * @Description: 获取组织机构列表
     * @author liangriyu
     * @date 2016-2-20 下午7:23:24
     * @return
     * @return List<Organization>
     */
    List<Organization> getOrganizations();

    /**
     * @Title: getOrganizationById
     * @Description: TODO(通过id获取组织机构。)
     * @author liangriyu
     * @date 2016-2-20 下午9:17:00
     * @param orgId
     * @return
     * @return Organization
     */
    Organization getOrganizationById(String orgId);
    
//    /**
//     * @Title: getOrganizationByName
//     * @Description: TODO(通过id获取组织机构。)
//     * @author lianhaowen
//     * @date 2017-6-3 下午9:17:00
//     * @param orgName
//     * @return
//     * @return Organization
//     */
//    Organization getOrganizationByName(String orgName);


    /**
     * @Title: getList
     * @Description: TODO(异步获取机构列表。)
     * @author liangriyu
     * @date 2016-2-21 下午3:51:35
     * @param id
     * @return
     * @return List<Organization>
     */
    List<Organization> getList(String id);

    /**
     * @Title: getChildren
     * @Description: TODO(通过id获取下级列表。)
     * @author liangriyu
     * @date 2016-2-21 下午3:56:13
     * @param orgId
     * @return
     * @return List<Organization>
     */
    List<Organization> getChildren(String orgId);

    /**
     * @Title: saveOrganization
     * @Description: TODO(新增组织机构。)
     * @author liangriyu
     * @date 2016-2-21 下午8:43:59
     * @param org
     * @return void
     */
    void saveOrganization(Organization org);

    /**
     * @Title: updateOrganization
     * @Description: TODO(新增组织机构。) 
     * @author liangriyu
     * @date 2016-2-21 下午8:43:59 
     * @param org 
     * @return void
      */
    void updateOrganization(Organization org);
    
    /**
    * @Title: orgToUser 
    * @Description: TODO(被引用列表。) 
    * @author liangriyu 
    * @date 2016-2-21 下午11:46:57 
    * @param orgId
    * @return 
    * @return List
     */
    List<User> orgToUser(String orgId);

    /**
     * @Title: deleteOrganization 
     * @Description: TODO(删除。) 
     * @author liangriyu 
     * @date 2016-2-21 下午11:46:57 
     * @param orgId
     * @return 
     * @return List
      */
    void deleteOrganization(Organization organizationById);
    
    /**
     * 根据组织名字和父组织id返回组织
     * @Title: getOrgByNameAndParentId
     * @Description: TODO
     * @param orgName
     * @param parentId
     * @return
     * @return: Organization
     */
	Organization getOrgByNameAndParentId(String orgName, String parentId);
	Organization getOrgByName(String orgName);
	
}
