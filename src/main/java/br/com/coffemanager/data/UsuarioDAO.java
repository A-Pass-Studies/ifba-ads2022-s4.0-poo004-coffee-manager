package br.com.coffemanager.data;

import java.util.List;

import br.com.coffemanager.data.connection.ConnectionFactory;
import br.com.coffemanager.model.Usuario;
import br.com.coffemanager.model.UsuarioTipo;

import java.sql.*;
import java.util.ArrayList;

// UsuarioDAO Implementation
public class UsuarioDAO implements GenericDAO<Usuario> {
    private static UsuarioDAO instance;
    private ConnectionFactory connection;

    private UsuarioDAO(final ConnectionFactory connectionFactory) {
        this.connection = connectionFactory;
        System.out.println("criou UsuárioDAO");
    }

    public static UsuarioDAO getInstance(final ConnectionFactory connectionFactory) {
        if (instance == null) {
            instance = new UsuarioDAO(connectionFactory);
        }
        return instance;
    }

    @Override
    public void save(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO auth.usuarios (username, senha, tipo) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo().name());
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario findById(Long id) throws SQLException {
        String sql = "SELECT * FROM auth.usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("senha"),
                    UsuarioTipo.valueOf(rs.getString("tipo")),
                    rs.getTimestamp("criado_em").toLocalDateTime(),
                    rs.getTimestamp("atualizado_em").toLocalDateTime()
                );
            }
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM auth.usuarios";
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("senha"),
                    UsuarioTipo.valueOf(rs.getString("tipo")),
                    rs.getTimestamp("criado_em").toLocalDateTime(),
                    rs.getTimestamp("atualizado_em").toLocalDateTime()
                ));
            }
        }
        return usuarios;
    }
    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE auth.usuarios SET username = ?, senha = ?, tipo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo().name());
            stmt.setLong(4, usuario.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM auth.usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

	public Usuario findByUserName(final String username) {
		 String sql = "SELECT * FROM auth.usuarios WHERE username = ?";
	        try (PreparedStatement stmt = connection.getConnection().prepareStatement(sql)) {
	            stmt.setString(1, username);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
		            System.out.println(username);

	                return new Usuario(
	                    rs.getLong("id"),
	                    rs.getString("username"),
	                    rs.getString("senha"),
	                    UsuarioTipo.valueOf(rs.getString("tipo")),
	                    rs.getTimestamp("criado_em").toLocalDateTime(),
	                    rs.getTimestamp("atualizado_em").toLocalDateTime()
	                );
	            }
	        } catch (SQLException e) {

				e.printStackTrace();
				throw new RuntimeException(e);

			} catch (Exception exception) {
				throw exception;
			}
	        return null;
		
	}
}
