package com.zhbit.xuexin.sys.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.zhbit.xuexin.domain.Organization;

/**
 * 组织机构业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-20 下午6:21:20
 * @version 1.0
 */
public interface OrganizationService {

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
     * @Title: getList
     * @Description: TODO(获取组织机构列表。)
     * @author liangriyu
     * @date 2016-2-21 下午3:28:12
     * @param id
     * @return
     * @return JSONArray
     */
    JSONArray getList(String id);

    /**
     * @Title: saveOrganization
     * @Description: TODO(新增组织机构。)
     * @author liangriyu
     * @date 2016-2-21 下午8:28:02
     * @param parentId
     * @param orgName
     * @param contactMan
     * @param tell
     * @param fax
     * @param email
     * @param postCode
     * @param state
     * @param address
     * @param parentIds
     * @return void
     */
    void saveOrganization(String parentId, String orgName, String contactMan, String tell, String fax,
            String email, String postCode, String state, String address, String parentIds);

    /**
     * @Title: updateOrganization
     * @Description: TODO(修改组织机构。)
     * @author liangriyu
     * @date 2016-2-21 下午8:28:45
     * @param id
     * @param orgName
     * @param contactMan
     * @param tell
     * @param fax
     * @param email
     * @param postCode
     * @param state
     * @param address
     * @return void
     */
    void updateOrganization(String id, String orgName, String contactMan, String tell, String fax, String email,
            String postCode, String state, String address);

    /**
    * @Title: getOrganizationById 
    * @Description: TODO(获取组织机构。) 
    * @author lliangriyu 
    * @date 2016-2-21 下午9:56:08 
    * @param id 
    * @return void
     */
    Organization getOrganizationById(String id);
    
    /**
    * @Title: deleteOrganization 
    * @Description: TODO(删除。) 
    * @author liagnriyu 
    * @date 2016-2-21 下午11:43:00 
    * @param id 
    * @return void
     */
    void deleteOrganization(String id);
    
    /**
     * 判断该组织是否存在
     * @Title: isExisted
     * @Description: TODO
     * @param orgName
     * @param parentId
     * @return
     * @return: boolean
     */
	boolean isExisted(String orgName, String parentId);

}
