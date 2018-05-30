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
import com.zhbit.xuexin.domain.PoliticalStatus;
import com.zhbit.xuexin.student.dao.PoliticalStatusDao;

/**
 *党团关系持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-11 下午1:07:13
 *@version 1.0
 */
@Repository("politicalStatusDao")
public class PoliticalStatusDaoImpl implements PoliticalStatusDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<PoliticalStatus>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<PoliticalStatus> getList(Page<PoliticalStatus> page) {
        String hqlResult = "From "+PoliticalStatus.class.getName()+" info";
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
        List<PoliticalStatus> list = q.list();
        page.setResult(list);
        return page;
    }

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-14 上午2:49:53
     * @param ps
     * @return
     */
    @Override
    public void save(PoliticalStatus ps) {
        hibernateTemplate.save(ps);
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-14 上午2:50:30
     * @return void
     */
    @Override
    public void update(PoliticalStatus ps) {
        hibernateTemplate.update(ps);
    }

    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param ids
     * @return void
     */
    @Override
    public void delete(PoliticalStatus info) {
        hibernateTemplate.delete(info);
    }

    /**
     * @Title: getPoliticalStatusById
     * @Description: TODO(获取党团关系对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return PoliticalStatus
     */
    @Override
    public PoliticalStatus getPoliticalStatusById(String id) {
        return hibernateTemplate.get(PoliticalStatus.class, id);
    }

	@Override
	public boolean CheckIfPoliticalStatusIsExists(String studentno ,String politicalstatus) {
		String hql = "From "+PoliticalStatus.class.getName()+" info where info.studentno=? and info.politicalstatus=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, studentno);
		query.setParameter(1, politicalstatus);
		//System.out.println("检验的列表："+query.list().size()+" "+query.getQueryString());
 		return query.list().size()==0;
	}

	@Override
	public boolean CheckPoliticalStatusUpdateLegitimacy(String politicalstatusid, String politicalstatus) {
		String hql = "From "+PoliticalStatus.class.getName()+" info where info.id!=? and info.politicalstatus=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, politicalstatusid);
		query.setParameter(1, politicalstatus);
		//System.out.println("检验的列表："+query.list().size()+" "+query.getQueryString());
 		return query.list().size()==0;
	}
}
