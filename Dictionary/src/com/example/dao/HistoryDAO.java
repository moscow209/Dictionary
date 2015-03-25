package com.example.dao;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.example.common.MyArrayList;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class HistoryDAO {
	private MyArrayList<Dictionary> history;

	public HistoryDAO() {
		history = new MyArrayList<Dictionary>();
	}

	public void addList(Dictionary dict) {
		if (history.size() == 10) {
			history.remove(0);
		}
		history.add(dict);
	}

	public void read(String file) throws ParserConfigurationException,
			SAXException, IOException {
		history = ParseDAO.read(file, "history");
	}

	public void write(String file, String root)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {
		(new ParseDAO()).write(file, history, root);
	}

	public MyArrayList<Dictionary> getHistory() {
		return history;
	}

}
