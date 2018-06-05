package com.zhbit.xuexin.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.TeaRewardPunishment;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.teacher.dao.RewardPunishmentDao;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;
import com.zhbit.xuexin.teacher.service.RewardPunishmentService;

@Service("tearewardPunishmentService")
@Transactional(readOnly = true)
public class RewardPunishmentServiceImpl implements RewardPunishmentService{
	private Logger logger = LoggerFactory
			.getLogger(RewardPunishmentServiceImpl.class);
	
	@Autowired
	@Qualifier("tearewardPunishmentDao")
	private RewardPunishmentDao dao;
	
	@Autowired
	@Qualifier("teacherInfoDao")
	private TeacherInfoDao teacherInfoDao;
	
	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao orgDao;
	@Override
	public Page<TeaRewardPunishment> getList(Page<TeaRewardPunishment> page) {
		return dao.getList(page);
	}
	@Override
	@Transactional(readOnly = false)
	public int save(TeaRewardPunishment info, String userId,
			String happenedDateStr, String rewardDateStr,
			Page<TeaRewardPunishment> page) throws ParseException {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<TeaRewardPunishment> pageResult = dao.getList(page);
		List<TeaRewardPunishment> list = pageResult.getResult();
		if(info != null) {
			TeacherInfo tea = teacherInfoDao.getTeacherInfoByNoAndName(info.getEmployno(),info.getEmployname());
			if(tea == null) {
				return 0;
			} else {
				for (int i = 0; i < list.size(); i++) {
					TeaRewardPunishment info_old = list.get(i);
					if(!info_old.getEmployname().equals(info.getEmployname())) {
						return 0;
					}
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Organization org = orgDao.getOrganizationById(info.getOrgId());
			info.setOrgName(org.getOrgName());
			Organization org2 = orgDao.getOrganizationById(info.getRpOrgId());
			info.setRpOrgName(org2.getOrgName());
			if (happenedDateStr != null && !"".equals(happenedDateStr)) {
				info.setHappenedDate(df.parse(happenedDateStr));
			}
			if (rewardDateStr != null && !"".equals(rewardDateStr)) {
				info.setRewardDate(df.parse(rewardDateStr));
			}
			info.setCreateTime(new Date());
			info.setCreator(userId);
			info.setId(tea.getId());
			dao.save(info);
			return 1;
		} else {
			return 0;
		}
		
	}
	/**
	 * 
	* @Title: getTeaRewardPunishmentByid   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param id
	* @param @return    设定文件   
	* @date 2018-5-31 下午5:41:17
	* @author 林敬凯
	* @throws
	 */
	@Override
	public TeaRewardPunishment getTeaRewardPunishmentByid(String id) {
		return dao.getTeaRewardPunishmentByid(id);
	}
	/**
	 * 
	* @Title: update   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param info
	* @param @param oldInfo
	* @param @param happenedDateStr
	* @param @param rewardDateStr    设定文件   
	* @date 2018-5-31 下午5:57:35
	* @author 林敬凯
	* @throws
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(TeaRewardPunishment info, TeaRewardPunishment oldInfo,
			String happenedDateStr, String rewardDateStr) throws Exception {
		if(info != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Organization org = orgDao.getOrganizationById(info.getOrgId());
			info.setOrgName(org.getOrgName());
			Organization org2 = orgDao.getOrganizationById(info.getRpOrgId());
			info.setRpOrgName(org2.getOrgName());
			if (happenedDateStr != null && !"".equals(happenedDateStr)) {
				info.setHappenedDate(df.parse(happenedDateStr));
			}
			if (rewardDateStr != null && !"".equals(rewardDateStr)) {
				info.setRewardDate(df.parse(rewardDateStr));
			}
			info.setEmployname(oldInfo.getEmployname());
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			info.setId(oldInfo.getId());
			dao.update(info);
		}
		
	}
	/**
	 * 
	* @Title: delete   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param ids    设定文件   
	* @date 2018-5-31 下午9:28:54
	* @author 林敬凯
	* @throws
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for(String id : sids) {
				TeaRewardPunishment info = dao.getTeaRewardPunishmentByid(id);
				dao.delete(info);
			}
		}
		
	}
	/**
	 * 
	* @Title: exportExcelList   
	* @Description: TODO(导出。)   
	* @param @param page
	* @param @return    设定文件   
	* @date 2018-6-3 下午10:41:44
	* @author 林敬凯
	* @throws
	 */
	@Override
	public ExportExcelVO exportExcelList(Page<TeaRewardPunishment> page) {
		page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<TeaRewardPunishment> pageResult = dao.getList(page);
        List<TeaRewardPunishment> list = pageResult.getResult();
        // 设置表头
        String[] title = {"工号","姓名","学院","职务","发生时间","奖惩时间","公文文号","事项描述","奖惩类型","奖惩部门","备注"};
        // 设置文件名
        String filename = "教师奖惩信息表";
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        for(int i = 0; i < list.size(); i++) {
        	TeaRewardPunishment info = list.get(i);
        	String[] str = new String[11];
        	str[0] = info.getEmployno();
        	str[1] = info.getEmployname();
        	str[2] = info.getOrgName();
        	str[3] = info.getDuty();
        	str[4] = info.getHappenedDate() == null ? "" : ExcelUtil
					.setDateValue(info.getHappenedDate(), "yyyy.MM.dd");
        	str[5] = info.getRewardDate() == null ? "" : ExcelUtil
					.setDateValue(info.getRewardDate(), "yyyy.MM.dd");
        	str[6] = info.getFileNo();
        	str[7] = info.getDescription();
        	str[8] = info.getRewardType();
        	str[9] = info.getRpOrgName();
        	str[10] = info.getMemo();
        	listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
	}
	/**
	 * 
	* @Title: importFile   
	* @Description: TODO(导入。)   
	* @param @param excel
	* @param @param user
	* @param @param suffix
	* @param @return    设定文件   
	* @date 2018-6-4 上午8:42:56
	* @author 林敬凯
	* @throws
	 */
	@Override
	@Transactional(readOnly = false)
	public int[] importFile(File excel, User user, String suffix) {
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
		Workbook wb = null;
		FileInputStream in = null;
		int importCount = 0;// 成功导入的总条数
		int insertCount = 0;// 导入新增的总条数
		int updateCount = 0;// 导入更新的总条数
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch(Exception e) {
			logger.error("读取导入的网站配置文件出异常", e);
			return new int[] { -1, insertCount, updateCount };
		} finally {
			if(in != null) {
				try{
					in.close();
				} catch(IOException e) {
					logger.error("读取导入的网站配置文件时关闭输入流异常", e);
				}
			}
		}
		if(wb != null) {
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			rowIterator.next();// 第一行标题不读
			String msg = null;
			
			while(rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					if(!"".equals(ExcelUtil.getCellValue(row.getCell(0)))) {
						importCount++;
						System.out.println(".........." + ExcelUtil.getCellValue(row.getCell(0)));
						TeacherInfo tea = teacherInfoDao.getTeacherInfoByNo(ExcelUtil.getCellValue(row.getCell(0)));
						if (tea == null) {
							updateCount++;
						} else {
							TeaRewardPunishment info = new TeaRewardPunishment();
							info.setEmployno(ExcelUtil.getCellValue(row.getCell(0)));
							info.setEmployname(ExcelUtil.getCellValue(row.getCell(1)));
							info.setOrgName(ExcelUtil.getCellValue(row.getCell(2)));
							if (!"".equals(ExcelUtil.getCellValue(row.getCell(2)))) {
								info.setOrgId(Const.ORG_ID_MAP.get(ExcelUtil.getCellValue(row.getCell(2))));
							}
							info.setDuty(ExcelUtil.getCellValue(row.getCell(3)));
							info.setHappenedDate(ExcelUtil.getDateValue(
									ExcelUtil.getCellValue(row.getCell(4)),
									"yyyy.MM.dd"));
							info.setRewardDate(ExcelUtil.getDateValue(
									ExcelUtil.getCellValue(row.getCell(5)),
									"yyyy.MM.dd"));
							info.setFileNo(ExcelUtil.getCellValue(row.getCell(6)));
							info.setDescription(ExcelUtil.getCellValue(row.getCell(7)));
							info.setRewardType(ExcelUtil.getCellValue(row.getCell(8)));
							info.setRpOrgName(ExcelUtil.getCellValue(row.getCell(9)));
							if (!"".equals(ExcelUtil.getCellValue(row.getCell(9)))) {
								info.setRpOrgId(Const.ORG_ID_MAP.get(ExcelUtil.getCellValue(row.getCell(9))));
							}
							info.setMemo(ExcelUtil.getCellValue(row.getCell(10)));
							info.setCreateTime(new Date());
							info.setId(tea.getId());
							dao.save(info);
							insertCount++;
						}
					}
					
				} catch(Exception e) {
					msg = (ExcelUtil.getCellValue(row.getCell(4)))
							+ (ExcelUtil.getCellValue(row.getCell(5)));
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
				} 
			}
		}
		return new int[] { importCount, insertCount, updateCount };
	}

}
