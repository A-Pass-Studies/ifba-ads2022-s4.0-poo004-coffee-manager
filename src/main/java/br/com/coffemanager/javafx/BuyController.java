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
import java.net.URL;
import java.util.ResourceBundle;

public class BuyController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxProduto;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialize o ChoiceBox com alguns produtos (exemplo)
        choiceBoxProduto.getItems().addAll("Café", "Leite", "Açúcar", "Chá");
    }

    private void handleVoltar(ActionEvent event) throws IOException {
        // Carrega o arquivo FXML da tela principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.MAIN.getResource()));
        Scene scene = new Scene(loader.load());

        // Obtém o Stage (janela) atual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // Define a nova cena e exibe
        stage.setScene(scene);
        stage.setTitle("Tela Principal"); // Define o título da janela
        stage.show();
    }


    @FXML
    private void handleDeletarCompra(ActionEvent event) {
        // Lógica para deletar uma compra
        System.out.println("Compra deletada!");
    }

    @FXML
    private void handleConfirmar(ActionEvent event) {
        // Lógica para confirmar a adição de um produto
        String produto = choiceBoxProduto.getValue();
        String valor = textFieldValor.getText();
        String quantidade = textFieldQuantidade.getText();
        String nota = textFieldNota.getText();

        System.out.println("Produto: " + produto);
        System.out.println("Valor: " + valor);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Nota: " + nota);
    }
}