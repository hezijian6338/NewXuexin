package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.GuidanceInfo;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.GuidanceInfoDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.GuidanceInfoService;

/**
 * 辅导记录Action
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-11 下午2:42:46
 * @version 1.0
 */
@Service("guidanceInfoService")
@Transactional(readOnly = true)
public class GuidanceInfoServiceImpl implements GuidanceInfoService {

	private Logger logger = LoggerFactory.getLogger(GuidanceInfoServiceImpl.class);

	@Autowired
	@Qualifier("guidanceInfoDao")
	private GuidanceInfoDao dao;

	@Autowired
	@Qualifier("studentsDao")
	private StudentsDao studentsDao;

	/**
	 * (not Javadoc)
	 * <p>
	 * Title: getList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param page
	 * @return
	 * @see com.zhbit.xuexin.student.service.GuidanceInfoService#getList(com.zhbit.xuexin.common.action.Page)
	 */
	@Override
	public Page<GuidanceInfo> getList(Page<GuidanceInfo> page) {
		return dao.getList(page);
	}

	/**
	 * (not Javadoc)
	 * <p>
	 * Title: save
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param studentno
	 * @param stuname
	 *            学生姓名
	 * @param classname
	 *            行政班
	 * @param guiddate
	 * @param guidcontent
	 * @param guidaddress
	 * @param counselor
	 * @param docpath
	 * @param mediapath
	 * @param userId
	 * @throws ParseException
	 * @see com.zhbit.xuexin.student.service.GuidanceInfoService#save(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(String studentno, String stuname, String classname, String guiddate, String guidcontent,
			String guidaddress, String counselor, String docpath, String mediapath, String userId)
			throws ParseException {
		Students stu = studentsDao.getStudentByNo(studentno);
		GuidanceInfo info = new GuidanceInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (stu != null && stuname.equals(stu.getStuname())) {
			info.setStuname(stu.getStuId());
			info.setStudentno(studentno);
			info.setStuname(stuname);
			info.setClassname(classname);
			info.setGuiddate(df.parse(guiddate));
		}
	}

	/**
	 * (not Javadoc)
	 * <p>
	 * Title: update
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @param studentno
	 * @param stuname
	 * @param classname
	 * @param guiddate
	 * @param guidcontent
	 * @param guidaddress
	 * @param counselor
	 * @param docpath
	 * @param mediapath
	 * @param userId
	 * @see com.zhbit.xuexin.student.service.GuidanceInfoService#update(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(String id, String studentno, String stuname, String classname, String guiddate,
			String guidcontent, String guidaddress, String counselor, String docpath, String mediapath, String userId)
			throws ParseException {

	}

	/**
	 * (not Javadoc)
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param ids
	 * @see com.zhbit.xuexin.student.service.GuidanceInfoService#delete(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = false)
	public void delete(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for (String id : sids) {
				GuidanceInfo info = dao.getGuidanceInfoByid(id);
				dao.delete(info);
			}
		}
	}

	/**
	 * (not Javadoc)
	 * <p>
	 * Title: getGuidanceInfoByid
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param id
	 * @return
	 * @see com.zhbit.xuexin.student.service.GuidanceInfoService#getGuidanceInfoByid(java.lang.String)
	 */
	@Override
	public GuidanceInfo getGuidanceInfoByid(String id) {
		return dao.getGuidanceInfoByid(id);
	}

	/**
	 * @Title: save
	 * @Description: TODO(保存新增。)
	 * @author lry
	 * @date 2016-3-13 下午3:32:52
	 * @param info
	 * @param guiddate
	 * @param userId
	 * @return 0-保存失败学号不存在 1-保存成功
	 */
	@Override
	@Transactional(readOnly = false)
	public int save(GuidanceInfo info, String guiddate, String userId) throws ParseException {
		Students stu = studentsDao.getStudentByNo(info.getStudentno());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (stu != null && stu.getStuname().equals(info.getStuname())) {
			info.setStuId(stu.getStuId());
			if (guiddate != null && !"".equals(guiddate)) {
				System.out.println("=================" + guiddate + "=----");
				info.setGuiddate(df.parse(guiddate));
			}
			dao.save(info);
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * @Title: update
	 * @Description: TODO(修改辅导信息。)
	 * @author lry
	 * @date 2016-3-13 下午8:21:17
	 * @param info
	 * @param guiddate
	 * @param userId
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void update(GuidanceInfo info, String guiddate, String userId) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (guiddate != null && !"".equals(guiddate)) {
			info.setGuiddate(df.parse(guiddate));
		}
		dao.update(info);
	}

	/**
	 * @Title: importFile
	 * @Description: TODO(导入。)
	 * @author lry
	 * @date 2016-3-14 上午12:59:25
	 * @param excel
	 * @param user
	 * @return
	 * @return int[]
	 */
	@Override
	@Transactional(readOnly = false)
	public int[] importFile(File excel, User user, String suffix) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
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
			logger.error("读取导入的网站配置文件出异常", e);
			return new int[] { -1, insertCount, updateCount };
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("读取导入的网站配置文件时关闭输入流异常", e);
				}
			}
		}
		if (wb != null) {
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			rowIterator.next();// 第一行标题不读
			String msg = null;
			int columnNumber = 6;
			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();
					//设置所有单元格的读取格式为文本格式读取
					for(int i=0;i<columnNumber;i++){
						if(row.getCell(i)!=null)
							row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
					}
					if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue())) {
						importCount++;
						Students stu = studentsDao.getStudentByNo(row.getCell(0).getStringCellValue());
						if (stu == null) {// 存在学生信息才导入
							updateCount++;
						} else {
							GuidanceInfo info = new GuidanceInfo();
							info.setStudentno(row.getCell(0).getStringCellValue());
							info.setStuname(row.getCell(1).getStringCellValue());
							info.setClassname(row.getCell(2).getStringCellValue());
							if (row.getCell(3) != null && !"".equals(row.getCell(3).getStringCellValue())) {
								info.setGuiddate(df.parse(row.getCell(3).getStringCellValue()));
							}
							if (row.getCell(4) != null)
								info.setGuidcontent(row.getCell(4).getStringCellValue());
							if (row.getCell(5) != null)
								info.setGuidaddress(row.getCell(5).getStringCellValue());
							info.setStuId(stu.getStuId());
							if (dao.CheckGuidanceInfoIfExist(info)) {
								dao.save(info);
								insertCount++;
							} else
								updateCount++;
						}
					}
				} catch (Exception e) {
					msg = (row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "")
							+ (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
					logger.error("读取导入的网站配置文件持久化出异常,异常数据:\n" + msg, e);
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
	public ExportExcelVO exportExcelList(Page<GuidanceInfo> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<GuidanceInfo> pageResult = dao.getList(page);
		List<GuidanceInfo> list = pageResult.getResult();
		// 设置表头
		String[] title = { "学号", "专业班级", "姓名", "辅导时间", "辅导地点", "辅导内容", "辅导老师" ,"电子文档","多媒体路径"};
		// 设置文件名
		String filename = "辅导记录表格";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < list.size(); i++) {
			String[] str = new String[9];
			str[0] = list.get(i).getStudentno();
			str[1] = list.get(i).getClassname();
			str[2] = list.get(i).getStuname();
			if (list.get(i).getGuiddate() != null)
				str[3] = df.format(list.get(i).getGuiddate());
			str[4] = list.get(i).getGuidaddress();
			str[5] = list.get(i).getGuidcontent();
			str[6] = list.get(i).getCounselor();
			str[7] = list.get(i).getDocpath();
			str[8] = list.get(i).getMediapath();
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

}
