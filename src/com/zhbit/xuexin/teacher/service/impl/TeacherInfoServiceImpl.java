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

import org.apache.commons.io.FileUtils;
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
import com.zhbit.xuexin.common.utils.ExcelUtil;
import com.zhbit.xuexin.common.utils.FileOP;
import com.zhbit.xuexin.common.utils.SecurityUtil;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.TeacherInfo;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.sys.dao.OrganizationDao;
import com.zhbit.xuexin.sys.dao.UserDao;
import com.zhbit.xuexin.teacher.dao.TeacherInfoDao;
import com.zhbit.xuexin.teacher.service.TeacherInfoService;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-15 下午8:54:59
 * @version 1.0
 */
@Service("teacherInfoService")
@Transactional(readOnly = true)
public class TeacherInfoServiceImpl implements TeacherInfoService {

    private Logger logger = LoggerFactory.getLogger(TeacherInfoServiceImpl.class);

    @Autowired
    @Qualifier("teacherInfoDao")
    private TeacherInfoDao dao;

    @Autowired
    @Qualifier("organizationDao")
    private OrganizationDao orgDao;
    
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    /**
     * @Title: getList
     * @Description: TODO(分页获取教师基本信息列表。)
     * @author lry
     * @date 2016-3-15 下午10:41:16
     * @param page
     * @return
     * @return Page<TeacherInfo>
     */
    @Override
    public Page<TeacherInfo> getList(Page<TeacherInfo> page) {
        return dao.getList(page);
    }

