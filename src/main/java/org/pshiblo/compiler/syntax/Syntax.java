package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.exceptions.MatcherCompileException;
import org.pshiblo.compiler.exceptions.SyntaxException;
import org.pshiblo.compiler.lexis.Lexeme;
import org.pshiblo.compiler.lexis.LexisResult;
import org.pshiblo.compiler.syntax.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Syntax {

    private LexisResult lexisResult;

    public Syntax(LexisResult lexisResult) {
        this.lexisResult = lexisResult;
    }

    public void syntaxAnalysisDoWhile() throws SyntaxException {
        //{}
        Tree<CodeBlock> rootTree = new Tree<>();

        CodeBlock codeBlockExpr = new CodeBlock();
        CodeBlock codeBlockDo = new CodeBlock();
        CodeBlock codeBlockWhile = new CodeBlock();
        boolean go = false;
        int i = -1;
        //do{.....}while
        List<Lexeme> lexemes = lexisResult.getLexemes();
        for (int j = 0; j < lexemes.size(); j++) {
            Lexeme lexeme = lexemes.get(j);
            if (go && lexeme.getLexeme().equals("}")) {
                go = false;
                codeBlockWhile = analysisBoolean(lexemes.subList(j + 1, lexemes.size() - 1));
                rootTree.setRootValue(codeBlockExpr);
                rootTree.insertLeft(codeBlockDo);
                rootTree.insertRight(codeBlockWhile);
                break;
            }
            if (go) {
                codeBlockExpr.get(i).add(lexeme);
            }
            if (lexeme.getLexeme().equals("{")) {
                go = true;
                i++;
                codeBlockExpr.addNewExpression();
                codeBlockDo.getExpressions().add(new Expression());
                for (int k = 0; k < j; k++) {
                    codeBlockDo.getFirst().add(lexeme);
                }
            }
        }

        //n=x+a; in {....}
        i = 0;
        for (Expression expression : codeBlockExpr.getExpressions()) {
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

    private void syntaxAnalysisExpression(List<Lexeme> lexemes) throws SyntaxException {
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

    private CodeBlock analysisBoolean(List<Lexeme> lexemes) {

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
