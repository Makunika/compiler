package org.pshiblo.UI.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import org.pshiblo.UI.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}