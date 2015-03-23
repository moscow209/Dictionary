package com.example.presentation;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class FrmDictionary extends JPanel {

	private JList list;
	private JScrollPane sp;
	private JLabel lblMean;

	public FrmDictionary() {
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}

	private JScrollPane getJList() {
		JList list = new JList();
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(int i = 0; i < 50; i++){
			model.addElement("moskva" + i);
		}
		
		list.setModel(model);
		list.setSelectedIndex(21);
		sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		list.ensureIndexIsVisible(21);
		
		
		return sp;
	}

	private JLabel getJLabel() {
		lblMean = new JLabel("New label");
		lblMean.setBounds(231, 2, 356, 499);
		return lblMean;
	}

}
