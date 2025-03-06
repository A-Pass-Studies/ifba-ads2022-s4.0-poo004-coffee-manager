package br.com.coffemanager.javafx;

import java.text.NumberFormat;

import br.com.coffemanager.model.Compra;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public final class CompraTableView {

	public final Compra compra;

	private final SimpleLongProperty itemId;

	private final SimpleStringProperty valorUnitario;

	private final SimpleIntegerProperty qtd;

	private final SimpleStringProperty nf;

	CompraTableView(final Compra compra) {
		this.compra = compra;
		this.itemId = new SimpleLongProperty(compra.getItem().getId());
		this.valorUnitario = new SimpleStringProperty(
				NumberFormat.getCurrencyInstance().format(compra.getValorUnitario()));
		this.qtd = new SimpleIntegerProperty(compra.getQtd());
		this.nf = new SimpleStringProperty(compra.getNfCupomCod());
	}

	public final SimpleLongProperty itemIdProperty() {
		return new SimpleLongProperty(compra.getItem().getId());
	}

	public final Long getItemId() {
		return itemId.get();
	}

	/**
	 * @return the valorUnitario
	 */
	public final SimpleStringProperty getValorUnitarioProperty() {
		return valorUnitario;
	}

	public final String getValorUnitario() {
		return valorUnitario.get();
	}

	/**
	 * @return the qtd
	 */
	public final SimpleIntegerProperty getQtdProperty() {
		return qtd;
	}

	public final Integer getQtd() {
		return qtd.get();
	}

	/**
	 * @return the nf
	 */
	public final SimpleStringProperty getNfProperty() {
		return nf;
	}

	public final String getNf() {
		return nf.get();
	}
}
