package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.EnrollTransaction;
import com.zhbit.xuexin.domain.StudentDutys;
import com.zhbit.xuexin.student.dao.EnrollTransactionDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:59
 *@version 1.0
 */
@Repository("enrollTransactionDao")
public class EnrollTransactionDaoImpl implements EnrollTransactionDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<EnrollTransaction>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<EnrollTransaction> getList(Page<EnrollTransaction> page) {
        String hqlResult = "From " + EnrollTransaction.class.getName() + " info";
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
            if ("ydqcollege".equals(key)||"ydhcollege".equals(key)) {
  
                sb.append(" and info.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
        }
        // do count
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlCount + hqlResult + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by info.createTime desc");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<EnrollTransaction> list = q.list();
        page.setResult(list);
        return page;
    }
    /**
     * @Title: getDateExist
     * @Description: TODO(通过学生异动的具体信息info来获取对象。根据学号和年级判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/29
     * @param info
     * @return list.size()
     */
	public int getDateExist(EnrollTransaction info) {
		String hql = "From EnrollTransaction info where info.studentno= ? and info.academicyear= ? and info.term=? ";
		List<EnrollTransaction> list = (List<EnrollTransaction>) hibernateTemplate.find(
				hql,new Object[] { info.getStudentno(), info.getAcademicyear(),info.getTerm() });
		return list.size();
	}
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    @Override
    public void save(EnrollTransaction info) {
        hibernateTemplate.save(info);
    }
    /**
     * @Title: EnrollTransaction
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return EnrollTransaction
     */
    @Override
    public EnrollTransaction getEnrollTransactionByid(String id) {
        return hibernateTemplate.get(EnrollTransaction.class, id);
    }
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    @Override
    public void update(EnrollTransaction info) {
        hibernateTemplate.update(info);
    }
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    @Override
    public void delete(EnrollTransaction info) {
        hibernateTemplate.delete(info);
    }

}
