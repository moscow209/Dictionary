package com.example.presentation;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.example.common.MyArrayList;
import com.example.dao.HistoryDAO;
import com.example.dao.HistorySessionDAO;
import com.example.model.HistorySession;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class FrmSession extends JPanel {

	private JList list;
	private JTextPane lblMean;
	private MyArrayList<HistorySession> session;
	
	public FrmSession() {
		
	}

	public FrmSession(String title){
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}
	
	private JScrollPane getJList() {
		list = new JList();
		JScrollPane sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		list.addListSelectionListener(new ListSessionListener());
		return sp;
	}

	private JScrollPane getJLabel() {
		lblMean = new JTextPane();
		JScrollPane sp = new JScrollPane(lblMean);
		sp.setLocation(231, 2);
		sp.setSize(385, 499);
		return sp;
	}

	public void load(MyArrayList<HistorySession> history) {
		this.session = history;
		DefaultListModel<String> model = new DefaultListModel<String>();
		for (int i = 0; i < session.size(); i++) {
			model.addElement(session.get(i).getWord());
		}
		list.setModel(model);
		list.setSelectedIndex(0);
	}

	private class ListSessionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
				System.out.println("Selectdsadas");
				String word = list.getSelectedValue().toString();
				for (int i = 0; i < session.size(); i++) {
					if (session.get(i).getWord().equalsIgnoreCase(word)) {
						String mean = session.get(i).getMean();
						mean = mean.substring(1) + "\nBạn đã tra từ này "
								+ session.get(i).getFrequency() + " lần!";
						lblMean.setText(mean);
						break;
					}
				}
			}
		}
	}
}
