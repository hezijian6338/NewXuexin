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
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.JsonUtil;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.sys.dao.AuthorityDao;
import com.zhbit.xuexin.sys.service.AuthorityService;

/**
 *权限信息业务实现层
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2015-12-27 下午8:42:04
 *@version 1.0
 */
@Service("authorityService")
@Transactional(readOnly=true)
public class AuthorityServiceImpl implements AuthorityService {
    
    @Autowired
    @Qualifier("authorityDao")
    private AuthorityDao authrityDao;

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
    @Override
    public List<Authority> getAllAuthorities() {
        return authrityDao.getAllAuthorities();
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
     * @return JSONArray
     */
    @Override
    public JSONArray getList(String authId, String authorityName, Integer authorityType, Integer isSearch) {
        List<Authority> list = authrityDao.getList(authId, authorityName, authorityType, isSearch);
        JSONArray ja = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            Boolean hasChildren = false;
            if (authrityDao.getChildren(list.get(i).getAuthorityId()).size() > 0) {
                hasChildren = true;
            }
            JSONObject jsonObject = JsonUtil.jsonObjectForTree(list.get(i),
                    hasChildren);
            ja.add(jsonObject);
        }
        return ja;
    }

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
     * @return void
     */
    @Override
    @Transactional(readOnly=false)
    public void saveAuthority(String parentId, String parentIds, String authorityName, Integer authorityType,
            String url, String memo, String moduleName, String menuNo) {
        if("".equals(menuNo)){
            menuNo="99999";
        }
        Authority auth =new Authority();
        auth.setAuthorityName(authorityName);
        auth.setAuthorityType(authorityType);
        auth.setModuleName(moduleName);
        auth.setMenuNo(menuNo);
        auth.setUrl(url);
        auth.setMemo(memo);
        if(parentId==null||"".equals(parentId)){
            parentId=Const.defult_Pid;
        }
        auth.setParentId(parentId);
        if(parentIds==null||"".equals(parentIds)){
            parentIds=Const.defult_Pid;
        }else{
            parentIds+=","+parentId;
        }
        auth.setParentIds(parentIds);       
        authrityDao.saveAuthority(auth);
    }

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
    @Override
    public Authority getAuthorityById(String authId) {
        return authrityDao.getAuthorityById(authId);       
    }

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
     * @return void
     */
    @Override
    @Transactional(readOnly=false)
    public void updateAuthority(String authId, String authorityName, Integer authorityType, String url, String memo, String moduleName, String menuNo) {
        if("".equals(menuNo)){
            menuNo="99999";
        }
        Authority auth = authrityDao.getAuthorityById(authId);
        auth.setAuthorityName(authorityName);
        auth.setAuthorityType(authorityType);
        auth.setModuleName(moduleName);
        auth.setMenuNo(menuNo);
        auth.setUrl(url);
        auth.setMemo(memo);
        authrityDao.updateAuthority(auth);
    }

    /**
     * @Title: deleteAuthority
     * @Description: TODO(删除权限。)
     * @author 梁日宇
     * @date 2016-1-1 上午12:05:21
     * @param authId
     * @return void
     */
    @Override
    @Transactional(readOnly=false)
    public void deleteAuthority(String authId) {
        List<Authority> childrens = authrityDao.getChildren(authId);
        if (childrens!=null&&childrens.size() > 0) {
            for(Authority auth:childrens){
                deleteAuthority(auth.getAuthorityId());
                //authrityDao.deleteAuthority(auth);
            }
        }
        authrityDao.deleteAuthority(authrityDao.getAuthorityById(authId));
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
    @Override
    public Page<Authority> getPage(Page<Authority> page) {
        return authrityDao.getPage(page) ;
    }

	@Override
	public boolean isExisted(String authorityName, Integer authorityType,
			String parentId) {
		parentId = StringUtils.isBlank(parentId) ? Const.defult_Pid : parentId;
		return authrityDao.getAuthorityByNameAndTypeAndparentId(authorityName, authorityType, parentId) != null;
	}

}
