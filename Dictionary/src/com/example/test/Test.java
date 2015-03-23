package com.example.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.example.dao.HistoryDAO;
import com.example.model.Dictionary;


/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */

public class Test {
	/*public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		HistoryDAO dao = new HistoryDAO();
		dao.addList(new Dictionary("1", "tien"));
		dao.addList(new Dictionary("2", "tien"));
		dao.addList(new Dictionary("3", "tien"));
		dao.addList(new Dictionary("4", "tien"));
		dao.addList(new Dictionary("5", "tien"));
		dao.addList(new Dictionary("6", "tien"));
		dao.addList(new Dictionary("7", "tien"));
		dao.addList(new Dictionary("8", "tien"));
		dao.addList(new Dictionary("9", "tien"));
		dao.addList(new Dictionary("10", "tien"));
		
		dao.write("e:\\dads.xml");
		for(int i = 0; i < dao.history.size(); i++){
			System.out.println(dao.history.get(i).getWord());
		}
		
	}*/
}
