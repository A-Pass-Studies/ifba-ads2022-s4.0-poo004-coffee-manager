package br.com.coffemanager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Venda {
	private Long id;
	private final Usuario cadastroUsuario;
	private final LocalDateTime criadoEm;
	
	private final List<VendaItem> itens;

	/**
	 * @param id
	 * @param cadastroUsuario
	 * @param criadoEm
	 */
	public Venda(final Long id, final List<VendaItem> itens, final Usuario cadastroUsuario, final LocalDateTime criadoEm) {
		this.id = id;
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = criadoEm;
		this.itens = itens;
	}

	/**
	 * @param cadastroUsuario
	 */
	public Venda(final Usuario cadastroUsuario) {
		this.id = null;
		this.itens = new ArrayList<>();
		this.cadastroUsuario = cadastroUsuario;
		this.criadoEm = LocalDateTime.now();
	}

	public final VendaItem addItem(final Item item, final int qtd) {
		final var vitem = new VendaItem(item, qtd);
		this.itens.add(vitem);
		return vitem;
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}
	
	public final void setId(final Long newId) {
		this.id = newId;
	}

	/**
	 * @return the cadastroUsuarioId
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
	 * @return the itens
	 */
	public final List<VendaItem> getItens() {
		return itens;
	}
}


