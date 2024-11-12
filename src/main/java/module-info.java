module com.example.marveljavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.net.http;
    requires org.json;

    opens com.example.marveljavafx to javafx.fxml;
    exports com.example.marveljavafx;
}