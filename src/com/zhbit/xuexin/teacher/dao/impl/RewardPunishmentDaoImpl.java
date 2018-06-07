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
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.teacher.dao.RewardPunishmentDao;
@Repository("tearewardPunishmentDao")
public class RewardPunishmentDaoImpl implements RewardPunishmentDao{

	@Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
	@Override
	public Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page) {
		String hqlResult = "From " + TeaRewardPunishment.class.getName() + " info";
		String hqlCount = "select count(info)";
		StringBuffer sb = new StringBuffer();
		sb.append(" where 1=1");
		List<Object> ps = new ArrayList<Object>();
		
		for (Iterator<String> item = page.getParas().keySet().iterator(); item.hasNext();) {
			String key = item.next();
			if("employname".equals(key) || "employno".equals(key)) {
				sb.append(" and info.").append(key).append(" like ?");
				ps.add("%" + page.getParas().get(key) + "%");
			}
			if("orgName".equals(key)) {
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
        List<TeaRewardPunishment> list = q.list();
        page.setResult(list);
        return page;
	}
	/**
	 * 
	* @Title: save   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @date 2018-5-31 下午2:36:45
	* @author 林敬凯
	* @throws
	 */
	@Override
	public void save(TeaRewardPunishment info) {
		hibernateTemplate.save(info);
	}
	/**
	 * 
	* @Title: getTeaRewardPunishmentByid   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param id
	* @param @return    设定文件   
	* @date 2018-5-31 下午5:51:28
	* @author 林敬凯
	* @throws
	 */
	@Override
	public TeaRewardPunishment getTeaRewardPunishmentByid(String id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(TeaRewardPunishment.class,id);
	}
	/**
	 * 
	* @Title: update   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @date 2018-5-31 下午6:08:34
	* @author 林敬凯
	* @throws
	 */
	@Override
	public void update(TeaRewardPunishment info) {
		hibernateTemplate.update(info);
		
	}
	/**
	 * 
	* @Title: delete   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info    设定文件   
	* @date 2018-5-31 下午11:06:09
	* @author 林敬凯
	* @throws
	 */
	@Override
	public void delete(TeaRewardPunishment info) {
		hibernateTemplate.delete(info);
		
	}
	/**
	 * 
	* @Title: getTeaRewardPunishmentByNo   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param cellValue
	* @param @return    设定文件   
	* @date 2018-6-4 上午10:09:12
	* @author 林敬凯
	* @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TeaRewardPunishment getTeaRewardPunishmentByNo(String employNo) {
		String hql = "From TeaRewardPunishment info where info.employNo= ?";
		List<TeaRewardPunishment> list = (List<TeaRewardPunishment>) hibernateTemplate.find(hql, new Object[] {employNo});
		return list.isEmpty() ? null : list.get(0);
	}

	
}
