package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.Medico;
import com.uniquindio.edu.parcial3consultoriomedico.services.MedicoService;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDoctorController {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtNombres;

    private final MedicoService medicoService = MedicoService.getInstance();

    @FXML
    void saveDoctor(ActionEvent event) {
        // Validar campos
        if (txtCedula.getText().isEmpty() || txtNombres.getText().isEmpty() ||
                txtCelular.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtEspecialidad.getText().isEmpty()) {
            NavigationUtil.showWarning("Todos los campos son obligatorios");
            return;
        }

        // Crear médico usando Builder
        Medico medico = Medico.builder()
                .id(txtCedula.getText())
                .name(txtNombres.getText())
                .phone(txtCelular.getText())
                .correo(txtEmail.getText())
                .especialidad(txtEspecialidad.getText())
                .build();

        medicoService.addMedico(medico);

        NavigationUtil.showInfo("Médico agregado correctamente!");
        txtCedula.getScene().getWindow().hide();
    }
}
