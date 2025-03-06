package br.com.coffemanager.javafx;

import br.com.coffemanager.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

final class Navigator {

	private static Navigator instance;
	
	private Navigator() {
	}
	
	final static Navigator getInstance() {
		if (instance == null) {
			instance = new Navigator();
		}
		return instance;
	}
	
	final void navigateTo(final Resources res) {
		try {
            final Parent tela = FXMLLoader.load(App.class.getResource(res.getFXMLResource()));
            final Scene scene = new Scene(tela);

            final var stage = App.getStage();
            	
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
