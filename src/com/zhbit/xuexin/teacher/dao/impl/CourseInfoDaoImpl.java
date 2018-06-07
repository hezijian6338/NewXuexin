
package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.util.finder.ClassFinder.Info;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.dao.CourseInfoDao;

/**
 * 
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午8:51:37
 *@version 1.0
 */
@Repository("CourseInfoDao")
public class CourseInfoDaoImpl implements CourseInfoDao {
    
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<CourseInfo>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<CourseInfo> getList(Page<CourseInfo> page) {
        String hqlResult = "From "+CourseInfo.class.getName()+" info";
        String hqlCount = "select count(info)";
        StringBuffer sb = new StringBuffer();
        sb.append(" where 1=1");
        List<Object> ps = new ArrayList<Object>();
        for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
            String key = item.next();
            if ("employName".equals(key) || "employNo".equals(key)) {
                sb.append(" and info.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if ("coursecode".equals(key) || "coursename".equals(key)||"selectedcourseno".equals(key)||"employNo".equals(key)||"academicyear".equals(key)||"term".equals(key)) {
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
                .createQuery(hqlResult + sb.toString() + " order by info.id");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<CourseInfo> list = q.list();
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
    @Override
    public void save(CourseInfo info) {
        hibernateTemplate.save(info);
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
    public void update(CourseInfo info) {
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
    public void delete(CourseInfo info) {
        hibernateTemplate.delete(info);
    }
    /**
     * @Title: getCourseInfoByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return CourseInfo
     */
    @Override
    public CourseInfo getCourseInfoByid(String id) {
        return hibernateTemplate.get(CourseInfo.class, id);
    }
    /**
     * @Title: getCourseInfoByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return CourseInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public CourseInfo getCourseInfoByNo(String employNo) {
        String hql = "From CourseInfo info where info.employNo= ?";
        List<CourseInfo> list = (List<CourseInfo>) hibernateTemplate.find(hql, new Object[] {employNo});
        return list.isEmpty() ? null : list.get(0);
    }
    /**
     * @Title: getCourseInfoByCode
     * @Description: TODO(通过课程代码获取开课信息。)
     * @author lry
     * @date 2016-3-29 上午1:51:52
     * @param coursecode
     * @return
     * @return CourseInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public CourseInfo getCourseInfoByCode(String coursecode) {
        String hql = "From CourseInfo info where info.coursecode= ?";
        List<CourseInfo> list = (List<CourseInfo>) hibernateTemplate.find(hql, new Object[] {coursecode});
        return list.isEmpty() ? null : list.get(0);
    }
    
    /**
     * @Title: getCourseInfoByselectedcourseno
     * @Description: TODO(通过选课课号获取开课信息。)
     * @author LHW
     * @date 2016-3-29 上午1:51:52
     * @param selectedcourseno
     * @return
     * @return CourseInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public CourseInfo getCourseInfoBySelectedcourseno(String selectedcourseno) {
        String hql = "From CourseInfo info where info.selectedcourseno= ?";
        List<CourseInfo> list = (List<CourseInfo>) hibernateTemplate.find(hql, new Object[] {selectedcourseno});
        return list.isEmpty() ? null : list.get(0);
    }
    
	@Override
	public Page<Map<String,String>> getSemesterAndYearList(Page<Map<String,String>> page, String teaNo) {
		String sql = "select distinct info.academicyear, info.term from  T_COURSEINFO  info "
				+ "where info.EMPLOY_NO=?";
		SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);   
		query.setParameter(0, teaNo);
		List<Object[]> info = query.list();
		long count = (long)info.size();
		page.setCount(count*2);
		query.setFirstResult(page.getFirst()/2);
		query.setMaxResults(page.getRows()/2);
		info = query.list();
		System.out.println("查询到的个数:"+info.size()); 
		List<Map<String,String>> result = new ArrayList();
		for(Object[] o:info)
		{
			 
				Map<String,String> m = new HashMap();
				String date = (String)o[0]+"-"+(String)o[1];
				System.out.println("查到数据为"+date);
				m.put("year",(String)o[0]);
				m.put("term", (String)o[1]);
				m.put("activity",date+"正考成绩录入");
				m.put("type","正考");
				result.add(m);
				m = new HashMap();
				m.put("year",(String)o[0]);
				m.put("term", (String)o[1]);
				m.put("activity",date+"补考成绩录入");
				m.put("type","补考");
				result.add(m);
		 
		}
		page.setResult(result);
		return page;
	}
	@Override
	public Page<CourseInfo> getSemesterCourseList(Page<CourseInfo> page, String teaNo, String academicYear,
			String semester) {
		String hqlCount = "select count(info) ";
		String hql = "from  " +CourseInfo.class.getName()+" info "
				+ "where info.academicyear =? and info.term=? " 
				+ "and info.employNo=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);   	
		Query queryCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount+hql);   	
		query.setParameter(0, academicYear);
		query.setParameter(1, semester);
		query.setParameter(2, teaNo);
		queryCount.setParameter(0, academicYear);
		queryCount.setParameter(1, semester);
		queryCount.setParameter(2, teaNo);
		Long count = (Long) queryCount.uniqueResult();
		page.setCount(count); 
		query.setFirstResult(page.getFirst());
		query.setMaxResults(page.getRows());
	    List<CourseInfo> list = query.list();
	    page.setResult(list);
	    return page;
	}
	@Override
	public CourseInfo getCourseInfoBySelectedcourseno(String selectedCourseNo, String academicYear, String semester,
			String teaNo) {
		 String hql = "From CourseInfo info where info.selectedcourseno= ?"
		 		+ "and info.academicYear=? and  info.term=?"
		 		+ "and info.employNo=?";
	        List<CourseInfo> list = (List<CourseInfo>) hibernateTemplate.find(hql, new Object[] {selectedCourseNo,academicYear,semester,teaNo});
	        return list.isEmpty() ? null : list.get(0);
	}
	@Override
	public void flush() {
		hibernateTemplate.flush();
		hibernateTemplate.clear();
		
	}
}
