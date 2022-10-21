module com.example.paintgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens com.example.paintgui to javafx.fxml;
    exports com.example.paintgui;
}