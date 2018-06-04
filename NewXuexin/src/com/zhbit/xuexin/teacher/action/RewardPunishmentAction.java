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
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ApplicationUtil;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.RewardPunishment;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.teacher.service.RewardPunishmentService;
@Controller("tearewardPunishmentAction")
@Scope("prototype")
/**
 * 
* @ClassName: RewardPunishmentAction   
* @Description: TODO(这里用一句话描述这个类的作用)   
* @author 林敬凯   
* @date 2018-5-31 上午11:07:13   
*
 */
public class RewardPunishmentAction extends BaseAction implements ModelDriven<TeaRewardPunishment>{

	
	private static final long serialVersionUID = 1L;

	private TeaRewardPunishment info;
	@Override
	public TeaRewardPunishment getModel() {
		if (info == null) {
            info = new TeaRewardPunishment();
        }
        return info;
	}
	private String pId;
    
    private int page;// 当前页

    private int rows;// 页面大小

    private String ids;

    private File excel;// 导入文件

    private String excelFileName;// 导入文件名
    
    private String happenedDateStr;
    
    private String rewardDateStr;
    
    private File attachment;// 附件

    private String attachmentFileName;
    @Resource
    private RewardPunishmentService service;
    
    public String index() {
        return "index";
    }
    /**
     * 
    * @Title: viewAdd   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param @return    设定文件   
    * @return String    返回类型   
    * @throws
     */
    public String viewAdd() {
        return "viewAdd";
    }
    
    public String viewEdit() {
    	return "viewEdit";
    }
    /**
     * 
    * @Title: viewImport   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param @return    设定文件   
    * @return String    返回类型  
    * @date 2018-6-4 下午5:05:45
    * @author 林敬凯
    * @throws
     */
    public String viewImport() {
        return "viewImport";
    }
    
