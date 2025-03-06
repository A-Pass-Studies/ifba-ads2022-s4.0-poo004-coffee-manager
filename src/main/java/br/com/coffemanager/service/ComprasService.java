package br.com.coffemanager.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.coffemanager.data.CompraDAO;
import br.com.coffemanager.model.Compra;
import br.com.coffemanager.model.Item;

public final class ComprasService {

	private static ComprasService instance;

	private final CompraDAO compraDAO;
	private final AuthService auths;

	private ComprasService(final AuthService authService, final CompraDAO compraDAO) {
		this.compraDAO = compraDAO;
		this.auths = authService;
	}

	public final static ComprasService getInstsance(final AuthService authService, final CompraDAO compraDAO) {
		if (instance == null) {
			instance = new ComprasService(authService, compraDAO);
		}
		return instance;
	}

	public void adicionarRegistroDeCompra(final Item produto, final BigDecimal valorUnitario, final int qtd, final String nfCupomCod) {

		final var compra = new Compra(produto, valorUnitario, qtd, LocalDate.now(), LocalDate.now().plusYears(1),
				auths.getUsuarioLogado());
		compra.setNfCupomCod(nfCupomCod);

		compraDAO.save(compra);

	}
}
