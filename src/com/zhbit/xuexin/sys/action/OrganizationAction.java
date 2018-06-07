package com.zhbit.xuexin.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.sys.service.OrganizationService;
import com.zhbit.xuexin.sys.utils.OrgTreeUtil;

/**
 * 组织机构Action
 *
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-20 下午6:01:10
 * @version 1.0
 */

@Controller("organizationAction")
@Scope("prototype")
public class OrganizationAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODO(简单说明是做什么的。)
	 */
	private static final long serialVersionUID = 5817923462595601395L;

	private String orgId;

	private String orgName;

	private String parentId;

	private String parentIds;

	private String address;

	private String postCode;

	private String contactMan;

	private String tell;

	private String fax;

	private String email;

	private String status;

	private String id;

	@Resource
	OrganizationService organizationService;

	/**
	 * @Title: index
	 * @Description: TODO(机构管理页面)
	 * @author 梁日宇
	 * @date 2015-12-29 下午10:40:02
	 * @return
	 * @return String
	 */
	public String index() {
		return "index";
	}

	/**
	 * @Title: viewAdd
	 * @Description: TODO(新增页面)
	 * @author 梁日宇
	 * @date 2015-12-20 下午4:39:40
	 * @return
	 * @return String
	 */
	public String viewAdd() {
		return "viewAdd";
	}

	/**
	 * @Title: viewEdit
	 * @Description: TODO(修改页面)
	 * @author 梁日宇
	 * @date 2015-12-20 下午4:40:02
	 * @return
	 * @return String
	 */
	public String viewEdit() {
		return "viewEdit";
	}

	/**
	 * 
	 * 
	 * @Title: getOrgTree
	 * @Description: TODO(获取组织机构下拉列表)
	 * @author liangriyu
	 * @date 2015-12-16 下午11:46:36
	 * @return void
	 */
	public void getOrgTree() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			List<Organization> list = organizationService.getOrganizations();
			OrgTreeUtil otu = new OrgTreeUtil(list);
			map.put("result", otu.getOrgTree());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getList
	 * @Description: TODO(获取机构树表。)
	 * @author 梁日宇
	 * @date 2015-12-29 下午10:39:02
	 * @return void
	 */
	public void getList() {

		Map<String, Object> map = Const.getJsonMap();
		try {
			JSONArray ja = organizationService.getList(id);
			map.put("result", ja);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: updateCompany
	 * @Description: TODO(修改公司信息。)
	 * @author liangriyu
	 * @date 2016-1-19 下午11:29:53
	 * @return void
	 */
	public void updateOrganization() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Organization organization = organizationService
					.getOrganizationById(id);
			if (null != organization) {
				boolean isExisted = !organization.getOrgName().equals(orgName)
						&& organizationService.isExisted(orgName,
								organization.getParentId());
				if (!isExisted) {
					organizationService.updateOrganization(id, orgName,
							contactMan, tell, fax, email, postCode, status,
							address);
					map.put("status", Const.CODE_SUCCESS);
					map.put("msg", "更新成功");
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "组织名已存在");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "参数异常");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "更新失败,服务器异常..");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: saveCompany
	 * @Description: TODO(新增公司信息。)
	 * @author liangriyu
	 * @date 2016-1-19 下午11:29:53
	 * @return void
	 */
	public void saveOrganization() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			boolean isExisted = organizationService
					.isExisted(orgName, parentId);
			if (!isExisted) {
				organizationService.saveOrganization(parentId, orgName,
						contactMan, tell, fax, email, postCode, status,
						address, parentIds);
				map.put("status", Const.CODE_SUCCESS);
				map.put("msg", "保存成功");
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "组织名已存在");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "保存失败,服务器异常..");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getOrganization
	 * @Description: TODO(获取组织机构。)
	 * @author liangriyu
	 * @date 2016-2-21 下午9:54:54
	 * @return void
	 */
	public void getOrganization() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Organization org = organizationService.getOrganizationById(id);
			map.put("result", org);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: deleteAuthority
	 * @Description: TODO(删除权限。)
	 * @author 梁日宇
	 * @date 2016-1-1 上午12:22:00
	 * @return void
	 */
	public void delete() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			organizationService.deleteOrganization(id);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
