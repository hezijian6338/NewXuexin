package com.zhbit.xuexin.teacher.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;

/**
 *教师基本信息持久化
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午8:51:37
 *@version 1.0
 */
@Repository("teacherInfoDao")
public class TeacherInfoDaoImpl implements TeacherInfoDao {
    
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取教师信息列表。)
     * @author lry
     * @date 2016-3-16 下午8:57:48
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    @SuppressWarnings("unchecked")
    @Override
    public Page<TeacherInfo> getList(Page<TeacherInfo> page) {
        String hqlResult = "From "+TeacherInfo.class.getName()+" info";
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
                .createQuery(hqlResult + sb.toString() + " order by info.employNo");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        List<TeacherInfo> list = q.list();
        page.setResult(list);
        return page;
    }
    /**
     * @Title: save
     * @Description: TODO(保存教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:58:54
     * @param info
     * @return void
     */
    @Override
    public void save(TeacherInfo info) {
        hibernateTemplate.save(info);
    }
    /**
     * @Title: update
     * @Description: TODO(修改教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:22
     * @param info
     * @return void
     */
    @Override
    public void update(TeacherInfo info) {
        hibernateTemplate.update(info);
    }
    /**
     * @Title: delete
     * @Description: TODO(删除教师信息。)
     * @author lry
     * @date 2016-3-16 下午8:59:46
     * @param info
     * @return void
     */
    @Override
    public void delete(TeacherInfo info) {
        hibernateTemplate.delete(info);
    }
    /**
     * @Title: getTeacherInfoByid
     * @Description: TODO(通过id获取教师信息。)
     * @author lry
     * @date 2016-3-16 下午9:00:24
     * @param id
     * @return
     * @return TeacherInfo
     */
    @Override
    public TeacherInfo getTeacherInfoByid(String id) {
        return hibernateTemplate.get(TeacherInfo.class, id);
    }
    /**
     * @Title: getTeacherInfoByNo
     * @Description: TODO(通过职工号获取职工信息。)
     * @author lry
     * @date 2016-3-16 下午9:01:05
     * @param employNo
     * @return
     * @return TeacherInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public TeacherInfo getTeacherInfoByNo(String employNo) {
        String hql = "From TeacherInfo info where info.employNo= ?";
        List<TeacherInfo> list = (List<TeacherInfo>) hibernateTemplate.find(hql, new Object[] {employNo});
        return list.isEmpty() ? null : list.get(0);
    }
    /**
     * @Title: getTeacherInfoByName
     * @Description: TODO(通过职工姓名获取信息。)
     * @author lry
     * @date 2016-3-17 下午11:10:29
     * @param employName
     * @return
     * @return TeacherInfo
     */
    @SuppressWarnings("unchecked")
    @Override
    public TeacherInfo getTeacherInfoByName(String employName) {
        String hql = "From TeacherInfo info where info.employName= ?";
        List<TeacherInfo> list = (List<TeacherInfo>) hibernateTemplate.find(hql, new Object[] {employName});
        return list.isEmpty() ? null : list.get(0);
    }
    /**
     * @Title: queryPhotoPath
     * @Description: TODO(获取头像路径)
     * @author LRY
     * @date 2016-4-27 上午1:21:54
     * @param sql
     * @return
     * @return List<String>
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> queryPhotoPath(String sql) {
        SQLQuery query = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
//        System.out.println("~~~~~~~~~~~~~~~~~"+query.list().size());
        return query.list();
    }
    /**
	 * 
	* @Title: getTeacherInfoByNoAndName   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param employno
	* @param @param employname
	* @param @return    设定文件   
	* @date 2018-5-31 下午2:15:15
	* @author 林敬凯
	* @throws
	 */
    @SuppressWarnings("unchecked")
	@Override
	
	public TeacherInfo getTeacherInfoByNoAndName(String employno,
			String employname) {
		String hql = "From TeacherInfo" + " tea where tea.employNo=? and tea.employName=?";
		List<TeacherInfo> list = (List<TeacherInfo>) hibernateTemplate.find(hql, new Object[] {employno,employname});
		TeacherInfo tea = list.isEmpty() ? null : list.get(0);
		return tea;
	}

}
