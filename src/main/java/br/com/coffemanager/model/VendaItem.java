package br.com.coffemanager.model;

import java.math.BigDecimal;

//VendaItem Model
public class VendaItem {
	private Long vendaId;
	private Item item;
	private int qtd;
	private BigDecimal valorUnitarioVenda;

	/**
	 * @param vendaId
	 * @param itemId
	 * @param qtd
	 * @param valorUnitarioVenda
	 */
	public VendaItem(Long vendaId, final Item item, final int qtd, final BigDecimal valorUnitarioVenda) {
		this.vendaId = vendaId;
		this.item = item;
		this.qtd = qtd;
		this.valorUnitarioVenda = valorUnitarioVenda;
	}

	/**
	 * @param itemId
	 * @param qtd
	 */
	public VendaItem(final Item item, int qtd) {
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

	/**
	 * @return the itemId
	 */
	public final Item getItemId() {
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