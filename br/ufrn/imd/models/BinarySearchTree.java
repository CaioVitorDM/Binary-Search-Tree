package br.ufrn.imd.models;

import java.awt.*;
import java.util.ArrayList;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null; // Inicializa a árvore como vazia
    }

    public void insert(int value) {
        if(!search(value)){
            root = insertRecursive(root, value);
        }
        else {
            System.out.println(value + " insert error, value already exists.");
        }
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), value));
            current.setSizeLeft(current.getSizeLeft() + 1); // Atualiza o tamanho da subárvore à esquerda
        } else if (value > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), value));
            current.setSizeRight(current.getSizeRight() + 1); // Atualiza o tamanho da subárvore à direita
        }
        return current;
    }

    public void remove(int value) {
        if(search(value)){
            root = removeRecursive(root, value);
        }
        else {
            System.out.println(value + " remove error, value not found.");
        }
    }

    private Node removeRecursive(Node current, int value) {
        if (current == null) {
            return current;
        }

        //Se o valor a ser removido é menor que o valor do nó atual, chama a função recursivamente para a subárvore à esquerda
        if (value < current.getValue()) {
            current.setLeft(removeRecursive(current.getLeft(), value));
            current.setSizeLeft(current.getLeft() != null ? current.getLeft().getSizeLeft() : 0);
        }

        //Se o valor a ser removido é maior que o valor do nó atual, chama a função recursivamente para a subárvore à direita.
        else if (value > current.getValue()) {
            current.setRight(removeRecursive(current.getRight(), value));
            current.setSizeRight(current.getRight() != null ? current.getRight().getSizeRight() : 0);
        }
        else {
            //Se o nó atual não possui filhos à esquerda, substitui pelo nó à direita
            if (current.getLeft() == null) {
                return current.getRight();
            }
            //Se o nó atual não possui filhos à direita, substitui pelo nó à esquerda.
            else if (current.getRight() == null) {
                return current.getLeft();
            }

            // Para um nó com dois filhos, encontra o sucessor in-order, ou seja, o nó com menor valor na subárvore à direita
            current.setValue(findMinValue(current.getRight()));
            // Remove o nó sucessor da subárvore à direita
            current.setRight(removeRecursive(current.getRight(), current.getValue()));
            //Seta o valor da subárvore à direita agora sem o nó sucessor
            current.setSizeRight(current.getRight() != null ? current.getRight().getSizeRight() : 0);
        }

        return current;
    }

    //Encontra o menor valor da subárvore passada
    private int findMinValue(Node root) {
        int minValue = root.getValue();
        //Percorre a subárvore até o nó folha mais a esquerda, encontrando o nó com menor valor da subárvore
        while (root.getLeft() != null) {
            minValue = root.getLeft().getValue();
            root = root.getLeft();
        }
        return minValue;
    }

    public boolean search(int value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }

        if (current.getValue() == value) {
            return true;
        }

        //se o value for menor do que o valor do nó atual, vai para a subárvore à esquerda.
        if (value < current.getValue()) {
            return searchRecursive(current.getLeft(), value);
        }
        //se o value for maior do que o valor do nó atual, vai para a subárvore à direita.
        else {
            return searchRecursive(current.getRight(), value);
        }
    }

    public void imprimeArvore(int s) {
        if (s == 1) {
            imprimeArvoreFormato1(root, "", true);
        } else if (s == 2) {
            imprimeArvoreFormato2(root);
        }
    }

    private void imprimeArvoreFormato1(Node node, String prefix, boolean isTail) {
        //isTail serve para definir se é ou não o último na profundidade.
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getValue());

            if (node.getLeft() != null || node.getRight() != null) {

                // Se o nó tem ambos os filhos (esquerda e direita), imprime linhas para ambos
                if (node.getLeft() != null && node.getRight() != null) {
                    imprimeArvoreFormato1(node.getLeft(), prefix + (isTail ? "    " : "│   "), false); // Chama a função recursivamente para o nó à esquerda com prefixo não final
                    imprimeArvoreFormato1(node.getRight(), prefix + (isTail ? "    " : "│   "), true); // Chama a função recursivamente para o nó à direita com prefixo final
                }

                // Se o nó tem apenas o filho à esquerda, imprime linha para a esquerda
                else if (node.getLeft() != null) {
                    imprimeArvoreFormato1(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
                }

                // Se o nó tem apenas o filho à direita, imprime linha para a direita
                else {
                    imprimeArvoreFormato1(node.getRight(), prefix + (isTail ? "    " : "│   "), true);
                }
            }
        }
    }

    private void imprimeArvoreFormato2(Node node) {
        if (node == null) {
            return;
        }

        System.out.print("(" + node.getValue());

        // Imprime nó à esquerda
        imprimeArvoreFormato2(node.getLeft());

        // Imprime nó à direita
        imprimeArvoreFormato2(node.getRight());

        System.out.print(")");
    }


}
