package br.com.coffemanager.javafx;

import java.util.Objects;

import br.com.coffemanager.model.Item;

final class ItemChoiceBox {

	private final Item item;

	ItemChoiceBox(final Item item) {
		this.item = item;
	}

	final Item getItem() {
		return item;
	}

	@Override
	public String toString() {
		return item.getDescricao();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemChoiceBox other = (ItemChoiceBox) obj;
		return Objects.equals(item.getAtualizadoEm(), other.item.getAtualizadoEm())
				&& Objects.equals(item.getCadastroUsuario().getId(), other.item.getCadastroUsuario().getId())
				&& Objects.equals(item.getCriadoEm(), other.item.getCriadoEm())
				&& Objects.equals(item.getDescricao(), other.item.getDescricao())
				&& Objects.equals(item.getId(), other.item.getId())
				&& Objects.equals(item.getValorVendaAtual(), other.item.getValorVendaAtual());
	}

	@Override
	public int hashCode() {
		return Objects.hash(item.getAtualizadoEm(), item.getCadastroUsuario(), item.getCriadoEm(), item.getDescricao(),
				item.getId(), item.getValorVendaAtual());
	}
}
