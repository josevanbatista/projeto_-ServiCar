package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {

	// endereço do driver sql server
	private static final String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// endereco do banco de dados
	private static final String URL = "jdbc:sqlserver://192.168.60.145:61575;encrypt=false;databaseName=servicar";
	// usuario do banco de dados
	private static final String user = "sa";
	// Senha do banco de dados
	private static final String password = "Senailab03";

	public static Connection getConnection() {
		try {
			Class.forName(Driver);
			System.out.println("Conexão realizada !");
			return DriverManager.getConnection(URL, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Erro de conexão!", e);
		}

	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
				System.out.println(" Conexão fechada!");
			}

		} catch (Exception e) {

			throw new RuntimeException("Erro ao fechar conexão!", e);

		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		try {
			if (stmt != null) {
				stmt.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		closeConnection(con, stmt);
		try {
			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
	}

}
