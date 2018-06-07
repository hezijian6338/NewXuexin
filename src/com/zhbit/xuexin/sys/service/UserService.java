package com.zhbit.xuexin.sys.service;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.vo.UserVO;

/**
 * 
 * 用户管理接口
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
public interface UserService {

    /**
     * 获取用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    public Page<UserVO> getUserList(Page<UserVO> page);

    /**
     * @Title: getUserByNo
     * @Description: TODO(通过用户编号获取用户)
     * @author 梁日宇
     * @date 2015-12-10 下午9:49:25
     * @param employNo
     * @return
     * @return User
     */
    public User getUserByNo(String employNo);

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
     * @param orgId
     * @param roleIds
     * @param userType
     * @return void
     */
    public void saveUser(String employNo, String employName, String sex, String tell, Integer status, String address,
            String email, String orgId, String roleIds, String userType);

    /**
     * 
     * 
     * @Title: getUserById
     * @Description: TODO(获取用户。)
     * @author 梁日宇
     * @date 2015-12-20 下午10:10:58
     * @param userId
     *            用户id
     * @return User
     */
    public User getUserById(String userId);

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
    public UserVO getUserVOById(String userId);

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
     * @param status
     *            启用状态
     * @param address
     * @param email
     * @param deptId
     * @param companyId
     * @param roleIds
     * @param userType
     * @return void
     */
    public void updateUser(String userId, String employNo, String employName, String sex, String tell, Integer status,
            String address, String email, String orgId, String roleIds, String userType);

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
    public void deleteUser(String ids);

    /**
     * @Title: updatePwd
     * @Description: TODO(修改密码。)
     * @author LRY
     * @date 2016-5-18 下午11:29:41
     * @param password 新密码
     * @param passwordOld 旧密码
     * @param user
     * @return
     * @return int
     */
    public int updatePwd(String password, String passwordOld, User user);
    
    public int updatePwd2(String password, User user);
    
    /**
     * 获取所有用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    public Page<UserVO> getUsers(Page<UserVO> page);

}
