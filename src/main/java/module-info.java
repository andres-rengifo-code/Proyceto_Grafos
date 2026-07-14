
module practica1.demo {

    requires javafx.controls;
    requires javafx.fxml;

    opens controllers to javafx.fxml;

    exports app;
    exports controllers;
    exports models;
}

