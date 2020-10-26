package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.lexis.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock {
    private List<Expression> expressions;


    public CodeBlock() {
        expressions = new ArrayList<>();
    }

    public CodeBlock(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public Expression getLast() {
        return expressions.get(expressions.size() - 1);
    }

    public Expression get(int index) {
        return expressions.get(index);
    }

    public Expression getFirst() {
        return expressions.get(0);
    }

    public void add(Expression expression) {
        expressions.add(expression);
    }

    public void addNewExpression() {
        expressions.add(new Expression());
    }
}
