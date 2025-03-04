package br.com.coffemanager.javafx;

import java.io.File;

enum Resources {
	
	AUTH("auth"),
	MAIN("main"),
	ITEM_MANAGER("item_manager");
	
	
	private final String filename;

	private Resources(final String filename) {
		this.filename = filename;
	}
	
	String getResource() {
		return String.format("%s %s %s", "fxml", File.pathSeparator, filename);
	}
}
