module com.example.topicos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.topicos to javafx.fxml;
    exports com.example.topicos;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    opens modelos;
}