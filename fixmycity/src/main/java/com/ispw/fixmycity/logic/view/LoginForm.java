package com.ispw.fixmycity.logic.view;

import com.ispw.fixmycity.logic.exceptions.NoUserFound;
import com.ispw.fixmycity.logic.bean.BaseUserBean;
import com.ispw.fixmycity.logic.controller.SystemFacade;

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

		try{
			new SystemFacade().isSignedUp(baseUser).getMode();
		}
		catch (NoUserFound e) {
			// failed login 
		}

	}

}
