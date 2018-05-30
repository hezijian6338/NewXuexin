package com.zhbit.xuexin.sys.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LoginLog;
import com.zhbit.xuexin.sys.dao.LoginLogDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-2-25 上午10:20:32
 *@version 1.0
 */

@Repository("loginLogDao")
public class LoginLogDaoImpl implements LoginLogDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    /**
     * 保存登录日志
     * @author liangriyu
     */
    @Override
    public void saveLoginLog(LoginLog loginLog) {
        hibernateTemplate.save(loginLog);
    }

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
    @Override
    public Page<LoginLog> getLoginLogList(Page<LoginLog> page) throws ParseException {
        String hqlResult = "from " +LoginLog.class.getName()+" log";
        String hqlCount = "select count(*) ";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("employNo".equals(key)) {
                sb.append(" and log.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if("startTime".equals(key)){
                sb.append(" and log.loginTime").append(" >=?");
                ps.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(page.getParas().get(key)+" 00:00:00"));
            }
            if("endTime".equals(key)){
                sb.append(" and log.loginTime").append(" <?");
                ps.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(page.getParas().get(key)+" 24:59:59"));
            }
        }
        sb.append(" order by log.loginTime desc");
        // do count
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hqlResult + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by log.loginTime");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        @SuppressWarnings("unchecked")
        List<LoginLog> list = q.list();
        page.setResult(list);
        return page;
    }

    
    

}
