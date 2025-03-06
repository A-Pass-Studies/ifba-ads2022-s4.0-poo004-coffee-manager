package br.com.coffemanager.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

//Item Model
public class Item {
	private Long id;
	private final String descricao;
	private final BigDecimal valorVendaAtual;
	private final Usuario cadastroUsuario;
	private final LocalDateTime criadoEm;
	private LocalDateTime atualizadoEm;

	/**
	 * @param id
	 * @param descricao
	 * @param valorVendaAtual
	 * @param cadastroUsuarioId
	 * @param criadoEm
	 * @param atualizadoEm
	 */
	public Item(final Long id, final String descricao, final BigDecimal valorVendaAtual, final Usuario cadastroUsuario,
			final LocalDateTime criadoEm, final LocalDateTime atualizadoEm) {
		this.id = id;
		this.descricao = descricao;
		this.valorVendaAtual = valorVendaAtual;
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	/**
	 * 
	 * @param descricao
	 * @param valorVendaAtual
	 * @param cadastroUsuario
	 */
	public Item(final String descricao, final BigDecimal valorVendaAtual, final Usuario cadastroUsuario) {
		this.descricao = descricao;
		this.valorVendaAtual = valorVendaAtual;
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = LocalDateTime.now();
		this.atualizadoEm = LocalDateTime.from(this.criadoEm);
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public final String getDescricao() {
		return descricao;
	}

	/**
	 * @return the valorVendaAtual
	 */
	public final BigDecimal getValorVendaAtual() {
		return valorVendaAtual;
	}

	/**
	 * @return the cadastroUsuario
	 */
	public final Usuario getCadastroUsuario() {
		return cadastroUsuario;
	}

	/**
	 * @return the criadoEm
	 */
	public final LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	/**
	 * @return the atualizadoEm
	 */
	public final LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}
}
