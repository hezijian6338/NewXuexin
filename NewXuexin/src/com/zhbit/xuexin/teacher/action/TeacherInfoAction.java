package com.zhbit.xuexin.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.service.TeacherInfoService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午10:26:52
 * @version 1.0
 */
@Controller("teacherInfoAction")
@Scope("prototype")
public class TeacherInfoAction extends BaseAction implements ModelDriven<TeacherInfo> {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = 621285054244775127L;

    private String tid;

    private String birth;

    private String createDate;

    private int page;// 当前页

    private int rows;// 页面大小

    private String ids;

    private File excel;// 导入文件

    private String excelFileName;// 导入文件名

    private File photo;// 头像

    private String photoFileName;// 导入文件名
    
    private String passwordOld;

    private TeacherInfo info;

    @Override
    public TeacherInfo getModel() {
        if (info == null) {
            info = new TeacherInfo();
        }
        return info;
    }

    @Resource
    private TeacherInfoService service;

    /**
     * @Title: viewList
     * @Description: TODO(管理列表页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String index() {
        return "index";
    }

    /**
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
     * @Title: viewDetail
     * @Description: TODO(详情页面。)
     * @author liangriyu
     * @date 2016-3-9 下午11:14:27
     * @return
     * @return String
     */
    public String viewDetail() {
        return "viewDetail";
    }
    
    /**
     * @Title: viewUpdatePhoto
     * @Description: TODO(详情页面。)
     * @author liangriyu
     * @date 2016-3-9 下午11:14:27
     * @return
     * @return String
     */
    public String viewUpdatePhoto() {
        return "viewUpdatePhoto";
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
            Page<TeacherInfo> page = service.getList(new Page<TeacherInfo>(req));
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

            int state = service.save(info, birth, getSessionUser().getUserId());
            //
            if (state == 1) {
                map.put("status", Const.CODE_SUCCESS);
            }
            else {
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
     * @Description: TODO(修改信息。)
     * @author liangriyu
     * @date 2016-3-10 上午1:37:56
     * @return void
     */
    public void update() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            TeacherInfo oldInfo = service.getTeacherInfoByid(info.getId());
            service.update(info, oldInfo, birth);
            //
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
     * @Title: getTeacherInfo
     * @Description: TODO(获取对象。)
     * @author liangriyu
     * @date 2016-3-11 上午2:34:01
     * @return void
     */
    public void getTeacherInfo() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            TeacherInfo info = service.getTeacherInfoByid(tid);
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
            int[] count = { 0, 0, 0 };
            if (excel != null) {
                String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
                if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
                    User user = (User) getSession().getAttribute(Const.SESSION_USER);
                    count = service.importFile(excel, user, suffix);
                    if (count[0] < 0) {
                        resultCode = Const.CODE_UNKOWN_ERR;
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
            result.append("var updateCount=").append(count[2]).append(";");
            result.append("</script>");
            getResponse().getWriter().print(result.toString());
        }
        catch(IOException e) {
        }
    }

    /**
     * @Title: exportTemplate
     * @Description: TODO 导出模板excel文件
     * @author LRY
     * @date 2016年1月27日 下午2:36:19
     * @return void
     */
    public void exportTemplate() {
        InputStream in = null;
        try {
            getResponse().setContentType("application/octet-stream; charset=utf-8");
            getResponse().setHeader("Content-Disposition",
                    "attachment;filename=" + new String("教师基本信息导入模板.xls".getBytes("GBK"), "ISO8859-1"));

            String templatePath = ApplicationUtil.getInstance().getProjectPath() + File.separator + "template"
                    + File.separator + "teacherInfo.xls";
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
            ExportExcelVO vo = service.exportExcelList(new Page<TeacherInfo>(req));
            ExcelUtil.exportArray(getResponse(), vo.getTitle(), vo.getHeadTitle(), vo.getDataList());
        }
        catch(Exception err) {
            log.error("导出文件写出结果时出异常", err);
            err.printStackTrace();
        }
    }

    /**
     * @Title: updatePhoto
     * @Description: TODO(跟新头像。)
     * @author liangriyu
     * @date 2016-3-11 上午2:34:01
     * @return void
     */
    public void updatePhoto() {
        try {
            if (photo != null) {
                String suffix = photoFileName.substring(photoFileName.lastIndexOf("."));
                service.updatePhoto(info.getEmployNo(), photo, suffix);
            }
            StringBuffer result = new StringBuffer();
            result.append("<script type=\"text/javascript\">");
            result.append("var resultCode=").append(0).append(";");
            result.append("</script>");
            getResponse().getWriter().print(result.toString());
        }
        catch(Exception e) {
            log.error("上传附件件出错", e);
        }
    }

    /**
     * @Title: getPhotoFile
     * @Description: TODO(获取照片。)
     * @author LRY
     * @date 2016-4-27 上午12:58:13
     * @return void
     */
    public void getPhotoFile() {
        try {
            String realPath = ServletActionContext.getServletContext().getRealPath(Const.teacherPhoto);
            TeacherInfo tInfo = service.getTeacherInfoByNo(info.getEmployNo());
            if(tInfo.getPhotopath()!=null){
                OutUtil.outOpenFile(realPath + File.separator + tInfo.getPhotopath(), getResponse());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @Title: updatePwd
     * @Description: TODO(修改密码。)
     * @author LRY
     * @date 2016-5-18 下午11:23:24
     * @return void
     */
    public void updatePwd() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            int rs = service.updatePwd(info.getPassword(),passwordOld,info.getId());
            if(rs==0){
                map.put("status", Const.CODE_SUCCESS);
                map.put("message", "修改成功");
            }else if(rs==1){
                map.put("status", Const.CODE_FAIL);
                map.put("message", "密码不正确");
            }else if(rs==2){
                map.put("status", Const.CODE_FAIL);
                map.put("message", "该老师未绑定用户");
            }
            
        }
        catch(Exception err) {
            err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "修改失败");
        }
        OutUtil.outJson(map, getResponse());
    }
    
    /**
     * @Title: viewPwd
     * @Description: TODO(修改教师密码页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String viewPwd() {
        return "viewPwd";
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public TeacherInfo getInfo() {
        return info;
    }

    public void setInfo(TeacherInfo info) {
        this.info = info;
    }

}
