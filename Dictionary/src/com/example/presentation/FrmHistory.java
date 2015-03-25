package com.example.presentation;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.example.common.MyArrayList;
import com.example.dao.HistoryDAO;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class FrmHistory extends JPanel {

	private JList list;
	private JTextPane lblMean;
	private MyArrayList<Dictionary> history;

	public FrmHistory() {

	}

	public FrmHistory(String str) {
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}

	private JScrollPane getJList() {
		list = new JList();
		JScrollPane sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		list.addListSelectionListener(new ListHistoryListener());
		return sp;
	}

	private JScrollPane getJLabel() {
		lblMean = new JTextPane();
		JScrollPane sp = new JScrollPane(lblMean);
		sp.setLocation(231, 2);
		sp.setSize(385, 499);
		return sp;
	}

	public void load(MyArrayList<Dictionary> dict) {

		this.history = dict;
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < history.size(); i++) {
			model.addElement(history.get(i).getWord());
		}
		list.setModel(model);
		list.setSelectedIndex(0);

	}

	private class ListHistoryListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
				String word = list.getSelectedValue().toString();
				for (int i = 0; i < history.size(); i++) {
					if (history.get(i).getWord().equalsIgnoreCase(word)) {
						String mean = history.get(i).getMean();
						lblMean.setText(mean.substring(1));
						break;
					}
				}
			}
		}
	}
}
