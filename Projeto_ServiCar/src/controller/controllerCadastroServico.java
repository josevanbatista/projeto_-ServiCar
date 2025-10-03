package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import application.Main;
import dao.PrestadorDao;
import dao.ServicoDao;
import dao.VeiculoDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.TextFields;

import ConnectionFactory.ConnectionDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Prestador;
import model.Servico;
import model.Veiculo;

public class controllerCadastroServico implements Initializable {

	private static Object servicoEditar;

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCadastroPrestadores;

	@FXML
    private Button btCadastroServicos;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btProcurar;

	@FXML
	private Button btRegistroServicos;

	@FXML
	private Button btSair;

	@FXML
	private TextField txtAnoDeFab;

	@FXML
	private TextField txtComissao;

	@FXML
	private TextField txtCorDoVeiculo;

	@FXML
	private DatePicker txtDataDoServico;
	

	@FXML
	private ChoiceBox<String> txtFormaDePGTO;
//sncocebve vjclw 
	@FXML
	private TextField txtMarcaDoVeiculo;

	@FXML
	private TextField txtModeloDoVeiculo;

	@FXML
	private TextField txtNomeDoPrestador;

	@FXML
	private TextField txtObservacao;

	@FXML
	private TextField txtPlacaDoVeiculo;

	@FXML
	private Text txtUser;

	@FXML
	private TextField txtValorDoServico;

	@FXML
	void actionCadastroPrestadores(ActionEvent event) throws IOException {
		Main.TelaCadastroPrestadores();
	}
	
	@FXML
    void actionCadastroServicos(ActionEvent event) throws IOException {
		Main.TelaCadastroServico();
    }

	@FXML
	void actionCadastrar(ActionEvent event) {
	    // ==================== VALIDAÇÃO DE CAMPOS ====================
	    if (txtNomeDoPrestador.getText().isEmpty() || txtPlacaDoVeiculo.getText().isEmpty()
	            || txtValorDoServico.getText().isEmpty() || txtComissao.getText().isEmpty()
	            || txtFormaDePGTO.getValue() == null || txtDataDoServico.getValue() == null
	            || txtAnoDeFab.getText().isEmpty()) {

	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro ao cadastrar!");
	        erro.setContentText("Por favor, preencha todos os campos obrigatórios!");
	        erro.show();
	        return;
	    }

	    // ==================== VALIDAÇÃO DE PLACA ====================
	    if (!validarPlaca(txtPlacaDoVeiculo.getText())) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Placa inválida");
	        erro.setContentText("A placa informada é inválida!");
	        erro.show();
	        return;
	    }

	    // ==================== BUSCA VEÍCULO ====================
	    VeiculoDao veiculoDAO = new VeiculoDao();
	    var veiculos = veiculoDAO.search(txtPlacaDoVeiculo.getText());

