package br.com.coffemanager.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Compra;

public final class CompraDAO extends BaseDAO<Compra, Long> {

	private static CompraDAO instance = null;

	private final ItemDAO itemDAO;
	private final UsuarioDAO usuarioDAO;
	
	private final String TABLE_NAME = "movimento.compras";
	private final String ATTR_ID = "id";
	private final String ATTR_ITEM = "item_id";
	private final String ATTR_VALOR_UNITARIO = "valor_unitario";
	private final String ATTR_QTD = "qtd";
	private final String ATTR_DATA_COMPRA = "data_compra";
	private final String ATTR_VENCIMENTO = "vencimento";
	private final String ATTR_CADASTRO_USUARIO = "cadastro_usuario_id";
	private final String ATTR_CRIADO_EM = "criado_em";
	private final String ATTR_ATUALIZADO_EM = "atualizado_em";

	private CompraDAO(final ConnectionFactory connectionFactory, final ItemDAO itemDAO, final UsuarioDAO usuarioDAO) {
		super(connectionFactory);
		
		this.itemDAO = itemDAO;
		this.usuarioDAO = usuarioDAO;
	}

	public static CompraDAO getInstance(final ConnectionFactory connectionFactory, final ItemDAO itemDAO, final UsuarioDAO usuarioDAO) {
		if (instance == null) {
			instance = new CompraDAO(connectionFactory, itemDAO, usuarioDAO);
		}
		return instance;
	}

	@Override
	List<String> getAttributes(boolean withId) {
		final List<String> attrs = new ArrayList<>();
		if (withId) {
			attrs.add(ATTR_ID);
		}
		attrs.add(ATTR_ITEM);
		attrs.add(ATTR_VALOR_UNITARIO);
		attrs.add(ATTR_QTD);
		attrs.add(ATTR_DATA_COMPRA);
		attrs.add(ATTR_VENCIMENTO);
		attrs.add(ATTR_CADASTRO_USUARIO);
		attrs.add(ATTR_CRIADO_EM);
		attrs.add(ATTR_ATUALIZADO_EM);

		return attrs;
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	@Override
	void bindValues(Compra model, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, model.getItem().getId());
		stmt.setBigDecimal(2, model.getValorUnitario());
		stmt.setInt(3, model.getQtd());
		stmt.setDate(4, java.sql.Date.valueOf(model.getDataCompra()));
		stmt.setDate(5, java.sql.Date.valueOf(model.getVencimento()));
		stmt.setLong(6, model.getUsuarioCadastro().getId());
	}

	@Override
	Compra createModel(ResultSet rs) throws SQLException {
		return new Compra(
				rs.getLong(ATTR_ID),
	            itemDAO.findById(rs.getLong(ATTR_ITEM)),
	            rs.getBigDecimal(ATTR_VALOR_UNITARIO),
	            rs.getInt(ATTR_QTD),
	            rs.getDate(ATTR_DATA_COMPRA).toLocalDate(),
	            rs.getDate(ATTR_VENCIMENTO).toLocalDate(),
	            usuarioDAO.findById(rs.getLong(ATTR_CADASTRO_USUARIO)),
	            rs.getTimestamp(ATTR_CRIADO_EM).toLocalDateTime(),
	            rs.getTimestamp(ATTR_ATUALIZADO_EM).toLocalDateTime()
	    );
	}

	@Override
	void setId(Compra model, Long id) {
		model.setId(id);
	}

	@Override
	String getAttrId() {
		return ATTR_ID;
	}

	@Override
	void bindId(Long id, PreparedStatement stmt, byte position) throws SQLException {
		stmt.setLong(position, id);
	}

	@Override
	void bindId(PreparedStatement stmt, Compra model, byte position) throws SQLException {
		bindId(model.getId(), stmt, position);
	}

}
