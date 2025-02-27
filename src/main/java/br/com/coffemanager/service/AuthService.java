package br.com.coffemanager.service;

import br.com.coffemanager.data.UsuarioDAO;
import br.com.coffemanager.model.Usuario;

public final class AuthService {

	private static AuthService instance;

	private final UsuarioDAO userDao;

	private static Usuario logado;

	private AuthService(final UsuarioDAO userDao) {
		this.userDao = userDao;
	}

	public final static AuthService getInstance(final UsuarioDAO userDao) {
		if (instance == null) {
			instance = new AuthService(userDao);
		}
		return instance;
	}

	public boolean autenticar(final String username, final String senha) {
		System.out.println(username + senha);
		final Usuario usuario = userDao.findByUserName(username);
		System.out.println(usuario);

		if (usuario != null && usuario.autentica(senha)) {
			System.out.println("logou");

			logado = usuario;
			return true;
		}
		return false;
	}

	public static Usuario getUsuarioLogado() {
		return logado;
	}
}
