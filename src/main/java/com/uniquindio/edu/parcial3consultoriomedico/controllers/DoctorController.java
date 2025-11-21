package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.Medico;
import com.uniquindio.edu.parcial3consultoriomedico.repositories.Database;
import com.uniquindio.edu.parcial3consultoriomedico.services.MedicoService;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorController {

    @FXML
    private TableView<Medico> tableCitas;

    @FXML
    private TableColumn<Medico, String> colID;

    @FXML
    private TableColumn<Medico, String> colDoctor;

    @FXML
    private TableColumn<Medico, String> colTelefono;

    @FXML
    private TableColumn<Medico, String> colCorreo;

    @FXML
    private TableColumn<Medico, String> colEspecialidad;

    private final MedicoService medicoService = MedicoService.getInstance();

    private Database db = Database.getInstance();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarMedicosTabla();
    }

    private void configurarColumnas() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDoctor.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
    }

    private void cargarMedicosTabla() {
        tableCitas.getItems().setAll(db.getMedicos());
    }

    @FXML
    void addDoctor(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/edu/parcial3consultoriomedico/addDoctor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Agregar Médico");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


            cargarMedicosTabla();

        } catch (IOException e) {
            e.printStackTrace();
            NavigationUtil.showWarning("No se pudo abrir la ventana de agregar médico.");
        }
    }

    @FXML
    void deleteDoctor(ActionEvent event) {
        Medico medico = tableCitas.getSelectionModel().getSelectedItem();
        if (medico == null) {
            NavigationUtil.showWarning("Seleccione un médico!");
            return;
        }

        medicoService.eliminarMedico(medico.getId());
        NavigationUtil.showInfo("Eliminado correctamente.");
        cargarMedicosTabla();
    }

    @FXML
    void deseleccionar(ActionEvent event) {
        tableCitas.getSelectionModel().clearSelection();
    }

    @FXML
    void showDetails(ActionEvent event) {
        Medico medico = tableCitas.getSelectionModel().getSelectedItem();
        if (medico == null) {
            NavigationUtil.showWarning("Seleccione un médico!");
            return;
        }

        NavigationUtil.showInfo(
                "ID: " + medico.getId() +
                        "\nNombre: " + medico.getName() +
                        "\nTeléfono: " + medico.getPhone() +
                        "\nCorreo: " + medico.getCorreo() +
                        "\nEspecialidad: " + medico.getEspecialidad()
        );
    }
}
