module c482im.c482_ianmitchum {
    requires javafx.controls;
    requires javafx.fxml;


    opens Model.c482_ianmitchum to javafx.fxml;
    exports Model.c482_ianmitchum;
    exports Controller;
    opens Controller to javafx.fxml;
}