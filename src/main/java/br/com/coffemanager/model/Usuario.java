package br.com.coffemanager.model;

import java.time.LocalDateTime;

//User Model
public class Usuario {
	private Long id;
	private final String username;
	private final String senha;
	private final String nomeCompleto;
	private final UsuarioTipo tipo;
	private final LocalDateTime criadoEm;
	private final LocalDateTime atualizadoEm;
	
	public Usuario(final Long id, final String username, 
			final String nomeCompleto,
			final String senha, final UsuarioTipo tipo, final LocalDateTime criadoEm,
			final LocalDateTime atualizadoEm) {
		this.id = id;
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.tipo = tipo;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}
	
	public Usuario(final String username, 
			final String nomeCompleto,
			final String senha, final UsuarioTipo tipo) {
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.senha = senha;
		this.tipo = tipo;
		this.criadoEm = LocalDateTime.now();
		this.atualizadoEm = LocalDateTime.from(this.criadoEm);
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
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
