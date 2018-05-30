package com.zhbit.xuexin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.sys.dao.UserAuthorityDao;
import com.zhbit.xuexin.sys.service.UserAuthorityService;

@Service("userAuthorityService")
@Transactional
public class UserAuthorityServiceImpl implements UserAuthorityService {
	
	@Autowired
	@Qualifier("userAuthorityDao")
	private UserAuthorityDao userAuthorityDao;
	
	/**
     * 通过用户编号获取用户的所有权限
     * @param employNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Authority> getUserAuthorityByNo(String employNo) {
        return userAuthorityDao.getUserAuthorityByNo(employNo);
    }
    
    /**
     * 获取通过用户编号获取用户的菜单级权限
     * @param employNo
     * @return
     * @throws Exception
     */
    @Override
    public List<Authority> getUserAuthorityOfMenuByNo(String employNo) {
        return userAuthorityDao.getUserAuthorityOfMenuByNo(employNo);
    }
    

}
