/**   
 * Copyright (c) 1997-2016 Creawor All Rights Reserved
 * 地址: 珠海市高新区南方软件园B6栋2楼
 * 
 * 该软件是广东创我科技有限公司(下面简称创我科技)保密的信息和专利。
 * 非创我科技授权和许可，你不得披露该保密信息和不得使用它。 
 *
 * @Title: FamilyInfoService.java 
 * @author Administrator
 * @Description: TODO(简单说明这个文件是做什么的。) 
 * @date 2016-3-10 下午1:13:13 
 * @version V1.0   
 */
package com.zhbit.xuexin.student.service;

import java.io.File;

import javax.servlet.http.HttpSession;

import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;

/**
 * 学生家庭信息业务接口
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-10 下午1:13:13
 * @version 1.0
 */
public interface FamilyInfoService {

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
     * @Title: importExcel
     * @Description: TODO(导入学生家庭信息。)
     * @author liangriyu
     * @date 2016-3-6 上午10:35:19
     * @param stuFile
     *            文件对象
     * @param suffix
     *            文件名（去后缀）
     * @param user
     *            当前用户
     * @param suffix 
     * @return
     * @return int[]
     */
    int[] importExcel(File excel, User user, String suffix);

    /**
     * @Title: save
     * @Description: TODO(新增家庭信息。)
     * @author lry
     * @date 2016-3-18 上午2:59:35
     * @param studentno
     * @param stuname
     * @param call
     * @param name
     * @param politicalstatus
     * @param jobduty
     * @param company
     * @param companyaddress
     * @param telno
     * @param postcode
     * @param userId
     * @return
     * @return int
     */
    int save(String studentno, String stuname, String call, String name, String politicalstatus, String jobduty,
            String company, String companyaddress, String telno, String postcode, String userId);

    /**
     * @Title: update
     * @Description: TODO(修改家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:00:07
     * @param id
     * @param studentno
     * @param stuname
     * @param call
     * @param name
     * @param politicalstatus
     * @param jobduty
     * @param company
     * @param companyaddress
     * @param telno
     * @param postcode
     * @return void
     */
    void update(String id, String studentno, String stuname, String call, String name, String politicalstatus,
            String jobduty, String company, String companyaddress, String telno, String postcode);

    /**
     * @Title: delete
     * @Description: TODO(删除家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:00:39
     * @param ids
     * @return void
     */
    void delete(String ids);

    /**
     * @Title: getFamilyInfo
     * @Description: TODO(通过id获取家庭信息。)
     * @author lry
     * @date 2016-3-18 上午3:01:12
     * @param id
     * @return
     * @return FamilyInfo
     */
    FamilyInfo getFamilyInfo(String id);
    /**
     * 获取自己的家庭信息
     * @Title: getSelf
     * @Description: TODO
     * @param page
     * @param session
     * @return
     * @return: Page<Students>
     */
	Page<FamilyInfo> getSelf(Page<FamilyInfo> page, Students stu);
	/**
	 * 根据info对象删除信息
	 * @Title: deleteFamilyInfo
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	void deleteFamilyInfo(FamilyInfo info);

}
