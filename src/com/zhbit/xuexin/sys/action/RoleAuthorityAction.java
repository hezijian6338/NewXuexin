/**
 * 
 */
package com.zhbit.xuexin.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.Authority;
import com.zhbit.xuexin.sys.service.RoleAuthorityService;


/**
 * 
 * 角色权限信息管理
 * 
 * @author <b>梁日宇<br/>
 *         Email:1912813835@qq.com</b>
 * @version 1.0
 */
@Controller("roleAuthorityAction")
@Scope("prototype")
public class RoleAuthorityAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651187540644585613L;

	@Resource
	private RoleAuthorityService roleAuthoritySevice;
	
	private String roleId;// 角色id
	
	private String aids;//权限id字符串

	/**
	 * 获取该角色已有权限
	 */
	public void getAuthoritiesByRoleId() {
	    Map<String, Object> map = Const.getJsonMap();
        try {
            List<Authority> authHad = roleAuthoritySevice
                    .getAuthoritiesByRoleId(roleId);
            map.put("result", authHad);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
	}
	
	public void saveRoleAuthority(){
	    //System.out.println("----------------------aids="+aids);
	    Map<String, Object> map = Const.getJsonMap();
        try {
            roleAuthoritySevice.saveRoleAuthority(roleId,aids);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception e) {
            e.printStackTrace();
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

    public String getAids() {
		return aids;
	}

	public void setAids(String aids) {
		this.aids = aids;
	}
	
	
}
