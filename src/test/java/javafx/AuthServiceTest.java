package javafx;

import br.com.coffemanager.data.UsuarioDAO;
import br.com.coffemanager.data.connection.PostgresConnectionFactory;
import br.com.coffemanager.service.AuthService;

public class AuthServiceTest {
	public static void main(final String args[]) {
		var auths = AuthService.getInstance(
				UsuarioDAO.getInstance(
						PostgresConnectionFactory.getInstance()));
		
		var username = "admin";
		var senha = "admin123";
		
		auths.autenticar(username, senha);
		
		assert AuthService.getUsuarioLogado() != null;
	}
}
