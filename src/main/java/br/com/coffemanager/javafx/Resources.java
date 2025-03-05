package br.com.coffemanager.javafx;

import java.io.File;

enum Resources {
	
	AUTH("auth"),
	MAIN("main"),
	STOCK_MANAGER("stock_manager"),
	BUY_MANAGER("buy_manager"),
	REGISTER_ACC("register_acc"),
	SALE_MANAGER("sale_manager");
	
	private final String filename;

	private Resources(final String filename) {
		this.filename = filename;
	}
	
	String getResource() {
		return String.format("%s%s%s", "/fxml/", filename, ".fxml");
	}
}
