package com.zhbit.xuexin.student.action;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.service.LearningGuidStudentsListService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.lang.Object ;

/**
 *导学学生名单action
 *@author 梁日宇 email:1912813835@qq.com
 *@date 创建时间：2016-3-15 下午12:57:22
 *@version 1.0
 */
@Controller("learningGuidStudentsListAction")
@Scope("prototype")
public class LearningGuidStudentsListAction extends BaseAction {

    /** 
    * @Fields serialVersionUID : TODO(简单说明是做什么的。) 
    */ 
    private static final long serialVersionUID = 1L;
    
    private String id;

    private String stuId;

    private String studentno;

    private String stuname;

    private String academicyear;

    private String term;

    private String classname;

    private String teacherno;

    private String teachername;
    
    private int page;// 当前页

    private int rows;// 页面大小

    private String ids;
    
    private File excel;// 导入文件

    private String excelFileName;// 导入文件名
    
    @Resource
    private LearningGuidStudentsListService service;
    
    /**
     * 
     * 
     * @Title: viewList
     * @Description: TODO(列表页面)
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
    
    public String viewDetail(){
    	return "viewDetail";
    	
    }
    public String viewAllDetail(){
    	return "viewAllDetail";
    	
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
            User user=getSessionUser();
            boolean isAdmin = (Boolean) getSession().getAttribute("isAdmin");
            Page<LearningGuidStudentsList> page = service.getList(new Page<LearningGuidStudentsList>(req),user,isAdmin);
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
        	
            int state = service.save(studentno, stuname, academicyear, term, classname, teachername,
                    getSessionUser().getUserId());
			if (state == 1) {
				map.put("status", Const.CODE_SUCCESS);
			} else if (state == 2) {
				map.put("status", Const.DATA_EXIST);
			} else if(state == 3){
				map.put("status", "teachernameError");
			}else {
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
            int state=service.update(id, studentno, stuname, academicyear, term, classname, teachername,teacherno);
        	if (state == 1) {
				map.put("status", Const.CODE_SUCCESS);
			} else if (state == 2) {
				map.put("status", Const.DATA_EXIST);
			} else if(state == 3){
				map.put("status", "teachernameError");
			}else {
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
            service.delete(ids);
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
     * @author liangiryu
     * @date 2016-3-11 上午2:34:01
     * @return void
     */
    public void getLearningGuidStudentsList() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            LearningGuidStudentsList info = service.getLearningGuidStudentsListById(id);
            map.put("info", info);
            map.put("status", Const.CODE_SUCCESS);
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * @Title: importFile
     * @Description: TODO(导入学生信息)
     * @author liangriyu
     * @date 2016-3-9 下午11:19:18
     * @return void
     */
    public void importFile() {
        try {

            int resultCode = 0;
            int[] count = { 0, 0, 0, 0 ,0};
            if (excel != null) {
                String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
                if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
                    User user = (User) getSession().getAttribute(Const.SESSION_USER);
                    count = service.importFile(excel, user,suffix);
                    if (count[0] < 0) {
                        resultCode = Const.CODE_UNKOWN_ERR;
                    }
                    if(count[3]>0){//提示导入文件的所有字段不能为空
                    	resultCode = 2;
                    }
                }
                else {// 格式不符
                    resultCode = 1;
                }
            }

            StringBuffer result = new StringBuffer();
            result.append("<script type=\"text/javascript\">");
            result.append("var resultCode=").append(resultCode).append(";");
            result.append("var importCount=").append(count[0]).append(";");
            result.append("var insertCount=").append(count[1]).append(";");
            result.append("var infoIsNullCount=").append(count[2]).append(";");
            result.append("var dataNullCount=").append(count[3]).append(";");
            result.append("var existCount=").append(count[4]).append(";");
            result.append("</script>");
            getResponse().getWriter().print(result.toString());
        }
        catch(IOException e) {
        }
    }

    /**
     * @Title: exportTemplate
     * @Description: TODO 导出模板excel文件
     * @author YG Tan
     * @date 2016年1月27日 下午2:36:19
     * @return void
     */
    public void exportTemplate() {
        InputStream in = null;
        try {
            getResponse().setContentType("application/octet-stream; charset=utf-8");
            getResponse().setHeader("Content-Disposition",
                    "attachment;filename=" + new String("导学名单导入模板.xlsx".getBytes("GBK"), "ISO8859-1"));

            String templatePath = ApplicationUtil.getInstance().getProjectPath() + File.separator + "template"
                    + File.separator + "LearningGuidStudentsList.xlsx";
            File temFile = new File(templatePath);
            byte[] bytes = new byte[0xffff];
            in = new FileInputStream(temFile);
            ServletOutputStream out = getResponse().getOutputStream();
            int b = 0;
            while ((b = in.read(bytes, 0, 0xffff)) > 0) {
                out.write(bytes, 0, b);
            }
            out.flush();
        }
        catch(Exception e) {
            log.error("导出模板文件出错", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                    log.error("关闭导出模板文件的输入流出错", e);
                }
            }
        }
    }

    /**
     * @Title: exportExcelList
     * @Description: TODO(将查询结果导出到Excel表。)
     * @author liangriyu
     * @date 2016年1月12日 下午2:50:23
     * @return void
     */
    public void exportExcelList() {
        try {
            HttpServletRequest req = getRequest();
            User user=getSessionUser();
            boolean isAdmin = (boolean) getSession().getAttribute("isAdmin");
            ExportExcelVO vo = service.exportExcelList(new Page<LearningGuidStudentsList>(req),user,isAdmin);
            ExcelUtil.exportArray(getResponse(), vo.getTitle(), vo.getHeadTitle(), vo.getDataList());
        }
        catch(Exception err) {
            log.error("导出文件写出结果时出异常", err);
            err.printStackTrace();
        }
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

    public String getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(String academicyear) {
        this.academicyear = academicyear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTeacherno() {
        return teacherno;
    }

    public void setTeacherno(String teacherno) {
        this.teacherno = teacherno;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
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

    public File getExcel() {
        return excel;
    }

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }
    
}
