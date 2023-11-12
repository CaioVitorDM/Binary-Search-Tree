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

            for(int value: values){
                tree.insert(value);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();

        try {
            Scanner inputCommands = new Scanner(new File("br/ufrn/imd/Input/arquivo2.txt"));
            while (inputCommands.hasNext()) {
                String command = inputCommands.next();
                switch (command) {
                    case "CHEIA":
                        tree.ehCheia();
                        break;
                    case "COMPLETA":
                        tree.ehCompleta();
                        break;
                    case "ENESIMO":
                        int n = inputCommands.nextInt();
                        System.out.println("N-ésimo elemento: " + tree.enesimoElemento(n));
                        break;
                    case "INSIRA":
                        int value = inputCommands.nextInt();
                        tree.insert(value);
                        break;
                    case "PREORDEM":
                        tree.preOrder();
                        break;
                    case "IMPRIMA":
                        System.out.println();
                        int format = inputCommands.nextInt();
                        tree.imprimeArvore(format);
                        System.out.println();
                        break;
                    case "REMOVA":
                        int removeValue = inputCommands.nextInt();
                        tree.remove(removeValue);
                        break;
                    case "POSICAO":
                        int positionValue = inputCommands.nextInt();
                        System.out.println("Posição de " + positionValue + ": " + tree.posicao(positionValue));
                        break;
                    case "MEDIANA":
                        System.out.println("Mediana: " + tree.mediana());
                        break;
                    case "MEDIA":
                        int mediaValue = inputCommands.nextInt();
                        System.out.println("Média até " + mediaValue + ": " + tree.media(mediaValue));
                        break;
                    case "BUSCAR":
                        int searchValue = inputCommands.nextInt();
                        if (tree.search(searchValue)) {
                            System.out.println("Chave encontrada");
                        } else {
                            System.out.println("Chave não encontrada");
                        }
                        break;
                    default:
                        System.out.println("Comando não reconhecido: " + command);
                        break;
                }
            }
            inputCommands.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
