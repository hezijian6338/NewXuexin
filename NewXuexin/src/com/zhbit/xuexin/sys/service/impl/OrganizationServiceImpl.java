package com.zhbit.xuexin.sys.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.utils.JsonUtil;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.sys.service.OrganizationService;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-2-20 下午6:22:28
 *@version 1.0
 */
@Service("organizationService")
@Transactional(readOnly=true)
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    @Qualifier("organizationDao")
    private OrganizationDao organizationDao;
    
    /**
     * @Title: getOrganizations 
     * @Description: 获取组织机构列表
     * @author liangriyu 
     * @date 2016-2-20 下午7:23:24 
     * @return 
     * @return List<Organization>
      */
    @Override
    public List<Organization> getOrganizations() {
        return organizationDao.getOrganizations();
    }

    /**
     * @Title: getList
     * @Description: TODO(获取组织机构列表。)
     * @author liangriyu
     * @date 2016-2-21 下午3:28:12
     * @param id
     * @return
     * @return JSONArray
     */
    @Override
    public JSONArray getList(String id) {
        List<Organization> list = organizationDao.getList(id);
        JSONArray ja = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Boolean hasChildren = false;
            if (organizationDao.getChildren(list.get(i).getOrgId()).size() > 0) {
                hasChildren = true;
            }
            JSONObject jsonObject = JsonUtil.jsonObjectForTree(list.get(i),
                    hasChildren);
            ja.add(jsonObject);
        }
        return ja;
    }

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
    @Override
    @Transactional(readOnly=false)
    public void saveOrganization(String parentId, String orgName, String contactMan, String tell,
            String fax, String email, String postCode, String state, String address, String parentIds) {
        Organization org = new Organization();
        if(parentId==null||"".equals(parentId)){
            parentId=Const.defult_Pid;
        }
        if(parentIds==null||"".equals(parentIds)){
            parentIds=Const.defult_Pid;
        }else{
            parentIds+=","+parentId;
        }
        org.setParentId(parentId);
        org.setParentIds(parentIds);
        org.setOrgName(orgName);
        org.setContactMan(contactMan);
        org.setTell(tell);
        org.setFax(fax);
        org.setEmail(email);
        org.setPostCode(postCode);
        org.setState(state);
        org.setAddress(address);
        organizationDao.saveOrganization(org);
    }

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
    @Override
    @Transactional(readOnly=false)
    public void updateOrganization(String id, String orgName, String contactMan, String tell, String fax, String email,
            String postCode, String state, String address) {
        Organization org = organizationDao.getOrganizationById(id);
        org.setOrgName(orgName);
        org.setContactMan(contactMan);
        org.setTell(tell);
        org.setFax(fax);
        org.setEmail(email);
        org.setPostCode(postCode);
        org.setState(state);
        org.setAddress(address);
        organizationDao.updateOrganization(org);
    }

    @Override
    public Organization getOrganizationById(String id) {
        return organizationDao.getOrganizationById(id);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteOrganization(String id) {
        List<Organization> childrens = organizationDao.getChildren(id);
        if (childrens!=null&&childrens.size() > 0) {
            for(Organization org:childrens){
                deleteOrganization(org.getOrgId());
            }
        }
        organizationDao.deleteOrganization(organizationDao.getOrganizationById(id));
    }

	@Override
	public boolean isExisted(String orgName, String parentId) {
		parentId = StringUtils.isBlank(parentId) ? Const.defult_Pid : parentId;
		return organizationDao.getOrgByNameAndParentId(orgName,parentId) != null;
	}
}
