package com.zhbit.xuexin.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.SecurityUtil;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.domain.UserRole;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.sys.dao.RoleDao;
import com.zhbit.xuexin.sys.dao.UserDao;
import com.zhbit.xuexin.sys.dao.UserRoleDao;
import com.zhbit.xuexin.sys.service.UserService;
import com.zhbit.xuexin.sys.vo.UserVO;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;

/**
 *用户管理业务接口实现
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2015-12-9 下午11:19:42
 *@version 1.0
 */
@Service("userService")
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	@Autowired
    @Qualifier("userRoleDao")
    private UserRoleDao userRoleDao;
	
	@Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
	
	@Autowired
    @Qualifier("organizationDao")
	private OrganizationDao organizationDao;
	
	@Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;
	
	@Autowired
    @Qualifier("teacherInfoDao")
    private TeacherInfoDao teacherInfoDao;

	/**
	 * (not Javadoc) 
	* <p>Title: getUserList</p> 
	* <p>Description: </p> 
	* @param page
	* @return
	* @throws Exception 
	* @see com.zhbit.xuexin.sys.service.UserService#getUserList(com.zhbit.xuexin.common.action.Page)
	 */
	@Override
	public Page<UserVO> getUserList(Page<UserVO> page)  {
		return userDao.getUserList(page);
	}

	/**
     * @Title: getUserByNo
     * @Description: TODO(通过用户编号获取用户)
     * @author 梁日宇
     * @date 2015-12-10 下午9:49:25
     * @param employNo
     * @return
     * @return User
     */
    @Override
    public User getUserByNo(String employNo) {
        return userDao.getUserByNo(employNo);
    }

    /** 
     * @Title: saveUser
     * @Description: TODO(保存用户)
     * @author 梁日宇
     * @date 2015-12-15 下午11:20:46
     * @param employNo
     * @param employName
     * @param sex
     * @param tell
     * @param status
     * @param address
     * @param email
     * @param roleIds 
     * @param companyId 
     * @param deptId 
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void saveUser(String employNo, String employName, String sex, String tell, Integer status, String address,
            String email, String orgId, String roleIds,String userType) {
        User u = new User();
        u.setEmployNo(employNo);
        u.setEmployName(employName);
        u.setSex(sex);
        u.setTell(tell);
        u.setStatus(status);
        u.setAddress(address);
        u.setEmail(email);
        u.setOrganization(organizationDao.getOrganizationById(orgId));
        u.setUserType(userType);
        //设置默认值
        u.setPassword(SecurityUtil.GetMD5Code(Const.defult_password));
        u.setCreateTime(new Date());
        userDao.saveUser(u);
        if(roleIds!=null && !"".equals(roleIds)){
          //插入角色关联表
            String[] rids = roleIds.split(",");
            for(String rid:rids){
                UserRole ur = new UserRole();
                ur.setUser(u);
                ur.setRole(roleDao.getRoleById(rid));
                userRoleDao.saveUserRole(ur);
            }
        }     
    }

    /**
     * @Title: getUserById
     * @Description: TODO(获取用户。)
     * @author 梁日宇
     * @date 2015-12-20 下午10:10:58
     * @param userId 用户id
     * @return void
     */
    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);     
    }

    /**
     * 
     * 
     * @Title: getUserVOById
     * @Description: TODO(获取用户扩展对象)
     * @author 梁日宇
     * @date 2015-12-20 下午10:29:42
     * @param userId
     *            用户id
     * @return
     * @return UserVO
     */
    @Override
    public UserVO getUserVOById(String userId) {
        return userDao.getUserVOById(userId);
    }

    /**
     * 
     * 
     * @Title: updateUser
     * @Description: TODO(修改用户信息。)
     * @author 梁日宇
     * @date 2015-12-20 下午11:35:03
     * @param userId
     * @param employNo
     * @param employName
     * @param sex
     * @param tell
     * @param status 启用状态
     * @param address
     * @param email
     * @param deptId
     * @param companyId
     * @param roleIds
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void updateUser(String userId, String employNo, String employName, String sex, String tell, Integer status,
            String address, String email, String orgId, String roleIds, String userType) {
        if(userId!=Const.admin_userId){//管理员信息不能更改
            User u = userDao.getUserById(userId);
            u.setEmployNo(employNo);
            u.setEmployName(employName);
            u.setSex(sex);
            u.setTell(tell);
            u.setStatus(status);
            u.setAddress(address);
            u.setEmail(email);
            u.setOrganization(organizationDao.getOrganizationById(orgId));
            u.setUserType(userType);
            //设置默认值
            //u.setPassword("123456");
            u.setCreateTime(new Date());
            userDao.updateUser(u);
            //删除角色用户关联表对应数据
            List<UserRole> list = userRoleDao.getUserRolesByUserId(userId);
            if(list!=null){
                for(UserRole ur:list){
                    userRoleDao.deleteUserRole(ur);
                }          
            }
            if(roleIds!=null||!"".equals(roleIds)){
              //插入角色关联表
                String[] rids = roleIds.trim().split(",");
                for(String rid:rids){
                    UserRole ur = new UserRole();
                    ur.setUser(u);
                    ur.setRole(roleDao.getRoleById(rid));
                    userRoleDao.saveUserRole(ur);
                }
            }  
        } 
    }

    /**
     * 
     * 
     * @Title: deleteUser
     * @Description: TODO(删除用户)
     * @author 梁日宇
     * @date 2015-12-21 下午10:11:52
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteUser(String ids) {
        if(ids!=null){
            String[] uids = ids.split(",");
            for(String userId:uids){
                User user = userDao.getUserById(userId);
                userDao.deleteUser(user);
            }
            
        }   
    }

    /**
     * @Title: updatePwd
     * @Description: TODO(修改密码。)
     * @author LRY
     * @date 2016-5-18 下午11:29:41
     * @param password 新密码
     * @param passwordOld 旧密码
     * @param user
     * @return
     * @return int 0-成功，1-密码不正确
     */
    @Override
    @Transactional(readOnly = false)
    public int updatePwd(String password, String passwordOld, User user) {
        if(user.getUserType().equals("1")){
            Students stu = studentsDao.getStudent(user.getUserId());
            if(!SecurityUtil.GetMD5Code(passwordOld).equals(stu.getPassword())){
                return 1;
            }
            stu.setPassword(SecurityUtil.GetMD5Code(password));
            studentsDao.updateStudents(stu);
        }else{
            User u = userDao.getUserById(user.getUserId());
            if(!SecurityUtil.GetMD5Code(passwordOld).equals(u.getPassword())){
                return 1;
            }
            u.setPassword(SecurityUtil.GetMD5Code(password));
            userDao.updateUser(u);
            if(user.getUserType().equals("0")){
                TeacherInfo tea = teacherInfoDao.getTeacherInfoByNo(user.getEmployNo());
                tea.setPassword(SecurityUtil.GetMD5Code(password));
                teacherInfoDao.update(tea);
            }
        }
        return 0;
    }
    
    /**
     * @Title: updatePwd
     * @Description: TODO(修改密码。)
     * @author LRY
     * @date 2016-5-18 下午11:29:41
     * @param password 新密码
     * @param passwordOld 旧密码
     * @param user
     * @return
     * @return int 0-成功，1-密码不正确
     */
    @Override
    @Transactional(readOnly = false)
    public int updatePwd2(String password, User user) {
        User u = userDao.getUserById(user.getUserId());
        u.setPassword(SecurityUtil.GetMD5Code(password));
        userDao.updateUser(u);
        
      
        return 0;
    }

    /**
     * 获取所有用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    @Override
    public Page<UserVO> getUsers(Page<UserVO> page) {
        return userDao.getUsers(page);
    }

}
