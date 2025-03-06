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
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Usuario;
import br.com.coffemanager.model.UsuarioTipo;

/**
 * 
 */
abstract sealed class BaseDAO<T, TID> permits CompraDAO, UsuarioDAO, ItemDAO, VendaDAO, VendaItemDAO {

	static ConnectionFactory connFactory;

	BaseDAO(final ConnectionFactory connectionFactory) {
		connFactory = connectionFactory;
	}

	public void save(final T model) {
		final var attrs = getAttributes(false);
		final String[] paramsChars = new String[attrs.size()];
		Arrays.fill(paramsChars, "?");
		String params = String.join(", ", paramsChars);
		String sqlCompra = "INSERT INTO " + getTableName() + " (" + attributesToSql(false) + ") VALUES (" + params
				+ ")";

		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

			bindValues(model, pstmt);
			pstmt.execute();
			final var generateId = pstmt.getGeneratedKeys();
			generateId.next();
			setId(model, pstmt.getGeneratedKeys().getLong(1));

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

	public List<T> findAll(final List<String> wheres, final Consumer<PreparedStatement> bindWhere) {
		final String attrs = String.join(", ", getAttributes(true));

		final String whereSql = String.join(" = ?", wheres);

		final List<T> all = new ArrayList<T>();

		String sql = "SELECT " + attrs + " FROM " + getTableName() + " WHERE " + whereSql + " = ?;";
		try (final Connection conn = connFactory.getConnection();
				final PreparedStatement stmt = conn.prepareStatement(sql)) {
			bindWhere.accept(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				all.add(createModel(rs));
			}
			return all;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> findAll() {
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

	public void update(T model) {
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

	public void delete(T model) {
		String sql = "DELETE FROM " + getTableName() + " WHERE " + getAttrId() + " = ?";
		try (final Connection conn = connFactory.getConnection();
				final PreparedStatement stmt = conn.prepareStatement(sql)) {
			bindId(stmt, model, (byte) 1);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String attributesToSql(final boolean withId) {
		return String.join(", ", getAttributes(withId));
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
