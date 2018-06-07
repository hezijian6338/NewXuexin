package com.zhbit.xuexin.sys.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LoginLog;
import com.zhbit.xuexin.sys.dao.LoginLogDao;
import com.zhbit.xuexin.sys.service.LoginLogService;

/**
 * 登录日志
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-25 上午10:25:06
 * @version 1.0
 */
@Service("loginLogService")
@Transactional(readOnly=true)
public class LoginLogServiceImpl implements LoginLogService {
    
    @Autowired
    @Qualifier("loginLogDao")
    private LoginLogDao loginLogDao;
    
    /**
     * (not Javadoc)
     * <p>
     * Title: saveLog
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param employNo
     * @param custIp
     * @param status
     * @see com.zhbit.xuexin.sys.dao.LoginLogDao#saveLog(java.lang.String,
     *      java.lang.String, int)
     */
    @Transactional(readOnly=false)
    @Override
    public void saveLog(String employNo, String custIp, int status, String parentOrgId) {
        LoginLog loginLog = new LoginLog();
        loginLog.setEmployNo(employNo);
        loginLog.setLoginIp(custIp);
        loginLog.setIfSuccess(status);
        loginLog.setLoginTime(new Date());
        loginLog.setParentOrgId(parentOrgId);
        loginLogDao.saveLoginLog(loginLog);
    }

    /**
     * @Title: getLoginLogList
     * @Description: TODO(获取登录日志信息列表。)
     * @author liangriyu
     * @date 2016-2-28 下午10:19:35
     * @param page
     * @return
     * @return Page<LoginLog>
     */
    @Override
    public Page<LoginLog> getLoginLogList(Page<LoginLog> page) {
        Page<LoginLog> p=null;
        try {
            p = loginLogDao.getLoginLogList(page);
        }
        catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return p;
    }
    

}
