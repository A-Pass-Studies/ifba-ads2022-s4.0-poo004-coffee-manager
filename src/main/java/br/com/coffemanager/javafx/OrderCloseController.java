package br.com.coffemanager.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderCloseController {

    @FXML
    private TextField numeroPedidoFinalizarField;

    @FXML
    private void finalizarPedido(ActionEvent event) {
        String numeroPedido = numeroPedidoFinalizarField.getText();

        // back end pra finalizar pedido

        Stage stage = (Stage) numeroPedidoFinalizarField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) numeroPedidoFinalizarField.getScene().getWindow();
        stage.close();
    }
}