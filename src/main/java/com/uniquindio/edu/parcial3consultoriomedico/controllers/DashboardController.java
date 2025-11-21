package com.uniquindio.edu.parcial3consultoriomedico.controllers;

import com.uniquindio.edu.parcial3consultoriomedico.models.util.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DashboardController {

    @FXML
    private Button btnCitas;

    @FXML
    private Button btnDoctores;

    @FXML
    private Button btnPaciente;

    @FXML
    private Button btnSlideMenu;

    @FXML
    private Label lblGestion;

    @FXML
    private AnchorPane sideMenu;

    @FXML
    private AnchorPane mainContent;

    private boolean isMenuOpen = true;

    @FXML
    void goToCitas(ActionEvent event) {
        lblGestion.setText("GESTIÓN DE CITAS MÉDICAS");
        NavigationUtil.replaceMainContent(mainContent, "citas.fxml");
    }

    @FXML
    void goToDoctores(ActionEvent event) {
        lblGestion.setText("GESTIÓN DE MÉDICOS");
        NavigationUtil.replaceMainContent(mainContent, "doctor.fxml");
    }

    @FXML
    void goToPaciente(ActionEvent event) {
        lblGestion.setText("GESTIÓN DE PACIENTES");
        NavigationUtil.replaceMainContent(mainContent, "paciente.fxml");
    }

    @FXML
    void slideMenu(ActionEvent event) {
        BorderPane root = (BorderPane) mainContent.getScene().getRoot();

        if (isMenuOpen) {
            root.setLeft(null);
        } else {
            root.setLeft(sideMenu);
        }

        isMenuOpen = !isMenuOpen;
    }

}
