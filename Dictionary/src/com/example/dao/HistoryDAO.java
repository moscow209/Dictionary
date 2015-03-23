package com.example.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.common.MyArrayList;
import com.example.common.UICommons;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class HistoryDAO {
	public List<Dictionary> history;

	public HistoryDAO() {
		history = new MyArrayList<Dictionary>();
	}

	public void addList(Dictionary dict){
		if(history.size() == 10){
			history.remove(0);
		}
		history.add(dict);
	}
	
	public void read(String file) throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		Handler hander = new Handler();
		parser.parse(file, hander);
	}

	public void write(String file) throws ParserConfigurationException,
			SAXException, IOException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		Element root = document.createElement("dictionary");
		document.appendChild(root);
		for (int i = 0; i < history.size(); i++) {
			root.appendChild(createNode(document, history.get(i).getWord(),
					history.get(i).getMean()));
		}
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer trans = transFactory.newTransformer();
		trans.transform(new DOMSource(document), new StreamResult(
				new FileOutputStream(file)));

	}

	private Element createNode(Document doc, String word, String mean) {
		Element recordNode = doc.createElement("record");
		Element wordNode = doc.createElement("word");
		wordNode.appendChild(doc.createTextNode(word));
		recordNode.appendChild(wordNode);

		Element meanNode = doc.createElement("meaning");
		meanNode.appendChild(doc.createTextNode(mean));
		recordNode.appendChild(meanNode);
		return recordNode;
	}

	private final class Handler extends DefaultHandler {
		private StringBuilder builder = new StringBuilder();
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
				if (UICommons.WORD.equalsIgnoreCase(qName)) {
					model.setWord(builder.toString());
				} else if (UICommons.MEAN.equalsIgnoreCase(qName)) {
					model.setMean(builder.toString());
				} else if (UICommons.RECORD.equalsIgnoreCase(qName)) {
					history.add(model);
					isStart = false;
				}
			}
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			if (UICommons.RECORD.equalsIgnoreCase(qName)) {
				model = new Dictionary();
				isStart = true;
			}
			builder.setLength(0);
		}
	}
}
