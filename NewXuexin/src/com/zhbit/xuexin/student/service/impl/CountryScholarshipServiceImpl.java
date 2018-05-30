package com.zhbit.xuexin.student.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.domain.CountryScholarship;
import com.zhbit.xuexin.domain.Organization;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.User;
import com.zhbit.xuexin.student.dao.CountryScholarshipDao;
import com.zhbit.xuexin.student.dao.StudentsDao;
import com.zhbit.xuexin.student.service.CountryScholarshipService;
import com.zhbit.xuexin.sys.dao.OrganizationDao;

/**
 * 
 * @author 梁日宇 email:1912813835@qq.com
 * @date 创建时间：2016-3-19 下午9:36:07
 * @version 1.0
 */
@Service("countryScholarshipService")
@Transactional(readOnly = true)
public class CountryScholarshipServiceImpl implements CountryScholarshipService {
    private Logger logger = LoggerFactory.getLogger(CountryScholarshipServiceImpl.class);

    @Autowired
    @Qualifier("countryScholarshipDao")
    private CountryScholarshipDao dao;

    @Autowired
    @Qualifier("organizationDao")
    private OrganizationDao orgDao;

    @Autowired
    @Qualifier("studentsDao")
    private StudentsDao studentsDao;

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
    public Page<CountryScholarship> getList(Page<CountryScholarship> page) {
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
     */
    @Override
    @Transactional(readOnly = false)
    public int save(CountryScholarship info, String userId) {
        if (info != null) {
            Students stu = studentsDao.getStudentByNo(info.getStudentno());
            if (stu == null) {
                return 0;
            }
            //许彩开-2017.04.18-验证输入的姓名和学号对应的姓名是否一致
            if(!stu.getStuname().equals(info.getStuname())){
            	return 0;
            }
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setCreateTime(new Date());
            info.setCreator(userId);
            info.setStuId(stu.getStuId());
            dao.save(info);
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
     * @return void
     */
    @Override
    @Transactional(readOnly = false)
    public void update(CountryScholarship info ,CountryScholarship oldInfo) {
        if (info != null) {
            Organization org = orgDao.getOrganizationById(info.getOrgId());
            info.setOrgName(org.getOrgName());
            info.setCreateTime(oldInfo.getCreateTime());
            info.setCreator(oldInfo.getCreator());
            info.setStuId(oldInfo.getStuId());
            info.setStuname(oldInfo.getStuname());
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
                CountryScholarship info = dao.getCountryScholarshipByid(id);
                dao.delete(info);
            }
        }
    }

    /**
     * @Title: getCountryScholarshipByid
     * @Description: TODO(获取对象。)
     * @author lry
     * @date 2016-3-19 下午10:01:29
     * @param csId
     * @return
     * @return CountryScholarship
     */
    @Override
    public CountryScholarship getCountryScholarshipByid(String id) {
        return dao.getCountryScholarshipByid(id);
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
        int existCount=0;//导入文件中的数据跟数据库已存在的相同记录的重复数
        int dataNullCount=0;//导入文件中某些字段是否含有空值
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
            logger.error("读取导入的文件出异常", e);
            return new int[] { -1, insertCount, updateCount };
        } finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch(IOException e) {
                    logger.error("读取导入的文件时关闭输入流异常", e);
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
                    System.out.println("对方水电费水电费======row.getCell(1).getStringCellValue()==="+row.getCell(1).getStringCellValue());
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    //2017/04/13 许彩开解决导入普通奖学金导入模板时字段值含有空格问题
					String[] infoExcle = {
							row.getCell(1).getStringCellValue(),
							row.getCell(13).getStringCellValue(),
							row.getCell(14).getStringCellValue()};
                    for(int i=0;i<=2;i++){
						infoExcle[i]=infoExcle[i].replaceAll(" ","");
						if(infoExcle[i].equals("")){
							dataNullCount=1;
						}
                    }
                    
                    
                    if (row.getCell(1) != null && !"".equals(row.getCell(1).getStringCellValue())) {
                        importCount++;
                        Students stu = studentsDao.getStudentByNo(row.getCell(1).getStringCellValue());
                        if (stu == null) {// 存在学生信息才导入
                            updateCount++;
                        }
                        else {
                        	
                        	int existResult = getCountryScholarshipExist(
									infoExcle[0], infoExcle[1], infoExcle[2]);
                        	
                            CountryScholarship info = new CountryScholarship();
                            if(existResult==0&&dataNullCount==0){
                            info.setOrgName(row.getCell(0).getStringCellValue());
                            if (row.getCell(0) != null && !"".equals(row.getCell(0).getStringCellValue())) {
                                info.setOrgId(Const.ORG_ID_MAP.get(row.getCell(0).getStringCellValue()));
                            }
                            info.setStudentno(row.getCell(1).getStringCellValue());
                            info.setStuname(row.getCell(2).getStringCellValue());
                            String sex = "0";
                            if (row.getCell(3) != null && "女".equals(row.getCell(3).getStringCellValue())) {
                                sex = "1";
                            }
                            info.setSex(sex);
                            info.setGrade(row.getCell(4).getStringCellValue());
                            info.setMajor(row.getCell(5).getStringCellValue());
                            info.setPoliticalstatus(row.getCell(6).getStringCellValue());
                            info.setSchoolScholarInfo(row.getCell(7).getStringCellValue());
                            info.setProvinceScholarIinfo(row.getCell(8).getStringCellValue());
                            info.setAwardInfo(row.getCell(9).getStringCellValue());
                            info.setCompetitionInfo(row.getCell(10).getStringCellValue());
                            info.setDuty(row.getCell(11).getStringCellValue());
//                            System.out.println("---------------" + row.getCell(12).getStringCellValue());
                            info.setRewardname(row.getCell(12).getStringCellValue());
                            if (row.getCell(13) != null)
                                info.setAcademicyear(row.getCell(13).getStringCellValue());
                            if (row.getCell(14) != null)
                                info.setTerm(row.getCell(14).getStringCellValue());
                            if (row.getCell(15) != null)
                                info.setMemo(row.getCell(15).getStringCellValue());

                            info.setCreateTime(new Date());
                            info.setStuId(stu.getStuId());
                            dao.save(info);
                            insertCount++;
                            }
                            else{
                            	existCount++;
                            }
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
        return new int[] { importCount, insertCount, updateCount ,dataNullCount,existCount };
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
    public ExportExcelVO exportExcelList(Page<CountryScholarship> page) {
        page.setPage(1);
        page.setRows(100000);
        // 获取查询结果数据集
        Page<CountryScholarship> pageResult = dao.getList(page);
        List<CountryScholarship> list = pageResult.getResult();
        // 设置表头
        String[] title = { "专业学院", "学号", "姓名", "性别", "年级", "专业", "政治面貌", "获本校奖学金情况", "获省级及省级以上奖学金情况", "评优获奖情况",
                "比赛、竞赛类获奖情况", "担任职务", "获奖名称", "学年", "学期", "备注" };
        // 设置文件名
        String filename = "国家光大奖学金表";
        // 设置内容
        ExportExcelVO vo = new ExportExcelVO();
        List<Object[]> listInfo = new ArrayList<Object[]>();
        for (int i = 0; i < list.size(); i++) {
            CountryScholarship info = list.get(i);
            String[] str = new String[16];
            str[0] = info.getOrgName();
            str[1] = info.getStudentno();
            str[2] = info.getStuname();
            String sex = "男";
            if ("1".equals(info.getSex())) {
                sex = "女";
            }
            str[3] = sex;
            str[4] = info.getGrade() == null ? "" : info.getGrade();
            str[5] = info.getMajor() == null ? "" : info.getMajor();
            str[6] = info.getPoliticalstatus() == null ? "" : info.getPoliticalstatus();
            str[7] = info.getSchoolScholarInfo() == null ? "" : info.getSchoolScholarInfo();
            str[8] = info.getProvinceScholarIinfo() == null ? "" : info.getProvinceScholarIinfo();
            str[9] = info.getAwardInfo() == null ? "" : info.getAwardInfo();
            str[10] = info.getCompetitionInfo() == null ? "" : info.getCompetitionInfo();
            str[11] = info.getDuty() == null ? "" : info.getDuty();
            str[12] = info.getRewardname() == null ? "" : info.getRewardname();
            str[13] = info.getAcademicyear() == null ? "" : info.getAcademicyear();
            str[14] = info.getTerm() == null ? "" : info.getTerm();
            str[15] = info.getMemo() == null ? "" : info.getMemo();
            listInfo.add(str);
        }
        vo.setTitle(filename);
        vo.setHeadTitle(title);
        vo.setDataList(listInfo);
        return vo;
    }
/**
 * 
 *@author 许彩开 email:1121836563@qq.com
 *@date 创建时间：2017年5月8日 下午5:18:55
 *@Description:TODO()
 *@param studentno
 *@param academicyear
 *@param term
 *@return
 */
	@Override
	public int getCountryScholarshipExist(String studentno, String academicyear, String term) {
		// TODO Auto-generated method stub
		return dao.getCountryScholarshipExist(studentno, academicyear, term);
	}

}
