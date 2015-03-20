package com.example.dao;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.model.CollectionDictionary;
import com.example.model.Dictionary;

/**
 *@version 1.0 Mar 20, 2015.
 *@author Moscow209
 */
public class ReadXmlDAO extends DefaultHandler{
	private static final String RECORD = "record";
	private static final String WORD = "word";
	private static final String MEAN = "meaning";
	private Dictionary item;
	private StringBuilder builder = new StringBuilder();
	private CollectionDictionary<String, Dictionary> items = new CollectionDictionary<String, Dictionary>();
	
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(arg0, arg1, arg2);
	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(arg0, arg1, arg2, arg3);
	}

	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(arg0, arg1, arg2);
	}

}
