/**   
 * Copyright (c) 1997-2016 Creawor All Rights Reserved
 * 地址: 珠海市高新区南方软件园B6栋2楼
 * 
 * 该软件是广东创我科技有限公司(下面简称创我科技)保密的信息和专利。
 * 非创我科技授权和许可，你不得披露该保密信息和不得使用它。 
 *
 * @Title: FamilyInfoDao.java 
 * @author Administrator
 * @Description: TODO(简单说明这个文件是做什么的。) 
 * @date 2016-3-10 下午1:06:56 
 * @version V1.0   
*/
package com.zhbit.xuexin.student.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;

/**
 * 学生家庭信息持久化接口
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-10 下午1:06:56
 *@version 1.0
 */

public interface FamilyInfoDao {

    /**
     * @Title: getList
     * @Description: TODO(分页获取学生家庭信息。)
     * @author liangriyu
     * @date 2016-3-10 下午11:58:02
     * @param page
     * @return
     * @return Page<FamilyInfo>
     */
    Page<FamilyInfo> getList(Page<FamilyInfo> page);

    /**
    * @Title: save 
    * @Description: TODO(新增家庭。) 
    * @author lry 
    * @date 2016-3-19 下午3:05:10 
    * @param info 
    * @return void
     */
    void save(FamilyInfo info);

    /**
    * @Title: getFamilyInfo 
    * @Description: TODO(获取家庭信息。) 
    * @author lry 
    * @date 2016-3-19 下午3:10:28 
    * @param id
    * @return 
    * @return FamilyInfo
     */
    FamilyInfo getFamilyInfo(String id);
    
    /**
    * @Title: update 
    * @Description: TODO(修改家庭信息。) 
    * @author lry 
    * @date 2016-3-19 下午3:11:45 
    * @param info 
    * @return void
     */
    void update(FamilyInfo info);

    /**
    * @Title: delete 
    * @Description: TODO(删除家庭信息。) 
    * @author lry 
    * @date 2016-3-19 下午3:12:16 
    * @param info 
    * @return void
     */
    void delete(FamilyInfo info);
    /**
     * 
     * @Title: getSelf
     * @Description: 查询自己的家庭信息
     * @param page
     * @param session
     * @return
     * @return: Page<Students>
     */
	Page<FamilyInfo> getSelf(Page<FamilyInfo> page, Students stu);
	
	
	List<FamilyInfo> listByStuno(String stuno);
}
