package com.zhbit.xuexin.sys.dao;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.vo.UserVO;

/**
 * 
 * 用户管理持久化接口
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
public interface UserDao {

    /**
     * 获取用户列表，用户类型为3
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
     * 
     * 
     * @Title: saveUser
     * @Description: TODO(保存用户对象)
     * @author 梁日宇
     * @date 2015-12-16 下午8:47:19
     * @param u
     * @return void
     */
    public void saveUser(User u);

    /**
     * 
     * 
     * @Title: getUserById
     * @Description: TODO(获取用户。)
     * @author 梁日宇
     * @date 2015-12-20 下午10:10:58
     * @param userId
     *            用户id
     * @return void
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
     * @Description: TODO(更新用户)
     * @author 梁日宇
     * @date 2015-12-20 下午11:42:10
     * @param user
     * @return void
     */
    public void updateUser(User user);

    /**
     * 
     * 
     * @Title: deleteUser
     * @Description: TODO(删除用户)
     * @author 梁日宇
     * @date 2015-12-21 下午10:17:06
     * @param user
     * @return void
     */
    public void deleteUser(User user);
    
    /**
     * 获取所有用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    public Page<UserVO> getUsers(Page<UserVO> page);

}
