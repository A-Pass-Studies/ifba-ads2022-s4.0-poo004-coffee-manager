package br.com.coffemanager.javafx;

import java.text.NumberFormat;

import br.com.coffemanager.model.VendaItem;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemTableView {

	private final VendaItem vendaItem;

	private final SimpleStringProperty descricao;

	private final SimpleStringProperty valor;

	private final SimpleIntegerProperty qtd;

	ItemTableView(final VendaItem item) {
		this.vendaItem = item;
		this.descricao = new SimpleStringProperty(item.getItem().getDescricao());
		this.valor = new SimpleStringProperty(
				NumberFormat.getCurrencyInstance().format(item.getItem().getValorVendaAtual()));
		this.qtd = new SimpleIntegerProperty(item.getQtd());
	}

	/**
	 * @return the vendaItem
	 */
	public final VendaItem getVendaItem() {
		return vendaItem;
	}

	/**
	 * @return the descricao
	 */
	public final String getDescricao() {
		return descricao.get();
	}

	/**
	 * @return the valor
	 */
	public final String getValor() {
		return valor.get();
	}

	/**
	 * @return the qtd
	 */
	public final Integer getQtd() {
		return qtd.get();
	}

	public final SimpleStringProperty getDescricaoProperty() {
		return descricao;
	}

	/**
	 * @return the valor
	 */
	public final SimpleStringProperty getValorProperty() {
		return valor;
	}

	/**
	 * @return the qtd
	 */
	public final SimpleIntegerProperty getQtdProperty() {
		return qtd;
	}

}
