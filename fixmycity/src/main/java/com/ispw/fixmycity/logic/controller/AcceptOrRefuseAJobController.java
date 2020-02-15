package com.ispw.fixmycity.logic.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import com.ispw.fixmycity.logic.bean.AcceptOrRefuseJobBean;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.model.CompanyReport;
import com.ispw.fixmycity.logic.model.Job;
import com.ispw.fixmycity.logic.view.javafx.AcceptOrRefuseJobForm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AcceptOrRefuseAJobController {	
	public void updateReportStatus(CompanyReport report,String status) {
		report.setStatus(status);
	}
	
	AcceptOrRefuseJobForm form=new AcceptOrRefuseJobForm();
	
	public byte[] uploadDocument() {
		    GridPane gridPane=new GridPane();
			Window window = gridPane.getScene().getWindow();
			FileChooser docFileChooser = new FileChooser();
			File file= docFileChooser.showOpenDialog(window);

			if (file!= null) {
				try {
					 DataInputStream dis = new DataInputStream(new FileInputStream(file));
				        byte[] theBytes = new byte[dis.available()];
				        dis.read(theBytes, 0, dis.available());
				        dis.close();
				        return theBytes;
				
					
				  } catch (IOException ex) {
				    }
				    return null;
				}
			return null; }
	
	public void  jobCreation(AcceptOrRefuseJobBean bean) {
		Job job=new Job();
		job.setIdJob(bean.getIdJob());
	    job.setStartDate(bean.getStartDate());
	    job.setEndDate(bean.getEndDate());
	    job.setJobInfo(bean.getDocument());
	    job.setRelatedCompany(bean.getRelatedUser());
	    job.setCompanyReport(bean.getCompanyReport());
	    updateReportStatus(job.getCompanyReport(),"ReportAccepted");
	    CompanyReportDAO dao=new CompanyReportDAO();
	    dao.update(bean.getCompanyReport());
	    job.getCompanyReport().notify();
	    Stage stage = (Stage) form.submitAcceptButton.getScene().getWindow();
	    stage.close();
		
		
	}
	
  public void refuseReport(AcceptOrRefuseJobBean bean){
	  CompanyReportDAO dao=new CompanyReportDAO();
	   if(bean.getCompanyReport().getRefuseCounter()<=3) {
		bean.getCompanyReport().setRefuseDescription(bean.getRefuseMotivation());
		updateReportStatus(bean.getCompanyReport(),"reporRefused");
		dao.update(bean.getCompanyReport());
		
		bean.getCompanyReport().notify();
		Stage stage = (Stage) form.submitRefuseButton.getScene().getWindow();
	    stage.close();}
	   else {
		  dao.delete(bean.getCompanyReport().getIdReport());
		  Stage stage = (Stage) form.submitRefuseButton.getScene().getWindow();
		    stage.close();
	   }
		
		
	}
	
	public void deleteReport(int id) {
		CompanyReportDAO dao=new CompanyReportDAO();
		dao.delete(id);
		
	}
	
	@SuppressWarnings("null")
	public void updateView(String view) throws IOException {
		if(view.compareTo("accept")==1) {
		
		    	  Stage primaryStage = null;
		    	  Parent root = FXMLLoader.load(getClass().getResource("accept.fxml"));
					Scene scene = new Scene(root,400,400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
		    	
		}
		
		if(view.compareTo("refuse")==1) {
			  Stage primaryStage = null;
	    	  Parent root = FXMLLoader.load(getClass().getResource("refuse.fxml"));
				Scene scene = new Scene(root,400,400);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
		}
		
		
		
	}
	

	
}
