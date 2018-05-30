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
import com.zhbit.xuexin.domain.CommonScholarship;
import com.zhbit.xuexin.domain.CountryScholarship;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-19 下午9:35:25
 *@version 1.0
 */
@Repository("countryScholarshipDao")
public class CountryScholarshipDaoImpl implements com.zhbit.xuexin.student.dao.CountryScholarshipDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    @SuppressWarnings("unchecked")
    @Override
    public Page<CountryScholarship> getList(Page<CountryScholarship> page) {
        String hqlResult = "From " + CountryScholarship.class.getName() + " info";
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
            if ("orgName".equals(key)) {
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
                .createQuery(hqlResult + sb.toString() + " order by info.studentno");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<CountryScholarship> list = q.list();
        page.setResult(list);
        return page;
    }

    @Override
    public void save(CountryScholarship info) {
        hibernateTemplate.save(info);
    }

    @Override
    public void update(CountryScholarship info) {
        hibernateTemplate.update(info);
    }

    @Override
    public void delete(CountryScholarship info) {
        hibernateTemplate.delete(info);
    }

    @Override
    public CountryScholarship getCountryScholarshipByid(String id) {
        return hibernateTemplate.get(CountryScholarship.class, id);
    }

	@Override
	public int getCountryScholarshipExist(String studentno, String academicyear, String term) {
		studentno="'"+studentno+"'";
		academicyear="'"+academicyear+"'";
		term="'"+term+"'";
		Query qCount = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"From " + CountryScholarship.class.getName()
								+ " info where STUDENTNO=" + studentno
								+ " and academicyear=" + academicyear
								+ " and term=" + term);
		List list = qCount.list();
		return list.size();
	}

}
