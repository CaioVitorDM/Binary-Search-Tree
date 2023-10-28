package br.ufrn.imd;

import br.ufrn.imd.models.BinarySearchTree;

public class BSTView {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Montando a árvore
        tree.insert(5);
        tree.insert(3);
        tree.insert(8);

        // Imprimindo a árvore original
        System.out.println("Árvore original:");
        tree.imprimeArvore(1); // Formato 1
        tree.imprimeArvore(2); // Formato 2
        System.out.println();
        System.out.println();

        // Inserindo mais dois nós
        tree.insert(6);
        tree.insert(9);
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);

        System.out.println("Árvore com nós a mais:");
        tree.imprimeArvore(1); // Formato 1
        tree.imprimeArvore(2); // Formato 2
        System.out.println();
        System.out.println();


        System.out.println("Árvore com nós a mais:");
        tree.imprimeArvore(1); // Formato 1
        tree.imprimeArvore(2); // Formato 2
        System.out.println();
        System.out.println();

        System.out.println(tree.enesimoElemento(3));
        System.out.println(tree.posicao(10));

    }
}
