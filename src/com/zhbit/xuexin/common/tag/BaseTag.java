package com.zhbit.xuexin.common.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Role;


/**
 * 
 * 权限控制基础标签
 * 
 * @author <b>梁日宇<br/>
 * @version 1.0
 */
public class BaseTag extends TagSupport {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4801051094040520289L;

	/**
	 * 操作属性
	 */
	private String operateName;

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@SuppressWarnings("unchecked")
    @Override
	public int doStartTag() throws JspException {
		int result = SKIP_BODY;
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		List<Role> roles = (List<Role>) req.getSession().getAttribute(Const.SESSION_ROLE);//获取用户归属角色
		Boolean isAdmin = false;
		if(roles!=null && roles.size()>0){
		    for(Role role:roles){
		        if(role.getRoleId().equals(Const.admin_roleId)){
		            isAdmin = true;
		        }
		    }
		}
		if (isAdmin) { // 超级管理员角色
			result = EVAL_BODY_INCLUDE;
		} else {
			String url = (String) req.getAttribute(Const.REQUEST_URL);
			Map<String, List<Integer>> map = (Map<String, List<Integer>>) req.getSession().getAttribute(Const.SESSION_URL_TYPE);
			List<Integer> type = map.get(url);
			if(type!=null && type.size()>0){
			    for(int i=0;i<type.size();i++){
//			        System.out.println("-----------------------------operateName="+operateName);
//			        System.out.println("-----------------------------Const.authTypeMap="+Const.authTypeMap.get(type.get(i)));
			        if(operateName.equals(Const.authTypeMap.get(type.get(i)))){
			            result = EVAL_BODY_INCLUDE;
			        }
			    }
			}
		}
		return result;
	}
}
