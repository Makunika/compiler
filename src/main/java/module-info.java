module org.pshiblo {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.pshiblo.UI to javafx.fxml;
    exports org.pshiblo.UI;
}