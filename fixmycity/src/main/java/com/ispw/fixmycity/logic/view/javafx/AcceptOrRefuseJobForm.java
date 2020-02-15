package com.ispw.fixmycity.logic.view.javafx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.ispw.fixmycity.logic.bean.AcceptOrRefuseJobBean;
import com.ispw.fixmycity.logic.controller.AcceptOrRefuseAJobController;
import com.ispw.fixmycity.logic.dao.CompanyReportDAO;
import com.ispw.fixmycity.logic.model.CompanyReport;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AcceptOrRefuseJobForm implements Observer  {
	
	Stage stage;

	@FXML
	private ImageView reportImageView;
	@FXML
	public static Button backButton;
	@FXML
	
	public static Button submitAcceptButton;
	@FXML
	public static  Button submitRefuseButton;

	@FXML
	private Text reportTitleText;

	@FXML
	private DatePicker startDatePicker;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private TextArea refuseDescription;

	@FXML
	private TreeTableView<CompanyReport> reportTable;

	@FXML
	private TreeTableColumn<CompanyReport, String> statusColumn;

	@FXML
	private TreeTableColumn<CompanyReport, String> titleColumn;

	@FXML
	private TextArea description;
	@FXML
	private TextArea city;

	@FXML
	private TextArea submissionDate;

	@FXML
	private TextArea status;

	private CompanyReport reportSelected;
	
	AcceptOrRefuseJobBean bean = new AcceptOrRefuseJobBean();
	AcceptOrRefuseAJobController controller = new AcceptOrRefuseAJobController();
	CompanyReportDAO dao = new CompanyReportDAO();
	//List<CompanyReport> reports = dao.findAll();
	//public List<Integer> observableReportId = new ArrayList<>();
	

	
	
	@FXML
	public void initialize() {
		
		List<CompanyReport> reports = dao.findAll();
		final TreeItem<CompanyReport> root = new TreeItem<>();
		reportTable.setRoot(root);
		reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			reportSelected = observable.getValue().getValue();
			reportTitleText.setText(reportSelected.getTitle());
			Image image = new Image(new ByteArrayInputStream(reportSelected.getImage()));
			reportImageView.setImage(image);
			description.setText(reportSelected.getFullDescription());
			city.setText(reportSelected.getCity());
			submissionDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(reportSelected.getDateSubmission()));
		});
		root.setExpanded(true);
		reports.stream().forEach((report) -> {
			root.getChildren().add(new TreeItem<>(report));
		});
		
       
		statusColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReport, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().getStatus()));
        
		titleColumn.setCellValueFactory(
				(TreeTableColumn.CellDataFeatures<CompanyReport, String> param) -> new ReadOnlyStringWrapper(
						param.getValue().getValue().getTitle()));
		
	}
	@FXML
	public void acceptJob() throws IOException {
		CompanyReport report=this.reportSelected;
		bean.setIdJob(report.getIdReport());
		bean.setRelatedReport(report);
	    bean.setRelatedCompany(report.getCompanyUser());
			Stage primaryStage = null;
			Parent root = FXMLLoader.load(getClass().getResource("accept.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		}
	   
		

	
    @FXML
	public void uploadDocButton() {
		 bean.setDocument(controller.uploadDocument());
	}

	@FXML
	public void refuseJob() throws IOException {
		CompanyReport report=this.reportSelected;
		bean.setIdJob(report.getIdReport());
		bean.setRelatedReport(report);
	    bean.setRelatedCompany(report.getCompanyUser());
			Stage primaryStage = null;
			Parent root = FXMLLoader.load(getClass().getResource("refuse.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	    

	

	@FXML
	public void submitRefuse() {
		bean.setRefuseMotivation(refuseDescription);
		
		 controller.refuseReport( bean);

	}

	@FXML
	public void submitAcceptJob() {
	
		 
			bean.setStartDate(startDatePicker);
			bean.setEndDate(endDatePicker);
			
		    controller.jobCreation(bean);

	}
	
      @Override
      public void update() {
    	// initialize();//refresh windows}
      }
    	 
    	
	@FXML
      public void backButton(ActionEvent event) {
    	  
    		    Stage stage = (Stage) backButton.getScene().getWindow();
    		    stage.close();
    		}
    	}
      
      





   
