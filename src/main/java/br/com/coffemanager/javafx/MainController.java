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
	
	private Navigator nav = Navigator.getInstance();

	@FXML
	private void handleGerenciarVenda(ActionEvent event) throws IOException {
		nav.navigateTo(Resources.SALE_MANAGER);
	}

	@FXML
	private void handleGerenciarCompras(ActionEvent event) throws IOException {
		nav.navigateTo(Resources.BUY_MANAGER);
	}

	@FXML
	private void handleEstoque(ActionEvent event) throws IOException {
		nav.navigateTo(Resources.STOCK_MANAGER);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}