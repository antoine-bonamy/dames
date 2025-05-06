module fr.bonamy.dame_alliance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.bonamy.dame_alliance to javafx.fxml;
    exports fr.bonamy.dame_alliance;
}