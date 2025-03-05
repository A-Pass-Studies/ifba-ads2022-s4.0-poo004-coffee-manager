package br.com.coffemanager.service;

import br.com.coffemanager.data.CompraDAO;
import br.com.coffemanager.model.Compra;

public class ComprasService {

	private final CompraDAO compraDAO;
	
	private ComprasService(final AuthService authService, final CompraDAO compraDAO) {
		this.compraDAO = compraDAO;
	}
	
	public void adicionarRegistroDeCompra(final Compra compra) {
			compraDAO.save(compra);

	}
}
