package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Prestador;

public class controllerCadastroPrestadores<PrestadorDAO> {

    private static Object prestadorEditar;

	@FXML
    private Button btCadastrar;

    @FXML
    private Button btCadastroPrestadores;

    @FXML
    private Button btCadastroVeiculos;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btRegistroServicos;

    @FXML
    private Button btSair;

    @FXML
    private TextField txtAreaEspecializada;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtComisao;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Text txtUser;
    @FXML
    void actionCadastrar(ActionEvent event) {
        if (txtNome.getText().isEmpty() || txtCPF.getText().isEmpty()
                || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()
                || txtComisao.getText().isEmpty() || txtAreaEspecializada.getText().isEmpty()) {

            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("Erro ao cadastrar!");
            erro.setContentText("Por favor, preencha todos os campos obrigatórios!");
            erro.show();

        } else if (!validarCPF(txtCPF.getText())) {
            Alert erro = new Alert(Alert.AlertType.ERROR);
            erro.setTitle("CPF inválido");
            erro.setContentText("O CPF informado não é válido. Tente novamente.");
            erro.show();

        } else {
            Prestador prestador = new Prestador();
            PrestadorDAO prestadorDAO = new PrestadorDAO();

            prestador.setNome(txtNome.getText());
            prestador.setCpf_cnpj(txtCPF.getText());
            prestador.setTelefone(txtTelefone.getText());
            prestador.setEmail(txtEmail.getText());

            try {
                prestador.setComissao(Double.parseDouble(txtComisao.getText().replace(",", ".")));
            } catch (NumberFormatException e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro na comissão");
                erro.setContentText("O valor da comissão é inválido. Use apenas números.");
                erro.show();
                return;
            }

            prestador.txtAreaEspecializada(txtAreaEspecializada.getText());

            if (controllerCadastroPrestadores.prestadorEditar == null) {
                prestadorDAO.create(prestador);

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Sucesso!");
                msg.setContentText("Prestador cadastrado com sucesso!");
                msg.show();

                Stage stage = (Stage) btCadastrar.getScene().getWindow();
                stage.close();
            } else {
                prestador.setId(controllerCadastroPrestadores.prestadorEditar.getId());
                prestadorDAO.update(prestador);

                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Sucesso!");
                msg.setContentText("Prestador atualizado com sucesso!");
                msg.show();

                controllerCadastroPrestadores.prestadorEditar = null;

                Stage stage = (Stage) btCadastrar.getScene().getWindow();
                stage.close();
            }
        }
    }

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", ""); // Remove tudo que não é número
        if (!cpf.matches("\\d{11}") || cpf.matches("(\\d)\\1{10}")) return false;

        for (int j = 9; j < 11; j++) {
            int soma = 0;
            for (int i = 0; i < j; i++) {
                soma += (cpf.charAt(i) - '0') * ((j + 1) - i);
            }
            int digito = (soma * 10) % 11;
            if (digito == 10) digito = 0;
            if (digito != (cpf.charAt(j) - '0'))
            return false;
        }
        return true;
    }



    @FXML
    void actionCadastroPrestadores(ActionEvent event) {

    }

    @FXML
    void actionCadastroVeiculos(ActionEvent event) {

    }

    @FXML
    void actionCancelar(ActionEvent event) {

    }

    @FXML
    void actionRegistroServicos(ActionEvent event) {

    }

    @FXML
    void actionSair(ActionEvent event) {

    }

}
