package br.com.arthur.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateQtdController {

    @FXML
    private TextField nomeProdutoField;

    @FXML
    private TextField novaQuantidadeField;

    @FXML
    private void atualizarQuantidade(ActionEvent event) {
        String nomeProduto = nomeProdutoField.getText();
        String novaQuantidade = novaQuantidadeField.getText();

        // back end

        Stage stage = (Stage) nomeProdutoField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) nomeProdutoField.getScene().getWindow();
        stage.close();
    }
}