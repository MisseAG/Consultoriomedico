module com.uniquindio.edu.parcial3consultoriomedico {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.uniquindio.edu.parcial3consultoriomedico to javafx.fxml;
    opens com.uniquindio.edu.parcial3consultoriomedico.controllers to javafx.fxml;
    opens com.uniquindio.edu.parcial3consultoriomedico.models to javafx.fxml;
    opens com.uniquindio.edu.parcial3consultoriomedico.repositories to javafx.fxml;

    exports com.uniquindio.edu.parcial3consultoriomedico;
    exports com.uniquindio.edu.parcial3consultoriomedico.controllers;
    exports com.uniquindio.edu.parcial3consultoriomedico.models;
    exports com.uniquindio.edu.parcial3consultoriomedico.repositories;

}
