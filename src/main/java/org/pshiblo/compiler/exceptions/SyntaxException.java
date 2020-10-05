package org.pshiblo.compiler.exceptions;

public class SyntaxException extends Exception {
    public SyntaxException(String line) {
        super("Error syntax analysis in \"" + line + "\"");
    }
}
