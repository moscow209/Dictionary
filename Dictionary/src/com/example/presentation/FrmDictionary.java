package com.example.presentation;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class FrmDictionary extends JPanel {

	private JList list;
	private JTextPane lblMean;

	public FrmDictionary() {
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}

	private JScrollPane getJList() {
		list = new JList();
		JScrollPane sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		list.setSelectedIndex(0);
		return sp;
	}

	private JScrollPane getJLabel() {
		lblMean = new JTextPane();
		JScrollPane sp = new JScrollPane(lblMean);
		sp.setLocation(231, 2);
		sp.setSize(385, 499);
		return sp;
	}

	public JList getList() {
		return list;
	}

	public JTextPane getLblMean() {
		return lblMean;
	}
}
