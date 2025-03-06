package br.com.coffemanager.service;

import br.com.coffemanager.data.VendaDAO;
import br.com.coffemanager.model.Venda;

public final class VendaService {

	private static VendaService instance;

	private final AuthService auths;
	private final VendaDAO vendaDAO;

	private VendaService(final AuthService authService, final VendaDAO vendaDAO) {
		this.vendaDAO = vendaDAO;
		this.auths = authService;
	}

	public final static VendaService getInstsance(final AuthService authService, final VendaDAO vendaDAO) {
		if (instance == null) {
			instance = new VendaService(authService, vendaDAO);
		}
		return instance;
	}
	
	public final Venda abrirVenda() {
		return new Venda(auths.getUsuarioLogado());
	}
}
