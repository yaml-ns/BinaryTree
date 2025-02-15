package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Classe représentant un arbre AVL pour stocker des éléments de type générique T.
 *
 * @param <T> Le type des éléments stockés dans l'arbre AVL.
 */
public class ArbreAVL<T> {
    private Node<T> root;
    private Comparator<T> comparator;

    /**
     * Constructeur pour créer un arbre AVL avec un comparateur donné.
     *
     * @param comparator Le comparateur pour ordonner les éléments de l'arbre.
     */
    public ArbreAVL(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Insère un élément dans l'arbre AVL.
     *
     * @param key L'élément à insérer.
     */
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

    /**
     * Retourne la liste des éléments de l'arbre AVL en ordre croissant.
     *
     * @return Liste des éléments en ordre croissant.
     */
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

    /**
     * Recherche des éléments dans l'arbre AVL selon un critère donné.
     *
     * @param critereExtractor Fonction pour extraire le critère de chaque élément.
     * @param critere Valeur du critère recherché.
     * @return Liste des éléments correspondant au critère.
     */
    public List<T> rechercherParCritere(Function<T, ?> critereExtractor, Object critere) {
        List<T> result = new ArrayList<>();
        rechercherParCritereRec(root, critereExtractor, critere, result);
        return result;
    }

    private void rechercherParCritereRec(Node<T> node, Function<T, ?> critereExtractor, Object critere, List<T> result) {
        if (node != null) {
            rechercherParCritereRec(node.getLeft(), critereExtractor, critere, result);
            for (T key : node.getKeys()) {
                if (critereExtractor.apply(key).equals(critere)) {
                    result.add(key);
                }
            }
            rechercherParCritereRec(node.getRight(), critereExtractor, critere, result);
        }
    }
}
