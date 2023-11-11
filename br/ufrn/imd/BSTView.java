package br.ufrn.imd;

import br.ufrn.imd.models.BinarySearchTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BSTView {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        try {
            Scanner inputTreeValues = new Scanner(new File("br/ufrn/imd/Input/arquivo1.txt"));
            ArrayList<Integer> values = new ArrayList<>();

            // Lê os valores e armazena em um ArrayList
            while (inputTreeValues.hasNextInt()) {
                int value = inputTreeValues.nextInt();
                values.add(value);
            }
            inputTreeValues.close();

            // Ordena o ArrayList
            values.sort(Comparator.naturalOrder());

            tree.buildTree(values);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tree.imprimeArvore(1);
        tree.imprimeArvore(2);
        System.out.println();
        tree.insert(36);
        System.out.println(tree.mediana());
        System.out.println();
        tree.imprimeArvore(1);
        tree.imprimeArvore(2);
        


    }
}
