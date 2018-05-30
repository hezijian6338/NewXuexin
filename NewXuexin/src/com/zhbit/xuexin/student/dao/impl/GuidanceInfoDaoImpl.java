package com.zhbit.xuexin.student.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.GuidanceInfo;
import com.zhbit.xuexin.student.dao.GuidanceInfoDao;

/**
 *党团关系持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-11 下午2:41:17
 *@version 1.0
 */
@Repository("guidanceInfoDao")
public class GuidanceInfoDaoImpl implements GuidanceInfoDao {
    
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    /**
     * @Title: getList
     * @Description: TODO(分页获取辅导信息列表。)
     * @author liangriyu
     * @date 2016-3-12 下午6:43:15
     * @param page
     * @return
     * @return Page<GuidanceInfo>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<GuidanceInfo> getList(Page<GuidanceInfo> page) {
        String hqlResult = "From "+GuidanceInfo.class.getName()+" info";
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
        List<GuidanceInfo> list = q.list();
        page.setResult(list);
        return page;
    }
    
    /**
    *
    * @Title: save 
    * @Description: TODO(简单说明这个方法是做什么的。) 
    * @author Administrator 
    * @date 2016-3-13 下午2:58:03 
    * @param info
    * @return 
    * @return Serializable
     */
    public Serializable save(GuidanceInfo info) {
        return hibernateTemplate.save(info);
    }

    @Override
    public void update(GuidanceInfo info) {
        hibernateTemplate.update(info);
    }

    /**
     * @Title: getGuidanceInfoByid
     * @Description: TODO(获取辅导记录对象信息。)
     * @author lry
     * @date 2016-3-13 下午8:30:25
     * @param id
     * @return
     * @return GuidanceInfo
     */
    @Override
    public GuidanceInfo getGuidanceInfoByid(String id) {
        return hibernateTemplate.get(GuidanceInfo.class, id);
        //return hibernateTemplate.load(GuidanceInfo.class, id);
    }
    
    /**
     * (not Javadoc) 
    * <p>Title: delete</p> 
    * <p>Description: </p> 
    * @param info 
    * @see com.zhbit.xuexin.student.dao.GuidanceInfoDao#delete(com.zhbit.xuexin.domain.GuidanceInfo)
     */
    @Override
    public void delete(GuidanceInfo info) {
        hibernateTemplate.delete(info);
    }

	@Override
	public boolean CheckGuidanceInfoIfExist(GuidanceInfo info) {
		String hql = "From "+GuidanceInfo.class.getName()
				+" info where info.studentno=? ";
		int parameterCount = 1;
		Map<String,Object> parameterPostion  =new  HashMap<String,Object>();
		if(info.getGuiddate()==null ){
			hql+=" and info.guiddate is null";
		}
		else {
			hql+=" and info.guiddate=?";
			parameterPostion.put(String.valueOf(parameterCount),info.getGuiddate());
			parameterCount++;
		}
		if(info.getGuidaddress()==null||"".equals(info.getGuidaddress()))
			hql+=" and info.guidaddress is null";
		else{
			hql+= " and info.guidaddress=?";
			parameterPostion.put(String.valueOf(parameterCount), info.getGuidaddress());
			parameterCount++;
		}
		if(info.getGuidcontent()==null||"".equals(info.getGuidcontent()))
			hql+=" and info.guidcontent is null";
		else{
			hql+= " and info.guidcontent=?";
			parameterPostion.put(String.valueOf(parameterCount), info.getGuidcontent());
			parameterCount++;
		}
		if(info.getCounselor()==null||"".equals(info.getCounselor()))
			hql+=" and info.counselor is null";
		else{
			hql+= " and info.counselor=?";
			parameterPostion.put(String.valueOf(parameterCount), info.getCounselor());
			parameterCount++;
		}
		
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, info.getStudentno());
		for(java.util.Map.Entry<String, Object> e:parameterPostion.entrySet()){
			query.setParameter(Integer.parseInt(e.getKey()),e.getValue());
		}
		return query.list().size()==0;
	}

 

}
