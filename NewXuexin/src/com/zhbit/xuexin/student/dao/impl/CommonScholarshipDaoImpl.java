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
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.student.dao.CommonScholarshipDao;

/**
 * 普通奖学金持久化
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 上午12:31:53
 * @version 1.0
 */
@Repository("commonScholarshipDao")
public class CommonScholarshipDaoImpl implements CommonScholarshipDao {

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
     * @return Page<CommonScholarship>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<CommonScholarship> getList(Page<CommonScholarship> page) {
        String hqlResult = "From " + CommonScholarship.class.getName() + " info";
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
                .createQuery(hqlResult + sb.toString() + " order by info.studentno");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<CommonScholarship> list = q.list();
        page.setResult(list);
        return page;
    }

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-14 上午2:49:53
     * @param cs
     * @return
     */
    @Override
    public void save(CommonScholarship cs) {
        hibernateTemplate.save(cs);
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-14 上午2:50:30
     * @return void
     */
    @Override
    public void update(CommonScholarship cs) {
        hibernateTemplate.update(cs);
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
    public void delete(CommonScholarship info) {
        hibernateTemplate.delete(info);
    }

    /**
     * @Title: getCommonScholarshipById
     * @Description: TODO(获取普通奖学金对象。)
     * @author lry
     * @date 2016-3-14 上午2:51:12
     * @param id
     * @return
     * @return CommonScholarship
     */
    @Override
    public CommonScholarship getCommonScholarshipById(String id) {
        return hibernateTemplate.get(CommonScholarship.class, id);
    }
/**
 * 
 *@author 许彩开 email:1121836563@qq.com
 *@date 创建时间：2017年5月3日 上午11:35:06
 *@Description:TODO()
 *@param studentno
 *@param stuname
 *@param major
 *@param rewardname
 *@param academicyear
 *@param term
 *@return
 */
	@Override
	public int getCommonScholarshipExist(String studentno, String stuname, String major, String rewardname,
			String academicyear, String term) {
		stuname="'"+stuname+"'";
		major="'"+major+"'";
		rewardname="'"+rewardname+"'";
		academicyear="'"+academicyear+"'";
		term="'"+term+"'";
		Query qCount = hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"From " + CommonScholarship.class.getName()
								+ " info where STUDENTNO=" + studentno
								+ " and stuname=" + stuname
								+ " and major=" + major
								+ " and rewardname=" + rewardname
								+ " and academicyear=" + academicyear
								+ " and term=" + term);
		List list = qCount.list();
		return list.size();
	}
}