    public void getList() {
    	Map<String, Object> map = Const.getJsonMap();
    	try {
    		HttpServletRequest req = getRequest();
    		Page<TeaRewardPunishment> page = service.getList(new Page<TeaRewardPunishment>(req));
    		//
    		map.put("total", page.getCount());
            map.put("rows", page.getResult());
            map.put("status", Const.CODE_SUCCESS);
    	} catch(Exception err) {
    		err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
    	}
    	OutUtil.outJson(map, getResponse());
    }
	/**
	 * 
	* @Title: save   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param     设定文件   
	* @return void    返回类型  
	* @date 2018-5-31 上午11:22:50  
	* @throws
	 */
    public void save() {
    	Map<String, Object> map = Const.getJsonMap();
    	try {
    		HttpServletRequest req = getRequest();
    		int state = service.save(info, getSessionUser().getUserId(),happenedDateStr,rewardDateStr, new Page<TeaRewardPunishment>(req));
    		if(state==1){
                map.put("status", Const.CODE_SUCCESS);
            }else{
                map.put("status", Const.CODE_FAIL);
            }
    	} catch(Exception err) {
    		err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
    	}
    	OutUtil.outJson(map, getResponse());
    }
    /**
     * 
    * @Title: update   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-5-31 下午4:39:14
    * @author 林敬凯
    * @throws
     */
    public void update() {
    	Map<String, Object> map = Const.getJsonMap();
    	try {
    		TeaRewardPunishment oldInfo = service.getTeaRewardPunishmentByid(info.getRid());
            service.update(info,oldInfo,happenedDateStr,rewardDateStr);
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
    * @Title: delete   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-5-31 下午9:27:10
    * @author 林敬凯
    * @throws
     */
    public void delete() {
    	Map<String, Object> map = Const.getJsonMap();
    	try{
    		service.delete(ids);
            map.put("status", Const.CODE_SUCCESS);
            map.put("message", "删除成功");
    	} catch (Exception err) {
    		err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
            map.put("message", "删除失败");
    	} 
    	OutUtil.outJson(map, getResponse());
    }
    /**
     * 
    * @Title: getTeaRewardPunishment   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-5-31 下午7:22:48
    * @author 林敬凯
    * @throws
     */
    public void getTeaRewardPunishment() {
    	Map<String, Object> map = Const.getJsonMap();
    	try {
    		TeaRewardPunishment info = service.getTeaRewardPunishmentByid(pId);
    		map.put("info", info);
            map.put("status", Const.CODE_SUCCESS);
    	} catch(Exception err) {
    		err.printStackTrace();
            map.put("status", Const.CODE_FAIL);
    	}
    	OutUtil.outJson(map, getResponse());
    }
    /**
     * 
    * @Title: exportExcelList   
    * @Description: TODO(将查询结果导出到Excel表。)   
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-6-3 下午10:36:15
    * @author 林敬凯
    * @throws
     */
    public void exportExcelList() {
    	try {
    		HttpServletRequest req = getRequest();
    		ExportExcelVO vo = service.exportExcelList(new Page<TeaRewardPunishment>(req));
    		ExcelUtil.exportArray(getResponse(), vo.getTitle(), vo.getHeadTitle(), vo.getDataList());
    		
    	} catch (Exception err) {
    		log.error("导出文件写出结果时出异常", err);
            err.printStackTrace();
    	}
    }
    /**
     * 
    * @Title: importFile   
    * @Description: TODO(导入学生信息)
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-6-4 上午8:30:53
    * @author 林敬凯
    * @throws
     */
    public void importFile() {
    	try {
    		int resultCode = 0;
    		int[] count = { 0, 0, 0 };
    		if (excel != null) {
    			String suffix = excelFileName.substring(excelFileName.lastIndexOf("."));
    			if(".xlsx".equalsIgnoreCase(suffix) || ".xls".equalsIgnoreCase(suffix)) {
    				User user = (User) getSession().getAttribute(Const.SESSION_USER);
    				count = service.importFile(excel, user, suffix);
    				if(count[0] < 0) {
    					resultCode = Const.CODE_UNKOWN_ERR;
    				} 
    			} else {// 格式不符
					resultCode = 1;
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
    	} catch (IOException e) {
    	}
    }
    /**
     * 
    * @Title: exportTemplate   
    * @Description: TODO(这里用一句话描述这个方法的作用)   
    * @param     设定文件   
    * @return void    返回类型  
    * @date 2018-6-4 下午4:57:51
    * @author 林敬凯
    * @throws
     */
    public void exportTemplate() {
    	InputStream in = null;
    	try {
    		getResponse().setContentType("application/octet-stream; charset=utf-8");
    		getResponse().setHeader("Content-Disposition",
                    "attachment;filename=" + new String("教师奖惩记录导入模板.xls".getBytes("GBK"), "ISO8859-1"));
    		String templatePath = ApplicationUtil.getInstance().getProjectPath() + File.separator + "template"
                    + File.separator + "TeaRewardPunishment.xls";
    		File temFile = new File(templatePath);
    		byte[] bytes = new byte[0xffff];
    		in = new FileInputStream(temFile);
    		ServletOutputStream out = getResponse().getOutputStream();
    		int b = 0;
    		while ((b = in.read(bytes, 0, 0xffff)) > 0) {
                out.write(bytes, 0, b);
            }
            out.flush();
    	} catch(Exception e) {
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
    public TeaRewardPunishment getInfo() {
        return info;
    }

    public void setInfo(TeaRewardPunishment info) {
        this.info = info;
    }

    public String getGsId() {
        return pId;
    }

    public void setGsId(String gsId) {
        this.pId = gsId;
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

    public String getHappenedDateStr() {
        return happenedDateStr;
    }

    public void setHappenedDateStr(String happenedDateStr) {
        this.happenedDateStr = happenedDateStr;
    }

    public String getRewardDateStr() {
        return rewardDateStr;
    }

    public void setRewardDateStr(String rewardDateStr) {
        this.rewardDateStr = rewardDateStr;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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
