package br.com.arthur.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderAddController {

    @FXML
    private TextField numeroPedidoField;

    @FXML
    private TextArea conteudoPedidoArea;

    @FXML
    private void adicionarPedido(ActionEvent event) {
        String numeroPedido = numeroPedidoField.getText();
        String conteudoPedido = conteudoPedidoArea.getText();

        // backend pra adicionar pedido

        Stage stage = (Stage) numeroPedidoField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) numeroPedidoField.getScene().getWindow();
        stage.close();
    }
}