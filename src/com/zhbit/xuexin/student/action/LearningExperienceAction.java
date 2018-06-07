package com.zhbit.xuexin.student.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.student.service.LearningExperienceService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 上午12:30:04
 * @version 1.0
 */
@Controller("learningExperienceAction")
@Scope("prototype")
public class LearningExperienceAction extends BaseAction {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 5078051713730503571L;

    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String duration;

    private String schoolname;

    private String duty;

    private String witness;

    private int page;// 当前页

    private int rows;// 页面大小

    private String ids;

    @Resource
    private LearningExperienceService experienceService;

    /**
     * 
     * 
     * @Title: viewList
     * @Description: TODO(管理用户列表页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String index() {
        return "index";
    }

    /**
     * 
     * 
     * @Title: viewAdd
     * @Description: TODO(新增页面)
     * @author liangriyu
     * @date 2015-12-20 下午4:39:40
     * @return
     * @return String
     */
    public String viewAdd() {
        return "viewAdd";
    }

    /**
     * 
     * 
     * @Title: viewEdit
     * @Description: TODO(修改页面)
     * @author liangriyu
     * @date 2015-12-20 下午4:40:02
     * @return
     * @return String
     */
    public String viewEdit() {
        return "viewEdit";
    }

    /**
     * @Title: viewImport
     * @Description: TODO(导入页面。)
     * @author liangriyu
     * @date 2016-3-9 下午11:14:27
     * @return
     * @return String
     */
    public String viewImport() {
        return "viewImport";
    }

    /**
     * 获取列表
     * 
     * @author 梁日宇
     * @return
     */
    public void getList() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Page<LearningExperience> page = experienceService.getList(new Page<LearningExperience>(req));
            //
            map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    /**
     * 获取自己信息
     * 
     * @author 梁日宇
     * @return
     */
    public void getSelf() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Students stu = (Students)getSession().getAttribute(Const.SESSION_STUDENT);
            Page<LearningExperience> page = experienceService.getSelf(new Page<LearningExperience>(req),stu);
            //
            map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    /**
     * @Title: save
     * @Description: TODO(新增信息。)
     * @author liangriyu
     * @date 2016-3-9 下午11:20:51
     * @return void
     */
    public void save() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            int state = experienceService.saveLearningExperience(studentno, stuname, duration, schoolname, duty, witness,
                    getSessionUser().getUserId());
            //
            if(state==1){
                map.put("status", Const.CODE_SUCCESS);
            }else{
                map.put("status", Const.CODE_FAIL);
            }
            
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * 
     */
    public void saveSelf() {
        Map<String, Object> map = Const.getJsonMap();
        try {
        	Students self = (Students) getSession().getAttribute(Const.SESSION_STUDENT);
        	int state = experienceService.saveLearningExperience(self.getStudentno(), self.getStuname(), duration, schoolname, duty, witness,
        				getSessionUser().getUserId());
            if(state==1){
                map.put("status", Const.CODE_SUCCESS);
            }else{
                map.put("status", Const.CODE_FAIL);
            }
            
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * @Title: update
     * @Description: TODO(修改学生基本信息。)
     * @author liangriyu
     * @date 2016-3-10 上午1:37:56
     * @return void
     */
    public void update() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            experienceService.updateLearningExperience(id, studentno, stuname, duration, schoolname, duty, witness);
            //
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    public void updateSelf() {
        Map<String, Object> map = Const.getJsonMap();
        
        try {
        	Students self = (Students) getSession().getAttribute(Const.SESSION_STUDENT);
        	LearningExperience temp = experienceService.getLearningExperienceById(id);
        	if(null != temp && self.getStudentno().equals(temp.getStudentno())){
        		experienceService.updateLearningExperience(id,  self.getStudentno(), self.getStuname(), duration, schoolname, duty, witness);
        	}
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    /**
     * 
     * 
     * @Title: delete
     * @Description: TODO(删除)
     * @author 梁日宇
     * @date 2015-12-21 下午10:09:27
     * @return void
     */
    
    public void delete() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            experienceService.deleteLearningExperience(ids);
            map.put("status", Const.CODE_SUCCESS);
            map.put("message", "删除成功");
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "删除失败");
        }
        OutUtil.outJson(map, getResponse());
    }
    /**
     * 删除自己的信息
     * @Title: deleteSelf
     * @author: 
     * zoufanxiang
     * @Description: TODO
     * @return: void
     */
    public void deleteSelf() {
        Map<String, Object> map = Const.getJsonMap();
        try {
        	Students self = (Students) getSession().getAttribute(Const.SESSION_STUDENT);
        	if(ids!=null||"".equals(ids)){
                String[] sids = ids.split(",");
                for(String id:sids){
                	LearningExperience temp = experienceService.getLearningExperienceById(id);
                	if(null != temp && self.getStudentno().equals(temp.getStudentno())){
                		experienceService.deleteLearningExperience(temp);
                	}
                }
                
            } 
            map.put("status", Const.CODE_SUCCESS);
            map.put("message", "删除成功");
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "删除失败");
        }
        OutUtil.outJson(map, getResponse());
    }
    /**
     * @Title: getLearningExperience
     * @Description: TODO(获取学习经历。)
     * @author Administrator
     * @date 2016-3-11 上午2:34:01
     * @return void
     */
    public void getLearningExperience() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            LearningExperience experience = experienceService.getLearningExperienceById(id);
            map.put("info", experience);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    public void getLearningExperienceSelf() {
        Map<String, Object> map = Const.getJsonMap();
        try {
        	Students self = (Students) getSession().getAttribute(Const.SESSION_STUDENT);
            LearningExperience experience = experienceService.getLearningExperienceById(id);
            if(null != experience && self.getStudentno().equals(experience.getStudentno())){
	            map.put("info", experience);
	            map.put("status", Const.CODE_SUCCESS);
            }else{
            	map.put("status", Const.CODE_FAIL);
            }
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

}
