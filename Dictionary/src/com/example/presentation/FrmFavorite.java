package com.example.presentation;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import com.example.dao.FavoriteDAO;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */

public class FrmFavorite extends JPanel {

	private JList list;
	private JTextPane lblMean;
	private FavoriteDAO dao;
	private DefaultListModel<String> model;
	private boolean isRemove = false;
	
	public FrmFavorite() {
		
	}
	
	public FrmFavorite(String str){
		dao = new FavoriteDAO();
		setLayout(null);
		this.add(getJList());
		this.add(getJLabel());
	}
	
	private JScrollPane getJList() {
		list = new JList();
		JScrollPane sp = new JScrollPane(list);
		sp.setLocation(0, 0);
		sp.setSize(220, 501);
		list.addListSelectionListener(new ListFavoriteListener());
		return sp;
	}

	private JScrollPane getJLabel() {
		lblMean = new JTextPane();
		JScrollPane sp = new JScrollPane(lblMean);
		sp.setLocation(231, 2);
		sp.setSize(385, 499);
		return sp;
	}

	public void load() throws ParserConfigurationException, SAXException,
			IOException {
		dao.read("favorite.xml");
		model = new DefaultListModel<String>();
		for (int i = 0; i < dao.getFavorite().size(); i++) {
			model.addElement(dao.getFavorite().get(i).getWord());
		}
		list.setModel(model);
		list.setSelectedIndex(0);

	}

	private class ListFavoriteListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting() && !isRemove) {
				String word = list.getSelectedValue().toString();
				for (int i = 0; i < dao.getFavorite().size(); i++) {
					if (dao.getFavorite().get(i).getWord()
							.equalsIgnoreCase(word)) {
						String mean = dao.getFavorite().get(i).getMean();
						lblMean.setText(mean.substring(1));
						break;
					}
				}
			}
		}
	}
	
	public void removeFavorite() throws FileNotFoundException, XMLStreamException{
		int index = list.getSelectedIndex();
		if(index != -1){
			isRemove = true;
			model.remove(index);
			lblMean.setText("");
			dao.getFavorite().remove(index);
			dao.write("favorite.xml");
		}
		isRemove = false;
		
	}

}
