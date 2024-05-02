package inter;

import java.awt.Color;
import java.awt.Dimension;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import logic.*;
import util.*;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;

// TODO: Para mi el file esta muy largo, yo sacaria cada una de las funciones void donde se define la interfas y las pondria en una clase aparte
public class Principal extends JFrame {

	private JPanel contentPane;
	private JButton btnTeclado;
	private JButton btnTxt;
	private JTextArea textInput;
	private JTextArea textCode;
	private JScrollPane scrollPane;
	private JButton btnSaveTree;
	private JButton btnLoadTree;
	private HuffmanTree h;
	private String input;
	private String code;

	public Principal() {
		setBackground(new Color(34, 40, 49));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 659);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 62, 70));
		contentPane.setBorder(new MatteBorder(6, 6, 6, 6, new Color(34, 40, 49)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textInput = new JTextArea();
		textInput.setDisabledTextColor(new Color(0, 0, 0));
		textInput.setEnabled(false);
		textInput.setEditable(false);
		textInput.setBackground(new Color(238, 238, 238));
		textInput.setFont(new Font("Dubai", Font.BOLD, 18)); // trata de poner constantes para las funtes
		textInput.setBounds(45, 351, 657, 70);
		textInput.setForeground(Color.BLACK);
		contentPane.add(textInput);

		textCode = new JTextArea();
		textCode.setDisabledTextColor(new Color(0, 0, 0));
		textCode.setEnabled(false);
		textCode.setEditable(false);
		textCode.setBackground(new Color(238, 238, 238));
		textCode.setFont(new Font("Dubai", Font.BOLD, 18));
		textCode.setBounds(45, 445, 657, 70);
		textCode.setForeground(Color.BLACK);
		contentPane.add(textCode);

		UIManager.put("TextArea.disabledForeground", Color.BLACK);

		scrollPane = new JScrollPane();

		btnTxt = new JButton("TXT");
		btnTxt.setBackground(new Color(0, 173, 181));
		btnTxt.setFont(new Font("Dubai", Font.BOLD, 18));
		btnTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					File doc = new File("Input.txt");
					BufferedReader lector = new BufferedReader(new FileReader(doc));

					String temp;
					input = "";

					while ((temp = lector.readLine()) != null) {
						input = input.concat(temp);
					}

					lector.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

				h = new HuffmanTree(input);

				showEverything();
			}

		});
		btnTxt.setBounds(730, 110, 185, 49);
		contentPane.add(btnTxt);

		btnTeclado = new JButton("EntradaTeclado");
		btnTeclado.setBackground(new Color(0, 173, 181));
		btnTeclado.setFont(new Font("Dubai", Font.BOLD, 18));
		btnTeclado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do {
					input = JOptionPane.showInputDialog(null, "Ingrese la cadena a codificar", "Huffman", 1);
				} while (input == null || input.equals(""));

				h = new HuffmanTree(input);

				showEverything();
			}
		});
		btnTeclado.setBounds(730, 196, 185, 49);
		contentPane.add(btnTeclado);

		// guardar el arbol en el fichero
		btnSaveTree = new JButton("Save Tree");
		btnSaveTree.setBackground(new Color(0, 173, 181));
		btnSaveTree.setFont(new Font("Dubai", Font.BOLD, 18));
		btnSaveTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Node_External> planeTree = Tree_List.convert_Tree_to_ArrayList(h);
				// convierte el arbol en una lista

				int cantNodes = planeTree.size();// guarda su tamaño
				try {
					RandomAccessFile fich = new RandomAccessFile("file.bin", "rw"); // crea el fichero

					fich.writeInt(cantNodes);// establece el tamaño

					for (Node_External ne : planeTree) { // se llena con los elementos
						Convert.writeObject(fich, ne);
					}

					Convert.writeObject(fich, input); // guardar la frase
					Convert.writeObject(fich, code); // guardar el código

					fich.close(); // lo cierra
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Something went wrong.", "Error.", 0);
				}
			}
		});
		btnSaveTree.setBounds(730, 280, 185, 49);
		contentPane.add(btnSaveTree);

		// leer el arbol del fichero
		btnLoadTree = new JButton("Load Tree");
		btnLoadTree.setBackground(new Color(0, 173, 181));
		btnLoadTree.setFont(new Font("Dubai", Font.BOLD, 18));
		btnLoadTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RandomAccessFile fich;
				ArrayList<Node_External> listFromFich = new ArrayList<>();
				try {
					fich = new RandomAccessFile("file.bin", "rw");

					Node_External temp = null;

					int cantNodes = fich.readInt();
					for (int i = 0; i < cantNodes; i++) {
						temp = (Node_External) Convert.readObject(fich);
						listFromFich.add(temp);
					}
					input = (String) Convert.readObject(fich);
					code = (String) Convert.readObject(fich);

					fich.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Something went wrong.", "Error.", 0);
				}

				h = Tree_List.convert_ArrayList_to_Tree(listFromFich);

				showEverything();

			}
		});
		btnLoadTree.setBounds(730, 360, 185, 49);
		contentPane.add(btnLoadTree);

		changeColors(); // para la paleta de colores
	}

	private void showEverything() {
		Lienzo objLienzo = new Lienzo(h); 

		objLienzo.setVisible(true);
		objLienzo.setSize(new Dimension(1600, 900));
		objLienzo.setPreferredSize(new Dimension(1200, 1000));
		objLienzo.setMinimumSize(new Dimension(300, 300));
		objLienzo.setMaximumSize(new Dimension(900, 900));
		objLienzo.setBackground(new Color(230, 230, 250));

		objLienzo.setLocation(0, 65);
		scrollPane.setBounds(50, 25, 650, 250);
		contentPane.add(scrollPane);

		contentPane.add(objLienzo);
		scrollPane.setViewportView(objLienzo);

		JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
		horizontalScrollBar.setValue(horizontalScrollBar.getWidth() / 2);

		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setValue(1);

		textInput.setText(input);

		code = h.encode(input);
		textCode.setText(code); // encode
	}

	public void changeColors() {
		contentPane.setBorder(new MatteBorder(6, 6, 6, 6, new Color(4, 13, 18))); // borde
		contentPane.setBackground(new Color(24, 61, 61)); // fondo

		textInput.setBackground(new Color(92, 131, 116)); // textAreas
		textCode.setBackground(new Color(92, 131, 116));

		btnTxt.setBackground(new Color(92, 131, 116)); // botones
		btnTeclado.setBackground(new Color(92, 131, 116));
		btnSaveTree.setBackground(new Color(92, 131, 116));
		btnLoadTree.setBackground(new Color(92, 131, 116));

		textInput.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
		textCode.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
		btnTxt.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
		btnTeclado.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
		btnSaveTree.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
		btnLoadTree.setBorder(new MatteBorder(2, 2, 2, 2, new Color(4, 13, 18)));
	}

}
