package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.model.Dictionary;
import com.example.model.MyDictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class HandlerXmlDAO extends DefaultHandler {
	private static final String RECORD = "record";
	private static final String WORD = "word";
	private static final String MEAN = "meaning";
	private StringBuilder builder = new StringBuilder();
	private MyDictionary<String, String> items = new MyDictionary<String, String>();
	private boolean isStart = false;
	private Dictionary model;

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if (isStart) {
			builder.append(ch, start, length);
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if (model != null) {
			if (WORD.equalsIgnoreCase(qName)) {
				model.setWord(builder.toString());
			} else if (MEAN.equalsIgnoreCase(qName)) {
				model.setMean(builder.toString());
			} else if (RECORD.equalsIgnoreCase(qName)) {
				items.put(model.getWord(), model.getMean());
				isStart = false;
			}
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (RECORD.equalsIgnoreCase(qName)) {
			model = new Dictionary();
			isStart = true;
		}
		builder.setLength(0);
	}

	public Map<String, String> getItems() {
		return items.getDictionary();
	}
}
