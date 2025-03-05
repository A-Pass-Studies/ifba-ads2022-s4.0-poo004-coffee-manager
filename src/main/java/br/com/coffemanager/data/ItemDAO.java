package br.com.coffemanager.data;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Item;
import br.com.coffemanager.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class ItemDAO extends BaseDAO<Item, Long> {
	private static ItemDAO instance;

	private final static String TABLE_NAME = "estoque.itens";
	private final static String ATTR_ID = "id";
	private final static String ATTR_DESCRICAO = "descricao";
	private final static String ATTR_VALOR_VENDA_ATUAL = "valor_venda_atual";
	private final static String ATTR_CADASTRO_USUARIO_ID = "cadastro_usuario_id";
	private final static String ATTR_CRIADO_EM = "criado_em";
	private final static String ATTR_ATUALIZADO_EM = "atualizado_em";

	private ItemDAO(final ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}

	public static ItemDAO getInstance(final ConnectionFactory connectionFactory) {
		if (instance == null) {
			instance = new ItemDAO(connectionFactory);
		}
		return instance;
	}

	@Override
	List<String> getAttributes(final boolean withId) {
		final ArrayList<String> attrs = new ArrayList<>();
		if (withId) {
			attrs.add(ATTR_ID);
		}
		attrs.add(ATTR_DESCRICAO);
		attrs.add(ATTR_VALOR_VENDA_ATUAL);
		attrs.add(ATTR_CADASTRO_USUARIO_ID);
		attrs.add(ATTR_CRIADO_EM);
		attrs.add(ATTR_ATUALIZADO_EM);
		return attrs;
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	@Override
	Item createModel(final ResultSet rs) throws SQLException {
		Usuario cadastroUsuario = UsuarioDAO.getInstance(connFactory).findById(rs.getLong(ATTR_CADASTRO_USUARIO_ID));
		return new Item(rs.getLong(ATTR_ID), rs.getString(ATTR_DESCRICAO), rs.getBigDecimal(ATTR_VALOR_VENDA_ATUAL),
				cadastroUsuario, rs.getTimestamp(ATTR_CRIADO_EM).toLocalDateTime(),
				rs.getTimestamp(ATTR_ATUALIZADO_EM).toLocalDateTime());
	}

	@Override
	void setId(final Item model, final Long id) {
		model.setId(id);
	}

	@Override
	void bindValues(final Item model, final PreparedStatement stmt) throws SQLException {
		stmt.setString(1, model.getDescricao());
		stmt.setBigDecimal(2, model.getValorVendaAtual());
		stmt.setLong(3, model.getCadastroUsuario().getId());
		stmt.setTimestamp(4, Timestamp.valueOf(model.getCriadoEm()));
		stmt.setTimestamp(5, Timestamp.valueOf(model.getAtualizadoEm()));
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
	void bindId(final PreparedStatement stmt, final Item model, final byte position) throws SQLException {
		bindId(model.getId(), stmt, position);
	}
}