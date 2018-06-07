package com.zhbit.xuexin.student.service.impl;

import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.impl.BaseServiceImpl;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.LearningGuidStudentsList;
import com.zhbit.xuexin.domain.StuconfirmDetail;
import com.zhbit.xuexin.domain.StuconfirmMaster;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.dao.StuconfirmMasterDao;
import com.zhbit.xuexin.student.service.StuconfirmMasterService;
import com.zhbit.xuexin.sys.dao.OrganizationDao;

import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



@Service("stuconfirmMasterService")
@Transactional(readOnly=true)
public class StuconfirmMasterServiceImpl extends BaseServiceImpl<StuconfirmMaster> implements StuconfirmMasterService {
	private StuconfirmMasterDao dao ;
	@Resource
	public void setStuconfirmMasterDao(StuconfirmMasterDao stuconfirmMasterDao){
		this.dao = stuconfirmMasterDao ;
		super.setBaseDao(stuconfirmMasterDao);
	}
	
	@Autowired
	@Qualifier("organizationDao")
	private OrganizationDao orgDao ;
	
	/**
	 * HZJ
	 * 根据学生的学号，找到导学名单列表的数据，从而或许具体数据，导学老师名称工号等等
	 * 
	 */
	
	@Override
	public LearningGuidStudentsList getLearningGuidStudentsListByStuno(
			String studentno) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(LearningGuidStudentsList.class, "l");
		qh.setWhere("l.studentno=?",studentno);
		qh.setOrder("l.academicyear", QueryHelper.DESC);
        qh.setOrder("l.term", QueryHelper.DESC);
//		System.out.print(qh.getHQL());
//		System.out.print(qh.getArgs());
		List<LearningGuidStudentsList> ls = dao.query(qh);
		if(ls != null && ls.size() > 0){
			return ls.get(0);
		}
		return null ;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteMultiple(String ids) {
		if(ids != null || "".equals(ids)){
			String[] sids = ids.split(",");
			for(String id : sids){
				dao.delete(id);
				dao.deleteDetailByMasterId(id);
			}
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSelfMultiple(Students self, String ids) {
		if(null != ids && !"".equals(ids) && null !=self){
			String[] sids = ids.split(",");
			for(String id : sids){
				StuconfirmMaster s = dao.get(id);
				if(null != s && self.getStudentno().equals(s.getStudentno())){
					if(Const.CONFIRM_STATUS_NEW.equals(s.getStatus())
							|| Const.CONFIRM_STATUS_RESET.equals(s.getStatus())){
						dao.delete(s);
					}
				}
			}
		}
	}

	@Override
	public ExportExcelVO exportExcelList(StuconfirmMaster scfm) {
		ExportExcelVO vo = new ExportExcelVO();
		QueryHelper stuQh = new QueryHelper().setFrom(Students.class, "s")
				.setWhere("s.studentno=?", scfm.getStudentno());
		List<Students> stus  = dao.query(stuQh);
		QueryHelper dQh = new QueryHelper().setFrom(StuconfirmDetail.class, "sd")
				.setWhere("sd.masterId=?", scfm.getId())
				.setOrder("sd.coursename", QueryHelper.ASC);
		List<StuconfirmDetail> sds = dao.query(dQh);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!stus.isEmpty()){
			
			String title = String.format("%s学年第%s学期学生选课确认表", scfm.getAcademicyear(),scfm.getTerm());
			
			String jck = String.valueOf(scfm.getJck());
			String jckyx = String.valueOf(scfm.getJckyx());
			String bxk = String.valueOf(scfm.getBxk());
			String bxkyx = String.valueOf(scfm.getBxkyx());
			String zyk = String.valueOf(scfm.getZyk());
			String zykyx = String.valueOf(scfm.getZykyx());
			String tsbxkyx = String.valueOf(scfm.getTsbxkyx());
			String tsbxxkyx = String.valueOf(scfm.getTsxxkyx());
			String klyxk = String.valueOf(scfm.getKlyxk());
			String othercourse = scfm.getOthercourse();
			String othercredit = String.valueOf(scfm.getOthercredit());	
			if(scfm.getJckyx() == null)
			{
				jckyx = "0" ;
				scfm.setJckyx(0.0);
			}
			if(scfm.getBxkyx() == null)
			{
				bxkyx = "0" ;
				scfm.setBxkyx(0.0);
			}
			if(scfm.getZykyx() == null)
			{
				zykyx = "0" ;
				scfm.setZykyx(0.0);
			}
			if(scfm.getTsxxkyx() == null)
			{
				tsbxxkyx = "0" ;
				scfm.setTsxxkyx(0.0);
			}
			if(scfm.getKlyxk() == null)
			{
				klyxk = "0" ;
				scfm.setKlyxk(0.0);
			}
			if(scfm.getOthercredit() == null)
			{
				scfm.setOthercredit(0.0);
			}
			Integer sum = (int) (scfm.getJckyx() + scfm.getBxkyx() + scfm.getZykyx() + scfm.getTsxxkyx() + scfm.getKlyxk() + scfm.getOthercredit());
			String sum1 = String.valueOf(sum);
			
			String[] heads = new String[17];
			heads[0] = scfm.getStuname();
			heads[1] = scfm.getStudentno();
			heads[2] = scfm.getClassname();
			heads[3] = scfm.getTelno();
			heads[4] = jck;
			heads[5] = jckyx;
			heads[6] = bxk;
			heads[7] = bxkyx;
			heads[8] = zyk;
			heads[9] = zykyx;
			heads[10] = tsbxkyx;
			heads[11] = tsbxxkyx;
			heads[12] = klyxk;
			heads[13] = othercourse;
			heads[14] = othercredit;
			heads[15] = sum1;
			heads[16] = scfm.getAcademicyear();
			List<Object[]> data = new ArrayList<Object[]>();
			for (StuconfirmDetail sd : sds) {
				String[] arrs = new String[5];
				arrs[0] = sd.getCoursecode() + "-" + sd.getCoursename();
				arrs[1] = sd.getCoursetype();
				String preconfirmTime = sdf.format(sd.getPredate());
				arrs[2] = preconfirmTime;	//需要调整时间格式
				arrs[3] = sd.getIsselected();
				arrs[4] = sd.getReasons();
				data.add(arrs);
			}
			vo.setTitle(title);
			vo.setHeadTitle(heads);
			vo.setDataList(data);
		}
		return vo ;
	}
	
	/**
	 * HZJ
	 * 通过学生的学号找到学生的选课信息，未使用
	 * 
	 */

	@Override
	public List<CourseInfoStudents> getCourseInfoStudentsByStuno(
			String studentno) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.studentno=?",studentno);
		return dao.query(qh);
	}


