package org.pshiblo.compiler.lexis;

public class Lexeme {

    private int pointer;

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

    public static Lexeme number(int lexeme) {
        return new Lexeme(String.valueOf(lexeme), false, true, false);
    }

    public static Lexeme val(String lexeme) {
        return new Lexeme(lexeme, false, false, true);
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getPointer() {
        return pointer;
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
        return String.valueOf(pointer) + " : " + lexeme;
    }
}
