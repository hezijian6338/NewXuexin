package com.zhbit.xuexin.sys.dao;

import java.text.ParseException;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LoginLog;

/**
 * 登录日志持久化接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-25 上午10:07:01
 * @version 1.0
 */

public interface LoginLogDao {

    /**
     * 保存登录日志
     * @author liangriyu
     */
    void saveLoginLog(LoginLog loginLog);

    /**
     * @Title: getLoginLogList
     * @Description: TODO(获取登录日志信息列表。)
     * @author liangriyu
     * @date 2016-2-28 下午10:19:35
     * @param page
     * @return
     * @return Page<LoginLog>
     * @throws ParseException 
     */
    Page<LoginLog> getLoginLogList(Page<LoginLog> page) throws ParseException;

    
}
