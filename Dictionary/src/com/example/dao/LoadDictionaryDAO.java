package com.example.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.common.MyArrayList;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class LoadDictionaryDAO {

	private MyArrayList<Dictionary> items = new MyArrayList<Dictionary>();

	public MyArrayList<Dictionary> read(String file)
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		Handler hander = new Handler();
		parser.parse(new BufferedInputStream(new FileInputStream(file)), hander);
		return items;
	}

	public void write(String file, MyArrayList<Dictionary> dict)
			throws XMLStreamException, IOException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(
				new BufferedOutputStream(new FileOutputStream(file)), "UTF-8");
		writer.writeStartDocument();
		writer.writeCharacters("\r\n  ");
		writer.writeStartElement("dictionary");
		for (int i = 0; i < dict.size(); i++) {
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("record");
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("word");
			writer.writeCharacters(dict.get(i).getWord());
			writer.writeEndElement();
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("meaning");
			writer.writeCharacters(dict.get(i).getMean());
			writer.writeEndElement();
			writer.writeCharacters("\r\n  ");
			writer.writeEndElement();
		}
		writer.writeCharacters("\r\n  ");
		writer.writeEndElement();
		writer.writeCharacters("\r\n  ");
		writer.writeEndDocument();

		writer.flush();
		writer.close();

	}

	private final class Handler extends DefaultHandler {
		private static final String RECORD = "record";
		private static final String WORD = "word";
		private static final String MEAN = "meaning";
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
			if (WORD.equalsIgnoreCase(qName)) {
				model.setWord(builder.toString());
			} else if (MEAN.equalsIgnoreCase(qName)) {
				model.setMean(builder.toString());
			} else if (RECORD.equalsIgnoreCase(qName)) {
				items.add(model);
				isStart = false;
			}
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			if (RECORD.equalsIgnoreCase(qName)) {
				isStart = true;
				model = new Dictionary();
			}
			builder.setLength(0);
		}
	}
}
