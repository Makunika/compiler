package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.lexis.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    private List<Lexeme> expression;

    private Tree tree;

    public Expression(List<Lexeme> expression) {
        this.expression = expression;
        tree = new Tree();
    }

    public Expression() {
        expression = new ArrayList<>();
        tree = new Tree();
    }

    public Tree getTree() {
        return tree;
    }

    public List<Lexeme> getList() {
        return expression;
    }

    public void setExpression(List<Lexeme> expression) {
        this.expression = expression;
    }

    public void add(Lexeme value) {
        expression.add(value);
    }

    public int size() {
        return expression.size();
    }

    public Lexeme get(int i) {
        return expression.get(i);
    }
}
