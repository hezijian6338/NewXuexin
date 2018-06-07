package com.zhbit.xuexin.sys.service;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LoginLog;

/**
 * 登录日志记录业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-2-25 上午10:23:16
 * @version 1.0
 */
public interface LoginLogService {

    /**
     * @Title: saveLog
     * @Description: TODO(记录登录日志。)
     * @author liangriyu
     * @date 2016-2-25 上午10:16:51
     * @param employNo
     *            用户编号
     * @param custIp
     *            客户端IP
     * @param status
     *            登录状态
     * @param parentOrgId 
     * @return void 0-成功 1-失败
     */
    void saveLog(String employNo, String custIp, int status, String parentOrgId);

    /**
     * @Title: getLoginLogList
     * @Description: TODO(获取登录日志信息列表。)
     * @author liangriyu
     * @date 2016-2-28 下午10:19:35
     * @param page
     * @return
     * @return Page<LoginLog>
     */
    Page<LoginLog> getLoginLogList(Page<LoginLog> page);
}
