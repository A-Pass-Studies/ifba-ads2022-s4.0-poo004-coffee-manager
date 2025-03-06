package br.com.coffemanager.javafx;

import java.io.File;

enum Resources {
	
	AUTH("auth", "Autenticação"),
	MAIN("main", "Menu Principal"),
	STOCK_MANAGER("stock_manager", "Gestão de Estoque"),
	BUY_MANAGER("buy_manager", "Gestão de Compras"),
	REGISTER_ACC("register_acc", "Registro Vendas"),
	SALE_MANAGER("sale_manager", "Gestão de Vendas");
	
	private final String filename;
	
	private final String title;

	private Resources(final String filename, final String title) {
		this.filename = filename;
		this.title = title;
	}
	
	String getFXMLResource() {
		return "/fxml/" + filename + ".fxml";
	}
	
	String getTitle() {
		return title;
	}
}
