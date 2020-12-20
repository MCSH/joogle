package com.joogle.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Joogle {

	private JFrame frame;
	private JTextField textField;

	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Joogle window = new Joogle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Joogle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Joogle");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");

		panel.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addElement("USA");
		listModel.addElement("India");
		listModel.addElement("Vietnam");
		listModel.addElement("Canada");
		listModel.addElement("Denmark");
		listModel.addElement("France");
		listModel.addElement("Great Britain");
		listModel.addElement("Japan");

		JScrollBar scrollBar = new JScrollBar();

		panel_2.add(scrollBar);

		JList list = new JList();
		panel_2.add(list);

		list.setModel(listModel);
		list.setAutoscrolls(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked!");
				textField.setText("");
			}
		});
	}

}
