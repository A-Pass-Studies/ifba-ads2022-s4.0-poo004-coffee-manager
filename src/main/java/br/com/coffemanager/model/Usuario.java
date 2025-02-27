package br.com.coffemanager.model;

import java.time.LocalDateTime;

//User Model
public class Usuario {
	private final Long id;
	private final String username;
	private final String senha;
	private final UsuarioTipo tipo;
	private final LocalDateTime criadoEm;
	private final LocalDateTime atualizadoEm;
	
	public Usuario(final Long id, final String username, final String senha, final UsuarioTipo tipo, final LocalDateTime criadoEm,
			final LocalDateTime atualizadoEm) {
		super();
		this.id = id;
		this.username = username;
		this.senha = senha;
		this.tipo = tipo;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getSenha() {
		return senha;
	}

	public boolean autentica(final String senha) {
		return this.senha.equals(senha);
	}
	
	public UsuarioTipo getTipo() {
		return tipo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}
}
