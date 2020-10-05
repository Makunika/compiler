package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.exceptions.MatcherCompileException;
import org.pshiblo.compiler.exceptions.SyntaxException;
import org.pshiblo.compiler.lexis.Lexeme;
import org.pshiblo.compiler.lexis.LexemeString;
import org.pshiblo.compiler.lexis.LexisResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Syntax {

    public static void syntaxAnalysisDoWhile(LexisResult lexisResult) throws SyntaxException {
        List<List<LexemeString>> doWhiles = new ArrayList<>();
        boolean go = false;
        int i = -1;
        //do{.....}while
        for (LexemeString lexeme : lexisResult.getLexemes()) {
            if (go && lexeme.getLexeme().equals("}")) {
                go = false;
            }
            if (go) {
                doWhiles.get(i).add(lexeme);
            }
            if (lexeme.getLexeme().equals("{")) {
                go = true;
                i++;
                doWhiles.add(new ArrayList<>());
            }
        }

        //n=x+a; in {....}
        i = 0;
        for (List<LexemeString> doWhile : doWhiles) {
            List<List<LexemeString>> expressions = new ArrayList<>();
            expressions.add(new ArrayList<>());
            for (LexemeString lexeme : doWhile) {
               expressions.get(i).add(lexeme);
                if (lexeme.getLexeme().equals(";")) {
                   expressions.add(new ArrayList<>());
                   i++;
               }
            }
            for (List<LexemeString> expression : expressions) {
                if (expression.size() != 0)
                    syntaxAnalysisExpression(expression);
            }
        }
    }

    public static void syntaxAnalysisExpression(LexisResult lexisResult) throws SyntaxException {
    }


    public static void syntaxAnalysisExpression(List<LexemeString> lexemes) throws SyntaxException {
        System.out.println("123");
        lexemes.forEach(lexemeString ->
            System.out.print(lexemeString.getLexeme()));
        System.out.println();
    }



}
