package com.zhbit.xuexin.sys.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.sys.service.RoleService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2015-12-17 下午11:29:24
 * @version 1.0
 */
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 2103296657095252787L;

    private String roleId;// 角色id

    private String roleNo;

    private String roleName;

    private String memo;

    private int page;// 当前页

    private int rows;// 页面大小
    
    private String ids;//角色ids

    @Resource
    private RoleService roleService;

    /**
     * 
     * 
     * @Title: viewList
     * @Description: TODO(角色列表页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String index() {
        return "index";
    }

    /**
     * 
     * 
     * @Title: viewAdd
     * @Description: TODO(新增页面)
     * @author 梁日宇
     * @date 2015-12-20 下午4:39:40
     * @return
     * @return String
     */
    public String viewAdd() {
        return "viewAdd";
    }

    /**
     * 
     * 
     * @Title: viewEdit
     * @Description: TODO(修改页面)
     * @author 梁日宇
     * @date 2015-12-20 下午4:40:02
     * @return
     * @return String
     */
    public String viewEdit() {
        return "viewEdit";
    }

    /**
     * 
     * 
     * @Title: viewTab
     * @Description: TODO(tab页面)
     * @author 梁日宇
     * @date 2015-12-20 下午4:40:24
     * @return
     * @return String
     */
    public String viewTab() {
        return "viewTab";
    }

    /**
     * 获取所有用户
     * 
     * @author 梁日宇
     * @return
     */
    public void getRoleList() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Page<Role> page = roleService.getRoleList(new Page<Role>(req));
            //
            map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }

    /**
     * @Title: getRoles
     * @Description: TODO(通过获取角色列表。)
     * @author 梁日宇
     * @date 2015-12-17 下午11:51:08
     * @return void
     */
    public void getRoles() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            List<Role> list = roleService.getRoles();
            map.put("result", list);
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
     * @Title: getRole
     * @Description: TODO(获取角色。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:06:24
     * @return void
     */
    public void getRole() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            Role info = roleService.getRoleById(roleId);
            map.put("info", info);
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
     * @Title: updateRole
     * @Description: TODO(修改角色信息。)
     * @author 梁日宇
     * @date 2015-12-28 下午11:52:03
     * @return void
     */
    public void updateRole() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            roleService.updateRole(roleId, roleNo, roleName, memo);
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
     * @Title: saveRole
     * @Description: TODO(保存角色信息)
     * @author Administrator
     * @date 2015-12-28 下午11:53:28
     * @return void
     */
    public void saveRole() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            roleService.saveRole(roleNo, roleName, memo);
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
     * @Title: delete
     * @Description: TODO(删除用户)
     * @author 梁日宇
     * @date 2015-12-21 下午10:09:27
     * @return void
     */
    public void deleteRole() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            roleService.deleteRole(ids);
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

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    
    

}
