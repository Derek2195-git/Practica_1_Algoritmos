module com.example.practica1algoritmos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practica1algoritmos to javafx.fxml;
    exports com.example.practica1algoritmos;
}