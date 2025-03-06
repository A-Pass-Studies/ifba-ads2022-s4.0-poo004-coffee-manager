package br.com.coffemanager.model;

import java.math.BigDecimal;

//VendaItem Model
public class VendaItem {
	private Long vendaId;
	private final Item item;
	private final int qtd;
	private final BigDecimal valorUnitarioVenda;

	
	
	/**
	 * @param vendaId
	 * @param item
	 * @param qtd
	 * @param valorUnitarioVenda
	 */
	public VendaItem(final Long vendaId, final Item item, final int qtd, final BigDecimal valorUnitarioVenda) {
		this.vendaId = vendaId;
		this.item = item;
		this.qtd = qtd;
		this.valorUnitarioVenda = valorUnitarioVenda;
	}

	public VendaItem(final Item item, final int qtd) {
		this.item = item;
		this.qtd = qtd;
		this.valorUnitarioVenda = item.getValorVendaAtual();
	}

	/**
	 * @return the vendaId
	 */
	public final Long getVendaId() {
		return vendaId;
	}

	public final void setVendaId(final Long vendaId) {
		this.vendaId = vendaId;
	}
	
	/**
	 * @return the itemId
	 */
	public final Item getItem() {
		return item;
	}

	/**
	 * @return the qtd
	 */
	public final int getQtd() {
		return qtd;
	}

	/**
	 * @return the valorUnitarioVenda
	 */
	public final BigDecimal getValorUnitarioVenda() {
		return valorUnitarioVenda;
	}

	
}