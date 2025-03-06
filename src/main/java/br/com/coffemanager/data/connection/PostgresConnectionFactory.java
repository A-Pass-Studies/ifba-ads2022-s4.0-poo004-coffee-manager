package br.com.coffemanager.data.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.coffemanager.data.UsuarioDAO;

public final class PostgresConnectionFactory implements ConnectionFactory {

	private static PostgresConnectionFactory instance;

	private Connection connection;

	private PostgresConnectionFactory() {
	}

	public static ConnectionFactory getInstance() {
		if (instance == null) {
			instance = new PostgresConnectionFactory();
		}

		return instance;
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				final String url = "jdbc:postgresql://localhost:5432/postgres";
				final String user = "postgres";
				final String password = "postgres";
				connection = DriverManager.getConnection(url, user, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Throwable t) {
			throw t;
		}
		return connection;
	}
}
