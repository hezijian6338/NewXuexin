package com.zhbit.xuexin.teacher.action;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.BaseAction;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.utils.OutUtil;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.teacher.service.RewardPunishmentService;
@Controller("tearewardPunishmentAction")
@Scope("prototype")
public class RewardPunishmentAction extends BaseAction implements ModelDriven<TeaRewardPunishment>{

	/**
	 * 
	 */
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
