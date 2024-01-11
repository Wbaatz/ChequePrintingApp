module Semester_4_Calenderfx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires java.sql;
	requires org.apache.poi.ooxml;
	requires org.apache.poi.poi;
	requires javafx.web;
	requires jdk.jsobject;
	requires jdk.xml.dom;
	requires java.net.http;
	requires java.websocket;

	
	
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
