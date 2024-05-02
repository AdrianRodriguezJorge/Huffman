package util;

import java.io.Serializable;

public class FileNode <E>  implements Serializable {
    private E info;
    private boolean terminal;
    private int rightNode;

    public FileNode(E info) {
        this.info = info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public void setRightNode(int rightNode) {
        this.rightNode = rightNode;
    }

    public E getInfo() {
        return info;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public int getRightNode() {
        return rightNode;
    }
}
