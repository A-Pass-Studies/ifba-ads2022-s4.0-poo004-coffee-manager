/**
 * 
 */
package br.com.coffemanager.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Usuario;
import br.com.coffemanager.model.UsuarioTipo;

/**
 * 
 */
abstract sealed class BaseDAO<T, TID> permits CompraDAO, UsuarioDAO, ItemDAO {

	static ConnectionFactory connFactory;

	BaseDAO(final ConnectionFactory connectionFactory) {
		connFactory = connectionFactory;
	}

	public void save(final T model) {
		final String attrs = String.join(", ", getAttributes(false));
		final CharSequence[] paramsChars = new CharSequence[attrs.length()];
		Arrays.fill(paramsChars, '?');
		String params = String.join(", ", paramsChars);
		String sqlCompra = "INSERT INTO " + getTableName() + " (" + attrs + ") VALUES (" + params + ")";

		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

			bindValues(model, pstmt);
			pstmt.execute();
			setId(model, pstmt.getGeneratedKeys().getLong(0));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public T findById(TID id) throws SQLException {
		final String attrs = String.join(", ", getAttributes(true));

		String sql = "SELECT " + attrs + " FROM " + getTableName() + " WHERE " + getAttrId() + " = ?;";
		try (final Connection conn = connFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			bindId(id, stmt, (byte) 1);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return createModel(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> findAll() throws SQLException {
		List<T> models = new ArrayList<>();
		final String attrs = String.join(", ", getAttributes(true));

		String sql = "SELECT " + attrs + " FROM " + getTableName() + ";";
		try (final Connection conn = connFactory.getConnection(); final Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				models.add(createModel(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return models;
	}

	public void update(T model) throws SQLException {
		final var attrs = getAttributes(false);
		final var attrsFotUpdate = String.join(" = ?, ", getAttributes(false));
		String sql = "UPDATE " + getTableName() + " SET " + attrsFotUpdate + " = ? WHERE " + getAttrId() + " = ?;";
		try (final Connection conn = connFactory.getConnection();
				final PreparedStatement stmt = conn.prepareStatement(sql)) {
			bindValues(model, stmt);
			bindId(stmt, model, (byte) (attrs.size() + 1));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(T model) throws SQLException {
		String sql = "DELETE FROM " + getTableName() + " WHERE " + getAttrId() + " = ?";
		try (final Connection conn = connFactory.getConnection();
				final PreparedStatement stmt = conn.prepareStatement(sql)) {
			bindId(stmt, model, (byte) 1);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	abstract List<String> getAttributes(final boolean withId);

	abstract String getTableName();

	abstract T createModel(final ResultSet rs) throws SQLException;

	abstract void setId(final T model, final Long id);
	
	abstract String getAttrId();

	abstract void bindValues(final T model, final PreparedStatement stmt) throws SQLException;

	abstract void bindId(final TID id, final PreparedStatement stmt, byte position) throws SQLException;

	abstract void bindId(final PreparedStatement stmt, final T model, byte position) throws SQLException;

}
