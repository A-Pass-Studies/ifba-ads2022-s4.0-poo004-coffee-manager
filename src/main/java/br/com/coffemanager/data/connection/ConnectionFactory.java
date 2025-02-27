package br.com.coffemanager.data.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
	public Connection getConnection();
}
