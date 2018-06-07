package com.zhbit.xuexin.student.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningGuidInfo;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.action.LearningGuidInfoAction;
import com.zhbit.xuexin.student.dao.LearningGuidInfoDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-18 上午2:20:05
 * @version 1.0
 */
@Repository("learningGuidInfoDao")
public class LearningGuidInfoDaoImpl implements LearningGuidInfoDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;
    
    
  //2017.04.10xucaikai
/**
     * 
     *@author 许彩开 email:1121836563@qq.com
     *@date 创建时间：2017年4月10日 下午10:34:13
     *@Description:TODO(获取导学名单的list)
     *@return
     */
    
 
    
    
    public List<LearningGuidStudentsList> getStuNoList(){
    	
        LearningGuidInfoAction LearningGuidInfoAction=new LearningGuidInfoAction();  
        User user = LearningGuidInfoAction.getUser();
        boolean isAdmin = LearningGuidInfoAction.getIsAdmin();
    	 	
    	String hqlResult = "From " + LearningGuidStudentsList.class.getName()
				+ " info";
		StringBuffer sb = new StringBuffer();
		String EmployNo = user.getEmployNo();
		String EmployNo1=null;
		
		if (isAdmin) {
			sb.append(" where 1=1");
		} else {
			EmployNo1 = "'"+EmployNo+ "'";
			
			sb.append(" where TEACHERNO=" + EmployNo1);
		}
	
        // do result
       Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
              .createQuery(hqlResult + sb.toString() + " order by info.createTime");
    /*	Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("From "+LearningGuidStudentsList.class.getName()+" info where TEACHERNO=?");
    	qCount.setParameter(0, "02079");*/
        List<LearningGuidStudentsList> list = q.list();
        List list2=new ArrayList<>();
        
        for(Iterator iterInfo=list.iterator() ; iterInfo.hasNext();){
        	LearningGuidStudentsList info = (LearningGuidStudentsList)iterInfo.next();
   			
   				list2.add(info.getStudentno());
   			
   		}
        
        
        return list2;
    }
    
    
   
    /**
     * @Title: getList
     * @Description: TODO(分页获取信息列表。)
     * @author lry
     * @date 2016-3-19 下午11:46:42
     * @param page
     * @return
     * @return Page<LearningGuidInfo>
     */
    @Override
    public Page<LearningGuidInfo> getList(Page<LearningGuidInfo> page) {
        String hqlResult = "From " + LearningGuidInfo.class.getName() + " info";
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
                .createQuery(hqlCount + hqlResult + sb.toString()+ "and info.studentno in (:studentnoList)");
        for (int i = 0; i < ps.size(); i++) {
            qCount.setParameter(i, ps.get(i));
           
        }
        qCount.setParameterList("studentnoList", getStuNoList());
        Long count = (Long) qCount.uniqueResult();
        page.setCount(count);
        // do result
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession()
                .createQuery(hqlResult + sb.toString() + "and info.studentno in (:studentnoList) order by info.studentno desc, info.guiddate desc");
        for (int i = 0; i < ps.size(); i++) {
            q.setParameter(i, ps.get(i));
        }
        
        q.setParameterList("studentnoList", getStuNoList());
    
        
        q.setFirstResult(page.getFirst());
        q.setMaxResults(page.getRows());
        @SuppressWarnings("unchecked")
        List<LearningGuidInfo> list = q.list();
        
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
    public void save(LearningGuidInfo info) {
        hibernateTemplate.save(info);
    }

    /**
     * @Title: update
     * @Description: TODO(修改)
     * @author lry
     * @date 2016-3-19 下午11:47:56
     * @param info
     * @return void
     */
    @Override
    public void update(LearningGuidInfo info) {
        hibernateTemplate.update(info);
    }

    /**
     * @Title: LearningGuidInfo
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-19 下午11:48:51
     * @param id
     * @return
     * @return LearningGuidInfo
     */
    @Override
    public LearningGuidInfo getLearningGuidInfoByid(String id) {
        return hibernateTemplate.get(LearningGuidInfo.class, id);
    }

    /**
     * @Title: delete
     * @Description: TODO(删除)
     * @author lry
     * @date 2016-3-19 下午11:48:26
     * @param info
     * @return void
     */
    @Override
    public void delete(LearningGuidInfo info) {
        hibernateTemplate.delete(info);
    }
/**
 * 
 *@author 许彩开 email:1121836563@qq.com
 *@date 创建时间：2017年4月10日 下午8:24:21
 *@Description:TODO(通过id获取对象。通过学生学号student,guiddate来获取对象。判断数据库中是否存在记录)
 *@param studentno
 *@param guiddate
 *@return
 */
	@Override
	public int getLearningGuidInfo_exist(String studentno, String guiddate) {
		// TODO Auto-generated method stub
		
    	String temp=null;
    	int flag=0;//标志
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    	if(guiddate!=null){
    		temp=guiddate;
    	}
    	if(getStuNoList().contains(studentno)){
    	Query qCount = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("From "+LearningGuidInfo.class.getName()+" info where info.studentno in (:studentnoList) and STUDENTNO="+studentno);
    	qCount.setParameterList("studentnoList", getStuNoList());
    	List list = qCount.list();
    	for(Iterator iter=list.iterator() ; iter.hasNext() ; ) {
    		LearningGuidInfo info = (LearningGuidInfo)iter.next();
    		String gg=df.format(info.getGuiddate());
	    		if(gg.equals(temp)){
	    			flag=1;//已经存在
	    		}   			
    		}
    	}else{
    		flag=1;
    	}
    	return flag;
	}

}
