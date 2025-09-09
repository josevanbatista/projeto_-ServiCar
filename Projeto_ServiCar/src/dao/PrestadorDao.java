package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import connectionFactory.ConnectionDataBase;
import model.Funcionario;
import model.Prestador;

public class PrestadorDao {

    public void create(Prestador prestador) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
//cv bfgbtbgtbaaa
        try {
            stmt = con.prepareStatement("INSERT INTO Prestador (nome, cpf_cnpj, funcao, telefone, email, comissao, observacoes) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, prestador.getNome());
            stmt.setString(2, prestador.getCpf_cnpj());
            stmt.setString(3, prestador.getFuncao());
            stmt.setString(4, prestador.getTelefone());
            stmt.setString(5, prestador.getEmail());
            stmt.setString(6, prestador.getComissao());
            stmt.setString(7, prestador.getObservacoes());

            stmt.execute();
            System.out.println("Prestador cadastrado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar prestador!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Prestador> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Prestador> prestadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Prestador");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Prestador prestador = new Prestador(null, null, null, null, null, null, null, null);
                prestador.setId(rs.getString("idPrestador"));
                prestador.setNome(rs.getString("nome"));
                prestador.setCpf_cnpj(rs.getString("cpf_cnpj"));
                prestador.setFuncao(rs.getString("funcao"));
                prestador.setTelefone(rs.getString("telefone"));
                prestador.setEmail(rs.getString("email"));
                prestador.setComissao(rs.getString("comissao"));
                prestador.setObservacoes(rs.getString("observacoes"));

                prestadores.add(prestador);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os dados dos prestadores!", e);
        } finally {
            ConnectionFactory.ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return prestadores;
    }

    public void delete(int idPrestador) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Prestador WHERE idPrestador = ?");
            stmt.setInt(1, idPrestador);

            stmt.execute();
            System.out.println("Prestador excluído!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir prestador!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Prestador> search(String pesquisar) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        pesquisar = "%" + pesquisar + "%";
        ArrayList<Prestador> prestadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Prestador WHERE nome LIKE ? OR cpf_cnpj LIKE ?");
            stmt.setString(1, pesquisar);
            stmt.setString(2, pesquisar);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Prestador prestador = new Prestador(pesquisar, pesquisar, pesquisar, pesquisar, pesquisar, pesquisar, pesquisar, pesquisar);
                prestador.setId(rs.getString("idPrestador"));
                prestador.setNome(rs.getString("nome"));
                prestador.setCpf_cnpj(rs.getString("cpf_cnpj"));
                prestador.setFuncao(rs.getString("funcao"));
                prestador.setTelefone(rs.getString("telefone"));
                prestador.setEmail(rs.getString("email"));
                prestador.setComissao(rs.getString("comissao"));
                prestador.setObservacoes(rs.getString("observacoes"));

                prestadores.add(prestador);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prestadores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return prestadores;
    }

    public void update(Prestador prestador) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(
                "UPDATE Prestador SET nome = ?, cpf_cnpj = ?, funcao = ?, telefone = ?, email = ?, comissao = ?, observacoes = ? WHERE idPrestador = ?"
            );

            stmt.setString(1, prestador.getNome());
            stmt.setString(2, prestador.getCpf_cnpj());
            stmt.setString(3, prestador.getFuncao());
            stmt.setString(4, prestador.getTelefone());
            stmt.setString(5, prestador.getEmail());
            stmt.setString(6, prestador.getComissao());
            stmt.setString(7, prestador.getObservacoes());
            stmt.setString(8, prestador.getId());

            stmt.executeUpdate();
            System.out.println("Prestador atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar prestador!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    public Prestador autenticarUser(String user, String password) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Prestador prestador = new Prestador();
		try {
			stmt = con.prepareStatement("SELECT * FROM Funcionario where cpfFuncionario = ? and senha = ?");
			stmt.setString(1, user);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				prestador.setId(rs.getString("idPrestador"));
                prestador.setNome(rs.getString("nome"));
                prestador.setCpf_cnpj(rs.getString("cpf_cnpj"));
                prestador.setFuncao(rs.getString("funcao"));
                prestador.setTelefone(rs.getString("telefone"));
                prestador.setEmail(rs.getString("email"));
                prestador.setComissao(rs.getString("comissao"));
                prestador.setObservacoes(rs.getString("observacoes"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao pesquisar os dados!",e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return prestador;
	}
    
}
