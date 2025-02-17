module com.example.boxingarena {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.opencsv;
    requires org.apache.commons.lang3;

    exports com.example.boxingarena;
    opens com.example.boxingarena to javafx.fxml;
    exports com.example.boxingarena.bean;
    opens com.example.boxingarena.bean to javafx.fxml;
    exports com.example.boxingarena.graphic_controller to javafx.fxml;
    opens com.example.boxingarena.graphic_controller to javafx.fxml;
}