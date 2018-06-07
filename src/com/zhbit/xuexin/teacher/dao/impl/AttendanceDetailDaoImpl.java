package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.AttendanceDetail;
import com.zhbit.xuexin.domain.AttendanceMaster;
import com.zhbit.xuexin.domain.LearningGuidInfo;
import com.zhbit.xuexin.teacher.dao.AttendanceDetailDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:51:37
 * @version 1.0
 */
@Repository("attendanceDetailDao")
public class AttendanceDetailDaoImpl implements AttendanceDetailDao {

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
     * @return Page<AttendanceDetail>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<AttendanceDetail> getList(Page<AttendanceDetail> page) {
        String hqlResult = "From " + AttendanceDetail.class.getName() + " info";
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
        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlCount + hqlResult + sb.toString());
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
        }
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + " order by info.createTime");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<AttendanceDetail> list = q.list();
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
    public void save(AttendanceDetail info) {
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
    public void update(AttendanceDetail info) {
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
    public void delete(AttendanceDetail info) {
        hibernateTemplate.delete(info);
    }

    /**
     * @Title: getAttendanceDetailByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return AttendanceDetail
     */
    @Override
    public AttendanceDetail getAttendanceDetailByid(String id) {
        return hibernateTemplate.get(AttendanceDetail.class, id);
    }

    /**
     * @Title: deleteBySelectedcourseno
     * @Description: TODO(通过选课号来删除学生考勤明细信息。)
     * @author lry
     * @date 2016-4-11 下午7:40:17
     * @param selectedcourseno
     * @return void
     */
    @Override
    public void deleteBySelectedcourseno(String selectedcourseno) {
        String sql = "delete from T_ATTENDANCE_DETAIL info where info.selectedcourseno=?";
        SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, selectedcourseno);
        query.executeUpdate();
    }

    /**
     * @Title: getAttendanceTimes
     * @Description: TODO(通过选课号查询该课程的考勤时间)
     * @author LRY
     * @date 2016-4-24 下午11:43:52
     * @param selectedcourseno
     * @param time
     * @return
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAttendanceTimes(String selectedcourseno) {
        String sql = "SELECT TO_CHAR(ATTENDANCE_TIME, 'YYYY-MM-DD') FROM T_ATTENDANCE_DETAIL WHERE SELECTEDCOURSENO=? GROUP BY (ATTENDANCE_TIME) ORDER BY ATTENDANCE_TIME";
        SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, selectedcourseno);
        System.out.println("~~~~~~~~~~~~~~~~~"+query.list().size());
        return query.list();
    }

    /**
     * @Title: getDetailList
     * @Description: TODO(通过选课号和考勤时间查询该日期考勤明细)
     * @author LRY
     * @date 2016-4-24 下午11:45:03
     * @param selectedcourseno
     * @param time
     * @return
     * @return Object[]
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AttendanceDetail> getDetailList(String selectedcourseno, String time) {
        String sql = "SELECT * from T_ATTENDANCE_DETAIL where SELECTEDCOURSENO=? AND TO_CHAR(ATTENDANCE_TIME, 'YYYY-MM-DD') = ?";
        SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, selectedcourseno);
        query.setParameter(1, time);
        query.addEntity(AttendanceDetail.class);
        return query.list();
    }

	@Override
	public int getSelectedcoursenol_exist(String selectedcourseno) {
    	Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("From "+AttendanceMaster.class.getName()+" info where info.selectedcourseno=?");
    	qCount.setParameter(0, selectedcourseno);
    	List list = qCount.list();
		return list.size();
	}

}
