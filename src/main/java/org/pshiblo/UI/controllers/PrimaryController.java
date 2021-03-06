package org.pshiblo.UI.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.pshiblo.compiler.codegenerator.CodeGenerator;
import org.pshiblo.compiler.codegenerator.CodeGeneratorOutput;
import org.pshiblo.compiler.codegenerator.CodeOptimization;
import org.pshiblo.compiler.exceptions.MatcherCompileException;
import org.pshiblo.compiler.lexis.Lexis;
import org.pshiblo.compiler.lexis.LexisResult;
import org.pshiblo.compiler.lexis.LexemeHash;
import org.pshiblo.compiler.syntax.Syntax;
import org.pshiblo.compiler.syntax.SyntaxOutput;


public class PrimaryController implements Initializable {

    @FXML
    private TextField textBoxInput;

    @FXML
    private TextField textBoxLexemes;

    @FXML
    private TableView<LexemeHash> table;

    @FXML
    private TableColumn<LexemeHash, String> columnId;

    @FXML
    private TableColumn<LexemeHash, String> columnAbout;

    @FXML
    private Button buttonAnalysis;

    @FXML
    private TableView<LexemeHash> tableDoWhile;

    @FXML
    private TableColumn<LexemeHash, String> columnId1;

    @FXML
    private TableColumn<LexemeHash, String> columnAbout1;

    @FXML
    private Button buttonAnalysisDoWhile;

    @FXML
    private TextArea textAreaInputDoWhile;

    @FXML
    private TextArea textAreaAfterOptimization;

    @FXML
    private TextArea textAreaBeforeOptimization;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cellData - это объект RowTableName из коллекции.
        //new SimpleStringProperty(cellData.getValue().getId()) - печатает id в этой колонке

        columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStringElement()));
        columnAbout.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAbout()));




        columnId1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStringElement()));
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
                table.setItems(FXCollections.observableList(lexisResult.getHashTableAsList()));
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
                //textAreaLexemesDoWhile.setText(lexisResult.getLexemesString());
                tableDoWhile.setItems(FXCollections.observableList(lexisResult.getHashTableAsList()));
                Syntax syntax = new Syntax(lexisResult);
                SyntaxOutput syntaxOutput = syntax.syntaxAnalysisDoWhile();
                CodeGenerator codeGenerator = new CodeGenerator(syntaxOutput);
                CodeGeneratorOutput codeGeneratorOutput = codeGenerator.generateCodeDoWhile();
                textAreaBeforeOptimization.setText(codeGeneratorOutput.toString());
                CodeOptimization codeOptimization = new CodeOptimization(codeGeneratorOutput);
                String s = codeOptimization.optimization().toString();
                textAreaAfterOptimization.setText(s);

            } catch (Exception ex) {
                ex.printStackTrace();
                textAreaAfterOptimization.setText(ex.getMessage());
            }
        }
    }
}
