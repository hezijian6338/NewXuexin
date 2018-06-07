package com.zhbit.xuexin.sys.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.UserDao;
import com.zhbit.xuexin.sys.vo.UserVO;

/**
 * 
 * 用户管理持久化接口实现
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * 获取用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    @SuppressWarnings({ "unchecked" })
    public Page<UserVO> getUserList(Page<UserVO> page) {
        // System.out.println("------>1111");
        String hqlResult = "select new " + UserVO.class.getName() + UserVO.STRUCT;
        String hqlCount = "select count(user)";
        StringBuffer sb = new StringBuffer();
        sb.append(" from ").append(User.class.getName()).append(" user");
        sb.append(" where 1=1");
//        sb.append(" and user.userType").append("=").append(Const.user_type[2]);
        sb.append(" and user.employNo").append(" <> '").append(Const.admin_userno).append("'");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("employName".equals(key) || "employNo".equals(key)) {
                sb.append(" and user.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if ("userType".equals(key)) {
                sb.append(" and user.").append(key).append(" = ?");
                ps.add(page.getParas().get(key));
            }
        }
        // do count
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by user.employNo");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        // 组装值对象
        List<UserVO> userList = q.list();
        for (UserVO vo : userList) {
            String sql = "select * from TB_SYS_ROLE where ROLE_ID in (select ROLE_ID from TB_SYS_USER_ROLE where USER_ID = ?)";
            SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addEntity(Role.class);
            query.setParameter(0, vo.getUserId());
            List<Role> roles = query.list();
            List<String> roleIds = new ArrayList<String>();
            List<String> roleNames = new ArrayList<String>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
                roleNames.add(role.getRoleName());
            }
            vo.setRoleIds(roleIds);
            vo.setRoleNames(roleNames);
        }

        page.setResult(userList);
        return page;
    }

    /**
     * @Title: getUserByNo
     * @Description: TODO(通过用户编号获取启用用户)
     * @author 梁日宇
     * @date 2015-12-10 下午9:49:25
     * @param employNo
     * @return
     * @return User
     */
    @SuppressWarnings("unchecked")
    @Override
    public User getUserByNo(String employNo) {
        String hql = "From User where employNo= ?";
        List<User> list = (List<User>) hibernateTemplate.find(hql, new Object[] { employNo});
        return list.isEmpty() ? null : list.get(0);
    }

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
    @Override
    public void saveUser(User user) {
        hibernateTemplate.save(user);
    }

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
    @Override
    public User getUserById(String userId) {
        return hibernateTemplate.get(User.class, userId);
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
    @SuppressWarnings("unchecked")
    @Override
    public UserVO getUserVOById(String userId) {
        String hqlResult = "select new " + UserVO.class.getName() + UserVO.STRUCT;
        StringBuffer sb = new StringBuffer();
        sb.append(" from ").append(User.class.getName()).append(" user");
        sb.append(" where user.userId=?");
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlResult + sb.toString());
        q.setParameter(0, userId);
        // 组装值对象
        List<UserVO> userList = q.list();
        if(userList!=null){
            UserVO vo = userList.get(0);
            String sql = "select * from TB_SYS_ROLE where ROLE_ID in (select ROLE_ID from TB_SYS_USER_ROLE where USER_ID = ?)";
            SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addEntity(Role.class);
            query.setParameter(0, vo.getUserId());
            List<Role> roles = query.list();
            List<String> roleIds = new ArrayList<String>();
            List<String> roleNames = new ArrayList<String>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
                roleNames.add(role.getRoleName());
            }
            vo.setRoleIds(roleIds);
            vo.setRoleNames(roleNames);
            return vo;
        }  
        return null;
    }

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
    @Override
    public void updateUser(User user) {
        hibernateTemplate.update(user);     
    }

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
    @Override
    public void deleteUser(User user) {
        hibernateTemplate.delete(user);    
    }

    /**
     * 获取所有用户列表
     * 
     * @param page
     * @author 梁日宇
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<UserVO> getUsers(Page<UserVO> page) {
        String hqlResult = "select new " + UserVO.class.getName() + UserVO.STRUCT;
        String hqlCount = "select count(user)";
        StringBuffer sb = new StringBuffer();
        sb.append(" from ").append(User.class.getName()).append(" user");
        sb.append(" where 1=1");
        sb.append(" and user.employNo").append(" <> '").append(Const.admin_userno).append("'");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("employName".equals(key) || "employNo".equals(key)) {
                sb.append(" and user.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if ("userType".equals(key)) {
                sb.append(" and user.").append(key).append(" = ?");
                ps.add(page.getParas().get(key));
            }
            if ("orgId".equals(key)) {
              sb.append(" and user.organization.").append(key).append(" = ?");
              ps.add(page.getParas().get(key));
          }
        }
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by user.employNo");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        // 组装值对象
        List<UserVO> userList = q.list();
        for (UserVO vo : userList) {
            String sql = "select * from TB_SYS_ROLE where ROLE_ID in (select ROLE_ID from TB_SYS_USER_ROLE where USER_ID = ?)";
            SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql)
                    .addEntity(Role.class);
            query.setParameter(0, vo.getUserId());
            List<Role> roles = query.list();
            List<String> roleIds = new ArrayList<String>();
            List<String> roleNames = new ArrayList<String>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
                roleNames.add(role.getRoleName());
            }
            vo.setRoleIds(roleIds);
            vo.setRoleNames(roleNames);
        }
        page.setResult(userList);
        return page;
    }

}
