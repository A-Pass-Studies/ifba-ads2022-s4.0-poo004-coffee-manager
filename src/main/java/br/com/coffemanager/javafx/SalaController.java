package br.com.coffemanager.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.coffemanager.App;
import br.com.coffemanager.data.ItemDAO;
import br.com.coffemanager.model.Venda;
import br.com.coffemanager.service.VendaService;

public class SalaController {

	private final ItemDAO itemDAO = App.getItemDAO();

	private final VendaService vendaService = App.getVendaService();

	@FXML
	private ChoiceBox<ItemChoiceBox> choiceBoxProduto;

	@FXML
	private TextField textFieldQuantidade;

	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonCriar;

	@FXML
	private TableView<ItemTableView> tableViewItens;

	@FXML
	private TableColumn<ItemTableView, String> tbvItensColDescricao;

	@FXML
	private TableColumn<ItemTableView, String> tbvItensColValor;

	@FXML
	private TableColumn<ItemTableView, String> tbvItensColQtd;

	private final Venda vendaAberta = vendaService.abrirVenda();

	private final List<ItemTableView> selectedItens = new ArrayList<>();

	public void initialize() {
		final var itens = itemDAO.findAll();

		final List<ItemChoiceBox> itensChoices = itens.stream().map(ItemChoiceBox::new).toList();
		choiceBoxProduto.getItems().addAll(itensChoices);

		tbvItensColDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		tbvItensColValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		tbvItensColQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));

		tableViewItens.setItems(FXCollections.observableList(selectedItens));
	}

	@FXML
	private void handleVoltar(ActionEvent event) throws IOException {
		// Carrega o arquivo FXML da tela principal
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.MAIN.getFXMLResource()));
		Scene scene = new Scene(loader.load());

		// Obtém o Stage (janela) atual
		Stage stage = (Stage) buttonVoltar.getScene().getWindow();

		// Define a nova cena e exibe
		stage.setScene(scene);
		stage.setTitle("Tela Principal"); // Define o título da janela
		stage.show();
	}

	@FXML
	private void handleCriar(ActionEvent event) {
		final var vitem = vendaAberta.addItem(choiceBoxProduto.getSelectionModel().getSelectedItem().getItem(),
				Integer.parseInt(textFieldQuantidade.getText()));
		selectedItens.add(new ItemTableView(vitem));
	}
}