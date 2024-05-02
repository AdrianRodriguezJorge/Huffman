package logic;

import java.util.ArrayList;
import java.util.LinkedList;

import cu.edu.cujae.ceis.tree.binary.BinaryTree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public class HuffmanTree extends BinaryTree<CharFrequency> {

    public HuffmanTree(String input) {

        LinkedList<BinaryTreeNode<CharFrequency>> nodeList = new LinkedList<>();
        BinaryTreeNode<CharFrequency> father = null;
        ArrayList<CharFrequency> list = makeCharList(input);

        if (list.size() > 1) {
            for (CharFrequency cf : list) {
                nodeList.add(new BinaryTreeNode<>(cf));
            }

            while (nodeList.size() > 1) {
                father = new BinaryTreeNode<>();

                father.setLeft(nodeList.removeFirst());
                father.setRight(nodeList.removeFirst());

                father.setInfo(new CharFrequency(
                        father.getLeft().getInfo().getFrequency() + father.getRight().getInfo().getFrequency()));

                nodeList.add(father);
                QuickSort(nodeList);
            }

            root = father;
        }
        else root = new BinaryTreeNode<CharFrequency>(list.get(0));
    }

    public HuffmanTree() {

    }

    public HuffmanTree(BinaryTreeNode<CharFrequency> root) {
        setNodeRoot(root);
    }

    private ArrayList<CharFrequency> makeCharList(String input) {
        ArrayList<CharFrequency> list = new ArrayList<>();
        ArrayList<Character> rev = new ArrayList<>();
        char c;
        int j = 0;

        for (int i = 0; i < input.length(); i++) {
            j = 0;
            c = input.charAt(i);
            if (!rev.contains(c)) {
                rev.add(c);
                list.add(new CharFrequency(c));
            } else
                while (!list.get(j).increaseFrequency(c)) {
                    j++;
                }
        }
        list.sort(null);

        return list;
    }

    public String encode(String s) {
        String code = "";

        if (!this.isEmpty() && s != null) {
            BinaryTreeNode<CharFrequency> node = null;
            // String inverted;
            for (int i = 0; i < s.length(); i++) {
                node = searchLeave(s.charAt(i));
                // inverted = getEncodeToRoot(node);
                // inverted = new StringBuilder(inverted).reverse().toString();
                // code = code.concat(inverted);
                code += new StringBuilder(getEncodeToRoot(node)).reverse().toString(); // es lo mismo
            }
        }

        return code;
    }

    private BinaryTreeNode<CharFrequency> searchLeave(char c) {
        ArrayList<BinaryTreeNode<CharFrequency>> leaves = (ArrayList) getNodeLeaves();
        BinaryTreeNode<CharFrequency> node = null;
        boolean found = false;
        for (int i = 0; i < leaves.size() && !found; i++) {
            node = leaves.get(i);
            if (node.getInfo().getLetter().equals(c)) {
                found = true;
            }
        }

        return found ? node : null;
    }

    private String getEncodeToRoot(BinaryTreeNode<CharFrequency> node) {
        BinaryTreeNode<CharFrequency> father = getNodeFather(node);

        if (father != null) {
            return (father.getLeft().getInfo().equals(node.getInfo()) ? "0" : "1") + getEncodeToRoot(father);
        } else
            return "";

    }

    private void QuickSort(LinkedList<BinaryTreeNode<CharFrequency>> list) {
        BinaryTreeNode<CharFrequency> temp = null;
        int med = (list.size() - 1) / 2;
        BinaryTreeNode<CharFrequency> pivot = list.isEmpty() ? null : list.remove(med);
        LinkedList<BinaryTreeNode<CharFrequency>> list1 = new LinkedList<>();
        LinkedList<BinaryTreeNode<CharFrequency>> list2 = new LinkedList<>();

        while (!list.isEmpty()) {
            temp = list.removeFirst();
            if (temp.getInfo().compareTo(pivot.getInfo()) > 0) {
                list2.add(temp);
            } else
                list1.add(temp);
        }

        if (!list1.isEmpty()) {
            QuickSort(list1);
        }
        if (!list2.isEmpty()) {
            QuickSort(list2);
        }

        if (!list1.isEmpty()) {
            list.addAll(list1);
        }
        list.add(pivot);
        if (!list2.isEmpty()) {
            list.addAll(list2);
        }
    }
}
