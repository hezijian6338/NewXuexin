package com.zhbit.xuexin.student.service.impl;

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
import com.zhbit.xuexin.domain.PostInfo;
import com.zhbit.xuexin.domain.RewardPunishment;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.RewardPunishmentDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.RewardPunishmentService;
import com.zhbit.xuexin.sys.dao.OrganizationDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-20 下午9:33:35
 * @version 1.0
 */
@Service("rewardPunishmentService")
@Transactional(readOnly = true)
public class RewardPunishmentServiceImpl implements RewardPunishmentService {
	private Logger logger = LoggerFactory
			.getLogger(RewardPunishmentServiceImpl.class);

	@Autowired
	@Qualifier("rewardPunishmentDao")
	private RewardPunishmentDao dao;

	@Autowired
	@Qualifier("studentsDao")
	private StudentsDao studentsDao;

	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao orgDao;

	/**
	 * @Title: getList
	 * @Description: TODO(分页获取信息列表。)
	 * @author lry
	 * @date 2016-3-15 下午10:41:16
	 * @param page
	 * @return
	 * @return Page<TeacherInfo>
	 */
	@Override
	public Page<RewardPunishment> getList(Page<RewardPunishment> page) {
		return dao.getList(page);
	}

	/**
	 * @Title: save
	 * @Description: TODO(保存新增信息。)
	 * @author lry
	 * @date 2016-3-15 下午10:42:16
	 * @param info
	 * @param userId
	 *            创建者
	 * @return
	 * @return int
	 * @throws ParseException
	 */
	@Override
	@Transactional(readOnly = false)
	public int save(RewardPunishment info, String userId,
			String happenedDateStr, String rewardDateStr,
			Page<RewardPunishment> page) throws ParseException {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<RewardPunishment> pageResult = dao.getList(page);
		List<RewardPunishment> list = pageResult.getResult();
		if (info != null) {
			Students stu = studentsDao.getStudentByNoAndName(info.getStudentno(), info.getStuname());
			if (stu == null) {
				return 0;
			} else {
				for (int i = 0; i < list.size(); i++) {
					RewardPunishment info_old = list.get(i);
					if (!info_old.getStuname().equals(info.getStuname())) {
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
			info.setStuId(stu.getStuId());
			dao.save(info);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * @Description: TODO(通过id获取对象实体。)
	 * @author lry
	 * @date 2016-3-20 下午9:55:52
	 * @param id
	 * @return
	 * @return RewardPunishment
	 */
	@Override
	public RewardPunishment getRewardPunishmentByid(String id) {
		return dao.getRewardPunishmentByid(id);
	}

	/**
	 * @Title: update
	 * @Description: TODO(修改信息。)
	 * @author lry
	 * @date 2016-3-15 下午10:44:06
	 * @param info
	 * @param oldInfo
	 * @return void
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(RewardPunishment info, RewardPunishment oldInfo,
			String happenedDateStr, String rewardDateStr) throws Exception {
		if (info != null) {
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
			info.setStuname(oldInfo.getStuname());
			info.setCreateTime(oldInfo.getCreateTime());
			info.setCreator(oldInfo.getCreator());
			info.setStuId(oldInfo.getStuId());
			dao.update(info);
		}
	}

	/**
	 * @Title: delete
	 * @Description: TODO(删除。)
	 * @author lry
	 * @date 2016-3-15 下午10:46:26
	 * @param ids
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for (String id : sids) {
				RewardPunishment info = dao.getRewardPunishmentByid(id);
				dao.delete(info);
			}
		}
	}

	/**
	 * @Title: importFile
	 * @Description: TODO(导入。)
	 * @author lry
	 * @date 2016-3-15 下午10:47:48
	 * @param excel
	 * @param user
	 * @return
	 * @return int[]
	 */
	@Override
	@Transactional(readOnly = false)
	public int[] importFile(File excel, User user, String suffix) {
		Workbook wb = null;
		FileInputStream in = null;
		int importCount = 0;// 成功导入的总条数
		int insertCount = 0;// 导入新增的总条数、
		int updateCount = 0;// 导入更新的总条数
		try {
			in = new FileInputStream(excel);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入的文件出异常", e);
			return new int[] { -1, insertCount, updateCount };
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("读取导入的文件时关闭输入流异常", e);
				}
			}
		}
		if (wb != null) {
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			rowIterator.next();// 第一行标题不读
			rowIterator.next();// 第二行表头不读
			String msg = null;

			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					if (!"".equals(ExcelUtil.getCellValue(row.getCell(2)))) {
						importCount++;
						System.out.println("~~~~~~"
								+ ExcelUtil.getCellValue(row.getCell(2)));
						Students stu = studentsDao.getStudentByNo(ExcelUtil
								.getCellValue(row.getCell(2)));
						if (stu == null) {// 存在学生信息才导入
							updateCount++;
						} else {
							RewardPunishment info = new RewardPunishment();
							info.setStuname(ExcelUtil.getCellValue(row
									.getCell(1)));
							info.setStudentno(ExcelUtil.getCellValue(row
									.getCell(2)));
							info.setOrgName(ExcelUtil.getCellValue(row
									.getCell(3)));
							if (!"".equals(ExcelUtil.getCellValue(row
									.getCell(3)))) {
								info.setOrgId(Const.ORG_ID_MAP.get(ExcelUtil
										.getCellValue(row.getCell(3))));
							}
							info.setMajor(ExcelUtil.getCellValue(row.getCell(4)));
							info.setHappenedDate(ExcelUtil.getDateValue(
									ExcelUtil.getCellValue(row.getCell(5)),
									"yyyy.MM.dd"));
							info.setRewardDate(ExcelUtil.getDateValue(
									ExcelUtil.getCellValue(row.getCell(6)),
									"yyyy.MM.dd"));
							info.setFileNo(ExcelUtil.getCellValue(row
									.getCell(7)));
							info.setDescription(ExcelUtil.getCellValue(row
									.getCell(8)));
							info.setRewardType(ExcelUtil.getCellValue(row
									.getCell(9)));
							info.setRpOrgName(ExcelUtil.getCellValue(row
									.getCell(10)));
							if (!"".equals(ExcelUtil.getCellValue(row
									.getCell(10)))) {
								info.setRpOrgId(Const.ORG_ID_MAP.get(ExcelUtil
										.getCellValue(row.getCell(10))));
							}

							info.setMemo(ExcelUtil.getCellValue(row.getCell(11)));
							info.setCreateTime(new Date());
							info.setStuId(stu.getStuId());
							dao.save(info);
							insertCount++;
						}
					}
				} catch (Exception e) {
					msg = (ExcelUtil.getCellValue(row.getCell(4)))
							+ (ExcelUtil.getCellValue(row.getCell(5)));
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
				}
			}
		}
		return new int[] { importCount, insertCount, updateCount };
	}

	/**
	 * @Title: exportExcelList
	 * @Description: TODO(导出。)
	 * @author lry
	 * @date 2016-3-14 上午12:59:51
	 * @param page
	 * @return
	 * @return ExportExcelVO
	 */
	@Override
	public ExportExcelVO exportExcelList(Page<RewardPunishment> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<RewardPunishment> pageResult = dao.getList(page);
		List<RewardPunishment> list = pageResult.getResult();
		// 设置表头
		String[] title = { "序号", "学号", "姓名", "学院", "专业班级", "发生时间", "奖惩时间",
				"公文文号", "事项描述", "奖惩类型", "奖惩部门", "备注" };
		// 设置文件名
		String filename = "学生奖惩记录表";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			RewardPunishment info = list.get(i);
			String[] str = new String[12];
			str[0] = String.valueOf(++i);
			str[1] = info.getStudentno() == null ? "" : info.getStudentno();
			str[2] = info.getStuname() == null ? "" : info.getStuname();
			str[3] = info.getOrgName() == null ? "" : info.getOrgName();
			str[4] = info.getMajor() == null ? "" : info.getMajor();
			str[5] = info.getHappenedDate() == null ? "" : ExcelUtil
					.setDateValue(info.getHappenedDate(), "yyyy.MM.dd");
			str[6] = info.getRewardDate() == null ? "" : ExcelUtil
					.setDateValue(info.getRewardDate(), "yyyy.MM.dd");
			str[7] = info.getFileNo() == null ? "" : info.getFileNo();
			str[8] = info.getDescription() == null ? "" : info.getDescription();
			str[9] = info.getRewardType() == null ? "" : info.getRewardType();
			str[10] = info.getRpOrgName() == null ? "" : info.getRpOrgName();
			str[11] = info.getMemo() == null ? "" : info.getMemo();
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

	/**
	 * @Title: uploadAttachment
	 * @Description: TODO(上传附件。)
	 * @author 梁日宇
	 * @date 2016-4-11 下午8:36:00
	 * @param pId
	 * @param suffix
	 * @return void
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false)
	public void uploadFile(String sid, File attachment, String suffix)
			throws Exception {
		// StudentCertificate info = dao.getStudentCertificateByid(sid);
		// String file = info.getStudentno()+"_"+ suffix;
		// removeFile(info.getDocpath());
		// String savePath = Const.student_certificate + file;
		// // 保存到物理路径
		// FileOP.writeFile(savePath, attachment);
		// info.setDocpath(file);
		// dao.update(info);
	}

}
