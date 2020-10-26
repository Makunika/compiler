package org.pshiblo.compiler.syntax.tree;

class Node<T> {
    private T value;
    private Node<T> right;
    private Node<T> left;
    private Node<T> parent;

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public Node() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
