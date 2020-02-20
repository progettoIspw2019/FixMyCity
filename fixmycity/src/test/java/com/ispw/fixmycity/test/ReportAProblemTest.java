package com.ispw.fixmycity.test;

// Minut Robert Adrian

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.ispw.fixmycity.logic.bean.AddressBean;
import com.ispw.fixmycity.logic.bean.CitizenUserBean;
import com.ispw.fixmycity.logic.bean.ReportBeanView;
import com.ispw.fixmycity.logic.controller.ReportProblemController;
import com.ispw.fixmycity.logic.controller.SystemFacade;
import com.ispw.fixmycity.logic.exceptions.CouldNotConnectToGeolocationServiceException;
import com.ispw.fixmycity.logic.exceptions.InvalidReportException;
import com.ispw.fixmycity.logic.exceptions.NoMatchingCompanyFound;
import com.ispw.fixmycity.logic.util.CityEnum;
import com.ispw.fixmycity.logic.util.UserMode;
import com.ispw.fixmycity.logic.view.SessionView;

public class ReportAProblemTest {
	
	String profileImg = "testResources/profile.jpeg";
	String compImg = "testResources/atac.jpg";
	String streetImg = "testResources/viacupa.jpg";
	
	BigDecimal latitude = BigDecimal.valueOf(41.91760700);
	BigDecimal longitude = BigDecimal.valueOf(12.44201660);

	
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
	public void testSignUpCitizenUser() throws IOException {
		CitizenUserBean userBean = new CitizenUserBean();
		userBean.setCity(CityEnum.ROMA);
		userBean.setFirstName("Paolo");
		
		byte[] image = extractBytes(profileImg);
		userBean.setImage(image);
		
		userBean.setLastName("Fox");
		userBean.setMode(UserMode.CITIZEN);
		userBean.setPassword("fox");
		userBean.setUsername("foxxy");
		
		assertEquals(true, new SystemFacade().signupCitizenUser(userBean));
	}
	
	@Test
	public void testReportProblem() throws NoMatchingCompanyFound, InvalidReportException {
		SessionView.setCityEnum(CityEnum.ROMA);
		AddressBean addrBean = new AddressBean();
		addrBean.setCity(CityEnum.ROMA.toString());
		addrBean.setCountry("Italia");
		addrBean.setRoad("88, Via della Balduina");
		SessionView.setUsername("foxxy");
		
		ReportBeanView repBean = new ReportBeanView();
		repBean.setAddress("88, Via della Balduina");
		
		repBean.setCategory("Public transport");
		assertEquals(1, new ReportProblemController().reportProblem(repBean));
	}
	
	@Test
	public void testGeolocation() throws CouldNotConnectToGeolocationServiceException {
		SessionView.setLatitudeSetOnMap(latitude);
		SessionView.setLongitudeSetOnMap(longitude);
		new SystemFacade().setAddressForReport(longitude, latitude);
		assertEquals("88, Via della Balduina", SessionView.getAddressSetOnMap());
	}
}
