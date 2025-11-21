package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import com.uniquindio.edu.parcial3consultoriomedico.services.CitaService;
import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;
import com.uniquindio.edu.parcial3consultoriomedico.services.PacienteService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCitaController {

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TextField txtHora;

    @FXML
    private TextField txtMotivo;

    @FXML
    private TextField txtPacienteId;

    @FXML
    private TextField txtMedicoId;

    @FXML
    private TextField txtEspecialidad;

    private CitaService citaService = CitaService.getInstance();

    private CitasViewController parentController;

    public void setCitasViewController(CitasViewController controller) {
        this.parentController = controller;
    }

    @FXML
    void guardarCita() {

        try {
            String pacienteId = txtPacienteId.getText();

            if (PacienteService.getInstance().isCitaAsignada(pacienteId)) {
                NavigationUtil.showError("El paciente ya tiene una cita asignada.");
                return;
            }

            LocalDate fecha = dateFecha.getValue();
            LocalTime hora = LocalTime.parse(txtHora.getText());
            String motivo = txtMotivo.getText();
            String medicoId = txtMedicoId.getText();
            String especialidad = txtEspecialidad.getText();

            Cita cita = citaService.createCita(
                    fecha,
                    hora,
                    motivo,
                    pacienteId,
                    medicoId,
                    especialidad
            );

            citaService.addCita(cita);

            PacienteService.getInstance().buscarPacienteById(pacienteId).setCitaAsignada(true);

            parentController.refrescarTabla();
            cerrarVentana();

        } catch (Exception e) {
            e.printStackTrace();
            NavigationUtil.showError("Datos inválidos: " + e.getMessage());
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) dateFecha.getScene().getWindow();
        stage.close();
    }

}
