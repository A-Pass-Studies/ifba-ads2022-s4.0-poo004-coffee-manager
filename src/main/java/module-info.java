module br.com.arthur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens br.com.arthur.javafx to javafx.fxml;
    exports br.com.arthur.javafx;
}