module com.example.practica1algoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires javafx.graphics;


    opens com.example.practica1algoritmos to javafx.fxml;
    exports com.example.practica1algoritmos;
}