module core {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens core.$__legacyfiles to javafx.fxml;
    opens core.view to javafx.fxml;
    opens core.controller to javafx.fxml;
    opens core.service to javafx.fxml;
    opens core.model to javafx.fxml;

    exports core.$__legacyfiles;
    exports core.controller;
    exports core.model;
    exports core.model.dto;
    exports core.view;
    exports core.service;
    exports core;
}