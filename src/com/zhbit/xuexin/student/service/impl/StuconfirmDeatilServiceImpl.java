package com.zhbit.xuexin.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.zhbit.xuexin.common.Const;
import com.zhbit.xuexin.common.action.Page;
import com.zhbit.xuexin.common.domain.vo.ExportExcelVO;
import com.zhbit.xuexin.common.service.impl.BaseServiceImpl;
import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.CountryScholarship;
import com.zhbit.xuexin.domain.CourseInfo;
import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.StuconfirmDetail;
import com.zhbit.xuexin.domain.StuconfirmMaster;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.student.dao.StuconfirmDetailDao;
import com.zhbit.xuexin.student.dao.StuconfirmMasterDao;
import com.zhbit.xuexin.student.service.StuconfirmDetailService;


@Service("stuconfirmDetailService")
@Transactional(readOnly=true)
public class StuconfirmDeatilServiceImpl extends BaseServiceImpl<StuconfirmDetail> implements StuconfirmDetailService {

	private Logger logger = LoggerFactory .getLogger(StuconfirmDeatilServiceImpl.class);
	
	
	private StuconfirmDetailDao stuconfirmDetailDao;
	
	@Resource
	private StuconfirmMasterDao stuconfirmMasterDao;

	@Resource
	public void setStuconfirmDetailDao(
			StuconfirmDetailDao stuconfirmDetailDao) {
		this.stuconfirmDetailDao = stuconfirmDetailDao;
		super.setBaseDao(stuconfirmDetailDao);
	}
	
	@Override
	public CourseInfoStudents getCourseInfoStudentsByCourseId(String coursecode) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.id=?", coursecode);
		List<CourseInfoStudents> ls = stuconfirmDetailDao.query(qh);
		if(ls != null && ls.size() > 0){
			return ls.get(0);
		}
		return null;
	}

	@Override
	public boolean checkCourseIsSelected(String masterid, String coursecourse) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(StuconfirmDetail.class, "sf");
		qh.setWhere("sf.masterId=?", masterid);
		qh.setWhereAND("sf.coursecode=?", coursecourse);
		List<?> r = stuconfirmDetailDao.query(qh);
		return r != null && r.size() > 0;
	}

	@Override
	public CourseInfo getCourseInfoByCoursecode(String coursecode) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfoStudents.class, "c");
		qh.setWhere("c.coursecode=?", coursecode);
		List<CourseInfo> ls = stuconfirmDetailDao.query(qh);
		if(ls!=null && ls.size() > 0){
			return ls.get(0);
		}
		return null;
	}

	/**
	* Title: getCourseInfoByCourseId
	* Description: 根据学生选中的课程代码（id）获取该课程的信息
	* @param couseId
	* @return
	* @see com.zhbit.xuexin.student.service.StuconfirmDetailService#getCourseInfoByCourseId(java.lang.String)
	* @date 2017-4-14 上午9:07:18
	*/
	@Override
	public CourseInfo getCourseInfoByCourseId(String couseId) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(CourseInfo.class, "c");
		qh.setWhere("c.id=?", couseId);
		List<CourseInfo> ls = stuconfirmDetailDao.query(qh);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly=false)
	public void deleteMultipe(String ids) {
		if(ids != null || "".equals(ids)){
			String[] sids = ids.split(",");
			for(String id : sids){
				stuconfirmDetailDao.delete(id);
			}
		}
		
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteSelfMultiple(Students self, String ids) {
		if(null != ids && !"".equals(ids) && null != self){
			String[] sids = ids.split(",");
			for(String id : sids){
				StuconfirmDetail old = stuconfirmDetailDao.get(id);
				if(null != old){
					StuconfirmMaster scfm = stuconfirmMasterDao.get(old.getMasterId());
					if(Const.CONFIRM_STATUS_NEW.equals(scfm.getStatus())
							|| Const.CONFIRM_STATUS_RESET.equals(scfm.getStatus())){
						if(null != self && null != scfm && self.getStudentno().equals(scfm.getStudentno())){
							stuconfirmDetailDao.delete(id);
						} 
					}
				}
			}
		}
	}

	@Override
	public boolean isExist(String masterid, String coursename) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(StuscoreconfDetail.class, "s");
		qh.setWhere("s.masterId=?", masterid);
		qh.setWhereAND("s.coursename=?", coursename);
		return !stuconfirmDetailDao.query(qh).isEmpty();
	}

	/**
	 * HZJ
	 * 鍏蜂綋鐨凥QL璇彞锛岃幏鍙栦粠琛ㄥ叿浣撲俊鎭�
	 * 
	 */
	
	@Override
	public List<StuconfirmDetail> getStuconfirmDetailByMasterId(String id) {
		QueryHelper qh = new QueryHelper();
		qh.setFrom(StuconfirmDetail.class, "s");
		qh.setWhere("s.masterId=?", id);
		return stuconfirmDetailDao.query(qh);
	}

	@Override
	public ExportExcelVO exportExcelList(QueryHelper qh) {
		List<StuconfirmMaster> list = stuconfirmDetailDao.query(qh);
		String[] title = {"课程名字","课程性质","预定时间","是否选上","未选上原因"};
		String filename = "学生选课明细表";
		
		ExportExcelVO vo = new ExportExcelVO();
		List<Object[]> listInfo = new ArrayList<Object[]>();
		
		for (int i =  0 ; i < list.size() ; i++ ){
			StuconfirmMaster info = list.get(i);
			String[] str = new String[5];
			
			listInfo.add(str);
		} 
		vo.setTitle(filename);
		vo.setHeadTitle(title);
		vo.setDataList(listInfo);
		return vo;
	}

	



}
