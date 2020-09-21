package org.pshiblo.UI.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.pshiblo.compiler.lexis.Exceptions.MatcherCompileException;
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

    @FXML
    private TableView<RowTableName> tableDoWhile;

    @FXML
    private TableColumn<RowTableName, String> columnNumber1;

    @FXML
    private TableColumn<RowTableName, String> columnId1;

    @FXML
    private TableColumn<RowTableName, String> columnAbout1;

    @FXML
    private Button buttonAnalysisDoWhile;

    @FXML
    private TextArea textAreaInputDoWhile;

    @FXML
    private TextArea textAreaLexemesDoWhile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnNumber.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber()))
        );
        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnAbout.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAbout()));
        columnNumber1.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getNumber()))
        );
        columnId1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columnAbout1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAbout()));




        buttonAnalysis.setOnAction(this::clickAnalysis);

        buttonAnalysisDoWhile.setOnAction(this::clickAnalysisDoWhile);
    }

    private void clickAnalysis(ActionEvent e) {
        if (!textBoxInput.getText().isBlank()) {
            try {
                LexisResult lexisResult = null;
                lexisResult = Lexis.analysisExpression(textBoxInput.getText());
                textBoxLexemes.setText(lexisResult.getLexemesString());
                table.setItems(FXCollections.observableList(lexisResult.getTableNames()));
            } catch (MatcherCompileException matcherCompileException) {
                matcherCompileException.printStackTrace();
                textBoxLexemes.setText(matcherCompileException.getMessage());
            }
        }
    }

    private void clickAnalysisDoWhile(ActionEvent e) {
        if (!textAreaInputDoWhile.getText().isBlank()) {
            try {
                LexisResult lexisResult = Lexis.analysisDoWhile(textAreaInputDoWhile.getText());
                textAreaLexemesDoWhile.setText(lexisResult.getLexemesString());
                tableDoWhile.setItems(FXCollections.observableList(lexisResult.getTableNames()));
            } catch (MatcherCompileException matcherCompileException) {
                matcherCompileException.printStackTrace();
                textAreaLexemesDoWhile.setText(matcherCompileException.getMessage());
            }
        }
    }
}
