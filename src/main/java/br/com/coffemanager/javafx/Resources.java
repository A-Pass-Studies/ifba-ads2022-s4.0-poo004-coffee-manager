package br.com.coffemanager.javafx;

import java.io.File;

enum Resources {
	
	AUTH("auth"),
	MAIN("main"),
	STOCK_MANAGER("stock_manager"),
	ORDER_MANAGER("order_manager"),
	REGISTER_ACC("register_acc"),
	BUY_MANAGER("buy_manager");
	
	private final String filename;

	private Resources(final String filename) {
		this.filename = filename;
	}
	
	String getResource() {
		return String.format("%s%s%s%s%s", File.pathSeparator, "fxml", File.pathSeparator, filename, ".fxml");
	}
}