	/**
	 * HZJ
	 * 根据学生学期，学年，班级来查找可选择的课程名字
	 * 2017.4.10
	 * 
	 * 2017.4.27
	 * 尝试增加对16选课信息班级的模糊查询
	 * 
	 */
	
	@Override
	public List<CourseInfo> getCoursesByStuTermAndAcayear(String term ,String acayear ,String classname) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfo.class, "c");
		qh.setWhere("c.term=?",term);
		qh.setWhereAND("c.academicyear=?", acayear) ;
		qh.setWhereAND("c.classinfo like ?", "%" + classname + "%") ;
		System.out.println(qh.getHQL());
		return dao.query(qh) ;
	}
	
	/**
	 * HZJ
	 * 根据学生的学号来获取学生当前的班级信息，由于URL无法成功传递中文参数，派生出来的函数
	 * 2017.4.11
	 * 
	 */
	
	@Override
	public List<Students> getStudentClassnameByStuno(String stuno) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		qh.setWhere("s.studentno=?",stuno);
		return dao.query(qh) ;
	}

	@Override
	public boolean isExist(String studentno, String academicyear, String term) {
		QueryHelper qh = new QueryHelper().setSelect("1")
			.setFrom(StuconfirmMaster.class, "s")
			.setWhere("s.studentno=?", studentno)
			.setWhereAND("s.academicyear=?", academicyear)
			.setWhereAND("s.term=?", term);
		List<Object> list = dao.query(qh);
		return list != null && !list.isEmpty();
	}

	@Override
	public List<CourseInfo> getCourseTypeList() {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfo.class, "c");
		qh.setSelect("DISTINCT coursetype ") ;
		System.out.println(qh.getHQL());
		return dao.query(qh) ;
	}
}
