package br.com.coffemanager.javafx;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import br.com.coffemanager.App;
import br.com.coffemanager.data.CompraDAO;
import br.com.coffemanager.data.ItemDAO;
import br.com.coffemanager.model.Item;
import br.com.coffemanager.service.ComprasService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public final class BuyController {

	private final ItemDAO itemDAO = App.getItemDAO();

	private final CompraDAO compraDAO = App.getCompraDAO();

	private final ComprasService compraServ = App.getCompraService();

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

	@FXML
	private TableView<CompraTableView> tabelaCompras;

	@FXML
	private TableColumn<CompraTableView, Long> tableComprasColItem;

	@FXML
	private TableColumn<CompraTableView, String> tableComprasColValorUnitario;

	@FXML
	private TableColumn<CompraTableView, Integer> tableComprasColQtd;

	@FXML
	private TableColumn<CompraTableView, String> tableComprasColNF;

	public void initialize() {
		// Inicialize o ChoiceBox com alguns produtos (exemplo)
		final List<ItemChoiceBox> itens = itemDAO.findAll().stream().map(ItemChoiceBox::new).toList();
		choiceBoxProduto.getItems().addAll(itens);

		textFieldValor.textProperty().addListener(new DinheiroFieldFormat());

		final var compras = compraDAO.findAll();

		tableComprasColItem.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		tableComprasColValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		tableComprasColQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
		tableComprasColNF.setCellValueFactory(new PropertyValueFactory<>("nf"));

		tabelaCompras.setItems(FXCollections.observableList(compras.stream().map(CompraTableView::new).toList()));
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

		try {
			final Item produto = choiceBoxProduto.getValue().getItem();

			final var valor = new BigDecimal(
					NumberFormat.getCurrencyInstance().parse(textFieldValor.getText()).doubleValue());
			final var quantidade = Integer.parseInt(textFieldQuantidade.getText());
			String nota = textFieldNota.getText();

			compraServ.adicionarRegistroDeCompra(produto, valor, quantidade, nota);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
