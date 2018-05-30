package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.dto.StuQuery;
import com.zhbit.xuexin.common.dto.StuScoreQuery;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.StudentsDao;

/**
 * 学生信息持久化
 *
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-1 下午3:29:51
 * @version 1.0
 */
@Repository("studentsDao")
public class StudentsDaoImpl implements StudentsDao {

	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/**
	 * @Title: getStuToUser
	 * @Description: TODO(通过学生编号获取信息封装成user对象。)
	 * @author liangriyu
	 * @date 2016-3-1 下午3:11:04
	 * @return
	 * @return User
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getStuToUser(String stuNo) {
		String hql = "From " + Students.class.getName()
				+ " stu where stu.studentno=?";
		List<Students> list = (List<Students>) hibernateTemplate.find(hql,
				new Object[] { stuNo });
		Students stu = list.isEmpty() ? null : list.get(0);
		User user = new User();
		if (stu != null) {
			user.setUserId(stu.getStuId());
			user.setEmployNo(stu.getStudentno());
			user.setEmployName(stu.getStuname());
			user.setUserType(Const.user_type[1]);
			user.setSex(stu.getSex());
			user.setParentOrgId(stu.getParentOrgId());
			user.setOrganization((Organization) hibernateTemplate.get(
					Organization.class, stu.getOrgId()));
			user.setCreateTime(stu.getCreateTime());
			user.setPassword(stu.getPassword());
		}
		return user;
	}

	/**
	 * @Title: getList
	 * @Description: TODO(获取学生用户列表。)
	 * @author liangriyu
	 * @date 2016-3-3 上午2:00:33
	 * @param page
	 * @return
	 * @return Page<Students>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Students> getList(Page<Students> page) {
		String hqlResult = "From " + Students.class.getName() + " stu";
		String hqlCount = "select count(stu)";
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1");
		List<Object> ps = new ArrayList<Object>();
		for (Iterator<String> item = page.getParas().keySet().iterator(); item
				.hasNext();) {
			String key = item.next();
			if ("stuname".equals(key) || "studentno".equals(key)
					|| "orgName".equals(key) || "major".equals(key)) {
				sb.append(" and stu.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}
		}
		// do count
		Query qCount = hibernateTemplate.getSessionFactory()
				.getCurrentSession()
				.createQuery(hqlCount + hqlResult + sb.toString());
		for (int i = 0; i < ps.size(); i++) {
			qCount.setParameter(i, ps.get(i));
		}
		Long count = (Long) qCount.uniqueResult();
		page.setCount(count);
		// do result
		Query q = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						hqlResult + sb.toString() + " order by stu.studentno");
		for (int i = 0; i < ps.size(); i++) {
			q.setParameter(i, ps.get(i));
		}
		q.setFirstResult(page.getFirst());
		q.setMaxResults(page.getRows());
		List<Students> list = q.list();
		page.setResult(list);
		return page;
	}

	/**
	 * @Title: getStudent
	 * @Description: TODO(获取学生对象。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:47:02
	 * @return
	 * @return Students
	 */
	@Override
	public Students getStudent(String stuId) {
		return hibernateTemplate.get(Students.class, stuId);
	}

	/**
	 * @Title: getStudent
	 * @Description: TODO(通过学号获取学生对象。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:47:02
	 * @param stuNo
	 *            学号
	 * @return
	 * @return Students
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Students getStudentByNo(String stuNo) {
		String hql = "From " + Students.class.getName()
				+ " stu where stu.studentno=?";
		List<Students> list = (List<Students>) hibernateTemplate.find(hql,
				new Object[] { stuNo });
		Students stu = list.isEmpty() ? null : list.get(0);
		return stu;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Students getStudentByNoAndName(String stuNo,String stuname) {
		String hql = "From " + Students.class.getName()
				+ " stu where stu.studentno=? and stu.stuname=?";
		List<Students> list = (List<Students>) hibernateTemplate.find(hql,
				new Object[] { stuNo,stuname});
		Students stu = list.isEmpty() ? null : list.get(0);
		return stu;
	}

	/**
	 * @Title: saveStudents
	 * @Description: TODO(保存学生信息。)
	 * @author liangriyu
	 * @date 2016-3-7 上午12:08:44
	 * @param students
	 * @return void
	 */
	@Override
	public void saveStudents(Students students) {
		hibernateTemplate.save(students);
	}

	/**
	 * @Title: updateStudents
	 * @Description: TODO(修改学生信息。)
	 * @author liangriyu
	 * @date 2016-3-10 上午1:45:05
	 * @param students
	 * @return void
	 */
	@Override
	public void updateStudents(Students students) {
		hibernateTemplate.update(students);
	}

	/**
	 * @Title: deleteStudents
	 * @Description: TODO(删除学生信息。)
	 * @author liangiryu
	 * @date 2016-3-10 上午2:07:12
	 * @param students
	 * @return void
	 */
	@Override
	public void deleteStudents(Students students) {
		hibernateTemplate.delete(students);
	}

	/**
	 * @Title: queryPhotoPath
	 * @Description: TODO(获取头像路径)
	 * @author LRY
	 * @date 2016-4-27 上午1:21:54
	 * @param sql
	 * @return
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryPhotoPath(String sql) {
		SQLQuery query = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);
		// System.out.println("~~~~~~~~~~~~~~~~~"+query.list().size());
		return query.list();
	}

	/**
	 * @Title: getStudent
	 * @Description: TODO(通过身份证号获取学生对象。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:47:02
	 * @param idcardno
	 *            身份证号
	 * @return
	 * @return Students
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Students getStudentByIdcardNo(String idcardno) {
		String hql = "From " + Students.class.getName()
				+ " stu where stu.idcardno=?";
		List<Students> list = (List<Students>) hibernateTemplate.find(hql,
				new Object[] { idcardno });
		Students stu = list.isEmpty() ? null : list.get(0);
		return stu;
	}

	@Override
	public String getOrgNameByOrgId(String orgId) {
		String hql = "SELECT orgName FROM " + Organization.class.getName()
				+ " org WHERE org.orgId=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql);
		query.setParameter(0, orgId);
		return (String) query.uniqueResult();
	}

	@Override
	public void flush() {
		hibernateTemplate.flush();
		hibernateTemplate.clear();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listOrgName() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		qh.setSelect("s.orgName");
		qh.setGroup("s.orgName");
		qh.setOrder("s.orgName", QueryHelper.ASC);
		return hibernateTemplate.find(qh.getHQL());
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listClassname() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		qh.setSelect("s.classname");
		qh.setGroup("s.classname");
		qh.setOrder("s.classname", QueryHelper.ASC);
		return hibernateTemplate.find(qh.getHQL());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Students> getStudentsByStuQuery(StuQuery query) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		if (StringUtils.isNotBlank(query.getNo())) {
			qh.setWhere("s.studentno=?", query.getNo());
		} else if (StringUtils.isNotBlank(query.getClassname())) {
			qh.setWhere("s.classname=?", query.getClassname());
		} else {
			if (StringUtils.isNotBlank(query.getOrgName())) {
				qh.setWhere("s.orgName=?", query.getOrgName());
			}
			if (StringUtils.isNotBlank(query.getGrade())) {
				qh.setWhere("s.grade=?", query.getGrade());
			}
		}

		return hibernateTemplate.find(qh.getHQL(), qh.getArgs().toArray());
	}

}
