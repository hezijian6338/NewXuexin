package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Authority;

/**
 * 权限基础信息管理持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-27 下午8:35:18
 * @version 1.0
 */
public interface AuthorityDao {

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
     * @Title: getAuthorityById
     * @Description: TODO(通过id获取权限信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午9:40:06
     * @return
     * @return Authority
     */
    public Authority getAuthorityById(String authId);

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
    public List<Authority> getList(String authId, String authorityName, Integer authorityType, Integer isSearch);

    /**
     * 
     * 
     * @Title: getChildren
     * @Description: TODO(通过权限id获取子权限列表。)
     * @author 梁日宇
     * @date 2015-12-29 下午10:10:52
     * @param string
     * @return
     * @return List<Authority>
     */
    public List<Authority> getChildren(String string);

    /**
     * 
     * 
     * @Title: saveAuthority
     * @Description: TODO(保存权限。)
     * @author 梁日宇
     * @date 2015-12-30 下午11:24:40
     * @param auth
     * @return void
     */
    public void saveAuthority(Authority auth);

    /**
     * 
     * 
     * @Title: updateAuthority
     * @Description: TODO(修改权限。)
     * @author 梁日宇
     * @date 2015-12-30 下午11:24:40
     * @param auth
     * @return void
     */
    public void updateAuthority(Authority auth);

    /**
     * @Title: deleteAuthority
     * @Description: TODO(删除权限。)
     * @author 梁日宇
     * @date 2016-1-1 上午12:19:06
     * @param authority
     * @return void
     */
    public void deleteAuthority(Authority authority);

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
     * 根据名字类型和父id查找权限
     * @Title: getAuthorityByNameAndTypeAndparentId
     * @Description: TODO
     * @param authorityName
     * @param authorityType
     * @param parentId
     * @return
     * @return: Authority
     */
	public Authority getAuthorityByNameAndTypeAndparentId(String authorityName,
			Integer authorityType, String parentId);
}
