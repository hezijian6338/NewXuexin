package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.StudentDutys;
import com.zhbit.xuexin.teacher.dao.StudentDutysDao;

/**
 * 
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午8:51:37
 *@version 1.0
 */
@Repository("studentDutysDao")
public class StudentDutysDaoImpl implements StudentDutysDao {
    
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
     * @return Page<StudentDutys>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<StudentDutys> getList(Page<StudentDutys> page) {
        String hqlResult = "From "+StudentDutys.class.getName()+" info";
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
            if ("duty".equals(key) || "grade".equals(key)) {
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
        List<StudentDutys> list = q.list();
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
    public void save(StudentDutys info) {
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
    public void update(StudentDutys info) {
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
    public void delete(StudentDutys info) {
        hibernateTemplate.delete(info);
    }
    /**
     * @Title: getStudentDutysByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return StudentDutys
     */
    @Override
    public StudentDutys getStudentDutysByid(String id) {
        return hibernateTemplate.get(StudentDutys.class, id);
    }
    /**
     * @Title: getStudentDutysByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return StudentDutys
     */
    @SuppressWarnings("unchecked")
    @Override
    public StudentDutys getStudentDutysByNo(String employNo) {
        String hql = "From StudentDutys info where info.employNo= ?";
        List<StudentDutys> list = (List<StudentDutys>) hibernateTemplate.find(hql, new Object[] {employNo});
        return list.isEmpty() ? null : list.get(0);
    }
    /**
     * @Title: getStudentDutysExist
     * @Description: TODO(通过id获取对象。通过学生具体信息info来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/20 下午7:47:45
     * @param info
     * @return duty
     * @return list.size()
     * 
     */
	public int getStudentDutysExist(StudentDutys info) {
		String hql = "From StudentDutys info where info.grade= ? and info.classname= ? and info.studentno= ? and info.duty= ? and info.telno= ? and info.shorttelno= ? and info.address= ?";
		if (info.getMemo().equals("")) {
			hql = hql + "and info.memo is null";
			List<StudentDutys> list = (List<StudentDutys>) hibernateTemplate
					.find(hql,
							new Object[] { info.getGrade(),
									info.getClassname(), info.getStudentno(),
									info.getDuty(), info.getTelno(),
									info.getShorttelno(), info.getAddress() });
			return list.size();
		} else {
			hql = hql + "and info.memo =?";
			List<StudentDutys> list = (List<StudentDutys>) hibernateTemplate
					.find(hql,
							new Object[] { info.getGrade(),
									info.getClassname(), info.getStudentno(),
									info.getDuty(), info.getTelno(),
									info.getShorttelno(), info.getAddress(),
									info.getMemo() });
			return list.size();
		}
	}

}
