package com.zhbit.xuexin.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.SystemDao;
import com.zhbit.xuexin.sys.service.SystemService;
import com.zhbit.xuexin.sys.utils.TreeUtil;

/**
 * 系统初始化业务实现
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-1-1 下午6:04:44
 * @version 1.0
 */
@Service("systemService")
@Transactional
public class SystemServiceImpl implements SystemService {
    @Autowired
    @Qualifier("systemDao")
    private SystemDao systemDao;

    /**
     * @Title: initLogin
     * @Description: TODO(用户登录时初始化。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    @Override
    public void initLogin(HttpSession session, User user) {
    	List<Authority> auths = null;
        if(user.getUserType().equals(Const.user_type[1])){
        	List<Role> roles = systemDao.getUserRoles2();
            session.setAttribute(Const.SESSION_ROLE, roles);
            auths = systemDao.getStudentAuthorities();
        }else{
        	List<Role> roles = systemDao.getUserRoles(user);
            session.setAttribute(Const.SESSION_ROLE, roles);
            auths = systemDao.getUserAuthorities(user.getUserId());
        }
        // 链接所对应的操作类型权限-----------start
        TreeUtil myAuth = new TreeUtil(auths != null ? auths : new ArrayList<Authority>());
        Map<String, List<Integer>> map = myAuth.linkAuthority();
        session.setAttribute(Const.SESSION_URL_TYPE, map);
        // 链接所对应的操作类型权限-----------end
    }

    /**
     * @Title: initStuLogin
     * @Description: TODO(学生用户登录时初始化。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    @Override
    public void initStuLogin(HttpSession session) {
        List<Role> roles = systemDao.getStuRoles();
        session.setAttribute(Const.SESSION_ROLE, roles);

        // 链接所对应的操作类型权限-----------start
        TreeUtil myAuth = new TreeUtil(Const.STU_AUTH);
        Map<String, List<Integer>> map = myAuth.linkAuthority();
        session.setAttribute(Const.SESSION_URL_TYPE, map);
        // 链接所对应的操作类型权限-----------end
    }

    /**
     * @Title: initSystem
     * @Description: TODO(初始化系统常用数据映射。)
     * @author 梁日宇
     * @date 2016-1-1 下午6:03:16
     * @return void
     */
    @Override
    public void initSystem() {
        initAdminRoleAuthority();// 1、先初始化管理员权限
        initRoleLink();// 2、再初始化角色对应的链接
        // 初始化学生权限
        Const.STU_AUTH = systemDao.getStudentAuthorities();
        Const.STU_MENU = systemDao.getStudentMenu();
        
        //机构名称->机构id(方便导入时使用)
        initOrgNameId();
    }

    /**
     * @Title: initRoleLink
     * @Description: TODO(初始化角色对应的操作链接 roleId->List[link]。)
     * @author 梁日宇
     * @date 2016-1-1 下午10:57:18
     * @return void
     */
    public void initRoleLink() {
        Const.role_links = systemDao.getRoleLink();
    }

    /**
     * @Title: initAdminRoleAuthority
     * @Description: TODO(初始化管理员角色权限。)
     * @author 梁日宇
     * @date 2016-1-3 上午12:32:01
     * @return void
     */
    @Override
    public void initAdminRoleAuthority() {
        systemDao.initAdminRoleAuthority();
    }

    /**
     * @Title: initOrgNameId
     * @Description: TODO(机构名称->机构id(方便导入时使用)。)
     * @author liangriyu
     * @date 2016-3-6 下午11:52:38
     * @return void
     */
    public void initOrgNameId(){
        List<Organization> list = systemDao.getOrgs();
        if(list!=null){
            for(Organization org:list){
                Const.ORG_ID_MAP.put(org.getOrgName(), org.getOrgId());
            }
        }
    }
}
