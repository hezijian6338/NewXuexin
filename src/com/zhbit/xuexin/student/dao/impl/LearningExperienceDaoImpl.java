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
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.dao.LearningExperienceDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-11 上午12:28:10
 *@version 1.0
 */
@Repository("learningExperienceDao")
public class LearningExperienceDaoImpl implements LearningExperienceDao {

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * @Title: getList
     * @Description: TODO(分页获取学习经历列表。)
     * @author liangriyu
     * @date 2016-3-11 上午12:37:47
     * @param page
     * @return
     * @return Page<LearningExperience>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<LearningExperience> getList(Page<LearningExperience> page) {
        String hqlResult = "From "+LearningExperience.class.getName()+" experience";
        String hqlCount = "select count(experience)";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("stuname".equals(key) || "studentno".equals(key)) {
                sb.append(" and experience.").append(key).append(" like ?");
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
                .createQuery(hqlResult + sb.toString() + " order by experience.studentno");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<LearningExperience> list = q.list();
        page.setResult(list);
        return page;
    }

    /**
     * @Title: saveLearningExperience
     * @Description: TODO(保存学习经历信息。)
     * @author liangriyu
     * @date 2016-3-11 上午1:05:35
     * @param experience
     * @return void
     */
    @Override
    public void saveLearningExperience(LearningExperience experience) {
        hibernateTemplate.save(experience);
    }

    /**
     * @Title: getLearningExperienceById
     * @Description: TODO(通过id获取学习经历信息)
     * @author liangriyu
     * @date 2016-3-11 上午1:27:54
     * @param id
     * @return
     * @return LearningExperience
     */
    @Override
    public LearningExperience getLearningExperienceById(String id) {
        return hibernateTemplate.get(LearningExperience.class, id);
    }

    /**
     * @Title: updateLearningExperience
     * @Description: TODO(修改学习经历信息。)
     * @author liangriyu
     * @date 2016-3-11 上午1:05:35
     * @param experience
     * @return void
     */
    @Override
    public void updateLearningExperience(LearningExperience experience) {
        hibernateTemplate.update(experience);
    }

    /**
     * @Title: deleteLearningExperience
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-11 上午1:38:05
     * @param experience
     * @return void
     */
    @Override
    public void deleteLearningExperience(LearningExperience experience) {
        hibernateTemplate.delete(experience);
    }

	@Override
	public Page<LearningExperience> getSelf(Page<LearningExperience> page,
			Students stu) {
		if(null != stu){
			String hqlResult = "From "+LearningExperience.class.getName()+" l where l.studentno=? ";
	        String hqlCount = "select count(l) ";
	 
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
	        List<LearningExperience> list = q.list();
	        page.setResult(list);
		}else{
			page.setCount(0);
			page.setResult(new ArrayList<LearningExperience>(0));
		}
        return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LearningExperience> listByStuno(String stuno) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(LearningExperience.class, "l");
		qh.setWhere("l.studentno=?", stuno);
		return hibernateTemplate.find(qh.getHQL(), qh.getArgs().toArray());
	}
}
