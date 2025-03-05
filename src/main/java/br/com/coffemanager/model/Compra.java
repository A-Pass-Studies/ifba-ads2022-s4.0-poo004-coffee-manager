package br.com.coffemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Compra {
	private Long id;
	private Item item;
	private BigDecimal valorUnitario;
	private int qtd;
	private LocalDate dataCompra;
	private LocalDate vencimento;
	private Usuario cadastroUsuario;
	private LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;

	public Compra(final Long id, final Item item, final BigDecimal valorUnitario, final int qtd,
			final LocalDate dataCompra, final LocalDate vencimento, final Usuario cadastroUsuario,
			final LocalDateTime criadoEm, final LocalDateTime atualizadoEm) {
		
		this.id = id;
		this.item = item;
		this.valorUnitario = valorUnitario;
		this.qtd = qtd;
		this.dataCompra = dataCompra;
		this.vencimento = vencimento;
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	public Compra(final Item item, final BigDecimal valorUnitario, final int qtd, final LocalDate dataCompra,
			final LocalDate vencimento, final Usuario cadastroUsuario) {
		this.item = item;
		this.valorUnitario = valorUnitario;
		this.qtd = qtd;
		this.dataCompra = dataCompra;
		this.vencimento = vencimento;
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = LocalDateTime.now();
		this.atualizadoEm = LocalDateTime.from(this.criadoEm);
	}

	public final Long getId() {
		return id;
	}

	public final Item getItem() {
		return item;
	}

	public final BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public final int getQtd() {
		return qtd;
	}

	public final LocalDate getDataCompra() {
		return dataCompra;
	}

	public final LocalDate getVencimento() {
		return vencimento;
	}

	public final Usuario getUsuarioCadastro() {
		return cadastroUsuario;
	}

	public final LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public final LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

}