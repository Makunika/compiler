package org.pshiblo.compiler;

import org.pshiblo.compiler.lexis.Lexeme;
import org.pshiblo.compiler.lexis.Lexis;

public class Compiler {

    public static void main(String[] args) {
        Lexis.analysisInput("zat=10.0-20-40+10+10.0;");
        Lexis.analysisInput("x;");

    }
}
