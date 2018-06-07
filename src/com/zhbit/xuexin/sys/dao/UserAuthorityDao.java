package com.zhbit.xuexin.sys.dao;

import java.util.List;

import com.zhbit.xuexin.domain.Authority;


/**
 * 
 * 权限用户关联表管理持久化接口
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
public interface UserAuthorityDao {

	/**
	 * 通过用户编号获取用户所有权限
	 * @param employNo 用户编号
	 * @return
	 * 
	 */
	public List<Authority> getUserAuthorityByNo(String employNo);
	/**
	 * 通过用户编号获取用户菜单级权限
	 * @param employNo 用户编号
	 * @return
	 * 
	 */
	public List<Authority> getUserAuthorityOfMenuByNo(String employNo);
	
	

	
}
