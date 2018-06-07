package com.zhbit.xuexin.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.service.AuthorityService;
import com.zhbit.xuexin.sys.service.SystemService;
import com.zhbit.xuexin.sys.utils.TreeUtil;

/**
 * 权限信息Action
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-27 下午8:45:25
 * @version 1.0
 */
@Controller("authorityAction")
@Scope("prototype")
public class AuthorityAction extends BaseAction {

	/**
	 * @Fields serialVersionUID : TODO(简单说明是做什么的。)
	 */
	private static final long serialVersionUID = -2310738644279056448L;

	private String authId;

	private String parentId;

	private String parentIds;

	private String authorityName;

	private Integer authorityType;

	private String moduleName;

	private String url;

	private String operation;

	private String memo;

	private String menuNo;

	// 是否为条件查询 0-否，1-是
	private Integer isSearch;

	private String id;

	private int page;// 当前页

	private int rows;// 页面大小

	@Resource
	AuthorityService authorityService;

	@Resource
	private SystemService systemService;

	/**
	 * @Title: index
	 * @Description: TODO(权限管理页面)
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
	 * @Title: getAuthorityTree
	 * @Description: TODO(获取权限树。)
	 * @author 梁日宇
	 * @date 2015-12-27 下午9:02:33
	 * @return void
	 */
	public void getAuthorityTree() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			List<Authority> list = authorityService.getAllAuthorities();
			// 将权限处理封装成Tree
			TreeUtil tu = new TreeUtil(list);
			map.put("result", tu.getAuthorityTree());
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getList
	 * @Description: TODO(获取权限树表。)
	 * @author 梁日宇
	 * @date 2015-12-29 下午10:39:02
	 * @return void
	 */
	public void getList() {

		Map<String, Object> map = Const.getJsonMap();
		try {
			JSONArray ja = authorityService.getList(id, authorityName,
					authorityType, isSearch);
			map.put("result", ja);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * 获取权限列表
	 * 
	 * @author 梁日宇
	 * @return
	 */
	public void getListPage() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			HttpServletRequest req = getRequest();
			Page<Authority> page = authorityService
					.getPage(new Page<Authority>(req));
			//
			map.put("total", page.getCount());
			map.put("rows", page.getResult());
			map.put("code", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("code", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getAuthorityTypeList
	 * @Description: TODO(获取权限类型列表。)
	 * @author 梁日宇
	 * @date 2015-12-29 下午10:39:02
	 * @return void
	 */
	public void getAuthorityTypeList() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			map.put("result", Const.authorityType);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: saveAuthority
	 * @Description: TODO(保存权限。)
	 * @author 梁日宇
	 * @date 2015-12-31 上午12:28:12
	 * @return void
	 */
	public void saveAuthority() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			boolean isExisted = authorityService.isExisted(authorityName,
					authorityType, parentId);
			if (!isExisted) {
				authorityService.saveAuthority(parentId, parentIds,
						authorityName, authorityType, url, memo, moduleName,
						menuNo);
				systemService.initLogin(getSession(), (User) getSession()
						.getAttribute(Const.SESSION_USER));
				systemService.initSystem();
				map.put("status", Const.CODE_SUCCESS);
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "该权限已存在");
			}
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
			map.put("msg", "服务器异常");
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: getAuthority
	 * @Description: TODO(通过权限id获取权限。)
	 * @author 梁日宇
	 * @date 2015-12-31 上午12:29:15
	 * @return void
	 */
	public void getAuthority() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Authority auth = authorityService.getAuthorityById(authId);
			map.put("result", auth);
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	/**
	 * @Title: updateAuthority
	 * @Description: TODO(修改权限。)
	 * @author 梁日宇
	 * @date 2015-12-31 上午12:28:12
	 * @return void
	 */
	public void updateAuthority() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			Authority authority = authorityService.getAuthorityById(authId);
			if (null != authority) {
				boolean isExisted = !(authority.getAuthorityName().equals(
						authorityName) && authority.getAuthorityType().equals(
						authorityType))
						&& authorityService.isExisted(authorityName,
								authorityType, authority.getParentId());
				if (!isExisted) {
					authorityService.updateAuthority(authId, authorityName,
							authorityType, url, memo, moduleName, menuNo);
					systemService.initLogin(getSession(), (User) getSession()
							.getAttribute(Const.SESSION_USER));
					systemService.initSystem();
					map.put("status", Const.CODE_SUCCESS);
				} else {
					map.put("status", Const.CODE_FAIL);
					map.put("msg", "该权限已存在");
				}
			} else {
				map.put("status", Const.CODE_FAIL);
				map.put("msg", "参数异常");
			}
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
	public void deleteAuthority() {
		Map<String, Object> map = Const.getJsonMap();
		try {
			authorityService.deleteAuthority(authId);
			systemService.initLogin(getSession(), (User) getSession()
					.getAttribute(Const.SESSION_USER));
			systemService.initSystem();
			map.put("status", Const.CODE_SUCCESS);
		} catch (Exception err) {
			err.printStackTrace();
			map.put("status", Const.CODE_FAIL);
		}
		OutUtil.outJson(map, getResponse());
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
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

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public Integer getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(Integer authorityType) {
		this.authorityType = authorityType;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public Integer getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Integer isSearch) {
		this.isSearch = isSearch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

}
