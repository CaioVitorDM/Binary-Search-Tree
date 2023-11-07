package br.ufrn.imd.models;

import java.awt.*;
import java.util.ArrayList;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null; // Inicializa a árvore como vazia
    }

    public void insert(int value) {
        if (!search(value)) {
            insertRecursive(root, value);
            System.out.println(value + " inserido");
        } else {
            System.out.println(value + " já está na árvore, não pode ser inserido");
        }
    }

    private void insertRecursive(Node current, int value) {
        if (current == null) {
            root = new Node(value);
        } else if (value < current.getValue()) {
            if (current.getLeft() == null) {
                current.setLeft(new Node(value));
                current.setSizeLeft(1);
                return;
            }
            insertRecursive(current.getLeft(), value);
            current.setSizeLeft(current.getSizeLeft() + 1); // Atualiza o tamanho da subárvore à esquerda
        } else if (value > current.getValue()) {
            if (current.getRight() == null) {
                current.setRight(new Node(value));
                current.setSizeRight(1);
                return;
            }
            insertRecursive(current.getRight(), value);
            current.setSizeRight(current.getSizeRight() + 1); // Atualiza o tamanho da subárvore à direita
        }
    }

    public void remove(int value) {
        if (search(value)) {
            removeRecursive(root, value);
            System.out.println(value + " removido");
        } else {
            System.out.println(value + " não está na árvore, não pode ser removido");
        }
    }


    private Node removeRecursive(Node current, int value) {
        if (current == null) {
            return current;
        }

        if (value < current.getValue()) {
            current.setLeft(removeRecursive(current.getLeft(), value));
            current.setSizeLeft(current.getLeft() != null ? current.getLeft().getSizeLeft() : 0);
        } else if (value > current.getValue()) {
            current.setRight(removeRecursive(current.getRight(), value));
            current.setSizeRight(current.getRight() != null ? current.getRight().getSizeRight() : 0);
        } else {
            if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            }

            current.setValue(findMinValue(current.getRight()));
            current.setRight(removeRecursive(current.getRight(), current.getValue()));
            current.setSizeLeft(current.getLeft() != null ? current.getLeft().getSizeLeft() : 0);
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
            imprimeArvoreFormato1(root, 1);
        } else if (s == 2) {
            imprimeArvoreFormato2(root);
        }
    }

    private void imprimeArvoreFormato1(Node node, int depth) {
        if (node != null) {
            String nodeValue = String.valueOf(node.getValue());
            String prefix = "\t".repeat(depth);

            System.out.println(prefix + nodeValue + "-".repeat(depth * 7)); // Ajuste o valor 5 para controlar o espaçamento entre os traços

            imprimeArvoreFormato1(node.getLeft(), depth + 1);
            imprimeArvoreFormato1(node.getRight(), depth + 1);
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


    public int enesimoElemento(int n) {
        //Checa se o valor enesimo passado está dentro do intervalo da árvore
        if (n < 1 || n > countNodes(root)) {
            System.out.println("Provide a number in the correct interval.");
            return -1;
        }
        return enesimoElementoRec(root, n);
    }

    public int returnCountNodes(){
        return countNodes(root);
    }

    //Função auxiliar que conta a quantidade de elementos na árvore
    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + node.getSizeLeft() + node.getSizeRight();
    }

    //Função que encontra o elemento na n-ésima posição
    private int enesimoElementoRec(Node node, int n) {
        int nodesNaEsquerda = node.getSizeLeft() + 1; // Contagem de nós na subárvore esquerda + o próprio nó

        if (n == nodesNaEsquerda) {
            return node.getValue(); // Encontramos o n-ésimo elemento
        } else if (n < nodesNaEsquerda) {
            return enesimoElementoRec(node.getLeft(), n); // O n-ésimo elemento está na subárvore esquerda
        } else {
            return enesimoElementoRec(node.getRight(), n - nodesNaEsquerda); // O n-ésimo elemento está na subárvore direita
        }
    }

    public int posicao(int x) {
        int position = posicaoRec(root, x);
        if(position == -1){
            System.out.println("Element not found");
            return position;
        }
        return position;
    }

    private int posicaoRec(Node node, int x) {
        if (node == null) {
            return -1; // O Elemento não foi encontrado na árvore
        }

        int posicaoEsquerda = posicaoRec(node.getLeft(), x); // Chamada recursiva para o nó à esquerda

        if (posicaoEsquerda != -1) {
            return posicaoEsquerda; // Retorna a posição da subárvore esquerda se o elemento for encontrado
        }

        if (node.getValue() == x) {
            int posicaoSubarvoreEsquerda = node.getSizeLeft() + 1;
            return posicaoSubarvoreEsquerda; // Retorna a posição na raiz se o elemento for encontrado
        }

        int posicaoDireita = posicaoRec(node.getRight(), x);

        if (posicaoDireita != -1) {
            int posicaoSubarvoreEsquerda = node.getSizeLeft() + 1;
            return posicaoSubarvoreEsquerda + posicaoDireita; // Retorna a posição na subárvore direita se o elemento for encontrado
        }

        return -1; // Retorna -1 se o elemento não for encontrado na árvore
    }

    public int mediana() {
        int totalNodes = countNodes(root);
        System.out.println("quantiade de nos: " + totalNodes);

        // Verifica se o número total de nós é par ou ímpar
        if (totalNodes % 2 == 1) {
            // Se for ímpar, a mediana está em um único nó
            int medianPosition = (totalNodes + 1) / 2;
            return enesimoElementoRec(root, medianPosition);
        } else {
            // Se for par, a mediana é a média dos dois elementos do meio
            int medianPosition1 = totalNodes / 2;
            int medianPosition2 = totalNodes / 2 + 1;
            int medianValue1 = enesimoElementoRec(root, medianPosition1);
            int medianValue2 = enesimoElementoRec(root, medianPosition2);
            return Math.min(medianValue1, medianValue2);
        }
    }






}


