package br.com.coffemanager.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class Venda {
	private final Long id;
	private final Long cadastroUsuarioId;
	private final LocalDateTime criadoEm;
	
	private final List<VendaItem> itens;

	/**
	 * @param id
	 * @param cadastroUsuarioId
	 * @param criadoEm
	 */
	public Venda(final Long id, final List<VendaItem> itens, final Long cadastroUsuarioId, final LocalDateTime criadoEm) {
		this.id = id;
		this.cadastroUsuarioId = cadastroUsuarioId;
		this.criadoEm = criadoEm;
		this.itens = itens;
	}

	/**
	 * @param cadastroUsuarioId
	 */
	public Venda(final Long cadastroUsuarioId) {
		this.id = null;
		this.itens = new ArrayList<>();
		this.cadastroUsuarioId = cadastroUsuarioId;
		this.criadoEm = LocalDateTime.now();
	}

	public final void addItem(final Item item, final int qtd) {
		this.itens.add(new VendaItem(item, qtd));
	}

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @return the cadastroUsuarioId
	 */
	public final Long getCadastroUsuarioId() {
		return cadastroUsuarioId;
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


