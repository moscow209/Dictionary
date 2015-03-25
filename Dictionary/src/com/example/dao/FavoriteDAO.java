package com.example.dao;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.example.common.MyArrayList;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class FavoriteDAO {
	private MyArrayList<Dictionary> favorite;

	public FavoriteDAO() {
		favorite = new MyArrayList<Dictionary>();
	}

	public boolean isCheck(String word) {
		for (int i = 0; i < favorite.size(); i++) {
			if (favorite.get(i).getWord().equalsIgnoreCase(word)) {
				return false;
			}
		}
		return true;
	}

	public void read(String file) throws ParserConfigurationException,
			SAXException, IOException {
		favorite = ParseDAO.read(file, "favorite");
	}

	public void write(String file, String word, String mean)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {
		new ParseDAO().write(file, word, mean, "favorite");
	}

	public void write(String file) throws FileNotFoundException, XMLStreamException{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(
				new BufferedOutputStream(new FileOutputStream(file)), "UTF-8");
		writer.writeStartDocument();
		writer.writeCharacters("\r\n  ");
		writer.writeStartElement("favorite");
		for (int i = 0; i < favorite.size(); i++) {
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("record");
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("word");
			writer.writeCharacters(favorite.get(i).getWord());
			writer.writeEndElement();
			writer.writeCharacters("\r\n  ");
			writer.writeStartElement("meaning");
			writer.writeCharacters(favorite.get(i).getMean());
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
	
	public MyArrayList<Dictionary> getFavorite() {
		return favorite;
	}
	
	
}
