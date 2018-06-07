package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.SubjectContest;
import com.zhbit.xuexin.student.dao.SubjectContestDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:59
 *@version 1.0
 */
@Repository("subjectContestDao")
public class SubjectContestDaoImpl implements SubjectContestDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<SubjectContest>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<SubjectContest> getList(Page<SubjectContest> page) {
        String hqlResult = "From " + SubjectContest.class.getName() + " info";
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
                .createQuery(hqlResult + sb.toString() + " order by info.createTime desc");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<SubjectContest> list = q.list();
        page.setResult(list);
        return page;
    }
    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-19 下午11:47:19
     * @param info
     * @return void
     */
    @Override
    public void save(SubjectContest info) {
        hibernateTemplate.save(info);
    }
    /**
     * @Title: SubjectContest
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return SubjectContest
     */
    @Override
    public SubjectContest getSubjectContestByid(String id) {
        return hibernateTemplate.get(SubjectContest.class, id);
    }
    /**
     * @Title: update
     * @Description: TODO(修改。)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    @Override
    public void update(SubjectContest info) {
        hibernateTemplate.update(info);
    }
    /**
     * @Title: delete
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    @Override
    public void delete(SubjectContest info) {
        hibernateTemplate.delete(info);
    }
	@Override
	public boolean CheckSubjectContestIfExsits(SubjectContest info) {
		 
		String hql = "From "+SubjectContest.class.getName()+" info  where info.studentno=? ";
		Map<String,Object> parameterPostion = new HashMap<String,Object>();
		parameterPostion.put("0", info.getStudentno());
		int parameterCount = 1;
		if(info.getRewarddate()==null){
			hql +=" and info.rewarddate is null ";
		}
		else{
			hql +=" and info.rewarddate=? ";
			parameterPostion.put(String.valueOf(parameterCount), info.getRewarddate());
			parameterCount++;
		}
		if("".equals(info.getRewardname())){
			hql +=" and info.rewardname is null ";
		}
		else{
			hql +=" and info.rewardname=? ";
			parameterPostion.put(String.valueOf(parameterCount), info.getRewardname());
			parameterCount++;
		}
		if("".equals(info.getRewardproject())){
			hql +=" and info.rewardproject is null ";
		}
		else{
			hql +=" and info.rewardproject=? ";
			parameterPostion.put(String.valueOf(parameterCount), info.getRewardproject());
			parameterCount++;
		}
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		for(java.util.Map.Entry<String,Object> e:parameterPostion.entrySet()){
		 	query.setParameter(Integer.parseInt(e.getKey()), e.getValue());
		}
		 return query.list().size()==0;
	}

}
