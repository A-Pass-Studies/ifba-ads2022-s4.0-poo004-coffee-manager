package br.com.coffemanager.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Venda;

public final class VendaDAO extends BaseDAO<Venda, Long> {

	private static VendaDAO instance;
	private final VendaItemDAO vendaItemDAO;
	private final UsuarioDAO usuarioDAO;

	private final static String TABLE_NAME = "movimento.vendas";
	private final static String ATTR_ID = "id";
	private final static String ATTR_CADASTRO_USUARIO = "cadastro_usuario_id";
	private final static String ATTR_CRIADO_EM = "criado_em";

	private VendaDAO(final ConnectionFactory connectionFactory, final VendaItemDAO vendaItemDAO,
			final UsuarioDAO usuarioDAO) {
		super(connectionFactory);

		this.vendaItemDAO = vendaItemDAO;
		this.usuarioDAO = usuarioDAO;
	}

	public static VendaDAO getInstance(final ConnectionFactory connectionFactory, final VendaItemDAO vendaItemDAO,
			final UsuarioDAO usuarioDAO) {
		if (instance == null) {
			instance = new VendaDAO(connectionFactory, vendaItemDAO, usuarioDAO);
		}
		return instance;
	}

	@Override
	List<String> getAttributes(boolean withId) {
		final ArrayList<String> attrs = new ArrayList<>();
		if (withId) {
			attrs.add(ATTR_ID);
		}
		attrs.add(ATTR_CADASTRO_USUARIO);
		attrs.add(ATTR_CRIADO_EM);
		return attrs;
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	@Override
	Venda createModel(ResultSet rs) throws SQLException {
		final var vendaId = rs.getLong(ATTR_ID);

		final List<String> where = List.of(VendaItemDAO.ATTR_ID);
		final var vendaItens = vendaItemDAO.findAll(where, (stmt) -> {
			try {
				stmt.setLong(1, vendaId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		return new Venda(vendaId, vendaItens, usuarioDAO.findById(rs.getLong(ATTR_CADASTRO_USUARIO)),
				rs.getTimestamp(ATTR_CRIADO_EM).toLocalDateTime());
	}

	@Override
	void setId(Venda model, Long id) {
		model.setId(id);
	}

	@Override
	String getAttrId() {
		return ATTR_ID;
	}

	@Override
	void bindValues(Venda model, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, model.getCadastroUsuario().getId());
		stmt.setTimestamp(2, java.sql.Timestamp.valueOf(model.getCriadoEm()));
	}

	@Override
	void bindId(Long id, PreparedStatement stmt, byte position) throws SQLException {
		stmt.setLong(position, id);
	}

	@Override
	void bindId(PreparedStatement stmt, Venda model, byte position) throws SQLException {
		bindId(model.getId(), stmt, position);
	}
}
