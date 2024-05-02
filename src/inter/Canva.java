package inter;

import java.awt.Graphics;
import javax.swing.JPanel;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import logic.*;

public class Canva extends JPanel {
    private HuffmanTree objArbol;
    public static final int DIAMETRO = 40;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 50; // estaba en 50

    public Canva (HuffmanTree objArbol) {
        super();
        this.objArbol = objArbol;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // To change body of generated methods, choose Tools | Templates.
        pintar(g, getWidth() / 2, 20, objArbol.getNodeRoot());
    }

    private void pintar(Graphics g, int x, int y, BinaryTreeNode<CharFrequency> n) {
        if (n == null) {
        } else { // poner scroll pane para cuando sea un arbol muy grande
            int EXTRA = nodosCompletos(n) * (ANCHO / 2);
            g.drawOval(x, y, DIAMETRO, DIAMETRO);
            g.drawString(n.getInfo().toString(), x + 17, y + 23);
            // g.setFont(new java.awt.Font("Dubai",0,18));
            // g.fillOval(x, y, y, EXTRA); // ver para el color del nodo
            // g.setColor(Color.CYAN);
            // g.set
            if (n.getLeft() != null)
                g.drawLine(x + RADIO, y + 2 * RADIO, x - ANCHO - EXTRA + 2 * RADIO, y + ANCHO + RADIO);
            if (n.getRight() != null) {
                g.drawLine(x + RADIO, y + 2 * RADIO, x + ANCHO + EXTRA + RADIO / 10, y + ANCHO + RADIO); // pensar bien
                                                                                                         // con logica
                                                                                                         // este
            }
            // g.setPaintMode(); // suavizado ver
            pintar(g, x - ANCHO - EXTRA, y + ANCHO, n.getLeft());
            pintar(g, x + ANCHO + EXTRA, y + ANCHO, n.getRight());
        }

    }

    public int nodosCompletos(BinaryTreeNode<CharFrequency> n) { // metodo nuestro
        if (n == null)
            return 0;
        else {
            if (n.getLeft() != null && n.getRight() != null)
                return nodosCompletos(n.getLeft()) + nodosCompletos(n.getRight()) + 1;
            return nodosCompletos(n.getLeft()) + nodosCompletos(n.getRight());
        }
    }
}
