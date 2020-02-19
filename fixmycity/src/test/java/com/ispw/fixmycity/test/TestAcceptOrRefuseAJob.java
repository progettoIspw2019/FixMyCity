package com.ispw.fixmycity.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;

public class TestAcceptOrRefuseAJob {
	
	@Test
	public void TestAcceptJobCase() throws ParseException {
		SystemFacade facade=new SystemFacade();
		JobBeanView bean=new JobBeanView();
		/*String sDate1="31/12/2021";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		String sDate2="31/12/2022";
		Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);*/
		//bean.setRelatedReport(relatedReport);
		//facade.jobCreation(jBean);
	}

	@Test
	public void TestRefuseJobCaseWithoutDeletion() {
		
	}
	@Test
	public void TestRefuseJobWithDeletion() {
		
	}
}
