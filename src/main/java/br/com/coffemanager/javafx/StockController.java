package br.com.coffemanager.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class StockController {

    @FXML
    private TextField textFieldNomeProduto;

    @FXML
    private TextField textFieldValorUnitario;

    @FXML
    private Button buttonVoltar;

    @FXML
    private Button buttonCriar;

    @FXML
    private void handleVoltar(ActionEvent event) throws IOException {
        // Carrega o arquivo FXML da tela principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Resources.MAIN.getResource()));
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
        // Lógica para criar um novo item no estoque
        String nomeProduto = textFieldNomeProduto.getText();
        String valorUnitario = textFieldValorUnitario.getText();

        System.out.println("Nome do Produto: " + nomeProduto);
        System.out.println("Valor Unitário: " + valorUnitario);
    }
}