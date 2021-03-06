module com.ispw.fixmycity.logic {

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
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires java.logging;
	requires com.jfoenix;
	requires com.google.gson;
	
	opens com.ispw.fixmycity.logic.app to javafx.fxml;
	opens com.ispw.fixmycity.logic.controller to javafx.fxml;
	opens com.ispw.fixmycity.logic.view to javafx.fxml;
	opens com.ispw.fixmycity.logic.view.javafx to javafx.fxml;
	opens com.ispw.fixmycity.logic.model to org.hibernate.orm.core;
	
	exports com.ispw.fixmycity.logic.app;
	exports com.ispw.fixmycity.logic.controller;
	exports com.ispw.fixmycity.logic.view;
	exports com.ispw.fixmycity.logic.bean;
	exports com.ispw.fixmycity.logic.util;
	exports com.ispw.fixmycity.logic.model;
	exports com.ispw.fixmycity.logic.dao;
	exports com.ispw.fixmycity.logic.view.javafx;
}