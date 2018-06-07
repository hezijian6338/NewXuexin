package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.dao.impl.BaseDaoImpl;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.StuScoreCarify;
import com.zhbit.xuexin.teacher.dao.StuScoreCarifyDao;

@Repository("StuScoreCarifyDao")
public class StuScoreCarifyDaoImpl extends BaseDaoImpl<StuScoreCarify> implements StuScoreCarifyDao{

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<StuScoreCarify> getStuScoreCarifyList(Page<StuScoreCarify> page, String teano, String selectCourseNo) {
		String hql = "From "+StuScoreCarify.class.getName()+" info "
				+ "where info.teacherno=? and info.selectedcourseno=?";
		String hqlCount = "select count(info) ";
		Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hql);
		qCount.setParameter(0, teano);
		qCount.setParameter(1, selectCourseNo);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hql);
		q.setParameter(0, teano);
		q.setParameter(1, selectCourseNo);
		Long count = (Long) qCount.uniqueResult();
		page.setCount(count);
		System.out.println("获取的数据"+count);
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getRows());
        List<StuScoreCarify> list = q.list();
        page.setResult(list);
		return page;
	}


	@Override
	public Page<StuScoreCarify> getStuScoreCarifyListByStudentInfo(Page<StuScoreCarify> page, String year, String term,
			String studentno) {
		String hql = "From "+StuScoreCarify.class.getName()+" info "
				+ "where info.academicyear=? and info.term=?  and info.studentno=?";
		String hqlCount = "select count(info) ";
		Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hql);
		qCount.setParameter(0, year);
		qCount.setParameter(1, term);
		qCount.setParameter(2, studentno);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hql);
		q.setParameter(0, year);
		q.setParameter(1, term);
		q.setParameter(2, studentno);
		Long count = (Long) qCount.uniqueResult();
		page.setCount(count);
		System.out.println("获取的数据"+count);
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getRows());
        List<StuScoreCarify> list = q.list();
        page.setResult(list);
		return page;
	}

	@Override
	public Page<StuScoreCarify> getStuScoreCarifyListByGuideTeacher(Page<StuScoreCarify> page, String teano) {
		String hql = "from "+ StuScoreCarify.class.getName() +" info "
				+ "where info.studentno in ( select studentno from "+LearningGuidStudentsList.class.getName()+" a"
						+ " where  a.teacherno=?) ";
		String hqlCount = "select count(*) from ";
		Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		qCount.setParameter(0, teano);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hql);
		q.setParameter(0, teano);
		Long count = Long.parseLong(String.valueOf(qCount.list().size()));
		page.setCount(count);
		System.out.println("获取的数据"+count);
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getRows());
        List<StuScoreCarify> list = q.list();
        page.setResult(list);
		return page;
		
	}

}
