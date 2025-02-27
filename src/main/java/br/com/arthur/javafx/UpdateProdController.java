package br.com.arthur.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProdController {

    @FXML
    private TextField nomeProdutoField;

    @FXML
    private TextField novoNomeField;

    @FXML
    private void atualizarProduto(ActionEvent event) {
        String nomeProduto = nomeProdutoField.getText();
        String novoNome = novoNomeField.getText();

        // back end pra atualizar o produto

        Stage stage = (Stage) nomeProdutoField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deletarProduto(ActionEvent event) {
        String nomeProduto = nomeProdutoField.getText();

        // back end pra deletar o produto

        Stage stage = (Stage) nomeProdutoField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) nomeProdutoField.getScene().getWindow();
        stage.close();
    }
}