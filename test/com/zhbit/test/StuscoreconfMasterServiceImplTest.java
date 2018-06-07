package com.zhbit.test;

import com.zhbit.xuexin.domain.CourseInfoStudents;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.service.StuscoreconfMasterService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StuscoreconfMasterServiceImplTest {
	private StuscoreconfMasterService ss;
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:applicationContext.xml");

		ss = (StuscoreconfMasterService) ctx.getBean("stuscoreconfMasterService");

		assertNotNull(ss);
	}

	@Test
	public void test() {
		StuscoreconfMaster sm = new StuscoreconfMaster();
		sm.setStudentno("140202061012");
		sm.setStuname("邹方翔2");
		sm.setStuId("test");
		StuscoreconfMaster sm2 = ss.save(sm);
		System.out.println(sm2.getId());
	}
	
	@Test
	public void test1() {
		List<CourseInfoStudents> infos = ss.getCourseInfoStudentsByStunoAndAcademicyearTerm("140202061012","2014-2015","1");
		System.out.println(infos);
	}

}
