package com.zhbit.xuexin.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.service.UserRoleService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-24 下午9:05:36
 * @version 1.0
 */
@Controller("userRoleAction")
@Scope("prototype")
public class UserRoleAction extends BaseAction {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 7803161380399073360L;

    private String roleId;//角色id
    
    private String uids;//用户id

    @Resource
    UserRoleService userRoleService;

    /**
     * 
     * 
     * @Title: getUserByRoleId
     * @Description: TODO(通过关联表角色id获取用户列表)
     * @author 梁日宇
     * @date 2015-12-24 下午8:56:48
     * @return void
     */
    public void getUserByRoleId() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            List<User> result = userRoleService.getUserByRoleId(roleId);
            map.put("result", result);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * 
     * 
     * @Title: saveUserRole
     * @Description: TODO(保存用户角色。)
     * @author 梁日宇
     * @date 2015-12-24 下午10:05:08
     * @return void
     */
    public void saveUserRole() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            userRoleService.saveUserRole(uids,roleId);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }
    
    
}
