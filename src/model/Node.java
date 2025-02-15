package model;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private List<T> keys;
    private Node<T> left, right;
    private int height;

    public Node(T key) {
        keys = new ArrayList<>();
        keys.add(key);
        left = right = null;
        height = 1;
    }

    public List<T> getKeys() {
        return keys;
    }

    public void addKey(T key) {
        keys.add(key);
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
