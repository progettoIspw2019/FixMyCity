package com.ispw.fixmycity.logic.controller;

import com.ispw.fixmycity.logic.bean.BaseUserBean;

public class LoginController {

	public static boolean checkCredentials(BaseUserBean user) {

		// TODO qui ci sarà chiamata al DAO per fare la query
		return user.getUsername().compareTo("ciao") == 0 && user.getPassword().compareTo("ciaociao") == 0;

	}

	public LoginController() {

	}

}
