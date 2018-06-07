package com.zhbit.xuexin.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.domain.User;

/**
 * Struts2 Action基类,封装了将json对象response的方法
 * 
 * @author 梁日宇
 * @email 1912813835@qq.com
 * 
 */

public class CopyOfBaseAction extends ActionSupport {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -3494304233178216577L;

    /**
     * 日志对象
     */
    protected static final Log log = LogFactory.getLog(CopyOfBaseAction.class);

    /**
     * 取得HttpRequest的简化函数.
     */
    public static HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * 取得HttpResponse的简化函数.
     */
    public static HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * @Title: getUser
     * @Description: TODO(获取当前用户。)
     * @author Administrator
     * @date 2016-3-8 下午8:47:12
     * @return
     * @return User
     */
    public User getSessionUser() {
        return (User) getSession().getAttribute(Const.SESSION_USER);
    }

}
