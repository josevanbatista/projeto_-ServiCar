package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ConnectionFactory.ConnectionDatabase;

public class RegistroDao {

	public static ObservableList<Registro> buscarPorFuncionario(String nomeFuncionario) {
		ObservableList<Registro> registros = FXCollections.observableArrayList();

		String sql = "SELECT descricao, data, forma_pgto, modelo, valor, comissao " + "FROM registros_servico "
				+ "WHERE LOWER(nome_funcionario) LIKE ?";

		try (Connection conn = ConnectionDatabase.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, "%" + nomeFuncionario.toLowerCase() + "%");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Registro r = new Registro(rs.getString("descricao"), rs.getString("data"), rs.getString("forma_pgto"),
						rs.getString("modelo"), rs.getDouble("valor"), rs.getDouble("comissao"));
				registros.add(r);
			}

		} catch (Exception e) {
			e.printStackTrace(); 
		}

		return registros;
	
	}
	
	
	
}
