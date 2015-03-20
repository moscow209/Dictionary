package com.example.test;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.example.dao.HandlerXmlDAO;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */

public class Test {
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parse = factory.newSAXParser();
			HandlerXmlDAO handler = new HandlerXmlDAO();
			parse.parse("e:\\Anh_Viet.xml", handler);
			Map<String, String> d = handler.getItems();
			System.out.println(d.size());

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
