package com.zhbit.xuexin.student.service.impl;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.dao.LearningExperienceDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.LearningExperienceService;

/**
 *
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-11 上午12:29:08
 *@version 1.0
 */
@Service("learningExperienceService")
@Transactional(readOnly = true)
public class LearningExperienceServiceImpl implements LearningExperienceService {

    @Autowired
    @Qualifier("learningExperienceDao")
    private LearningExperienceDao experienceDao;
    
    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;
    
    
    /**
     * @Title: getList
     * @Description: TODO(分页获取学习经历列表。)
     * @author liangriyu
     * @date 2016-3-11 上午12:37:47
     * @param page
     * @return
     * @return Page<LearningExperience>
     */
    @Override
    public Page<LearningExperience> getList(Page<LearningExperience> page) {
        return experienceDao.getList(page);
    }
    /**
     * @Title: saveLearningExperience
     * @Description: TODO(新增学习经历。)
     * @author liangriyu
     * @date 2016-3-11 上午12:54:45
     * @param studentno
     * @param stuname
     * @param duration
     * @param schoolname
     * @param duty
     * @param witness
     * @return 0-保存失败学号不存在 1-保存成功
     */
    @Override
    @Transactional(readOnly = false)
    public int saveLearningExperience(String studentno, String stuname, String duration, String schoolname,
            String duty, String witness, String creator) {
        Students stu = studentsDao.getStudentByNo(studentno);
        LearningExperience experience = new LearningExperience();
        if(stu!=null){
            experience.setStuId(stu.getStuId());
            experience.setStudentno(studentno);
            experience.setStuname(stuname);
            experience.setDuration(duration);
            experience.setSchoolname(schoolname);
            experience.setDuty(duty);
            experience.setWitness(witness);
            experience.setCreator(creator);
            experience.setCreateTime(new Date());
            
            experienceDao.saveLearningExperience(experience);
            return 1;
        }else{
            return 0;
        }
        
        
    }
    /**
     * @Title: updateLearningExperience
     * @Description: TODO(修改学习经历信息。)
     * @author liangriyu
     * @date 2016-3-11 上午1:09:46
     * @param id
     * @param studentno
     * @param stuname
     * @param duration
     * @param schoolname
     * @param duty
     * @param witness
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void updateLearningExperience(String id, String studentno, String stuname, String duration,
            String schoolname, String duty, String witness) {
        LearningExperience experience = experienceDao.getLearningExperienceById(id);
        if(null != experience){
	        //experience.setStudentno(studentno);
	        experience.setStuname(stuname);
	        experience.setDuration(duration);
	        experience.setSchoolname(schoolname);
	        experience.setDuty(duty);
	        experience.setWitness(witness);
	        experienceDao.updateLearningExperience(experience);
        }
    }
    
    /**
     * @Title: deleteLearningExperience
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-11 上午1:34:46
     * @param ids
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteLearningExperience(String ids) {
        if(ids!=null||"".equals(ids)){
            String[] sids = ids.split(",");
            for(String id:sids){
                LearningExperience experience = experienceDao.getLearningExperienceById(id);
                experienceDao.deleteLearningExperience(experience);
            }
            
        } 
    }
    
    /**
     * @Title: getLearningExperienceById
     * @Description: TODO(通过id获取对象。)
     * @author liangriuy
     * @date 2016-3-11 上午2:23:32
     * @param id
     * @return
     * @return LearningExperience
     */
    @Override
    public LearningExperience getLearningExperienceById(String id) {
        return experienceDao.getLearningExperienceById(id);
    }
	@Override
	public Page<LearningExperience> getSelf(Page<LearningExperience> page,
			Students stu) {
		
		return experienceDao.getSelf(page,stu);
	}
	@Override
	@Transactional(readOnly = false)
	public void deleteLearningExperience(LearningExperience l) {
		
        experienceDao.deleteLearningExperience(l);
	}

}
