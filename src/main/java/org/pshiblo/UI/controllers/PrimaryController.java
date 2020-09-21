package org.pshiblo.UI.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.pshiblo.UI.App;
import org.pshiblo.compiler.lexis.Lexis;
import org.pshiblo.compiler.lexis.LexisResult;
import org.pshiblo.compiler.lexis.RowTableName;

public class PrimaryController implements Initializable {

    @FXML
    private TextField textBoxInput;

    @FXML
    private TextField textBoxLexemes;

    @FXML
    private TableView<RowTableName> table;

    @FXML
    private TableColumn<RowTableName, String> columnNumber;

    @FXML
    private TableColumn<RowTableName, String> columnId;

    @FXML
    private TableColumn<RowTableName, String> columnAbout;

    @FXML
    private Button buttonAnalysis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNumber.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber()))
        );
        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnAbout.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAbout()));
        buttonAnalysis.setOnAction(this::clickAnalysis);
    }

    private void clickAnalysis(ActionEvent e) {
        if (!textBoxInput.getText().isBlank()) {
            LexisResult lexisResult = Lexis.analysisInput(textBoxInput.getText());
            textBoxLexemes.setText(lexisResult.getLexemesString());
            table.setItems(FXCollections.observableList(lexisResult.getTableNames()));
        }
    }
}
