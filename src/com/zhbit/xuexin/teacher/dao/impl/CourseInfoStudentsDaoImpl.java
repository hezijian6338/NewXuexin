package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.teacher.dao.CourseInfoStudentsDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:51:37
 * @version 1.0
 */
@Repository("courseInfoStudentsDao")
public class CourseInfoStudentsDaoImpl implements CourseInfoStudentsDao {

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	public Session getSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	/**
	 * @Title: getList
	 * @Description: TODO(分页获取信息列表。)
	 * @author lry
	 * @date 2016-3-16 下午8:57:48
	 * @param page
	 * @return
	 * @return Page<CourseInfoStudents>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<CourseInfoStudents> getList(Page<CourseInfoStudents> page) {
		String hqlResult = "From " + CourseInfoStudents.class.getName() + " info";
		String hqlCount = "select count(info)";
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1");
		List<Object> ps = new ArrayList<Object>();
		for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
			String key = item.next();
			if ("studentno".equals(key) || "stuname".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}
			if ("coursecode".equals(key) || "coursename".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}
			if ("employNo".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add(page.getParas().get(key) + "%");
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
				.createQuery(hqlResult + sb.toString() + " order by info.id");
		for (int i = 0; i < ps.size(); i++) {
			q.setParameter(i, ps.get(i));
		}
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getRows());
		List<CourseInfoStudents> list = q.list();
		page.setResult(list);
		return page;
	}

	/**
	 * @Title: save
	 * @Description: TODO(保存信息。)
	 * @author lry
	 * @date 2016-3-16 下午8:58:54
	 * @param info
	 * @return void
	 */
	int i = 0;

	@Override
	public void save(CourseInfoStudents info) {
		// 2017.04.13xucaikai

		i++;
//		System.out.println("计数======发过的==" + i);
		hibernateTemplate.save(info);
		if (i % 200 == 0) {

			getSession().flush();
			getSession().clear();
		}
	}

	/**
	 * @Title: update
	 * @Description: TODO(修改信息。)
	 * @author lry
	 * @date 2016-3-16 下午8:59:22
	 * @param info
	 * @return void
	 */
	@Override
	public void update(CourseInfoStudents info) {
		hibernateTemplate.update(info);
	}

	/**
	 * @Title: delete
	 * @Description: TODO(删除信息。)
	 * @author lry
	 * @date 2016-3-16 下午8:59:46
	 * @param info
	 * @return void
	 */
	@Override
	public void delete(CourseInfoStudents info) {
		hibernateTemplate.delete(info);
	}

	/**
	 * @Title: getCourseInfoStudentsByid
	 * @Description: TODO(通过id获取信息。)
	 * @author lry
	 * @date 2016-3-16 下午9:00:24
	 * @param id
	 * @return
	 * @return CourseInfoStudents
	 */
	@Override
	public CourseInfoStudents getCourseInfoStudentsByid(String id) {
		return hibernateTemplate.get(CourseInfoStudents.class, id);
	}

	/**
	 * 
	 * @author 许彩开 email:1121836563@qq.com
	 * @date 创建时间：2017年4月14日 上午10:19:07
	 * @Description:TODO(通过学号查询是否存在该学号)
	 * @param id
	 * @return
	 */
	public int getCourseInfoStudentsByid_exist(String id) {
		String sql = "select * from T_STUDENTS where studentno=?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0, id);

		return query.list().size();
	}

	/**
	 * 
	 * @author 许彩开 email:1121836563@qq.com
	 * @date 创建时间：2017年4月20日 下午7:29:18
	 * @Description:TODO(通过学号，课程代码查询是否已存在)
	 * @param id
	 * @return
	 */
	public int getCourseInfoStudentsByT_COURSEINFO_STUDENTS_exist(String id, String courseCode) {
		String sql = "select * from T_COURSEINFO_STUDENTS where studentno=? and COURSECODE=?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.setParameter(1, courseCode);
		return query.list().size();
	}
	
	public int getCourseInfoStudentsByT_COURSEINFO_STUDENTS_selected_exist(String id, String selectedCourseNo) {
		String sql = "select * from T_COURSEINFO_STUDENTS where studentno=? and SELECTEDCOURSENO=?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0, id);
		query.setParameter(1, selectedCourseNo);
		return query.list().size();
	}

	/**
	 * @Title: getCourseDetail
	 * @Description: TODO(通过学号获取课程及成绩明细。)
	 * @author lry
	 * @date 2016-5-12 下午10:12:36
	 * @param studentno
	 * @return
	 * @return List<Object[]>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCourseDetail(String studentno) {
		String sql = "SELECT CI.ACADEMICYEAR,CI.TERM,CI.COURSECODE,CI.COURSENAME,CI.COURSETYPE,CI.BELONGTO,CI.CREDIT,CIS.GRADEPOINT,CIS.FINALSCORE,CIS.RETAKEFLAG,CIS.RESITSCORE,CIS.REPAIRSCORE,CIS.ORG_NAME,CIS.MEMO FROM T_COURSEINFO CI,T_COURSEINFO_STUDENTS CIS WHERE CI.COURSECODE = CIS.COURSECODE AND CIS.STUDENTNO=?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0, studentno);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseInfoStudents> getCourseInfoStudentNeedUpdateScore(String academicYear,
			String semester, String courceNo ,String employNo) {
		String hql = "From " + CourseInfoStudents.class.getName()+" info "
				+ "where info.academicYear=? "
				+ "and info.term=? and info.coursecode=? and info.employNo=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0,academicYear);
		query.setParameter(1, semester);
		query.setParameter(2, courceNo);
		query.setParameter(3, employNo);
		return query.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CourseInfoStudents> listByStuNoAndAcademicYearAndTerm(String stuNo, String academicYear, String term) {
		QueryHelper qh = new  QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.studentno=?", stuNo)
			.setWhereAND("c.academicYear=?", academicYear)
			.setWhereAND("c.term=?", term);
		return hibernateTemplate.find(qh.getHQL(),qh.getArgs().toArray());
	}
	@Override
	public void flush() {
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		
	}

	@Override
	public CourseInfoStudents getByStuNoAndCourseCodeAndAcademicYearAndTerm(String stuNo, String courseCode,
			String academicYear, String term) {
		QueryHelper qh = new  QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.studentno=?", stuNo)
			.setWhereAND("c.coursecode=?", courseCode)
			.setWhereAND("c.academicYear=?", academicYear)
			.setWhereAND("c.term=?", term);
		List<CourseInfoStudents> r = hibernateTemplate.find(qh.getHQL(),qh.getArgs().toArray());
		if(r.size()>1){
			throw new RuntimeException("选课信息不唯一:" + stuNo + ":"+ courseCode + ":"+ academicYear + ":"+ term);
		}
		return r.isEmpty() ? null : r.get(0);
	}

	@Override
	public void saveOrUpdate(CourseInfoStudents cs) {
		hibernateTemplate.saveOrUpdate(cs);
		
	}
}
