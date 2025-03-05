package br.com.coffemanager.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Compra;

public final class CompraDAO implements GenericDAO<Compra, Long> {

	private static CompraDAO instance = null;

	private final ConnectionFactory connectionF;

	private CompraDAO(final ConnectionFactory connectionFactory) {
		this.connectionF = connectionFactory;
	}

	public static CompraDAO getInstance(final ConnectionFactory connectionFactory) {
		if (instance == null) {
			instance = new CompraDAO(connectionFactory);
		}
		return instance;
	}

	@Override
	public void save(final Compra compra) {
		String sqlCompra = "INSERT INTO movimento.compras (item_id, valor_unitario, qtd, data_compra, vencimento, cadastro_usuario_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = connectionF.getConnection();
				PreparedStatement pstmtCompra = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

			pstmtCompra.setLong(1, compra.getItem().getId());
			pstmtCompra.setBigDecimal(2, compra.getValorUnitario());
			pstmtCompra.setInt(3, compra.getQtd());
			pstmtCompra.setDate(4, java.sql.Date.valueOf(compra.getDataCompra()));
			pstmtCompra.setDate(5, java.sql.Date.valueOf(compra.getVencimento()));
			pstmtCompra.setLong(6, compra.getUsuarioCadastro().getId());
			pstmtCompra.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Compra findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Compra t) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}
}
