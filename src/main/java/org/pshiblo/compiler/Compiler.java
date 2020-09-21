package org.pshiblo.compiler;

import org.pshiblo.compiler.lexis.Exceptions.MatcherCompileException;
import org.pshiblo.compiler.lexis.Lexis;
import org.pshiblo.compiler.lexis.LexisResult;

public class Compiler {

    public static void main(String[] args) {
        //Lexis.analysisExpression("zat=10.0-20-40+10+10.0;");
        //Lexis.analysisExpression("x;");
        try {
            Lexis.analysisDoWhile(
                    "do\n{\nn=x+1;\na=g+s;\n} while(a >= b)\n"
            );
        } catch (MatcherCompileException e) {
            e.printStackTrace();
        }

    }
}
