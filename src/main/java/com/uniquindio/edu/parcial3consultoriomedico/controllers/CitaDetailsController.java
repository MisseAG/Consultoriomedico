package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;

public class CitaDetailsController {

    @FXML private Label lblId;
    @FXML private Label lblFecha;
    @FXML private Label lblHora;
    @FXML private Label lblPaciente;
    @FXML private Label lblMedico;
    @FXML private Label lblEspecialidad;
    @FXML private Label lblMotivo;
    @FXML private Label lblEstado;

    public void setCita(Cita cita) {
        lblId.setText("ID: " + cita.getId());
        lblFecha.setText("Fecha: " + cita.getFecha());
        lblHora.setText("Hora: " + cita.getHora());
        lblPaciente.setText("Paciente: " + cita.getPaciente().getName());
        lblMedico.setText("Médico: " + cita.getMedico().getName());
        lblEspecialidad.setText("Especialidad: " + cita.getEspecialidad());
        lblMotivo.setText("Motivo: " + cita.getMotivo());
        lblEstado.setText("Estado: " + cita.getEstado().getName());
    }
}
