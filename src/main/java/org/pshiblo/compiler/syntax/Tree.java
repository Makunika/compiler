package org.pshiblo.compiler.syntax;

import org.pshiblo.compiler.lexis.Lexeme;

import java.util.List;

public class Tree {

    private Node root;
    private Node current;


    public Tree() {
        root = new Node();
        current = root;
    }

    public void insertLeft(Lexeme value) {
        current.setLeft(new Node(value, current));
        current = current.getLeft();
    }

    public void insertRight(Lexeme value) {
        current.setRight(new Node(value, current));
        current = current.getRight();
    }

    public void toParent() {
        if (current.getParent() != null) {
            current = current.getParent();
        }
    }

    public void toRight() {
        if (current.getRight() == null) {
            throw new IndexOutOfBoundsException();
        }
        current = current.getRight();
    }

    public void toLeft() {
        if (current.getLeft() == null) {
            throw new IndexOutOfBoundsException();
        }
        current = current.getLeft();
    }

    public Node getRoot() {
        return root;
    }

    public Node getCurrent() {
        return current;
    }

    public void setRootValue(Lexeme value) {
        root.setValue(value);
    }

    public void insertToNullLeft(Lexeme value) {
        if (current.getLeft() == null) {
            insertLeft(value);
            toParent();
        } else {
            current.setRight(new Node(null, current));
            current = current.getRight();
            insertLeft(value);
            toParent();

        }
    }

    public void setCurrentValue(Lexeme value) {
        current.setValue(value);
    }

    public void setRoot(Node root) {
        this.root = root;
    }


    public Tree getTreeForExp(List<Lexeme> expression) {
        root = new Node();
        listToTree(expression, root);
        return this;
    }


    private void listToTree(List<Lexeme> lexemes, Node current) {
        if (lexemes.size() == 1) {
            current.setValue(lexemes.get(0));
        } else {
            int indexLowPriority = 0;
            int currentLowPriority = 100;
            int currentPriority = 0;
            for (int i = 0; i < lexemes.size(); i++) {
                Lexeme lexeme = lexemes.get(i);
                if (lexeme.getLexeme().equals("(")) {
                    currentPriority++;
                    continue;
                }
                if (lexeme.getLexeme().equals(")")) {
                    currentPriority--;
                    continue;
                }
                if (lexeme.isSign() || lexeme.isOperator()) {
                    int pr = getPriorityForLexeme(lexeme, currentPriority);
                    if (pr <= currentLowPriority) {
                        currentLowPriority = pr;
                        indexLowPriority = i;
                    }
                }
            }
            if (lexemes.get(0).getLexeme().equals("(") && lexemes.get(lexemes.size() - 1).getLexeme().equals(")")) {
                lexemes = lexemes.subList(1, lexemes.size() - 1);
                indexLowPriority--;
            }
            current.setValue(lexemes.get(indexLowPriority));
            Node left = new Node();
            left.setParent(current);
            listToTree(lexemes.subList(0, indexLowPriority), left);
            Node right = new Node();
            right.setParent(current);
            listToTree(lexemes.subList(indexLowPriority + 1, lexemes.size()), right);
            current.setLeft(left);
            current.setRight(right);
        }

    }


    private int getPriorityForLexeme(Lexeme lexeme, int currentPriority) {
        if (lexeme.getLexeme().equals("=")){
            return 0;
        } else if (lexeme.getLexeme().equals("*") || lexeme.getLexeme().equals("/")) {
            return 2 + currentPriority;
        } else {
            return 1 + currentPriority;
        }
    }
}


