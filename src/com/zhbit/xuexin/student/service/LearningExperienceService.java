package com.zhbit.xuexin.student.service;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 上午12:28:42
 * @version 1.0
 */
public interface LearningExperienceService {

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
     * @Description: TODO(新增学习经历。)
     * @author liangriyu
     * @date 2016-3-11 上午12:54:45
     * @param studentno
     * @param stuname
     * @param duration
     * @param schoolname
     * @param duty
     * @param witness
     * @param creator
     * @return 0-保存失败学号不存在 1-保存成功
     */
    int saveLearningExperience(String studentno, String stuname, String duration, String schoolname, String duty,
            String witness, String creator);

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
    void updateLearningExperience(String id, String studentno, String stuname, String duration, String schoolname,
            String duty, String witness);

    /**
     * @Title: deleteLearningExperience
     * @Description: TODO(删除。)
     * @author lry
     * @date 2016-3-11 上午1:34:46
     * @param ids
     * @return void
     */
    void deleteLearningExperience(String ids);

    /**
     * @Title: getLearningExperienceById
     * @Description: TODO(通过id获取对象。)
     * @author liangriuy
     * @date 2016-3-11 上午2:23:32
     * @param id
     * @return
     * @return LearningExperience
     */
    LearningExperience getLearningExperienceById(String id);
    /**
     * 获取自己的信息
     * @Title: getSelf
     * @Description: TODO
     * @param page
     * @param session
     * @return
     * @return: Page<LearningExperience>
     */
	Page<LearningExperience> getSelf(Page<LearningExperience> page,
			Students stu);
	/**
	 * 根据id删除
	 * @Title: deleteLearningExperienceBy
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	void deleteLearningExperience(LearningExperience id);

}
