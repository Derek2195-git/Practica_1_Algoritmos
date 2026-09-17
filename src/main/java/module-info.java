module com.example.practica1algoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.example.practica1algoritmos to javafx.fxml;
    exports com.example.practica1algoritmos;
}