module com.ispw.fixmycity.logic.app {

	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive leaflet4j;
	requires transitive net.java.html.boot.fx;
	requires transitive javafx.web;
	requires java.naming;
	requires java.base;
	requires java.instrument;
	requires transitive java.persistence;
	requires transitive org.hibernate.orm.core;
	requires transitive mysql.connector.java;
	requires java.sql;
	requires java.xml.bind;
	
	opens com.ispw.fixmycity.logic.app to javafx.fxml;
	opens com.ispw.fixmycity.logic.controller to javafx.fxml;
	opens com.ispw.fixmycity.logic.view to javafx.fxml;
	opens com.ispw.fixmycity.logic.model to org.hibernate.orm.core;
	 
	exports com.ispw.fixmycity.logic.app;
	exports com.ispw.fixmycity.logic.controller;
	exports com.ispw.fixmycity.logic.view;
	exports com.ispw.fixmycity.logic.bean;
	exports com.ispw.fixmycity.logic.util;
	exports com.ispw.fixmycity.logic.model;
	exports com.ispw.fixmycity.logic.dao;

}