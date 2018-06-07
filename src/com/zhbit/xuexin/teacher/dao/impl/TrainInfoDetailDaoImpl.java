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
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.TrainInfoDetail;
import com.zhbit.xuexin.teacher.dao.TrainInfoDetailDao;

/**
 * 
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午8:51:37
 *@version 1.0
 */
@Repository("TrainInfoDetailDao")
public class TrainInfoDetailDaoImpl implements TrainInfoDetailDao {
    
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
     * @return Page<TrainInfoDetail>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<TrainInfoDetail> getList(Page<TrainInfoDetail> page) {
        String hqlResult = "From "+TrainInfoDetail.class.getName()+" info";
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
                .createQuery(hqlResult + sb.toString() + " order by info.trainCode");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<TrainInfoDetail> list = q.list();
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
    public void save(TrainInfoDetail info) {
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
    public void update(TrainInfoDetail info) {
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
    public void delete(TrainInfoDetail info) {
        hibernateTemplate.delete(info);
    }
    /**
     * @Title: getTrainInfoDetailByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return TrainInfoDetail
     */
    @Override
    public TrainInfoDetail getTrainInfoDetailByid(String id) {
        return hibernateTemplate.get(TrainInfoDetail.class, id);
    }
    
    @Override
    public TrainInfoDetail getTrainInfoDetailByCodeAndName(String trainCode,String studentno) {
		String hql = "From " + TrainInfoDetail.class.getName()
				+ " detail where detail.trainCode=? and detail.studentno=?";
		List<TrainInfoDetail> list = (List<TrainInfoDetail>) hibernateTemplate.find(hql,
				new Object[]{trainCode,studentno});
		TrainInfoDetail trainInfoDetail = list.isEmpty() ? null : list.get(0);
		return trainInfoDetail;
	}

}
