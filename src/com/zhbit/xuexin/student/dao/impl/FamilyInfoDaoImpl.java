/**   
 * Copyright (c) 1997-2016 Creawor All Rights Reserved
 * 地址: 珠海市高新区南方软件园B6栋2楼
 * 
 * 该软件是广东创我科技有限公司(下面简称创我科技)保密的信息和专利。
 * 非创我科技授权和许可，你不得披露该保密信息和不得使用它。 
 *
 * @Title: FamilyInfoDaoImpl.java 
 * @author Administrator
 * @Description: TODO(简单说明这个文件是做什么的。) 
 * @date 2016-3-10 下午1:09:15 
 * @version V1.0   
*/
package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.dao.FamilyInfoDao;

/**
 *学生家庭信息持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-10 下午1:09:15
 *@version 1.0
 */

@Repository("familyInfoDao")
public class FamilyInfoDaoImpl implements FamilyInfoDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    /**
     * @Title: getList
     * @Description: TODO(分页获取学生家庭信息。)
     * @author liangriyu
     * @date 2016-3-10 下午11:58:02
     * @param page
     * @return
     * @return Page<FamilyInfo>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<FamilyInfo> getList(Page<FamilyInfo> page) {
        String hqlResult = "From "+FamilyInfo.class.getName()+" info";
        String hqlCount = "select count(info)";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("stuname".equals(key) || "studentno".equals(key)) {
                sb.append(" and info.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
        }
     // do count
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hqlResult + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by info.studentno");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<FamilyInfo> list = q.list();
        page.setResult(list);
        return page;
    }
    /**
     * @Title: save 
     * @Description: TODO(新增家庭。) 
     * @author lry 
     * @date 2016-3-19 下午3:05:10 
     * @param info 
     * @return void
      */
    @Override
    public void save(FamilyInfo info) {
        hibernateTemplate.save(info);
    }
    /**
     * @Title: getFamilyInfo 
     * @Description: TODO(获取家庭信息。) 
     * @author lry 
     * @date 2016-3-19 下午3:10:28 
     * @param id
     * @return 
     * @return FamilyInfo
      */
    @Override
    public FamilyInfo getFamilyInfo(String id) {
        return hibernateTemplate.get(FamilyInfo.class, id);
    }
    /**
     * @Title: update 
     * @Description: TODO(修改家庭信息。) 
     * @author lry 
     * @date 2016-3-19 下午3:11:45 
     * @param info 
     * @return void
      */
    @Override
    public void update(FamilyInfo info) {
        hibernateTemplate.update(info);
    }
    /**
     * @Title: delete 
     * @Description: TODO(删除家庭信息。) 
     * @author lry 
     * @date 2016-3-19 下午3:12:16 
     * @param info 
     * @return void
      */
    @Override
    public void delete(FamilyInfo info) {
        hibernateTemplate.delete(info);
    }
	@Override
	public Page<FamilyInfo> getSelf(Page<FamilyInfo> page, Students stu) {
		if(null != stu){
			String hqlResult = "From "+FamilyInfo.class.getName()+" info where info.studentno=? ";
	        String hqlCount = "select count(info) ";
	 
	     // do count
	        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hqlResult);
	        qCount.setParameter(0, stu.getStudentno());
	        Long count = (Long) qCount.uniqueResult();
	        page.setCount(count);
	        // do result
	        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
	                .createQuery(hqlResult);
	        q.setParameter(0, stu.getStudentno());
	        q.setFirstResult(page.getFirst());
	        q.setMaxResults(page.getRows());
	        List<FamilyInfo> list = q.list();
	        page.setResult(list);
		}else{
			page.setCount(0);
			page.setResult(new ArrayList<FamilyInfo>(0));
		}
        return page;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FamilyInfo> listByStuno(String stuno) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(FamilyInfo.class, "f");
		qh.setWhere("f.studentno=?", stuno);
		return hibernateTemplate.find(qh.getHQL(), qh.getArgs().toArray());
	}
    
}
