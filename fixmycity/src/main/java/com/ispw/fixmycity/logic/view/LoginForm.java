package com.ispw.fixmycity.logic.view;

import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.controller.LoginController;

public class LoginForm {

	private String username;
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void submitLogin() {

		BaseUserBean baseUser = new BaseUserBean();

		baseUser.setUsername(username);
		baseUser.setPassword(password);

		LoginController controller = new LoginController();
		controller.checkCredentials(baseUser);

	}

}
