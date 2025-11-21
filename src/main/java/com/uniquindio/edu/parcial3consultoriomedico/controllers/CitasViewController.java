package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.Cita;
import com.uniquindio.edu.parcial3consultoriomedico.services.CitaService;
import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CitasViewController {

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDeseleccionar;

    @FXML
    private Button btnDetails;

    @FXML
    private TableColumn<Cita, String> colEstado;

    @FXML
    private TableColumn<Cita, String> colID;

    @FXML
    private TableColumn<Cita, String> colMedico;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableView<Cita> tableCitas;

    private final CitaService citaService = CitaService.getInstance();


    @FXML
    public void initialize() {
        configurarColumnas();
        cargarCitasTabla();
    }

    private void configurarColumnas() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEstado.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getEstado().getName())
        );
        colPaciente.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getPaciente().getName())
        );
        colMedico.setCellValueFactory(c ->
                new javafx.beans.property.SimpleStringProperty(c.getValue().getMedico().getName())
        );
    }

    private void cargarCitasTabla() {
        tableCitas.getItems().setAll(citaService.getAllCitas());
    }


    @FXML
    void addCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/edu/parcial3consultoriomedico/addCita.fxml"));
            Parent root = loader.load();

            AddCitaController controller = loader.getController();
            controller.setCitasViewController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nueva Cita");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refrescarTabla() {
        cargarCitasTabla();
    }

    @FXML
    void deseleccionar(ActionEvent event) {
        tableCitas.getSelectionModel().clearSelection();
    }

    @FXML
    void showDetails(ActionEvent event) {

        Cita cita = tableCitas.getSelectionModel().getSelectedItem();

        if (cita == null) {
            NavigationUtil.showWarning("Seleccione una cita!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/edu/parcial3consultoriomedico/citasDetailViews.fxml"));
            Parent root = loader.load();

            CitaDetailsController controller = loader.getController();
            controller.setCita(cita);

            Stage stage = new Stage();
            stage.setTitle("Detalles de la Cita");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
