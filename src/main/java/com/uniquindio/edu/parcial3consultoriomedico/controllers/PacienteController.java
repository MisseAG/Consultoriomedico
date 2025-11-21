package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.Paciente;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import com.uniquindio.edu.parcial3consultoriomedico.services.PacienteService;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PacienteController {

    @FXML
    private TableView<Paciente> tableCitas;

    @FXML
    private TableColumn<Paciente, String> colID;

    @FXML
    private TableColumn<Paciente, String> colDoctor;

    @FXML
    private TableColumn<Paciente, String> colTelefono;

    @FXML
    private TableColumn<Paciente, String> colCorreo;

    @FXML
    private TableColumn<Paciente, String> colCitaAsignada;

    private final PacienteService pacienteService = PacienteService.getInstance();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarPacientesTabla();
    }

    private void configurarColumnas() {
        colID.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colDoctor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getName()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPhone()));
        colCorreo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));
        colCitaAsignada.setCellValueFactory(cell -> new SimpleStringProperty(
                cell.getValue().isCitaAsignada() ? "Sí" : "No"));
    }

    private void cargarPacientesTabla() {
        tableCitas.getItems().setAll(pacienteService.contarPacientes() > 0 ? pacienteService.getPacientes() : java.util.Collections.emptyList());
    }

    @FXML
    void addPaciente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/edu/parcial3consultoriomedico/addPaciente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Agregar Paciente");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarPacientesTabla();
        } catch (IOException e) {
            e.printStackTrace();
            NavigationUtil.showWarning("No se pudo abrir la ventana de agregar paciente.");
        }
    }

    @FXML
    void deletePaciente(ActionEvent event) {
        Paciente paciente = tableCitas.getSelectionModel().getSelectedItem();
        if (paciente == null) {
            NavigationUtil.showWarning("Seleccione un paciente!");
            return;
        }

        pacienteService.eliminarPaciente(paciente.getId());
        NavigationUtil.showInfo("Paciente eliminado con exito!");
        cargarPacientesTabla();
    }

    @FXML
    void deseleccionar(ActionEvent event) {
        tableCitas.getSelectionModel().clearSelection();
    }

    @FXML
    void showDetails(ActionEvent event) {
        Paciente paciente = tableCitas.getSelectionModel().getSelectedItem();
        if (paciente == null) {
            NavigationUtil.showWarning("Seleccione un paciente!");
            return;
        }

        NavigationUtil.showInfo(
                "ID: " + paciente.getId() +
                        "\nNombre: " + paciente.getName() +
                        "\nTeléfono: " + paciente.getPhone() +
                        "\nCorreo: " + paciente.getCorreo() +
                        "\nCita asignada: " + (paciente.isCitaAsignada() ? "Sí" : "No")
        );
    }
}