	    if (veiculos == null || veiculos.isEmpty()) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro ao cadastrar!");
	        erro.setContentText("Nenhum veículo encontrado com a placa informada.");
	        erro.show();
	        return;
	    }

	    Veiculo veiculo = veiculos.get(0);

	    // ==================== BUSCA PRESTADOR ====================
	    Prestador prestador = buscarPorNome(txtNomeDoPrestador.getText());
	    if (prestador == null) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro!");
	        erro.setContentText("Prestador não encontrado.");
	        erro.show();
	        return;
	    }

	    // ==================== CONVERSÃO DE DATAS E NÚMEROS ====================
	    LocalDate dataServico = txtDataDoServico.getValue();
	    int anoFabricacao;
	    double valorServico;
	    double valorComissao;

	    try {
	        anoFabricacao = Integer.parseInt(txtAnoDeFab.getText());
	    } catch (NumberFormatException e) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro no ano de fabricação");
	        erro.setContentText("Ano de fabricação inválido.");
	        erro.show();
	        return;
	    }

	    try {
	        valorServico = Double.parseDouble(txtValorDoServico.getText().replace(",", "."));
	        valorComissao = valorServico * (prestador.getComissao() / 100);
	    } catch (NumberFormatException e) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro nos valores");
	        erro.setContentText("Valor do serviço inválido.");
	        erro.show();
	        return;
	    }

	    // ==================== CRIA OBJETO SERVIÇO ====================
	    Servico servico = new Servico();
	    ServicoDao servicoDao = new ServicoDao();

	    servico.setVeiculo_id(veiculo.getId());
	    servico.setPrestador_id(prestador.getId());
	    servico.setModeloVeiculo(txtModeloDoVeiculo.getText());
	    servico.setMarcaDoVeiculo(txtMarcaDoVeiculo.getText());
	    servico.setCorVeiculo(txtCorDoVeiculo.getText());
	    servico.setForma_pagamento(txtFormaDePGTO.getValue());
	    servico.setObservacao(txtObservacao.getText());
	    servico.setDataServico(dataServico); // LocalDate
	    servico.setAnoFabricacao(anoFabricacao);
	    servico.setValor_total(valorServico);
	    servico.setValor_comissao(valorComissao);

	    // ==================== SALVA OU ATUALIZA ====================
	    try {
	        if (controllerCadastroServico.servicoEditar == null) {
	            servicoDao.create(servico);
	            Alert msg = new Alert(Alert.AlertType.INFORMATION);
	            msg.setTitle("Sucesso!");
	            msg.setContentText("Serviço cadastrado com sucesso!");
	            msg.show();
	        } else {
	            servicoDao.update(servico);
	            Alert msg = new Alert(Alert.AlertType.INFORMATION);
	            msg.setTitle("Sucesso!");
	            msg.setContentText("Serviço atualizado com sucesso!");
	            msg.show();
	            controllerCadastroServico.servicoEditar = null;
	        }
	    } catch (Exception e) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro ao cadastrar serviço!");
	        erro.setContentText("Ocorreu um erro ao salvar no banco: " + e.getMessage());
	        erro.show();
	        return;
	    }

	    // ==================== FECHA A JANELA ====================
	    Stage stage = (Stage) btCadastrar.getScene().getWindow();
	    stage.close();
	}




	public static boolean validarPlaca(String placa) {
		if (placa == null || placa.isEmpty()) {
			return false;
		}

		// Normaliza
		placa = placa.toUpperCase().replaceAll("[^A-Z0-9]", "");

		// Aceita placas antigas (AAA1234) ou Mercosul (AAA1A23)
		boolean padraoAntigo = placa.matches("[A-Z]{3}[0-9]{4}");
		boolean padraoMercosul = placa.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");

		return padraoAntigo || padraoMercosul;
	}

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void actionProcurar(ActionEvent event) {

	}

	@FXML
	void actionRegistroServicos(ActionEvent event) throws IOException {
		Main.TelaRegidstroDeServicos();
	}

	@FXML
	void actionSair(ActionEvent event) throws IOException {
		Main.TelaLogin();
	}

	@FXML
	void actonPLACAClick(MouseEvent event) {

		if (txtPlacaDoVeiculo.getText().length() > 3) {
			VeiculoDao veiculoDao = new VeiculoDao();
			ArrayList<Veiculo> veiculos = veiculoDao.search(txtPlacaDoVeiculo.getText());

			if (veiculos != null && !veiculos.isEmpty()) {
				Veiculo veiculo = veiculos.get(0);

				// Preenche todos os campos relacionados ao veículo
				txtPlacaDoVeiculo.setText(veiculo.getPlaca());
				txtModeloDoVeiculo.setText(veiculo.getModelo());
				txtMarcaDoVeiculo.setText(veiculo.getMarca());
				txtCorDoVeiculo.setText(veiculo.getCor());
				txtAnoDeFab.setText(String.valueOf(veiculo.getAno()));

			} else {
				Alert alerta = new Alert(Alert.AlertType.WARNING);
				alerta.setTitle("Placa não encontrada");
				alerta.setContentText("Nenhum veículo encontrado com essa placa!");
				alerta.show();
			}
		}
	}

	@FXML
	void actonPLACAType(KeyEvent event) {
		if (txtPlacaDoVeiculo.getText().length() > 3) {
			VeiculoDao veiculoDao = new VeiculoDao();
			Veiculo veiculo = new Veiculo();
			veiculo.setPlaca(txtPlacaDoVeiculo.getText());
			ArrayList<Veiculo> veiculos = new ArrayList<>();
			veiculos = veiculoDao.search(veiculo.getPlaca());
			veiculo = veiculos.get(0);
			txtPlacaDoVeiculo.setText(veiculo.getPlaca());
			// } else {
			// txtNome.setText("");
			// }
		}
	}
	
	// Busca o prestador pelo nome no banco
	public Prestador buscarPorNome(String nome) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Prestador prestador = null;

	    try {
	        stmt = con.prepareStatement("SELECT * FROM Prestador WHERE nome = ?");
	        stmt.setString(1, nome);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            prestador = new Prestador();
	            prestador.setId(rs.getString("id"));
	            prestador.setNome(rs.getString("nome"));
	            prestador.setComissao(rs.getDouble("comissao"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }

	    return prestador;
	}

	
	// Calcula comissão baseado no prestador e valor do serviço
	@FXML
	public void calcularComissaoPrestador() {
	    try {
	        Prestador prestador = buscarPorNome(txtNomeDoPrestador.getText());

	        if (prestador != null && !txtValorDoServico.getText().isEmpty()) {
	            double valorServico = Double.parseDouble(txtValorDoServico.getText().replace(",", "."));
	            double percentual = prestador.getComissao();
	            double valorComissao = valorServico * (percentual / 100);

	            txtComissao.setText(String.valueOf(percentual)); // mantém percentual
	            txtComissao.setText(String.format("R$ %.2f", valorComissao));
	        } else {
	            txtComissao.setText("");
	        }

	    } catch (NumberFormatException e) {
	        txtComissao.setText("Valor inválido");
	    }
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Recalcula comissão sempre que o prestador ou valor do serviço mudar
	    txtNomeDoPrestador.textProperty().addListener((obs, oldText, newText) -> calcularComissaoPrestador());
	    txtValorDoServico.textProperty().addListener((obs, oldText, newText) -> calcularComissaoPrestador());
		
		// TODO Auto-generated method stub
		txtFormaDePGTO.getItems().addAll("Pix", "Dinheiro", "Cartão");
		txtFormaDePGTO.setValue("Dinheiro");

		// txtNomeDoPrestador.setText(controllerLogin.prestador.getNome());
		// txtNomeDoPrestador.setEditable(false);

		// txtValorDoServico.setText("0,00");
		// txtValorDoServico.setEditable(false);
		// txtComissao.setText("0,00");
		// txtComissao.setEditable(false);

		txtUser.setText(controllerLogin.prestador.getNome());
		VeiculoDao veiculoDao = new VeiculoDao();
		ArrayList<Veiculo> arrayVeiculos = VeiculoDao.read();
		String[] listaVeiculos = new String[VeiculoDao.read().size()];
		for (int i = 0; i < VeiculoDao.read().size(); i++) {
			Veiculo veiculo = arrayVeiculos.get(i);
			listaVeiculos[i] = veiculo.getPlaca();

			PrestadorDao prestadorDao = new PrestadorDao();
			ArrayList<Prestador> arrayPrestadores = PrestadorDao.read();
			String[] listaPrestadores = new String[PrestadorDao.read().size()];
			for (int  j = 0; j < PrestadorDao.read().size(); j++) {
				Prestador prestador = arrayPrestadores.get(j);
				listaPrestadores[j] = prestador.getNome();

			}
			TextFields.bindAutoCompletion(txtPlacaDoVeiculo, listaVeiculos)
					.setOnAutoCompleted(event -> actonPLACAClick(null));

			TextFields.bindAutoCompletion(txtNomeDoPrestador, listaPrestadores).setOnAutoCompleted(event -> actonPLACAClick(null));

		}
	}
}
