package com.zhbit.xuexin.student.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.NceeScore;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.dao.NceeScoreDao;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-20 下午9:32:59
 *@version 1.0
 */
@Repository("nceeScoreDao")
public class NceeScoreDaoImpl implements NceeScoreDao {
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
     * @return Page<NceeScore>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<NceeScore> getList(Page<NceeScore> page) {
        String hqlResult = "From " + NceeScore.class.getName() + " info";
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
            if ("idcardno".equals(key) || "zkhNo".equals(key)) {
                sb.append(" and info.").append(key).append(" like ?");
                ps.add("%" + page.getParas().get(key) + "%");
            }
            if ("parentOrgId".equals(key)) {
                sb.append(" and info.").append(key).append(" = ?");
                ps.add(page.getParas().get(key));
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
        List<NceeScore> list = q.list();
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
    public void save(NceeScore info) {
        hibernateTemplate.save(info);
    }
    /**
     * @Title: NceeScore
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return NceeScore
     */
    @Override
    public NceeScore getNceeScoreByid(String id) {
        return hibernateTemplate.get(NceeScore.class, id);
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
    public void update(NceeScore info) {
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
    public void delete(NceeScore info) {
        hibernateTemplate.delete(info);
    }
	@Override
	public Page<NceeScore> getSelf(Page<NceeScore> page, Students stu) {
		if(null != stu){
			String hqlResult = "From "+NceeScore.class.getName()+" n where n.studentno=? ";
	        String hqlCount = "select count(n) ";
	 
	     // do count
	        Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hqlCount + hqlResult);
	        qCount.setParameter(0, stu.getStudentno());
	        Long count = (Long) qCount.uniqueResult();
	        page.setCount(count);
	        // do result
	        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
	                .createQuery(hqlResult);
	        q.setParameter(0, stu.getStudentno());
	        q.setFirstResult(page.getFirst());
	        q.setMaxResults(page.getRows());
	        List<NceeScore> list = q.list();
	        page.setResult(list);
		}else{
			page.setCount(0);
			page.setResult(new ArrayList<NceeScore>(0));
		}
        return page;
	}

}
