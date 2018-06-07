package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.dto.StuQuery;
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.common.utils.SecurityUtil;
import com.zhbit.xuexin.common.utils.zip.MultipleFileZipUtil;
import com.zhbit.xuexin.common.utils.zip.MultipleFileZipUtil.CompressFile;
import com.zhbit.xuexin.common.utils.zip.TempFolderHelper;
import com.zhbit.xuexin.domain.FamilyInfo;
import com.zhbit.xuexin.domain.LearningExperience;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.FamilyInfoDao;
import com.zhbit.xuexin.student.dao.LearningExperienceDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.StudentsService;

/**
 * 学生信息管理业务
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-1 下午3:18:39
 * @version 1.0
 */
@Service("studentsService")
@Transactional(readOnly = true)
public class StudentsServiceImpl implements StudentsService {
	private Logger logger = LoggerFactory.getLogger(StudentsServiceImpl.class);

	@Autowired
	@Qualifier("studentsDao")
	private StudentsDao studentsDao;
	@Autowired
	private LearningExperienceDao leDao;
	@Autowired
	private FamilyInfoDao fiDao;

	/**
	 * @Title: getStuToUser
	 * @Description: TODO(通过学生编号获取信息封装成user对象。)
	 * @author liangriyu
	 * @date 2016-3-1 下午3:11:04
	 * @return
	 * @return User
	 */
	@Override
	public User getStuToUser(String stuNo) {
		return studentsDao.getStuToUser(stuNo);
	}

	/**
	 * @Title: getList
	 * @Description: TODO(获取学生用户列表。)
	 * @author Administrator
	 * @date 2016-3-3 上午2:00:33
	 * @param page
	 * @return
	 * @return Page<Students>
	 */
	@Override
	public Page<Students> getList(Page<Students> page) {
		return studentsDao.getList(page);
	}

