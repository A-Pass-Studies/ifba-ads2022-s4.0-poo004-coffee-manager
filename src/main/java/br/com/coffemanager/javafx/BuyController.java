package br.com.coffemanager.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import br.com.coffemanager.App;
import br.com.coffemanager.data.ItemDAO;
import br.com.coffemanager.model.Item;

public final class BuyController {

	private final ItemDAO itemDAO = App.getItemDAO();

	private final Navigator nav = Navigator.getInstance();

	@FXML
	private ChoiceBox<ItemChoiceBox> choiceBoxProduto;

	@FXML
	private TextField textFieldValor;

	@FXML
	private TextField textFieldQuantidade;

	@FXML
	private TextField textFieldNota;

	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonDeletarCompra;

	@FXML
	private Button buttonConfirmar;

	public void initialize() {
		// Inicialize o ChoiceBox com alguns produtos (exemplo)
		final List<ItemChoiceBox> itens = itemDAO.findAll().stream().map(ItemChoiceBox::new).toList();
		choiceBoxProduto.getItems().addAll(itens);

		textFieldValor.textProperty().addListener(new DinheiroFieldFormat());
	}

	@FXML
	private void handleVoltar(ActionEvent event) throws IOException {
		nav.navigateTo(Resources.MAIN);
	}

	@FXML
	private void handleDeletarCompra(ActionEvent event) {
		// Lógica para deletar uma compra
		System.out.println("Compra deletada!");
	}

	@FXML
	private void handleConfirmar(ActionEvent event) {

		// Lógica para confirmar a adição de um produto
		Item produto = choiceBoxProduto.getValue().getItem();
		String valor = textFieldValor.getText();
		String quantidade = textFieldQuantidade.getText();
		String nota = textFieldNota.getText();

		System.out.println("Produto: " + produto.getDescricao());
		System.out.println("Valor: " + valor);
		System.out.println("Quantidade: " + quantidade);
		System.out.println("Nota: " + nota);
	}
}
