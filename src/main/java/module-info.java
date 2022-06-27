module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    opens User to javafx.fxml;
    exports User;
    exports com.example.demo;
}