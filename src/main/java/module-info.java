module com.example.model_practic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.model_practic to javafx.fxml;
    exports com.example.model_practic;

    opens com.example.model_practic.domain to javafx.fxml;
    exports com.example.model_practic.domain;
}