package org.pshiblo.compiler.syntax.tree;

public class Tree<T> {

    private final Node<T> root;
    private Node<T> current;


    public Tree() {
        root = new Node<>();
        current = root;
    }

    public void insertLeft(T value) {
        current.setLeft(new Node<>(value, current));
        current = current.getLeft();
    }

    public void insertRight(T value) {
        current.setRight(new Node<>(value, current));
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

    public Node<T> getRoot() {
        return root;
    }

    public Node<T> getCurrent() {
        return current;
    }

    public void setRootValue(T value) {
        root.setValue(value);
    }
}


