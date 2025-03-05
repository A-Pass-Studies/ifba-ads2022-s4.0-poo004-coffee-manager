package br.com.coffemanager.javafx;
/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;

import br.com.coffemanager.App;
import br.com.coffemanager.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthController implements Initializable {
    
    @FXML
    private Label lblOut;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField senha;
    
    
    private final AuthService authService = App.getAuthService();
    
    @FXML
    private void btnClickAction(ActionEvent event) {
        if (authService.login(username.getText(), senha.getText())) {
        	
        	
        	try {
                Parent tela2 = FXMLLoader.load(App.class.getResource("/fxml/home.fxml"));
                Scene scene = new Scene(tela2);

                // Pegando a janela atual (Stage)
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
   
        }
        
        else {
        	lblOut.setText("Autenticação falhou. Tente novamente...");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
