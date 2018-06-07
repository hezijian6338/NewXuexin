package com.zhbit.test;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zhbit.xuexin.common.utils.QueryHelper;
import com.zhbit.xuexin.domain.Students;
import com.zhbit.xuexin.domain.StuscoreconfDetail;
import com.zhbit.xuexin.domain.StuscoreconfMaster;
import com.zhbit.xuexin.student.service.StuscoreconfDetailService;
import com.zhbit.xuexin.student.service.StuscoreconfMasterService;

public class StuscoreconfDetailServiceImplTest {
	private StuscoreconfMasterService sms;
	private StuscoreconfDetailService sds;
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"classpath:applicationContext.xml");

		sds = (StuscoreconfDetailService) ctx.getBean("stuscoreconfDetailService");
		sms = (StuscoreconfMasterService) ctx.getBean("stuscoreconfMasterService");

		assertNotNull(sms);
		assertNotNull(sds);
	}

	@Test
	public void test() {
		List<StuscoreconfMaster> list = sms.listAll();
		StuscoreconfDetail  sd = new StuscoreconfDetail();
		sd.setMasterId(list.get(0).getId());
		sd.setCreateTime(new Date());
		sds.save(sd);
	}
	
	@Test
	public void test1(){
		QueryHelper qh = new QueryHelper();
		qh.setFrom(Students.class, "s");
		//PageResult r = sms.queryPageResult(qh, 1, 10);
		//System.out.println(r);
		
	}

}
