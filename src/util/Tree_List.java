<<<<<<< HEAD:src/util/Tree_List.java
package util;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.PreOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;
import logic.HuffmanTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Tree_List {

    // Este metodo convierte el arbol en un arrayList con la informacion necesaria
    // para ser serializado
    public static ArrayList<Node_External> convert_Tree_to_ArrayList(HuffmanTree tree) {

        // Se recorre el arbol en entreOrden y se almacenan sus nodos en una lista, y su
        // posicion en una hastTable
        PreOrderIterator it = tree.preOrderIterator();
        LinkedList<BinaryTreeNode> list = new LinkedList<>();
        HashMap<BinaryTreeNode, Integer> map = new HashMap<>();

        int i = 0;
        while (it.hasNext()) {
            BinaryTreeNode node = it.nextNode();
            list.add(node);
            map.put(node, i++);
        }

        // Ahora creo un arrayList donde guardo la informacion de cada nodo, asi como si
        // es terminal, y en que
        // posicion esta su nodo derecho o -1 si no tiene

        Iterator<BinaryTreeNode> itList = list.iterator();
        ArrayList<Node_External> outList = new ArrayList<>(list.size());
        i = 0;
        while (itList.hasNext()) {
            BinaryTreeNode node = itList.next();
            Node_External node_external = new Node_External(node.getInfo());

            boolean isterminal = node.getLeft() == null;
            int right = node.getRight() == null ? -1 : map.get(node.getRight());

            node_external.setTerminal(isterminal);
            node_external.setRightNode(right);

            outList.add(i++, node_external);

        }
        return outList;
    }

    // Este metodo convierte el arraylist serializable en un arbol
    public static HuffmanTree convert_ArrayList_to_Tree(ArrayList<Node_External> list) {
        HuffmanTree outTree = null;

        if (!list.isEmpty()) {
            // Creo el nodo raiz, y empienzo a contruir el arbol de forma recursica
            BinaryTreeNode rootNode = new BinaryTreeNode(list.get(0).getInfo());
            create_Tree_Recursive(rootNode, 0, list);
            outTree = new HuffmanTree(rootNode);
        }

        return outTree;

    }

    // Este metodo crea el arbol de forma recursiva
    private static void create_Tree_Recursive(BinaryTreeNode node, int posCurrent, ArrayList<Node_External> list) {
        int posRight = list.get(posCurrent).getRightNode();

        // Compruebo si tiene hijo derecho, si tiene continuo la llamada recursiva
        if (posRight != -1) {
            BinaryTreeNode rightNode = new BinaryTreeNode(list.get(posRight).getInfo());
            node.setRight(rightNode);
            create_Tree_Recursive(rightNode, posRight, list);
        }

        // Compruebo si tiene hijo izquierdo, si tiene continuo la llamada recursiva
        if (!list.get(posCurrent).isTerminal()) {
            BinaryTreeNode leftNode = new BinaryTreeNode(list.get(++posCurrent).getInfo());
            node.setLeft(leftNode);
            create_Tree_Recursive(leftNode, posCurrent, list);
        }

    }

}
=======
package util;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.PreOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;
import logic.HuffmanTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class External_Memory_Tree_Convert {

    // Este metodo convierte el arbol en un arrayList 
    public static ArrayList<Node_External> convert_Tree_to_ArrayList(HuffmanTree tree) {

        PreOrderIterator ite = tree.preOrderIterator(); //Iterador en preorden para recorrer
        LinkedList<BinaryTreeNode> list = new LinkedList<>(); //lista para almacenar sus nodos
        HashMap<BinaryTreeNode, Integer> map = new HashMap<>(); //Hashmap para almacenar sus posiciones

        int i = 0; 
        while (ite.hasNext()) {
            BinaryTreeNode temp = ite.nextNode(); //Me quedo con el nodo 
            list.add(temp); //lo agrego a la lista
            map.put(temp, i++);  //guardo su posicion
        }

        Iterator<BinaryTreeNode> iteList = list.iterator(); //Iterador para recorrer la lista
        ArrayList<Node_External> aux = new ArrayList<>(list.size()); //lista para guardar la info de los nodos

        i = 0;

        while (iteList.hasNext()) {
            BinaryTreeNode node = iteList.next(); //guardo el nodo
            Node_External temp = new Node_External(node.getInfo()); //la info

            boolean terminal = (node.getLeft() == null); 
            int right = node.getRight() == null ? -1 : map.get(node.getRight()); //si es igual a null -1 sino su posicion

            temp.setTerminal(terminal); //guardo si es terminal
            temp.setRightNode(right); //guardo la posicion de su hijo derecho

            aux.add(i++, temp); //guardo en la posicion especificada el nodo

        }
        return aux; //retorno la lista
    }

    // Este metodo convierte el arraylist serializable en un arbol
    public static HuffmanTree convert_ArrayList_to_Tree(ArrayList<Node_External> list) {
        HuffmanTree outTree = null;

        if (!list.isEmpty()) {
            // Creo el nodo raiz, y empienzo a contruir el arbol de forma recursica
            BinaryTreeNode rootNode = new BinaryTreeNode(list.get(0).getInfo());
            create_Tree_Recursive(rootNode, 0, list);
            outTree = new HuffmanTree(rootNode);
        }

        return outTree;

    }

    // Este metodo crea el arbol de forma recursiva
    private static void create_Tree_Recursive(BinaryTreeNode node, int posCurrent, ArrayList<Node_External> list) {
        int posRight = list.get(posCurrent).getRightNode();
        ;

        // Compruebo si tiene hijo derecho, si tiene continuo la llamada recursiva
        if (posRight != -1) {
            BinaryTreeNode rightNode = new BinaryTreeNode(list.get(posRight).getInfo());
            node.setRight(rightNode);
            create_Tree_Recursive(rightNode, posRight, list);
        }

        // Compruebo si tiene hijo izquierdo, si tiene continuo la llamada recursiva
        if (!list.get(posCurrent).isTerminal()) {
            BinaryTreeNode leftNode = new BinaryTreeNode(list.get(++posCurrent).getInfo());
            node.setLeft(leftNode);
            create_Tree_Recursive(leftNode, posCurrent, list);
        }

    }

}
>>>>>>> 575d0d726d9a61ab8de4f328b547dff3e3016937:src/util/External_Memory_Tree_Convert.java
