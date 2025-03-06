package br.com.coffemanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.function.Consumer;

import br.com.coffemanager.data.CompraDAO;
import br.com.coffemanager.data.ItemDAO;
import br.com.coffemanager.data.UsuarioDAO;
import br.com.coffemanager.data.VendaDAO;
import br.com.coffemanager.data.VendaItemDAO;
import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.data.connection.PostgresConnectionFactory;
import br.com.coffemanager.service.AuthService;
import br.com.coffemanager.service.ComprasService;
import br.com.coffemanager.service.VendaService;

public final class App extends Application {
	private static Stage stage;

	@Override
	public void start(@SuppressWarnings("exports") Stage s) throws IOException {
		stage = s;
		setRoot("auth", "");
	}

	static void setRoot(String fxml) throws IOException {
		setRoot(fxml, stage.getTitle());
	}

	static void setRoot(String fxml, String title) throws IOException {
		Scene scene = new Scene(loadFXML(fxml));
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	public static final Stage getStage() {
		return stage;
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public final static ConnectionFactory getConnectionFactory() {
		return PostgresConnectionFactory.getInstance();
	}

	public final static UsuarioDAO getUsuarioDAO() {
		return UsuarioDAO.getInstance(getConnectionFactory());
	}

	public final static ItemDAO getItemDAO() {
		return ItemDAO.getInstance(getConnectionFactory(), getUsuarioDAO());
	}

	public final static CompraDAO getCompraDAO() {
		return CompraDAO.getInstance(getConnectionFactory(), getItemDAO(), getUsuarioDAO());
	}
	
	public final static AuthService getAuthService() {
		return AuthService.getInstance(getUsuarioDAO());
	}
	
	public final static ComprasService getCompraService() {
		return ComprasService.getInstsance(getAuthService(), getCompraDAO());
	}

	public final static VendaItemDAO getVendaItemDAO() {
		return VendaItemDAO.getInstance(getConnectionFactory(), getItemDAO());
	}
	
	public final static VendaDAO getVendaDAO() {
		return VendaDAO.getInstance(getConnectionFactory(), getVendaItemDAO(), getUsuarioDAO());
	}
	
	public static VendaService getVendaService() {
		return VendaService.getInstsance(getAuthService(), getVendaDAO());
	}
}
