package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.LearningGuidStudentsListDao;

/**
 *导学学生名单持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午12:56:12
 *@version 1.0
 */
@Repository("learningGuidStudentsListDao")
public class LearningGuidStudentsListDaoImpl implements LearningGuidStudentsListDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    /**
     * @Title: getSession()
     * @Description: TODO(获取当期session。)
     * @author 余锡鸿
     * @date 2017-4-14 上午11:49:12
     * @param 
     * @return session
     */
    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    /**
     * @Title: getList
     * @Description: TODO(分页获取列表。)
     * @author lry
     * @date 2016-3-14 上午2:49:12
     * @param page
     * @return
     * @return Page<LearningGuidStudentsList>
     */
    @SuppressWarnings("unchecked")
    @Override
	public Page<LearningGuidStudentsList> getList(
			Page<LearningGuidStudentsList> page, User user, boolean isAdmin) {
		String hqlResult = "From " + LearningGuidStudentsList.class.getName()
				+ " info";
		String hqlCount = "select count(info)";
		StringBuffer sb = new StringBuffer();
		String EmployNo = user.getEmployNo();
		if (isAdmin) {
			sb.append(" where 1=1");
		} else {
			String EmployNo1 = "'" + EmployNo + "'";
			sb.append(" where TEACHERNO=" + EmployNo1);
		}
		List<Object> ps = new ArrayList<Object>();
		for (Iterator<String> item = page.getParas().keySet().iterator(); item
				.hasNext();) {
			String key = item.next();
			if ("stuname".equals(key) || "studentno".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}

			if ("teachername".equals(key) || "teacherno".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}
			//2017/4/24 余锡鸿 导学名单多增加学年和学期两个查询
			if ("academicyear".equals(key) || "term".equals(key)) {
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
                .createQuery(hqlResult + sb.toString() + " order by info.createTime");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<LearningGuidStudentsList> list = q.list();
        page.setResult(list);
        return page;
    }
    /**
     * @Title: save
     * @Description: TODO(保存新增。)
     * @author lry
     * @date 2016-3-15 下午7:45:51
     * @param info
     * @return
     * @return int
     */
   
    //2017/4/14 余锡鸿修复导入数据量大，导入时间长的问题
    int count=0;
    @Override
    public void save(LearningGuidStudentsList info) {
        hibernateTemplate.save(info);
        if(count%50==0){
        	getSession().flush();
        	getSession().clear();
        }
    }
    /**
     * @Title: getLearningGuidStudentsListById
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-15 下午7:47:45
     * @param id
     * @return
     * @return LearningGuidStudentsList
     */
    @Override
    public LearningGuidStudentsList getLearningGuidStudentsListById(String id) {
        return hibernateTemplate.get(LearningGuidStudentsList.class, id);
    }
    
    
    
    /**
     * @Title: getLearningGuidStudentsList_exist
     * @Description: TODO(通过id获取对象。通过学生学号student、班级名称classname、导师姓名teachername、学年academicyear、学期term来获取对象。判断数据库中是否存在记录)
     * @author 余锡鸿
     * @date 2017/4/1 下午7:47:45
     * @param studentno、classname、teachername、acadicyear、term
     * @return
     * @return list.size()
     */
	public int getLearningGuidStudentsListExist(String studentno,String classname,
		String teachername, String academicyear, String term) {
		classname="'"+classname+"'";
		teachername="'"+teachername+"'";
		academicyear="'"+academicyear+"'";
		term="'"+term+"'";
		Query qCount = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"From " + LearningGuidStudentsList.class.getName()
								+ " info where STUDENTNO=" + studentno
								+ " and CLASSNAME=" + classname
								+ " and TEACHERNAME=" + teachername
								+ " and ACADEMICYEAR=" + academicyear
								+ " and TERM=" + term);
		List list = qCount.list();
		return list.size();
	}
    
    
    
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-15 下午7:46:33
     * @param info
     * @return void
     */
    @Override
    public void update(LearningGuidStudentsList info) {
        hibernateTemplate.update(info);
    }
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-14 上午2:50:56
     * @param info
     * @return void
     */
    @Override
    public void delete(LearningGuidStudentsList info) {
        hibernateTemplate.delete(info);
    }
    
    @Override
    public LearningGuidStudentsList getLearningGuidStudentsListByStuId(String studentno) {
        String hql = "From "+LearningGuidStudentsList.class.getName()+" lgs where lgs.studentno=?";
        List<LearningGuidStudentsList> list =  (List<LearningGuidStudentsList>)hibernateTemplate.find(hql, new Object[] { studentno });
        LearningGuidStudentsList lgs = list.isEmpty() ? null :list.get(0) ;
        return lgs ;
    }
}