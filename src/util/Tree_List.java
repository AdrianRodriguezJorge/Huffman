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

    // Este metodo convierte el arraylist en un arbol
    public static HuffmanTree convert_ArrayList_to_Tree(ArrayList<Node_External> list) {
        HuffmanTree tree = null;

        if (!list.isEmpty()) {
            BinaryTreeNode root = new BinaryTreeNode(list.get(0).getInfo()); //nodo raiz
            createTree(root, 0, list); //llamo a la funcion que construye el arbol recursivamente
            tree = new HuffmanTree(root);
        }
        
        return tree;

    }

    // Este metodo crea el arbol de forma recursiva
    private static void createTree(BinaryTreeNode node, int posCurrent, ArrayList<Node_External> list) {
        int posRight = list.get(posCurrent).getRightNode(); //guardo la posicion
       

        if (posRight != -1) { //si es distinto de -1 quiere decir que tiene hijo derecho
            BinaryTreeNode rightNode = new BinaryTreeNode(list.get(posRight).getInfo()); //creo un BinaryTreeNode con la info
            node.setRight(rightNode); //le doy el valor de su hijo derecho
            createTree(rightNode, posRight, list); //hago una llamada recursiva con el nodo y la pos 
        }

        if (!list.get(posCurrent).isTerminal()) {//veo si tiene hijo izquierdo
            BinaryTreeNode leftNode = new BinaryTreeNode(list.get(++posCurrent).getInfo()); //creo el nodo con la info incrementando la pos del parametro
            node.setLeft(leftNode);
            createTree(leftNode, posCurrent, list); //hago llamada recursiva
        }

    }

}
