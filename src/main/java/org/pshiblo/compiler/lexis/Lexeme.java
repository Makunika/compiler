package org.pshiblo.compiler.lexis;

import org.pshiblo.compiler.hash.Hashable;

import java.io.IOException;

public class Lexeme {

    private final boolean isVal;

    private final boolean isSign;

    private final boolean isNumber;

    private final String lexeme;

    private Lexeme(String lexeme, boolean isSign, boolean isNumber, boolean isVal) {
        this.isVal = isVal;
        this.isSign = isSign;
        this.isNumber = isNumber;
        this.lexeme = lexeme;
    }


    public static Lexeme sign(String lexeme) {
        return new Lexeme(lexeme, true,false, false);
    }

    public static Lexeme number(String lexeme) {
        return new Lexeme(lexeme, false, true, false);
    }

    public static Lexeme val(String lexeme) {
        return new Lexeme(lexeme, false, false, true);
    }

    public String getLexeme() {
        return lexeme;
    }

    public boolean isSign() {
        return isSign;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isVal() {
        return isVal;
    }

    @Override
    public String toString() {
        return "< " + lexeme + " >";
    }
}