    /**
     * @Title: save
     * @Description: TODO(保存新增信息。)
     * @author lry
     * @date 2016-3-15 下午10:42:16
     * @param info
     * @param birth
     *            生日
     * @param userId
     *            创建者
     * @return
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int save(TeacherInfo info, String birth, String userId) {
        if (info != null) {
            if (dao.getTeacherInfoByNo(info.getEmployNo()) != null) {
                return 0;// 编号已存在
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (birth != null && !"".equals(birth)) {
                try {
                    info.setBirthday(df.parse(birth));
                }
                catch(ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setPassword(SecurityUtil.GetMD5Code(Const.defult_password));
            info.setCreateTime(new Date());
            info.setCreator(userId);
            dao.save(info);
            TeacherInfo tea = dao.getTeacherInfoByNo(info.getEmployNo());
            User user = setToUser(new User(),tea);
            userDao.saveUser(user);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * @Title: update
     * @Description: TODO(修改信息。)
     * @author lry
     * @date 2016-3-15 下午10:44:06
     * @param info
     *            教师对象
     * @param birth
     *            生日
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void update(TeacherInfo info, TeacherInfo oldInfo, String birth) {
        if (info != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (birth != null && !"".equals(birth)) {
                try {
                    info.setBirthday(df.parse(birth));
                }
                catch(ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setPhotopath(oldInfo.getPhotopath());// 头像信息单独在详情页面改
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(info.getCreator());
            dao.update(info);
//            User user = userDao.getUserByNo(info.getEmployNo());
//            userDao.updateUser(user);
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
                TeacherInfo info = dao.getTeacherInfoByid(id);
                dao.delete(info);
                User user = userDao.getUserByNo(info.getEmployNo());
                if(null != user){
                	userDao.deleteUser(user);
                }
            }
        }
    }

    /**
     * @Title: getTeacherInfoByid
     * @Description: TODO(通过id获取对象。)
     * @author lry
     * @date 2016-3-15 下午10:46:55
     * @param id
     * @return
     * @return TeacherInfo
     */
    @Override
    public TeacherInfo getTeacherInfoByid(String id) {
        return dao.getTeacherInfoByid(id);
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
            }
            else {
                wb = new HSSFWorkbook(in);// 2007以下（2003）
            }
        }
        catch(Exception e) {
            logger.error("读取导入的网站配置文件出异常", e);
            return new int[] { -1, insertCount, updateCount };
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
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

            while (rowIterator.hasNext()) {
                try {
                    row = rowIterator.next();
                    if (row.getCell(0) != null && !"".equals(ExcelUtil.getCellValue(row.getCell(0)))) {
                        importCount++;
                        TeacherInfo te = dao.getTeacherInfoByNo(ExcelUtil.getCellValue(row.getCell(0)));
                        if (te != null) {// 不存在信息才导入
                            updateCount++;
                        }
                        else {
                            TeacherInfo info = new TeacherInfo();
                            info.setEmployNo(ExcelUtil.getCellValue(row.getCell(0)));
                            info.setEmployName(ExcelUtil.getCellValue(row.getCell(1)));
                            String sex = "0";
                            if ("女".equals(ExcelUtil.getCellValue(row.getCell(2)))) {
                                sex = "1";
                            }
                            info.setSex(sex);
                            if (!"".equals(ExcelUtil.getCellValue(row.getCell(3)))) {
                                info.setBirthday(df.parse(ExcelUtil.getCellValue(row.getCell(3))));
                            }
                            info.setOrgName(ExcelUtil.getCellValue(row.getCell(4)));
                            if (!"".equals(ExcelUtil.getCellValue(row.getCell(4)))) {
                                info.setOrgId(Const.ORG_ID_MAP.get(ExcelUtil.getCellValue(row.getCell(4))));
                            }
                            info.setDepartment(ExcelUtil.getCellValue(row.getCell(5)));
                            info.setTelno(ExcelUtil.getCellValue(row.getCell(6)));
                            info.setEmail(ExcelUtil.getCellValue(row.getCell(7)));
                            info.setAddress(ExcelUtil.getCellValue(row.getCell(8)));
                            info.setCategory(ExcelUtil.getCellValue(row.getCell(9)));
                            info.setEducation(ExcelUtil.getCellValue(row.getCell(10)));
                            info.setDegree(ExcelUtil.getCellValue(row.getCell(11)));
                            info.setDuty(ExcelUtil.getCellValue(row.getCell(12)));
                            info.setAcdemictitle(ExcelUtil.getCellValue(row.getCell(13)));
                            info.setInvigilatorflag(ExcelUtil.getCellValue(row.getCell(14)));
                            info.setResearchdirection(ExcelUtil.getCellValue(row.getCell(15)));
                            info.setIntroduce(ExcelUtil.getCellValue(row.getCell(16)));
                            info.setMajor(ExcelUtil.getCellValue(row.getCell(17)));
                            info.setGraduate(ExcelUtil.getCellValue(row.getCell(18)));
                            String qualify = "";
                            if (!"没有".equals(ExcelUtil.getCellValue(row.getCell(19)))) {
                                qualify = "Y";
                            }
                            else {
                                qualify = "N";
                            }
                            info.setQualificationflag(qualify);
                            String js = "";
                            if (!"离职".equals(ExcelUtil.getCellValue(row.getCell(20)))) {
                                js = "Y";
                            }
                            else {
                                js = "N";
                            }
                            info.setJobstatus(js);
                            info.setTeacherLevel(ExcelUtil.getCellValue(row.getCell(21)));
                            String isL = "";
                            if (!"否".equals(ExcelUtil.getCellValue(row.getCell(22)))) {
                                isL = "Y";
                            }
                            else {
                                isL = "N";
                            }
                            info.setIslab(isL);
                            String isO = "";
                            if (!"否".equals(ExcelUtil.getCellValue(row.getCell(23)))) {
                                isO = "Y";
                            }
                            else {
                                isO = "N";
                            }
                            info.setIsouthire(isO);
                            info.setPoliticalstatus(ExcelUtil.getCellValue(row.getCell(24)));
                            info.setNation(ExcelUtil.getCellValue(row.getCell(25)));

                            info.setPassword(SecurityUtil.GetMD5Code(Const.defult_password));
                            info.setCreateTime(new Date());
                            info.setCreator(user.getUserId());
                            dao.save(info);
                            TeacherInfo tea = dao.getTeacherInfoByNo(info.getEmployNo());
                            User u = setToUser(new User(),tea);
                            userDao.saveUser(u);
                            insertCount++;
                        }
                    }
                }
                catch(Exception e) {
                    msg = (row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "")
                            + (row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
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
    public ExportExcelVO exportExcelList(Page<TeacherInfo> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<TeacherInfo> pageResult = dao.getList(page);
        List<TeacherInfo> list = pageResult.getResult();
        // 设置表头
        String[] title = { "职工号", "姓名", "性别", "出生日期", "部门（学院）", "科室（系）", "联系电话", "E_mail地址", "联系地址", "教职工类别", "学历",
                "学位", "职务", "职称", "派监考老师可用否", "教学研究方向", "教师简介", "专业名称", "毕业院校", "教师资格", "在职类别", "教师级别", "是否实验室人员",
                "是否外聘", "政治面貌", "民族" };
        // 设置文件名
        String filename = "教师基本信息表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < list.size(); i++) {
            TeacherInfo info = list.get(i);
            String[] str = new String[26];
            str[0] = info.getEmployNo();
            str[1] = info.getEmployName();
            String sex = "男";
            if ("1".equals(info.getSex())) {
                sex = "女";
            }
            str[2] = sex;
            str[3] = info.getBirthday() == null ? "" : df.format(info.getBirthday());
            str[4] = info.getOrgName() == null ? "" : info.getOrgName();
            str[5] = info.getDepartment() == null ? "" : info.getDepartment();
            str[6] = info.getTelno() == null ? "" : info.getTelno();
            str[7] = info.getEmail() == null ? "" : info.getEmail();
            str[8] = info.getAddress() == null ? "" : info.getAddress();
            str[9] = info.getCategory() == null ? "" : info.getCategory();
            str[10] = info.getEducation() == null ? "" : info.getEducation();
            str[11] = info.getDegree() == null ? "" : info.getDegree();
            str[12] = info.getDuty() == null ? "" : info.getDuty();
            str[13] = info.getAcdemictitle() == null ? "" : info.getAcdemictitle();
            str[14] = info.getInvigilatorflag() == null ? "" : info.getInvigilatorflag();
            str[15] = info.getResearchdirection() == null ? "" : info.getResearchdirection();
            str[16] = info.getIntroduce() == null ? "" : info.getIntroduce();
            str[17] = info.getMajor() == null ? "" : info.getMajor();
            str[18] = info.getGraduate() == null ? "" : info.getGraduate();
            str[19] = info.getQualificationflag().equals("Y") ? "有" : "无";
            str[20] = info.getJobstatus().equals("Y") ? "在职" : "离职";
            str[21] = info.getTeacherLevel() == null ? "" : info.getTeacherLevel();
            str[22] = info.getIslab().equals("Y") ? "是" : "否";
            str[23] = info.getIsouthire().equals("Y") ? "是" : "否";
            str[24] = info.getPoliticalstatus() == null ? "" : info.getPoliticalstatus();
            str[25] = info.getNation() == null ? "" : info.getNation();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }

    /**
     * @Title: updatePhoto
     * @Description: TODO(更新头像。)
     * @author LRY
     * @date 2016-4-26 下午11:52:27
     * @param employNo
     * @param photo
     *            图片文件
     * @param suffix
     *            文件后缀
     * @return void
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = false)
    public void updatePhoto(String employNo, File photo, String suffix) throws Exception {
        String realPath = ServletActionContext.getServletContext().getRealPath(Const.teacherPhoto);// 在tomcat中保存图片的实际路径
                                                                                                   // ==
                                                                                                   // "webRoot/userphoto/"
        // 对图片进行重命名
        String fileName = employNo + suffix;
        File saveFile = new File(new File(realPath), fileName); // 在该路径下实例化一个文件
        File diskFile = new File(new File(Const.teacher_photo), fileName);
        // 判断文件是否存在
        if (saveFile.exists()) {
            saveFile.delete();
        }
        FileUtils.copyFile(photo, saveFile);
        if (diskFile.exists()) {
            diskFile.delete();
        }
        FileOP.writeFile(Const.teacher_photo + fileName, photo);// 同步写到物理磁盘
        TeacherInfo info = dao.getTeacherInfoByNo(employNo);
        info.setPhotopath(fileName);
        dao.update(info);
    }

    /**
     * @Title: getTeacherInfoByNo
     * @Description: TODO(通过教工号获取教师信息。)
     * @author LRY
     * @date 2016-4-27 上午12:51:40
     * @param employNo
     * @return
     * @return TeacherInfo
     */
    @Override
    public TeacherInfo getTeacherInfoByNo(String employNo) {
        return dao.getTeacherInfoByNo(employNo);
    }

    /**
     * 启动初始化
     * @throws IOException 
     */
    public void init() {
//        try {
//         // 磁盘资源清理
//            String sql = "select PHOTOPATH from T_TEACHERINFO group by PHOTOPATH";
//            List<String> list = (List<String>) dao.queryPhotoPath(sql);
//            File appF = new File(Const.teacher_photo);
//            for (File f : appF.listFiles()) {
//                if (!list.contains(f.getName())) {
//                    f.delete();
//                }
//            }
//
//            // 对服务器资源缺失补回
//            String p = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//            String webapp = new File(p).getParentFile().getParentFile().getPath();
//            webapp = webapp.replaceAll("\\\\", "/");
//            // 检查在服务端webapp目录下是否有对应的文件，没有则上传
//            File fs = new File(Const.teacher_photo);
//            for (File f : fs.listFiles()) {
//                String fn = f.getName();
//                String aimPath = webapp + "/" + Const.teacherPhoto;
//                File aimf = new File(aimPath + "/" + fn);
//                // 判断文件是否存在
//                if (!aimf.exists()) {
//                    FileUtils.copyFile(f, aimf);
//                }
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
    	try {
    		String sql = "select PHOTOPATH from T_TEACHERINFO group by PHOTOPATH";
			List<String> list = (List<String>) dao.queryPhotoPath(sql);
			String p = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			String webapp = new File(p).getParentFile().getParentFile().getPath();
			// tomcat 下的图片文件夹路径
			String webpp = webapp + File.separator + Const.teacher_photo;
			// 将磁盘中的照片拷贝到tomcat下
			for (String pp : list) {
				if (pp != null) {
					File f1 = new File(webpp, pp);
					if (!f1.exists()) {
						File f2 = new File(Const.teacher_photo, pp);
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
     * @Title: getTeacherToUser
     * @Description: TODO(通过教工号获取信息组装成User对象。)
     * @author LRY
     * @date 2016-4-28 下午8:43:35
     * @param employNo
     * @return
     * @return User
     */
    @Override
    public User getTeacherToUser(String employNo) {
        TeacherInfo info = dao.getTeacherInfoByNo(employNo);
        User user = new User();
        if(info!=null){
            user.setUserId(info.getId());
            user.setEmployNo(info.getEmployNo());
            user.setEmployName(info.getEmployName());
            user.setCreateTime(info.getCreateTime());
            user.setPassword(info.getPassword());
        }
        return user;
    }
    
    public User setToUser(User user,TeacherInfo tea){
        user.setUserId(tea.getId());
        user.setEmployNo(tea.getEmployNo());
        user.setEmployName(tea.getEmployName());
        user.setUserType(Const.user_type[0]);
        user.setSex("0".equals(tea.getSex())?"男":"女");
        user.setParentOrgId(tea.getParentOrgId());
        Organization org = orgDao.getOrganizationById(tea.getOrgId());
        user.setOrganization(org);
        user.setCreateTime(tea.getCreateTime());
        user.setPassword(tea.getPassword());
        user.setStatus(Const.CONST_STATE);
        return user;
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
    public int updatePwd(String password, String passwordOld, String id) {
        TeacherInfo tea = dao.getTeacherInfoByid(id);
        User u = userDao.getUserByNo(tea.getEmployNo());
        if(!SecurityUtil.GetMD5Code(passwordOld).equals(tea.getPassword())){
            return 1;
        }
        if(null == u){
        	return 2;
        }
        u.setPassword(SecurityUtil.GetMD5Code(password));
        tea.setPassword(SecurityUtil.GetMD5Code(password));
        userDao.updateUser(u);
        dao.update(tea);
        return 0;
    }
}
