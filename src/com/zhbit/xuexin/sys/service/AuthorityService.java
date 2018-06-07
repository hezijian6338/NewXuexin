package com.zhbit.xuexin.sys.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Authority;

/**
 * 权限信息管理业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-27 下午8:39:32
 * @version 1.0
 */
public interface AuthorityService {

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
    public List<Authority> getAllAuthorities();

    /**
     * 
     * 
     * @Title: getList
     * @Description: TODO(获取权限树表。)
     * @author 梁日宇
     * @date 2015-12-29 下午9:47:32
     * @param id
     * @param authorityName
     * @param authorityType
     * @param isSearch
     *            是否为条件查询 0-否，1-是
     * @return
     * @return JSONArray
     */
    public JSONArray getList(String id, String authorityName, Integer authorityType, Integer isSearch);

    /**
     * 
     * 
     * @Title: saveAuthority
     * @Description: TODO(简单说明这个方法是做什么的。)
     * @author 梁日宇
     * @date 2015-12-30 下午11:15:59
     * @param parentId
     * @param parentIds
     * @param authorityName
     * @param authorityType
     * @param url
     * @param memo
     * @param moduleName 
     * @param menuNo 
     * @return void
     */
    public void saveAuthority(String parentId, String parentIds, String authorityName, Integer authorityType,
            String url, String memo, String moduleName, String menuNo);

    /**
     * 
     * 
     * @Title: getAuthorityById
     * @Description: TODO(通过权限id获取权限信息。)
     * @author 梁日宇
     * @date 2015-12-31 上午12:30:24
     * @param authId
     * @return void
     */
    public Authority getAuthorityById(String authId);

    /**
     * 
     * 
     * @Title: updateAuthority
     * @Description: TODO(修改权限信息。)
     * @author 梁日宇
     * @date 2015-12-31 上午12:44:35
     * @param authId
     * @param authorityName
     * @param authorityType
     * @param url
     * @param memo
     * @param moduleName 
     * @param menuNo 
     * @return void
     */
    public void updateAuthority(String authId, String authorityName, Integer authorityType, String url, String memo, String moduleName, String menuNo);

    /**
     * @Title: deleteAuthority
     * @Description: TODO(删除权限。)
     * @author 梁日宇
     * @date 2016-1-1 上午12:05:21
     * @param authId
     * @return void
     */
    public void deleteAuthority(String authId);

    /**
     * @Title: getPage
     * @Description: TODO(获取权限分页列表。)
     * @author liangriyu
     * @date 2016-1-10 下午12:00:23
     * @param page
     * @return
     * @return Page<Authority>
     */
    public Page<Authority> getPage(Page<Authority> page);
    
    /**
     * 判断是否存在
     * @Title: isExisted
     * @Description: TODO
     * @param authorityName
     * @param authorityType
     * @param parentId
     * @return
     * @return: boolean
     */
	public boolean isExisted(String authorityName, Integer authorityType,
			String parentId);
}
