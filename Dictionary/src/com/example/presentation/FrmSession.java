package com.example.presentation;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *@version 1.0 Mar 23, 2015.
 *@author Moscow209
 */
public class FrmSession extends JPanel {

	private JList list;
	private JScrollPane sp;
	private JLabel lblMean;

	public FrmSession() {
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}

	private JScrollPane getJList() {
		JList list = new JList();
		sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		return sp;
	}

	private JLabel getJLabel() {
		lblMean = new JLabel("New label");
		lblMean.setBounds(231, 2, 356, 499);
		return lblMean;
	}


}

