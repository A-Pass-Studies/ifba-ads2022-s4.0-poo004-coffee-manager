package br.com.coffemanager.javafx;
/*
Put header here


 */

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;

public class MainController implements Initializable {

	@FXML
	private Label lblOut;

	@FXML
	private void handleGerenciarVenda(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.SALE_MANAGER.getResource()));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Gerenciar Venda");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	@FXML
	private void handleGerenciarCompras(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.BUY_MANAGER.getResource()));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Gerenciar Compras");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	@FXML
	private void handleEstoque(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.STOCK_MANAGER.getResource()));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.setTitle("Estoque");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}