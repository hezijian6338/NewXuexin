package com.zhbit.xuexin.teacher.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.AttendanceMaster;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.service.AttendanceMasterService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:34:19
 * @version 1.0
 */
@Controller("attendanceMasterAction")
@Scope("prototype")
public class AttendanceMasterAction extends BaseAction implements ModelDriven<AttendanceMaster> {

    /**
     * @Fields serialVersionUID : TODO(简单说明是做什么的。)
     */
    private static final long serialVersionUID = -5329106811365077799L;

    private AttendanceMaster info;

    @Override
    public AttendanceMaster getModel() {
        if (info == null) {
            info = new AttendanceMaster();
        }
        return info;
    }

    private String sid;

    private int page;// 当前页

    private int rows;// 页面大小

    private String ids;

    private File excel;// 导入文件

    private String excelFileName;// 导入文件名

    private File attachment;// 附件

    private String attachmentFileName;

    @Resource
    private AttendanceMasterService service;

    /**
     * @Title: base
     * @Description: TODO(学生考勤tab页面)
     * @author 梁日宇
     * @date 2015-12-14 下午8:55:25
     * @return
     * @return String
     */
    public String base() {
        return "base";
    }
    
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
     * @Title: viewUpload
     * @Description: TODO(上传附件页面。)
     * @author liangriyu
     * @date 2016-3-9 下午11:14:27
     * @return
     * @return String
     */
    public String viewUpload() {
        return "viewUpload";
    }

    /**
     * @Title: viewDownload
     * @Description: TODO(下载附件页面。)
     * @author liangriyu
     * @date 2016-3-9 下午11:14:27
     * @return
     * @return String
     */
    public String viewDownload() {
        return "viewDownload";
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
     * 获取列表
     * 
     * @author 梁日宇
     * @return
     */
    public void getList() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            HttpServletRequest req = getRequest();
            Page<AttendanceMaster> page = service.getList(new Page<AttendanceMaster>(req));
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

            int state = service.save(info, getSessionUser().getUserId());
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
            AttendanceMaster oldInfo = service.getAttendanceMasterByid(info.getId());
            service.update(info, oldInfo);
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
     * @Title: getAttendanceMaster
     * @Description: TODO(获取对象信息。)
     * @author liangriyu
     * @date 2016-3-11 上午2:34:01
     * @return void
     */
    public void getAttendanceMaster() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            AttendanceMaster info = service.getAttendanceMasterByid(sid);
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
            // System.out.println("~~~~~~~~~~~~~~~~~~~~~"+excelFileName);
            if (excel != null) {
                String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
                if (".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
                    User user = (User) getSession().getAttribute(Const.SESSION_USER);
                    String sourceFile = Const.getUUID() + suffix;
                    count = service.importFile(excel, user, suffix, sourceFile);
                    if (count[0] < 0) {
                        resultCode = Const.CODE_UNKOWN_ERR;
                    }
                    else {// 导入成功则保存
                        String savePath = Const.student_attendance + sourceFile;
                        // 保存到物理路径
                        FileOP.writeFile(savePath, excel);
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
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Title: exportTemplate
     * @Description: TODO 导出模板excel文件
     * @author lry
     * @date 2016年1月27日 下午2:36:19
     * @return void
     */
    public void exportTemplate() {
        InputStream in = null;
        try {
            getResponse().setContentType("application/octet-stream; charset=utf-8");
            getResponse().setHeader("Content-Disposition",
                    "attachment;filename=" + new String("学生考勤登记主表导入模板.xls".getBytes("GBK"), "ISO8859-1"));

            String templatePath = ApplicationUtil.getInstance().getProjectPath() + File.separator + "template"
                    + File.separator + "AttendanceMaster.xls";
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

    public void uploadAttachment() {
        try {
            if (attachment != null) {
                String suffix = attachmentFileName.substring(attachmentFileName.lastIndexOf("."));
                service.uploadAttachment(sid, attachment, suffix);
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
     * @Title: downloadFile
     * @Description: TODO 下载附件
     * @author lry
     * @date 2016年1月27日 下午2:36:19
     * @return void
     */
    public void downloadFile() {
        InputStream in = null;
        try {
            String suffix = attachmentFileName.substring(attachmentFileName.lastIndexOf("."));
            getResponse().setContentType("application/octet-stream; charset=utf-8");
            getResponse().setHeader("Content-Disposition",
                    "attachment;filename=" + new String(("学生考勤登记表附件" + suffix).getBytes("GBK"), "ISO8859-1"));

            String templatePath = Const.student_attendance + attachmentFileName;
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
            log.error("下载文件出错", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                    log.error("关闭下载文件的输入流出错", e);
                }
            }
        }
    }

    /**
     * @Title: delteFile
     * @Description: TODO(删除附件。)
     * @author lry
     * @date 2016-4-12 上午12:51:07
     * @return void
     */
    public void delteFile() {
        Map<String, Object> map = Const.getJsonMap();
        try {
            service.delteFile(sid,attachmentFileName);
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

    public AttendanceMaster getInfo() {
        return info;
    }

    public void setInfo(AttendanceMaster info) {
        this.info = info;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

}
