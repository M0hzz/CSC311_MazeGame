module com.example.csc311_mazegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311_mazegame to javafx.fxml;
    exports com.example.csc311_mazegame;
}