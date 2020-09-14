package org.pshiblo.compiler.lexis;

import java.util.ArrayList;
import java.util.List;

public class LexisResult {

    private final List<RowTableName> tableNames;
    private final List<Lexeme> lexemes;
    private int count;

    public LexisResult() {
        this.tableNames = new ArrayList<>();
        this.lexemes = new ArrayList<>();
        count = 0;
    }

    public void addNextLexeme(Lexeme lexeme, About about) {
        Lexeme findLexeme = lexemes.stream()
                .filter(lexeme1 -> lexeme1.getLexeme().equals(lexeme.getLexeme()))
                .findFirst()
                .orElse(null);
        if (findLexeme != null) {
            lexeme.setPointer(findLexeme.getPointer());
        } else {
            if (!lexeme.isSign()) {
                lexeme.setPointer(count);
                tableNames.add(new RowTableName(lexeme.getLexeme(), count, about.getVal()));
                count++;
            }
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
}
