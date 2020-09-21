package org.pshiblo.compiler.lexis.Exceptions;

public class MatcherCompileException extends Exception {
    public MatcherCompileException(String line) {
        super("Error parse in \"" + line + "\"");
    }
}
