package com.zhbit.xuexin.student.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;

/**
 * 学习经历
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 上午12:27:09
 * @version 1.0
 */
public interface LearningExperienceDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取学习经历列表。)
     * @author liangriyu
     * @date 2016-3-11 上午12:37:47
     * @param page
     * @return
     * @return Page<LearningExperience>
     */
    Page<LearningExperience> getList(Page<LearningExperience> page);

    /**
     * @Title: saveLearningExperience
     * @Description: TODO(保存学习经历信息。)
     * @author liangriyu
     * @date 2016-3-11 上午1:05:35
     * @param experience
     * @return void
     */
    void saveLearningExperience(LearningExperience experience);

    /**
     * @Title: getLearningExperienceById
     * @Description: TODO(通过id获取学习经历信息)
     * @author liangriyu
     * @date 2016-3-11 上午1:27:54
     * @param id
     * @return
     * @return LearningExperience
     */
    LearningExperience getLearningExperienceById(String id);

    /**
     * @Title: updateLearningExperience
     * @Description: TODO(修改学习经历信息。)
     * @author liangriyu
     * @date 2016-3-11 上午1:05:35
     * @param experience
     * @return void
     */
    void updateLearningExperience(LearningExperience experience);

    /**
     * @Title: deleteLearningExperience
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-11 上午1:38:05
     * @param experience
     * @return void
     */
    void deleteLearningExperience(LearningExperience experience);
    /**
     * 
     * @Title: getSelf
     * @Description: 获取自己的信息
     * @param page
     * @param session
     * @return
     * @return: Page<LearningExperience>
     */
	Page<LearningExperience> getSelf(Page<LearningExperience> page,
			Students stu);
	
	List<LearningExperience> listByStuno(String stuno);
}
