package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import model.Servico;

public class ServicoDao {

    public void create(Servico servico) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
//
        try {
            stmt = con.prepareStatement("INSERT INTO Servico (veiculo_id, prestador_id, data_servico, descricao, valor_total, valor_comissao, forma_pagamento, comprovante_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, servico.getVeiculo_id());
            stmt.setString(2, servico.getPrestador_id());
            stmt.setString(3, servico.getData_servico());
            stmt.setString(4, servico.getDescricao());
            stmt.setDouble(5, servico.getValor_total());
            stmt.setDouble(6, servico.getValor_comissao());
            stmt.setString(7, servico.getForma_pagamento());
            stmt.setString(8, servico.getComprovante_path());

            stmt.execute();
            System.out.println("Serviço cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar serviço!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Servico> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Servico> servicos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Servico");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getString("idServico"),
                    rs.getString("veiculo_id"),
                    rs.getString("prestador_id"),
                    rs.getString("data_servico"),
                    rs.getString("descricao"),
                    rs.getDouble("valor_total"),
                    rs.getDouble("valor_comissao"),
                    rs.getString("forma_pagamento"),
                    rs.getString("comprovante_path")
                );
                servicos.add(servico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os serviços!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return servicos;
    }

    public void delete(int id) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Servico WHERE idServico = ?");
            stmt.setInt(1, id);

            stmt.execute();
            System.out.println("Serviço excluído com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir serviço!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void update(Servico servico) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Servico SET veiculo_id = ?, prestador_id = ?, data_servico = ?, descricao = ?, valor_total = ?, valor_comissao = ?, forma_pagamento = ?, comprovante_path = ? WHERE idServico = ?");

            stmt.setString(1, servico.getVeiculo_id());
            stmt.setString(2, servico.getPrestador_id());
            stmt.setString(3, servico.getData_servico());
            stmt.setString(4, servico.getDescricao());
            stmt.setDouble(5, servico.getValor_total());
            stmt.setDouble(6, servico.getValor_comissao());
            stmt.setString(7, servico.getForma_pagamento());
            stmt.setString(8, servico.getComprovante_path());
            stmt.setString(9, servico.getId());

            stmt.executeUpdate();
            System.out.println("Serviço atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Servico> search(String pesquisa) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Servico> servicos = new ArrayList<>();

        pesquisa = "%" + pesquisa + "%";

        try {
            stmt = con.prepareStatement("SELECT * FROM Servico WHERE descricao LIKE ?");
            stmt.setString(1, pesquisa);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico(
                    rs.getString("idServico"),
                    rs.getString("veiculo_id"),
                    rs.getString("prestador_id"),
                    rs.getString("data_servico"),
                    rs.getString("descricao"),
                    rs.getDouble("valor_total"),
                    rs.getDouble("valor_comissao"),
                    rs.getString("forma_pagamento"),
                    rs.getString("comprovante_path")
                );
                servicos.add(servico);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviços!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return servicos;
    }
    public Servico findById(int id) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM Servico WHERE idServico = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Servico(
                    rs.getString("idServico"),
                    rs.getString("veiculo_id"),
                    rs.getString("prestador_id"),
                    rs.getString("data_servico"),
                    rs.getString("descricao"),
                    rs.getDouble("valor_total"),
                    rs.getDouble("valor_comissao"),
                    rs.getString("forma_pagamento"),
                    rs.getString("comprovante_path")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço por ID!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return null;
    }
    
    

}
