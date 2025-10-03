package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import model.Veiculo;

public class VeiculoDao {

    public void create(Veiculo veiculo) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Veiculo (placa, marca, modelo, cor, ano, observacao) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getAno());
            stmt.setString(6, veiculo.getObservacao());

            stmt.execute();
            System.out.println("Veículo cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar veículo!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public static ArrayList<Veiculo> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Veiculo");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("id"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("cor"),
                    rs.getString("ano"),
                    rs.getString("observacoes")
                );
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os veículos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return veiculos;
    }

    public void update(Veiculo veiculo) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Veiculo SET placa = ?, marca = ?, modelo = ?, cor = ?, ano = ?, observacao = ? WHERE idVeiculo = ?");

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setString(4, veiculo.getCor());
            stmt.setString(5, veiculo.getAno());
            stmt.setString(6, veiculo.getObservacao());
            stmt.setString(7, veiculo.getId());

            stmt.executeUpdate();
            System.out.println("Veículo atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(int id) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Veiculo WHERE idVeiculo = ?");
            stmt.setInt(1, id);

            stmt.execute();
            System.out.println("Veículo excluído com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Veiculo> search(String pesquisa) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        pesquisa = "%" + pesquisa + "%";

        try {
            stmt = con.prepareStatement("SELECT * FROM Veiculo WHERE placa LIKE ? OR marca LIKE ? OR modelo LIKE ?");
            stmt.setString(1, pesquisa);
            stmt.setString(2, pesquisa);
            stmt.setString(3, pesquisa);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getString("id"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("cor"),
                    rs.getString("ano"),
                    rs.getString("observacoes")
                );
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return veiculos;
    }

    public Veiculo findById(int id) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM Veiculo WHERE idVeiculo = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                    rs.getString("idVeiculo"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("cor"),
                    rs.getString("ano"),
                    rs.getString("observacao")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo por ID!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return null;
    }
}
