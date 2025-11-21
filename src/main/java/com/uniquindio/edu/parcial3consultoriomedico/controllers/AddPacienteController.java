package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.Paciente;
import com.uniquindio.edu.parcial3consultoriomedico.services.PacienteService;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPacienteController {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEspecialidad; // Lo podemos ignorar si no es necesario

    @FXML
    private TextField txtNombres;

    private final PacienteService pacienteService = PacienteService.getInstance();

    @FXML
    void savePaciente(ActionEvent event) {

        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty() ||
                txtCelular.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            NavigationUtil.showWarning("Todos los campos son obligatorios");
            return;
        }

        Paciente paciente = Paciente.builder()
                .id(txtCedula.getText())
                .name(txtNombres.getText())
                .phone(txtCelular.getText())
                .correo(txtEmail.getText())
                .build();

        pacienteService.addPaciente(paciente);

        NavigationUtil.showInfo("Paciente agregado correctamente!");

        txtCedula.getScene().getWindow().hide();
    }
}
