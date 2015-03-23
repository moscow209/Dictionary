package com.example.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

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

import com.example.common.MyHashMap;
import com.example.common.UICommons;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class LoadDictionaryDAO {

	public Map<String, String> read(String file)
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		Handler hander = new Handler();
		parser.parse(file, hander);
		return hander.getItems();
	}

	public void write(String file, String word, String mean)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		Element root = document.getDocumentElement();
		root.appendChild(createNode(document, word, mean));
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer trans = transFactory.newTransformer();
		trans.transform(new DOMSource(document), new StreamResult(
				new FileOutputStream(file, true)));

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
		private MyHashMap<String, String> items = new MyHashMap<String, String>();
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
					items.put(model.getWord(), model.getMean());
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

		public Map<String, String> getItems() {
			return items.getDictionary();
		}
		
	}
}
