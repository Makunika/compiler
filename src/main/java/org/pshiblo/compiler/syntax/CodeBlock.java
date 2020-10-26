package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.lexis.Lexeme;
import org.pshiblo.compiler.syntax.Expression;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock {
    private Tree tree;
    private Lexeme lexeme;

    public boolean isLexeme() {
        return lexeme == null && tree != null;
    }

    public boolean isTree() {
        return !isLexeme();
    }

    public CodeBlock() {
    }

    public CodeBlock(Tree tree) {
        this.tree = tree;
    }

    public CodeBlock(Lexeme lexeme) {
        this.lexeme = lexeme;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        if (lexeme != null) {
            lexeme = null;
        }
        this.tree = tree;
    }

    public Lexeme getLexeme() {
        return lexeme;
    }

    public void setLexeme(Lexeme lexeme) {
        if (tree != null) {
            tree = null;
        }
        this.lexeme = lexeme;
    }
}
