package org.pshiblo.compiler.lexis;

import java.util.ArrayList;
import java.util.List;

public class LexisResult {

    private final List<RowTableName> tableNames;
    private final List<Lexeme> lexemes;
    private int count;
    private RowTableName lastVal;

    public LexisResult() {
        this.tableNames = new ArrayList<>();
        this.lexemes = new ArrayList<>();
        count = 0;
    }

    public LexisResult(LexisResult lexisResult) {
        this.tableNames = lexisResult.tableNames;
        this.lexemes = lexisResult.lexemes;
        count = lexisResult.count;
    }

    public void addNextLexeme(Lexeme lexeme, About about) {
        Lexeme findLexeme = lexemes.stream()
                .filter(lexeme1 -> lexeme1.getLexeme().equals(lexeme.getLexeme()))
                .findFirst()
                .orElse(null);
        if (findLexeme != null) {
            lexeme.setPointer(findLexeme.getPointer());
        } else {
            if (lexeme.isSign()) {
                lexeme.setPointer(0);
                tableNames.add(new RowTableName(lexeme.getLexeme(), 0, about.getVal()));
            } else {
                lexeme.setPointer(count);
                tableNames.add(new RowTableName(lexeme.getLexeme(), count, about.getVal()));
                count++;
            }
        }
        if (lexeme.getLexeme().equals("=")) {
            lastVal = tableNames.get(tableNames.size() - 1);
        }
        lexemes.add(lexeme);
    }

    public List<RowTableName> getTableNames() {
        return tableNames;
    }

    public List<Lexeme> getLexemes() {
        return lexemes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void changeLastValue(About about) {
        lastVal.setAbout(about.getVal());
    }

    public String getLexemesString() {
        StringBuilder sb = new StringBuilder();
        for (Lexeme lexeme : lexemes) {
                if (lexeme.isSign()) {
                    sb.append(lexeme.getLexeme());
                } else {
                    sb.append("<lex ").append(lexeme.getPointer()).append(">");
                }
        }
        return sb.toString();
    }
}