	/**
	 * @Title: importStudents
	 * @Description: TODO(导入学生信息。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:35:19
	 * @param stuFile
	 *            文件对象
	 * @param suffix
	 *            文件名（去后缀）
	 * @param user
	 *            当前用户
	 * @return
	 * @return int[]
	 */
	@Override
	@Transactional(readOnly = false)
	public int[] importStudents(File stuFile, User user, String suffix) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Workbook wb = null;
		FileInputStream in = null;
		int importCount = 0;// 成功导入的总条数
		int insertCount = 0;// 导入新增的总条数、
		int updateCount = 0;// 导入更新的总条数
		try {
			in = new FileInputStream(stuFile);
			if (".xlsx".equalsIgnoreCase(suffix)) {// 2007+
				wb = WorkbookFactory.create(in);
			} else {
				wb = new HSSFWorkbook(in);// 2007以下（2003）
			}
		} catch (Exception e) {
			logger.error("读取导入文件出异常", e);
			return new int[] { -1, insertCount, updateCount };
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("读取导入文件时关闭输入流异常", e);
				}
			}
		}
		if (wb != null) {
			Sheet sheet = wb.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();
			Row row = null;
			rowIterator.next();// 第一行标题不读
			String msg = null;

			while (rowIterator.hasNext()) {
				try {
					row = rowIterator.next();

					if (!ExcelUtil.getCellValue(row.getCell(0)).equals("")) {
						Students stu = studentsDao.getStudentByNo(ExcelUtil.getCellValue(row.getCell(0)));
						if (stu != null) {
							updateCount++;
						} else {
							Students students = new Students();
							students.setStudentno(ExcelUtil.getCellValue(row.getCell(0)));
							students.setStuname(ExcelUtil.getCellValue(row.getCell(1)));
							// String sex = "0";
							// if ("女".equals(ExcelUtil.getCellValue(row
							// .getCell(2)))) {
							// sex = "1";
							// }
							students.setSex(ExcelUtil.getCellValue(row.getCell(2)));
							if (!ExcelUtil.getCellValue(row.getCell(3)).equals(""))
								students.setBirthday(df.parse(ExcelUtil.getCellValue(row.getCell(3))));
							students.setPoliticalstatus(ExcelUtil.getCellValue(row.getCell(4)));
							students.setNation(ExcelUtil.getCellValue(row.getCell(5)));
							students.setNativeplace(ExcelUtil.getCellValue(row.getCell(6)));
							students.setFromPlace(ExcelUtil.getCellValue(row.getCell(7)));
							students.setOrgName(ExcelUtil.getCellValue(row.getCell(8)));
							students.setOrgId(Const.ORG_ID_MAP.get(ExcelUtil.getCellValue(row.getCell(8))));
							students.setMajor(ExcelUtil.getCellValue(row.getCell(9)));
							students.setClassname(ExcelUtil.getCellValue(row.getCell(10)));
							students.setEducationsystem(Integer.valueOf(ExcelUtil.getCellValue(row.getCell(11))));
							students.setMajorfield(ExcelUtil.getCellValue(row.getCell(12)));
							students.setMajorcategories(ExcelUtil.getCellValue(row.getCell(13)));
							if (!ExcelUtil.getCellValue(row.getCell(14)).equals(""))
								students.setAcceptancedate(df.parse(ExcelUtil.getCellValue(row.getCell(14))));
							students.setMiddleschool(ExcelUtil.getCellValue(row.getCell(15)));
							if (students.getClassname() != null && students.getClassname().length() > 2) {
								students.setGrade("20" + students.getClassname().substring(0, 2));
							}
							students.setCreateTime(new Date());
							students.setCreator(user.getUserId());
							students.setPassword(SecurityUtil.GetMD5Code(Const.stu_defult_password));

							studentsDao.saveStudents(students);

							insertCount++;
							if (insertCount % 50 == 0) {
								studentsDao.flush();
							}
						}
						importCount++;

					}
				} catch (Exception e) {
					msg = (ExcelUtil.getCellValue(row.getCell(0))) + (ExcelUtil.getCellValue(row.getCell(1)));
					logger.error("读取导入文件持久化出异常,异常数据:\n" + msg, e);
				}
			}
		}
		return new int[] { importCount, insertCount, updateCount };
	}

	/**
	 * @Title: getStudent
	 * @Description: TODO(获取学生对象。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:47:02
	 * @return
	 * @return Students
	 */
	@Override
	public Students getStudent(String stuId) {
		return studentsDao.getStudent(stuId);
	}

	/**
	 * @Title: getStudent
	 * @Description: TODO(通过学号获取学生对象。)
	 * @author liangriyu
	 * @date 2016-3-6 上午10:47:02
	 * @param stuNo
	 *            学号
	 * @return
	 * @return Students
	 */
	@Override
	public Students getStudentByNo(String stuNo) {
		return studentsDao.getStudentByNo(stuNo);
	}

	/**
	 * @Title: exportExcelList
	 * @Description: TODO(将查询结果导出到Excel表。)
	 * @author liangriyu
	 * @date 2016-3-8 下午8:49:09
	 * @param page
	 * @return ExportExcelVO
	 */
	@Override
	public ExportExcelVO exportExcelList(Page<Students> page) {
		page.setPage(1);
		page.setRows(100000);
		// 获取查询结果数据集
		Page<Students> pageResult = studentsDao.getList(page);
		List<Students> list = pageResult.getResult();
		// 设置表头
		String[] title = { "学号", "姓名", "性别", "出生日期", "政治面貌", "民族", "籍贯", "来源地区", "学院", "专业名称", "行政班", "学制", "专业方向",
				"专业类别", "入学日期", "毕业中学" };
		// 设置文件名
		String filename = "学生基本信息";
		// 设置内容
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < list.size(); i++) {
			String[] str = new String[16];
			str[0] = list.get(i).getStudentno();
			str[1] = list.get(i).getStuname();
			// String sex = list.get(i).getSex();
			// if (sex.equals("0")) {
			// str[2] = "男";
			// } else {
			// str[2] = "女";
			// }
			str[2] = list.get(i).getSex();
			str[3] = df.format(list.get(i).getBirthday());
			str[4] = list.get(i).getPoliticalstatus();
			str[5] = list.get(i).getNation();
			str[6] = list.get(i).getNativeplace();
			str[7] = list.get(i).getFromPlace();
			str[8] = list.get(i).getOrgName();
			str[9] = list.get(i).getMajor();
			str[10] = list.get(i).getClassname();
			str[11] = String.valueOf(list.get(i).getEducationsystem());
			str[12] = list.get(i).getMajorfield();
			str[13] = list.get(i).getMajorcategories();
			str[14] = df.format(list.get(i).getAcceptancedate());
			str[15] = list.get(i).getMiddleschool();
			listInfo.add(str);
		}
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

	/**
	 * @Title: saveStudents
	 * @Description: TODO(新增学生信息。)
	 * @author liangriyu
	 * @date 2016-3-9 下午11:29:05
	 * @param studentno
	 * @param stuname
	 * @param sex
	 * @param birthday
	 * @param politicalstatus
	 * @param nation
	 * @param nativeplace
	 * @param fromPlace
	 * @param idcardno
	 * @param orgId
	 * @param orgName
	 * @param department
	 * @param major
	 * @param majorfield
	 * @param majorcategories
	 * @param cultivatedirection
	 * @param classname
	 * @param educationsystem
	 * @param schoolinglength
	 * @param acceptancedate
	 * @param middleschool
	 * @param mobileno
	 * @param familytelno
	 * @param postcode
	 * @param travelrange
	 * @param address
	 * @param skill
	 * @param selfdescription
	 * @param awards
	 * @param schoolstatus
	 * @param dqszj
	 * @param photopath
	 * @param graduateflag
	 * @param alumniflag
	 * @param creator
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveStudents(String studentno, String stuname, String sex, Date birthday, String politicalstatus,
			String nation, String nativeplace, String fromPlace, String idcardno, String orgId, String orgName,
			String department, String major, String majorfield, String majorcategories, String cultivatedirection,
			String classname, Integer educationsystem, Integer schoolinglength, Date acceptancedate,
			String middleschool, String mobileno, String familytelno, String postcode, String travelrange,
			String address, String skill, String selfdescription, String awards, String schoolstatus, String dqszj,
			String photopath, String graduateflag, String alumniflag, String creator) {

		Students stu = new Students();
		stu.setStudentno(studentno);
		stu.setStuname(stuname);
		stu.setSex(sex);
		stu.setBirthday(birthday);
		stu.setPoliticalstatus(politicalstatus);
		stu.setNation(nation);
		stu.setNativeplace(nativeplace);
		stu.setFromPlace(fromPlace);
		stu.setIdcardno(idcardno);
		stu.setOrgId(orgId);
		stu.setOrgName(orgName);
		stu.setClassname(classname);
		stu.setDepartment(department);
		stu.setMajor(major);
		stu.setMajorfield(majorfield);
		stu.setMajorcategories(majorcategories);
		stu.setCultivatedirection(cultivatedirection);
		stu.setClassname(classname);
		stu.setEducationsystem(educationsystem);
		stu.setSchoolinglength(schoolinglength);
		stu.setAcceptancedate(acceptancedate);
		stu.setMiddleschool(middleschool);
		stu.setMobileno(mobileno);
		stu.setFamilytelno(familytelno);
		stu.setPostcode(postcode);
		stu.setTravelrange(travelrange);
		stu.setAddress(address);
		stu.setSkill(skill);
		stu.setSelfdescription(selfdescription);
		stu.setAwards(awards);
		stu.setSchoolstatus(schoolstatus);
		stu.setDqszj(dqszj);
		stu.setPhotopath(photopath);
		stu.setGraduateflag(graduateflag);
		stu.setAlumniflag(alumniflag);
		stu.setCreator(creator);
		stu.setCreateTime(new Date());
		stu.setPassword(SecurityUtil.GetMD5Code(Const.stu_defult_password));
		if (stu.getClassname() != null && stu.getClassname().length() > 2) {
			stu.setGrade("20" + stu.getClassname().substring(0, 2));
		}
		studentsDao.saveStudents(stu);
	}

	/**
	 * @Title: updateStudents
	 * @Description: TODO(修改学生信息。)
	 * @author 梁日宇
	 * @date 2016-3-10 上午1:36:29
	 * @param stuId
	 * @param studentno
	 * @param stuname
	 * @param sex
	 * @param parse
	 * @param politicalstatus
	 * @param nation
	 * @param nativeplace
	 * @param fromPlace
	 * @param idcardno
	 * @param orgId
	 * @param orgName
	 * @param department
	 * @param major
	 * @param majorfield
	 * @param majorcategories
	 * @param cultivatedirection
	 * @param classname
	 * @param educationsystem
	 * @param schoolinglength
	 * @param parse2
	 * @param middleschool
	 * @param mobileno
	 * @param familytelno
	 * @param postcode
	 * @param travelrange
	 * @param address
	 * @param skill
	 * @param selfdescription
	 * @param awards
	 * @param schoolstatus
	 * @param dqszj
	 * @param photopath
	 * @param graduateflag
	 * @param alumniflag
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateStudents(String stuId, String studentno, String stuname, String sex, Date birthday,
			String politicalstatus, String nation, String nativeplace, String fromPlace, String idcardno, String orgId,
			String orgName, String department, String major, String majorfield, String majorcategories,
			String cultivatedirection, String classname, Integer educationsystem, Integer schoolinglength,
			Date acceptancedate, String middleschool, String mobileno, String familytelno, String postcode,
			String travelrange, String address, String skill, String selfdescription, String awards,
			String schoolstatus, String dqszj, String photopath, String graduateflag, String alumniflag) {

		Students stu = studentsDao.getStudent(stuId);
		// stu.setStudentno(studentno);
		stu.setStuname(stuname);
		stu.setSex(sex);
		stu.setBirthday(birthday);
		stu.setPoliticalstatus(politicalstatus);
		stu.setNation(nation);
		stu.setNativeplace(nativeplace);
		stu.setFromPlace(fromPlace);
		// stu.setIdcardno(idcardno);
		stu.setOrgId(orgId);
		stu.setOrgName(orgName);
		stu.setClassname(classname);
		// stu.setDepartment(department);
		stu.setMajor(major);
		stu.setMajorfield(majorfield);
		stu.setMajorcategories(majorcategories);
		// stu.setCultivatedirection(cultivatedirection);
		stu.setClassname(classname);
		if (stu.getClassname() != null && stu.getClassname().length() > 2) {
			stu.setGrade("20" + stu.getClassname().substring(0, 2));
		}
		stu.setEducationsystem(educationsystem);
		stu.setSchoolinglength(schoolinglength);
		stu.setAcceptancedate(acceptancedate);
		stu.setMiddleschool(middleschool);
		// stu.setMobileno(mobileno);
		// stu.setFamilytelno(familytelno);
		// stu.setPostcode(postcode);
		// stu.setTravelrange(travelrange);
		// stu.setAddress(address);
		// stu.setSkill(skill);
		// stu.setSelfdescription(selfdescription);
		// stu.setAwards(awards);
		// stu.setSchoolstatus(schoolstatus);
		// stu.setDqszj(dqszj);
		// stu.setPhotopath(photopath);
		// stu.setGraduateflag(graduateflag);
		// stu.setAlumniflag(alumniflag);
		studentsDao.updateStudents(stu);
	}

	/**
	 * @Title: deleteStudents
	 * @Description: TODO(删除学生信息。)
	 * @author liangiryu
	 * @date 2016-3-10 上午2:02:19
	 * @param ids
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteStudents(String ids) {
		if (ids != null || "".equals(ids)) {
			String[] sids = ids.split(",");
			for (String stuId : sids) {
				Students students = studentsDao.getStudent(stuId);
				studentsDao.deleteStudents(students);
			}

		}
	}

	/**
	 * @Title: updatePhoto
	 * @Description: TODO(修改头像。)
	 * @author LRY
	 * @date 2016-4-27 下午3:10:17
	 * @param studentno
	 * @param photo
	 * @param suffix
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false)
	public void updatePhoto(String studentno, File photo, String suffix) throws Exception {

		String realPath = ServletActionContext.getServletContext().getRealPath(Const.studentPhoto);// 在tomcat中保存图片的实际路径
		// ==
		// "webRoot/userphoto/"
		// 对图片进行重命名
		// 根据学号选择保存目录
		String path = studentno.substring(0, 2) + File.separator + studentno.substring(2, 4) + File.separator
				+ studentno.substring(4, 6) + File.separator + studentno.substring(6, 8) + File.separator;
		String fileName = path + studentno + suffix;
		File saveFile = new File(new File(realPath), fileName); // 在该路径下实例化一个文件
		File diskFile = new File(new File(Const.student_photo), fileName);
		// 判断文件是否存在
		if (saveFile.exists()) {
			saveFile.delete();
		}
		FileUtils.copyFile(photo, saveFile);
		if (diskFile.exists()) {
			diskFile.delete();
		}
		FileOP.writeFile(Const.student_photo + fileName, photo);// 同步写到物理磁盘
		Students info = studentsDao.getStudentByNo(studentno);
		if (!fileName.equals(info.getPhotopath()) && null != info.getPhotopath()) {
			// 照片名称改变了就删除原来的照片
			// 删除磁盘中的
			File oldFile = new File(Const.student_photo, info.getPhotopath());
			if (oldFile.exists()) {
				oldFile.delete();
			}
			// 删除tomcat中的
			oldFile = new File(realPath, info.getPhotopath());
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}
		info.setPhotopath(fileName);
		studentsDao.updateStudents(info);

	}

	/**
	 * 启动初始化
	 * 
	 * @throws IOException
	 */
	public void init2() {
		try {
			// 磁盘资源清理
			String sql = "select PHOTOPATH from T_STUDENTS group by PHOTOPATH";
			List<String> list = (List<String>) studentsDao.queryPhotoPath(sql);
			File appF = new File(Const.student_photo);
			for (File f : appF.listFiles()) {
				if (!list.contains(f.getName())) {
					f.delete();
				}
			}
			// 对服务器资源缺失补回
			String p = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String webapp = new File(p).getParentFile().getParentFile().getPath();
			webapp = webapp.replaceAll("\\\\", "/");
			// 检查在服务端webapp目录下是否有对应的文件，没有则上传
			File fs = new File(Const.student_photo);
			for (File f : fs.listFiles()) {
				String fn = f.getName();
				String aimPath = webapp + "/" + Const.studentPhoto;
				File aimf = new File(aimPath + "/" + fn);
				// 判断文件是否存在
				if (!aimf.exists()) {
					FileUtils.copyFile(f, aimf);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: init2
	 * @Description: 新版头像文件初始化
	 * @return: void
	 */
	public void init() {
		try {
			String sql = "select PHOTOPATH from T_STUDENTS group by PHOTOPATH";
			List<String> list = (List<String>) studentsDao.queryPhotoPath(sql);
			String p = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String webapp = new File(p).getParentFile().getParentFile().getPath();
			// tomcat 下的图片文件夹路径
			String webpp = webapp + File.separator + Const.studentPhoto;
			// 将磁盘中的照片拷贝到tomcat下
			for (String pp : list) {
				if (pp != null) {
					File f1 = new File(webpp, pp);
					if (!f1.exists()) {
						File f2 = new File(Const.student_photo, pp);
						if (f2.exists()) {
							FileUtils.copyFile(f2, f1);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @Title: updatePwd
	 * @Description: TODO(修改学生登录密码。)
	 * @author LRY
	 * @date 2016-5-19 下午5:48:08
	 * @param password
	 * @param passwordOld
	 * @param stuId
	 * @return
	 * @return int
	 */
	@Override
	@Transactional(readOnly = false)
	public int updatePwd(String password, String passwordOld, String stuId) {
		Students stu = studentsDao.getStudent(stuId);
		// if (!SecurityUtil.GetMD5Code(passwordOld).equals(stu.getPassword()))
		// {
		// return 1;
		// }
		stu.setPassword(SecurityUtil.GetMD5Code(password));
		studentsDao.updateStudents(stu);
		return 0;
	}

	@Override
	public Page<Students> getSelf(Page<Students> page, Students stu) {

		ArrayList<Students> stus = new ArrayList<Students>();
		if (null != stu) {
			stus.add(studentsDao.getStudent(stu.getStuId()));
			page.setCount(1);
		} else {
			page.setCount(0);
		}
		page.setResult(stus);
		page.setToPage(false);

		return page;
	}

	@Override
	public String getOrgNameByOrgId(String orgId) {

		return studentsDao.getOrgNameByOrgId(orgId);
	}

	public void export(StuQuery query, OutputStream out) {
		// TODO Auto-generated method stub
		final TempFolderHelper tfh = new TempFolderHelper();
		;
		try {
			MultipleFileZipUtil m = new MultipleFileZipUtil(out);
			List<Students> stus = studentsDao.getStudentsByStuQuery(query);
			for(Students stu: stus){
				List<FamilyInfo> fis = fiDao.listByStuno(stu.getStudentno());
				List<LearningExperience> les = leDao.listByStuno(stu.getStudentno());
				StringBuilder path = new StringBuilder();
				if(StringUtils.isBlank(query.getOrgName()) && StringUtils.isBlank(query.getClassname())){
					path.append(dealNull(stu.getOrgName())).append(File.separator);
				}
				if(StringUtils.isBlank(query.getClassname())){
					path.append(dealNull(stu.getClassname())).append(File.separator);
				}
				path.append(stu.getStudentno()).append("-").append(stu.getStuname()).append(".xls");
				m.submitTask(new Task(tfh, path.toString(), stu, les, fis));
			}
			m.endSubmitAndShutdown();
		} finally {
			if (null != tfh) {
				// 开启一个线程删除临时文件
				new Thread(new Runnable() {
					@Override
					public void run() {
						tfh.drop();
					}
				}).start();
			}
		}
	}

	private static String dealNull(String s) {
		return null == s ? "未知" : s;
	}

	private static class Task implements Callable<CompressFile> {
		private TempFolderHelper tfh;
		private String path;
		private Students stu;
		private List<LearningExperience> les;
		private List<FamilyInfo> fis;

		public Task(TempFolderHelper tfh, String path, Students stu, List<LearningExperience> les,
				List<FamilyInfo> fis) {
			super();
			this.tfh = tfh;
			this.path = path;
			this.stu = stu;
			this.les = les;
			this.fis = fis;
		}

		@Override
		public CompressFile call() throws Exception {
			OutputStream out = null;
			FileInputStream in = null;
			Workbook wb = null;

			try {
				String webroot = System.getProperty("ebop-server.root");
				in = new FileInputStream(webroot + "template" + File.separator + "studentInfo.xls");
				wb = new HSSFWorkbook(in);
				Sheet sheet = wb.getSheetAt(0);
				Row row = sheet.getRow(1);
				row.getCell(1).setCellValue(dealNull(stu.getOrgName()));
				row.getCell(7).setCellValue(dealNull(stu.getClassname()));
				row = sheet.getRow(2);
				row.getCell(2).setCellValue(dealNull(stu.getStuname()));
				row.getCell(6).setCellValue(dealNull(stu.getSex()));
				//身高字段不存在
//				row.getCell(8).setCellValue(找不到身高字段);
//				row.getCell(10).setCellValue(dealNull(stu.getPhotopath()));
				row = sheet.getRow(3);
				row.getCell(2).setCellValue(dealNull(stu.getBirthday()));
				row.getCell(6).setCellValue(dealNull(stu.getNation()));
				row.getCell(8).setCellValue(dealNull(stu.getGrade()));
				row = sheet.getRow(4);
				row.getCell(2).setCellValue(dealNull(stu.getPoliticalstatus()));
				row.getCell(6).setCellValue(dealNull(stu.getMajor()));
				row = sheet.getRow(5);
				row.getCell(2).setCellValue(dealNull(stu.getIdcardno()));
				row.getCell(8).setCellValue(dealNull(stu.getMobileno()));
				row = sheet.getRow(6);
				row.getCell(2).setCellValue(dealNull(stu.getFromPlace()));
				row.getCell(8).setCellValue(dealNull(stu.getFamilytelno()));
				row = sheet.getRow(7);
				row.getCell(2).setCellValue(dealNull(stu.getTravelrange()));
				//填写父亲母亲电话
				for(FamilyInfo info : fis){
					if(info.getCall() != null && info.getCall().matches("(父亲|爸爸|爸|爹)")){
						row.getCell(8).setCellValue(info.getTelno());
					}
					if(info.getCall() != null && info.getCall().matches("(母亲|妈妈|妈|娘)")){
						Row row1 = sheet.getRow(8);
						row1.getCell(8).setCellValue(info.getTelno());
					}
				}
				row = sheet.getRow(8);
				row.getCell(2).setCellValue(dealNull(stu.getPostcode()));
				row = sheet.getRow(9);
				row.getCell(2).setCellValue(dealNull(stu.getAddress()));
				row = sheet.getRow(10);
				row.getCell(2).setCellValue(dealNull(stu.getSkill()));
				row = sheet.getRow(11);
				row.getCell(2).setCellValue(dealNull(stu.getSelfdescription()));
				row = sheet.getRow(12);
				row.getCell(2).setCellValue(dealNull(stu.getAwards()));
				//学习记录大于两条时只取前两条
				int count = les.size() > 2 ? 2 : les.size();
				for(int i=0;i<count;i++){
					row = sheet.getRow(15+i);
					LearningExperience learningExperience = les.get(i);
					row.getCell(0).setCellValue(dealNull(learningExperience.getDuration()));
					row.getCell(3).setCellValue(dealNull(learningExperience.getSchoolname()));
					row.getCell(10).setCellValue(dealNull(learningExperience.getDuty()));
				}
				//家庭记录大于三条时只取前两条
				count = fis.size() > 3 ? 4 : fis.size();
				for(int i=0;i<count;i++){
					row = sheet.getRow(19+i);
					FamilyInfo familyInfo = fis.get(i);
					row.getCell(0).setCellValue(dealNull(familyInfo.getCall()));
					row.getCell(1).setCellValue(dealNull(familyInfo.getName()));
					row.getCell(4).setCellValue(dealNull(familyInfo.getPoliticalstatus()));
					row.getCell(6).setCellValue(dealNull(familyInfo.getCompany()));
					row.getCell(9).setCellValue(dealNull(familyInfo.getJobduty()));
					row.getCell(10).setCellValue(dealNull(familyInfo.getTelno()));
				}
				File file = tfh.getFile(path);
				out = new FileOutputStream(file);
				wb.write(out);
				return new CompressFile(path, file);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != wb) {
					try {
						wb.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != in) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != out) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			return null;
		}

		private static String dealNull(Object s) {
			return null == s ? "" : s.toString();
		}

	}
	
	@Override
	public List<Map<String,String>> listOrgName() {
		List<String> orgNames = studentsDao.listOrgName();
		List<Map<String,String>> r = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>(1);
		map.put("value","");
		map.put("name","全部");
		r.add(map);
		for(String orgName : orgNames){
			if(StringUtils.isNotBlank(orgName)){
				map = new HashMap<String, String>(1);
				map.put("value",orgName);
				map.put("name",orgName);
				r.add(map);
			}
		}
		return r;
	}



	@Override
	public List<Map<String,String>> listClassname() {
		List<String> classnames = studentsDao.listClassname();
		List<Map<String,String>> r = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>(1);
		map.put("value","");
		map.put("name","全部");
		r.add(map);
		for(String classname : classnames){
			if(StringUtils.isNotBlank(classname)){
				 map = new HashMap<String, String>(1);
				map.put("value",classname);
				map.put("name",classname);
				r.add(map);
			}
		}
		return r;
	}
}
