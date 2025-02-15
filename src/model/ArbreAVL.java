package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArbreAVL<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    public ArbreAVL(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insererNode(T key) {
        root = insertRec(root, key);
    }

    private Node<T> insertRec(Node<T> node, T key) {
        if (node == null) {
            return new Node<>(key);
        }

        if (comparator.compare(key, node.getKeys().get(0)) == 0) {
            node.addKey(key);
        } else if (comparator.compare(key, node.getKeys().get(0)) < 0) {
            node.setLeft(insertRec(node.getLeft(), key));
        } else {
            node.setRight(insertRec(node.getRight(), key));
        }

        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if (node == null) return null;

        updateHeight(node);
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }

        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        return node;
    }

    private int getBalanceFactor(Node<T> node) {
        return node == null ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    private void updateHeight(Node<T> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        }
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.getRight();
        Node<T> T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    public List<T> parcoursInOrdre() {
        List<T> result = new ArrayList<>();
        parcoursInOrdreRec(root, result);
        return result;
    }

    private void parcoursInOrdreRec(Node<T> node, List<T> result) {
        if (node != null) {
            parcoursInOrdreRec(node.getLeft(), result);
            result.addAll(node.getKeys());
            parcoursInOrdreRec(node.getRight(), result);
        }
    }

    public List<T> rechercherParCritere(Object critere) {
        List<T> result = new ArrayList<>();
        rechercherParCritereRec(root, critere, result);
        return result;
    }

    private void rechercherParCritereRec(Node<T> node, Object critere, List<T> result) {
        if (node != null) {
            rechercherParCritereRec(node.getLeft(), critere, result);
            for (T key : node.getKeys()) {
                if (critere.equals(key)) {
                    result.add(key);
                }
            }
            rechercherParCritereRec(node.getRight(), critere, result);
        }
    }
}
