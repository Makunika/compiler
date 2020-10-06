package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.exceptions.MatcherCompileException;
import org.pshiblo.compiler.exceptions.SyntaxException;
import org.pshiblo.compiler.lexis.Lexeme;
import org.pshiblo.compiler.lexis.LexisResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Syntax {

    private LexisResult lexisResult;

    public Syntax(LexisResult lexisResult) {
        this.lexisResult = lexisResult;
    }

    public void syntaxAnalysisDoWhile() throws SyntaxException {
        List<List<Lexeme>> doWhiles = new ArrayList<>();
        boolean go = false;
        int i = -1;
        //do{.....}while
        for (Lexeme lexeme : lexisResult.getLexemes()) {
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
        for (List<Lexeme> doWhile : doWhiles) {
            List<List<Lexeme>> expressions = new ArrayList<>();
            expressions.add(new ArrayList<>());
            for (Lexeme lexeme : doWhile) {
               expressions.get(i).add(lexeme);
                if (lexeme.getLexeme().equals(";")) {
                   expressions.add(new ArrayList<>());
                   i++;
               }
            }
            for (List<Lexeme> expression : expressions) {
                if (expression.size() != 0)
                    syntaxAnalysisExpression(expression);
            }
        }
    }

    public void syntaxAnalysisExpression() throws SyntaxException {
    }


    private void syntaxAnalysisExpression(List<Lexeme> lexemes) throws SyntaxException {
        System.out.println("123");


        int scob = 0;
        for (int i = 0; i < lexemes.size(); i++) {
            if (lexemes.get(i).isOperator()) {
                if (i == 0 || i == lexemes.size() - 1) {
                    throw new SyntaxException(lexemes.get(i).getLexeme(), lexemesToString(lexemes));
                } else if (i == 1 && lexemes.get(i).getLexeme().equals(";")) {
                    if (lexemes.size() != 2)
                        throw new SyntaxException(lexemes.get(i).getLexeme(), lexemesToString(lexemes));
                } else {
                    if (lexemes.get(i).getLexeme().equals("=")){
                        if (leftIsOperator(lexemes, i))
                            throw new SyntaxException(lexemes.get(i).getLexeme(), lexemesToString(lexemes));
                    }
                    if (lexemes.get(i).isOperator()) {
                        if (!lexemes.get(i-1).isVal() && !lexemes.get(i-1).isNumber())
                            throw new SyntaxException(lexemes.get(i-1).getLexeme() + lexemes.get(i).getLexeme(),
                                    lexemesToString(lexemes));
                        if (!lexemes.get(i+1).isVal() && !lexemes.get(i+1).isNumber())
                            throw new SyntaxException(lexemes.get(i).getLexeme() + lexemes.get(i+1).getLexeme(),
                                    lexemesToString(lexemes));
                    }
                    else if (lexemes.get(i).getLexeme().equals("(")) {
                        scob++;
                    }
                    else if (lexemes.get(i).getLexeme().equals(")")) {
                        scob--;
                    }
                    else if (lexemes.get(i).isNumber() || lexemes.get(i).isVal()) {
                        if (!lexemes.get(i-1).isOperator())
                            throw new SyntaxException(lexemes.get(i-1).getLexeme() + lexemes.get(i).getLexeme(),
                                    lexemesToString(lexemes));
                        if (!lexemes.get(i+1).isOperator())
                            throw new SyntaxException(lexemes.get(i).getLexeme() + lexemes.get(i+1).getLexeme(),
                                    lexemesToString(lexemes));
                    }
                }
            }
        }

        if (scob != 0) {
            throw new SyntaxException("()",  lexemesToString(lexemes));
        }
    }

    private String lexemesToString(List<Lexeme> lexemes) {
        StringBuilder sb = new StringBuilder();
        lexemes.forEach(lexeme -> sb.append(lexeme.getLexeme()));
        return sb.toString();
    }

    private boolean leftIsOperator(List<Lexeme> lexemes, int i) {
        for (int j = i; j > 0; j--) {
            if (lexemes.get(j).isOperator() && !lexemes.get(j).getLexeme().equals("=")) {
                return true;
            }
        }
        return false;
    }



}
