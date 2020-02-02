package com.ispw.fixmycity.logic.view;

import java.io.IOException;
import java.util.Date;

import com.ispw.fixmycity.logic.app.App;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.bean.CommunityReportBean;
import com.ispw.fixmycity.logic.bean.VolunteeringEventBean;
import com.ispw.fixmycity.logic.controller.LoginController;
import com.ispw.fixmycity.logic.dao.CommunityReportDAOImpl;
import com.ispw.fixmycity.logic.dao.VolunteeringEventDAOImpl;
import com.ispw.fixmycity.logic.model.CommunityReport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginForm {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button submitButton;

	@FXML
	private void submitLogin() throws IOException {

		VolunteeringEventDAOImpl dao = new VolunteeringEventDAOImpl();
		VolunteeringEventBean bean = new VolunteeringEventBean();
		
		
		CommunityReport commReport = new CommunityReportDAOImpl().findByPrimaryKey(1);
		System.out.println(commReport);
		CommunityReportBean commRepBean = new CommunityReportBean();
		commRepBean.setIdReport(commReport.getIdReport());
		commRepBean.setAddress(commRepBean.getAddress());
		commRepBean.setDescription(commRepBean.getDescription());
		commRepBean.setLatitude(commReport.getLatitude());
		commRepBean.setLongitude(commReport.getLongitude());
		commRepBean.setTitle(commReport.getTitle());
		
		bean.setCommunityReport(commRepBean);
		bean.setCreationDate(new Date());
		bean.setEventDate(new Date());

		dao.addVolunteeringEvent(bean);

		BaseUserBean baseUser = new BaseUserBean();
		baseUser.setUsername(usernameField.getText());
		baseUser.setPassword(passwordField.getText());

		// TODO dove deve stare questo if? Nel controller?
		if (LoginController.checkCredentials(baseUser))
			App.setRoot("home");
	}

}
