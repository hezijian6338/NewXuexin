package com.zhbit.xuexin.common.action;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.Role;
import com.zhbit.xuexin.domain.User;
import com.zmcn.framework.common.util.CommonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 会话拦截器
 * 
 * @author <b>梁日宇<br/>
 *         email:1912813835@qq.com</b>
 * @version 1.0
 * 
 */
public class AuthorizationInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6338933709326212120L;
	private static Log log = LogFactory.getLog(AuthorizationInterceptor.class);

	/**
	 * 无限制链接
	 */
	private static List<String> freeLink = new ArrayList<String>();
	/**
	 * 基础功能权限链接免认证
	 */
	private static List<String> baseLink = new ArrayList<String>();
	static {
		freeLink.add("sys/loginAction_goLogin.action");
		freeLink.add("sys/loginAction_login.action");
		freeLink.add("sys/loginAction_logout.action");
		// 基础功能免认证
		baseLink.add("sys/loginAction_main.action");
		baseLink.add("sys/loginAction_mainBody.action");
		baseLink.add("sys/loginAction_leftMenu.action");
		baseLink.add("sys/authorityAction_getAuthorityTypeList.action");
		baseLink.add("sys/loginAction_leftMenu.action");
		baseLink.add("sys/loginAction_leftMenu.action");
		baseLink.add("sys/organizationAction_getOrgTree.action");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//return invocation.invoke();
		printPara(invocation);
		HttpServletRequest req = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse res = (HttpServletResponse) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		try {
			// 请求路径
			String reqUrl = req.getRequestURI().substring(1);
			reqUrl = reqUrl.substring(reqUrl.indexOf("/") + 1);
			String url = reqUrl;
			String p = req.getQueryString();
			if (null != p) {
				reqUrl += "?" + p;
			}
//			System.out.println("------------------------------------reqUrl="
//					+ reqUrl);

			Map<String, Object> session = invocation.getInvocationContext()
					.getSession();

			// 无限制链接
			for (String link : freeLink) {
				if (reqUrl.startsWith(link)) {
					return invocation.invoke();
				}
			}

			// 会话验证
			final User user = (User) session.get(Const.SESSION_USER);
			if (null == user) {
				log.info("没登入或者用户会话已经失效！");
				res.setCharacterEncoding("utf-8");
				res.setContentType("text/html; charset=utf-8");
				res.getWriter().write(errStr("没登入或者用户会话已经失效！", req));
				return null;
			}

			// 无限制链接
			for (String link : baseLink) {
				if (reqUrl.startsWith(link)) {
					return invocation.invoke();
				}
			}

//			System.out
//					.println("------------------------------------用户权限验证=start...");
			// 用户权限验证
			List<Role> roles = (List<Role>) session.get(Const.SESSION_ROLE);// 获取用户归属角色
			if (roles != null && roles.size() > 0) {
				Boolean isAdmin = false;
				for (Role role : roles) {
					if (role.getRoleId().equals(Const.admin_roleId)) {
						isAdmin = true;
					}
				}
				if (isAdmin) { // 超级管理员角色
					return invocation.invoke();
				} else {
					for (int i = 0; i < roles.size(); i++) {
						List<String> userLinks = new ArrayList<String>();
						userLinks = Const.role_links.get(roles.get(i)
								.getRoleId());
						// System.out.println("------------------------------------userLinks="+userLinks.size());
						if (null != userLinks) {
							for (String link : userLinks) {
//								System.out
//										.println("------------------------------------用户权限验证="
//												+ link);
								if (reqUrl.startsWith(link)) {
									//req.setAttribute(Const.REQUEST_URL, reqUrl);
									req.setAttribute(Const.REQUEST_URL, url);//去除连接所带的参数,避免自定义标签处无法匹配
//									System.out
//											.println("------------------------------------用户权限验证=success");
									return invocation.invoke();
								}
							}
						}
					}
				}

			}

			// 无权限
			log.info("无访问此链接的权限！");
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			res.getWriter().write(errStr("无权限访问此链接！", req));
		} catch (Exception e) {
			log.error("会话拦截器异常！" + e.getMessage(), e);
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html; charset=utf-8");
			res.getWriter().write(errStr("服务端发生数据异常！", req));
		}
		return null;
	}

	/**
	 * 合成跳转脚本
	 * 
	 * @param s
	 * @return
	 */
	private static String errStr(String s, HttpServletRequest request) {
		String loginPath = CommonUtil.getBasePathSeparator(request)
				+ "sys/loginAction_goLogin.action";
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("alert('" + s + "');");
		sb.append("if(window.parent.parent.parent){");
		sb.append("window.parent.parent.parent.location.href=\"" + loginPath
				+ "\";");
		sb.append("}");
		sb.append("else if(window.parent.parent){");
		sb.append("window.parent.parent.location.href=\"" + loginPath + "\";");
		sb.append("}");
		sb.append("else if(window.parent){");
		sb.append("window.parent.location.href=\"" + loginPath + "\";");
		sb.append("}");
		sb.append("else{");
		sb.append("location.href=\"" + loginPath + "\";");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}

	/**
	 * 打印请求参数
	 * 
	 * @param invocation
	 */
	@SuppressWarnings("rawtypes")
	private static void printPara(ActionInvocation invocation) {
		try {
			HttpServletRequest req = (HttpServletRequest) invocation
					.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			String reqUrl = req.getRequestURL().toString();
			StringBuffer sb = new StringBuffer("\r\n[")
					.append(req.getRemoteHost()).append("]request-->")
					.append(reqUrl);
			Map map = req.getParameterMap();
			for (Iterator item = map.keySet().iterator(); item.hasNext();) {
				String key = (String) item.next();
				for (String v : (String[]) map.get(key)) {
					sb.append("\r\n------para->").append(key).append("=")
							.append(v);
				}
			}
			log.info(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
