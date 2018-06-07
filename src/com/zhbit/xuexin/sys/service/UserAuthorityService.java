package com.zhbit.xuexin.sys.service;

import java.util.List;

import com.zhbit.xuexin.domain.Authority;

/**
 * 
 * 用户权限信息管理接口
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
public interface UserAuthorityService {
	/**
	 * 通过用户编号获取用户的所有权限
	 * @param employNo
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getUserAuthorityByNo(String employNo);
	/**
	 * 获取通过用户编号获取用户的菜单级权限
	 * @param employNo
	 * @return
	 * @throws Exception
	 */
	public List<Authority> getUserAuthorityOfMenuByNo(String employNo);
	
}
