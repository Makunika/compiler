package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.lexis.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    private List<Lexeme> expression;

    public Expression(List<Lexeme> expression) {
        this.expression = expression;
    }

    public Expression() {
        expression = new ArrayList<>();
    }

    public List<Lexeme> getExpression() {
        return expression;
    }

    public void setExpression(List<Lexeme> expression) {
        this.expression = expression;
    }

    public void add(Lexeme value) {
        expression.add(value);
    }
}
