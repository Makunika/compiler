package org.pshiblo.compiler.lexis;

public class LexemeString {

    private String lexeme;


    public LexemeString(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return getLexeme();
    }
}
