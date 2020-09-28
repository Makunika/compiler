package org.pshiblo.compiler.lexis;

import org.pshiblo.compiler.hash.Hashable;

public class LexemeHash implements Hashable {

    private final Lexeme lexeme;

    private String about;

    public LexemeHash(Lexeme lexeme, String about) {
        this.lexeme = lexeme;
        this.about = about;
    }

    public Lexeme getLexeme() {
        return lexeme;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String getStringElement() {
        return lexeme.getLexeme();
    }
}
