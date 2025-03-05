package br.com.coffemanager.data;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Usuario;
import br.com.coffemanager.model.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// UsuarioDAO Implementation
public final class UsuarioDAO extends BaseDAO<Usuario, Long> {
	private static UsuarioDAO instance;

	private final static String TABLE_NAME = "auth.usuarios";
	private final static String ATTR_ID = "id";
	private final static String ATTR_USERNAME = "username";
	private final static String ATTR_NOME_COMPLETO = "nome_completo";
	private final static String ATTR_SENHA = "senha";
	private final static String ATTR_TIPO = "tipo";
	private final static String ATTR_CRIADO_EM = "criado_em";
	private final static String ATTR_ATUALIZADO_EM = "atualizado_em";

	private UsuarioDAO(final ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	public static UsuarioDAO getInstance(final ConnectionFactory connectionFactory) {
		if (instance == null) {
			instance = new UsuarioDAO(connectionFactory);
		}
		return instance;
	}

	public Usuario findByUserName(final String username) {
		String sql = "SELECT * FROM auth.usuarios WHERE username = ?";
		try (final Connection conn = connFactory.getConnection();
				final PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return createModel(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);

		} catch (Exception exception) {
			throw exception;
		}
		return null;

	}

	@Override
	List<String> getAttributes(final boolean withId) {
		final ArrayList<String> attrs = new ArrayList<>();
		if (withId) {
			attrs.add(ATTR_ID);
		}
		attrs.add(ATTR_USERNAME);
		attrs.add(ATTR_NOME_COMPLETO);
		attrs.add(ATTR_SENHA);
		attrs.add(ATTR_TIPO);
		attrs.add(ATTR_CRIADO_EM);
		attrs.add(ATTR_ATUALIZADO_EM);
		return attrs;
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	@Override
	Usuario createModel(final ResultSet rs) throws SQLException {
		return new Usuario(rs.getLong("id"), rs.getString("username"), rs.getString("nomeCompleto"),
				rs.getString("senha"), UsuarioTipo.valueOf(rs.getString("tipo")),
				rs.getTimestamp("criado_em").toLocalDateTime(), rs.getTimestamp("atualizado_em").toLocalDateTime());
	}

	@Override
	void setId(final Usuario model, final Long id) {
		model.setId(id);

	}

	@Override
	void bindValues(final Usuario model, final PreparedStatement stmt) throws SQLException {
		stmt.setString(1, model.getUsername());
		stmt.setString(2, model.getSenha());
		stmt.setString(3, model.getTipo().name());
		stmt.setString(2, model.getNomeCompleto());
		stmt.setString(3, model.getSenha());
		stmt.setString(4, model.getTipo().name());
	}

	@Override
	String getAttrId() {
		return ATTR_ID;
	}

	@Override
	void bindId(final Long id, final PreparedStatement stmt, final byte position) throws SQLException {
		stmt.setLong(position, id);
	}

	@Override
	void bindId(final PreparedStatement stmt, final Usuario model, final byte position) throws SQLException {
		bindId(model.getId(), stmt, position);
	}
}
