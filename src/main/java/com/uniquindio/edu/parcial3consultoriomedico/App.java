package com.uniquindio.edu.parcial3consultoriomedico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/uniquindio/edu/parcial3consultoriomedico/dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Gestión de citas médicas");
        stage.setScene(scene);
        stage.show();
    }
}
