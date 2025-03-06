package br.com.coffemanager.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.VendaItem;

final public class VendaItemDAO extends BaseDAO<VendaItem, Long> {

	private static VendaItemDAO instance;
	private final ItemDAO itemDAO;

	static String TABLE_NAME = "movimento.venda_itens";
	static String ATTR_ID = "venda_id";
	static String ATTR_ITEM = "item_id";
	static String ATTR_QTD = "qtd";
	static String ATTR_VALOR_VENDA = "valor_venda_unitario";
	
	private VendaItemDAO(final ConnectionFactory connectionFactory, final ItemDAO itemDAO) {
		super(connectionFactory);
		this.itemDAO = itemDAO;
	}

	public static VendaItemDAO getInstance(final ConnectionFactory connectionFactory, final ItemDAO itemDAO) {
		if (instance == null) {
			instance = new VendaItemDAO(connectionFactory, itemDAO);
		}
		return instance;
	}

	@Override
	List<String> getAttributes(boolean withId) {
		final ArrayList<String> attrs = new ArrayList<>();
		if (withId) {
			attrs.add(ATTR_ID);
		}
		attrs.add(ATTR_ITEM);
		attrs.add(ATTR_QTD);
		attrs.add(ATTR_QTD);
		attrs.add(ATTR_VALOR_VENDA);
		return attrs;
	}

	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	@Override
	VendaItem createModel(ResultSet rs) throws SQLException {
		final var item = itemDAO.findById(rs.getLong(2));
		
		return new VendaItem(rs.getLong(ATTR_ID), item, rs.getInt(ATTR_QTD), rs.getBigDecimal(ATTR_VALOR_VENDA));
	}

	@Override
	void setId(VendaItem model, Long id) {
		model.setVendaId(id);
	}

	@Override
	String getAttrId() {
		return ATTR_ID;
	}

	@Override
	void bindValues(VendaItem model, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, model.getVendaId());
		stmt.setLong(2, model.getItem().getId());
		stmt.setInt(3, model.getQtd());
		stmt.setBigDecimal(4, model.getValorUnitarioVenda());
	}

	@Override
	void bindId(Long id, PreparedStatement stmt, byte position) throws SQLException {
		stmt.setLong(position, id);
	}

	@Override
	void bindId(PreparedStatement stmt, VendaItem model, byte position) throws SQLException {
		bindId(model.getVendaId(), stmt, position);
	}

}
