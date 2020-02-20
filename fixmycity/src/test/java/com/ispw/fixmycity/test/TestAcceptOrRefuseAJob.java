package com.ispw.fixmycity.test;
//Test Fanali Francesco

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.ispw.fixmycity.logic.bean.CompanyReportBean;
import com.ispw.fixmycity.logic.bean.CompanyUserBean;
import com.ispw.fixmycity.logic.bean.JobBeanView;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.dao.CompanyUserDAO;
import com.ispw.fixmycity.logic.exceptions.CompanyReportIsAcceptedException;
import com.ispw.fixmycity.logic.exceptions.InvalidDateIntervalException;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.util.CityEnum;

public class TestAcceptOrRefuseAJob {
	
	//CompanyReportDAO comp=new CompanyReportDAO();
	//int id=comp.findByPrimaryKey(1).getIdReport();
	int id;
	String profileImg = "testResources/profile.jpeg";
	String compImg = "testResources/atac.jpg";
	String streetImg = "testResources/viacupa.jpg";
	
	public byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		}
	
	@Test 
	public void testSignUpCompanyUser() throws IOException {
		CompanyUserDAO dao=new CompanyUserDAO();
		SystemFacade facade=new SystemFacade();
		CompanyUserBean bean=new CompanyUserBean();
		bean.setCategory("Public transport");
		bean.setCity(CityEnum.ROMA);
		bean.setCompanyName("name");
		bean.setImage(extractBytes(compImg));
		bean.setPassword("password");
		bean.setUsername("comp");
		Boolean check=facade.signupCompanyUser(bean);
		assertEquals("name",dao.findByPrimaryKey("comp").getCompanyName());
	}
	
	@Test
	public void createCompanyReports() throws ParseException, NoMatchingCompanyFound, InvalidReportException, IOException {
		double f=41.5;
		CompanyReportDAO compRepDAO=new CompanyReportDAO();
		int check;
		SystemFacade facade=new SystemFacade();
		ReportBeanView bean=new ReportBeanView();
		bean.setCity("Roma");
		bean.setCategory("Public transport");
		bean.setSubmitter("company");
		CompanyReportBean compRepBean = new CompanyReportBean();
	    bean.setAddress("via //");
		bean.setCategory("Public transport");
		String sDate2="31/12/2028";
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		bean.setDateSubmission(date);
		bean.setDescription("rifiuto perche ecc");
		bean.setLatitude(BigDecimal.valueOf(f));
	    bean.setLongitude(BigDecimal.valueOf(f));
		bean.setTitle("title");
		bean.setImage(extractBytes(streetImg));
	    check=facade.reportProblem(bean);
			//f=f+10;
	   id=compRepDAO.findByPrimaryKey(check).getIdReport();
		assertEquals(id,check);
		}
	
		
	

	
	@Test
	public void TestAcceptJobCase() throws ParseException, CompanyReportIsAcceptedException, InvalidDateIntervalException {
		SystemFacade facade=new SystemFacade();
		JobBeanView bean=new JobBeanView();
		bean.setRelatedReport(id);
		String sDate1="31/12/2001";
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		String sDate2="31/12/2022";
		Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
		//bean.setRelatedReport(relatedReport);
		//facade.jobCreation(jBean);
	}

	@Test
	public void TestRefuseJobCaseWithoutDeletion() throws CompanyReportIsAcceptedException {
		SystemFacade facade=new SystemFacade();
		JobBeanView bean=new JobBeanView();
		bean.setRelatedReport(id);
		int check=facade.rejectReport(bean);
		assertEquals(-1,check);
	}
	@Test
	public void TestRefuseJobWithDeletion() throws CompanyReportIsAcceptedException {
		SystemFacade facade=new SystemFacade();
		int result = 0;
		JobBeanView bean=new JobBeanView();
		CompanyReportDAO compRepDao=new CompanyReportDAO();
		bean.setRelatedReport(id);
		CompanyReport compRep = compRepDao.findByPrimaryKey(bean.getRelatedReport());
		for(int i=0;i<3;i++) {
	    result=facade.rejectReport(bean);}
		assertEquals(1,result);}
		
		
	}

