module br.com.coffemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires java.sql;
	requires javafx.graphics;
    opens br.com.coffemanager.javafx to javafx.fxml;
    exports br.com.coffemanager.javafx;
    exports br.com.coffemanager;
    exports br.com.coffemanager.data.connection;
    exports br.com.coffemanager.data;
    exports br.com.coffemanager.model;
    exports br.com.coffemanager.service;
}