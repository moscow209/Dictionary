package com.example.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.common.MyArrayList;
import com.example.model.Dictionary;

/**
 * @version 1.0 Mar 24, 2015.
 * @author Moscow209
 */
public class ParseDAO {
	public static MyArrayList<Dictionary> read(String file, String element)
			throws ParserConfigurationException, SAXException, IOException {
		File f = new File(file);
		MyArrayList<Dictionary> list = new MyArrayList<Dictionary>();
		if(!f.exists())
			return list;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new BufferedInputStream(
				new FileInputStream(file)));
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("record");
		Dictionary dict;
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				dict = new Dictionary();
				Element eElement = (Element) nNode;
				dict.setWord(eElement.getElementsByTagName("word").item(0)
						.getTextContent());
				dict.setMean(eElement.getElementsByTagName("meaning").item(0)
						.getTextContent());
				list.add(dict);
			}
		}
		return list;
	}

	public void write(String file, MyArrayList<Dictionary> history, String sRoot)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		Element root = document.createElement(sRoot);
		document.appendChild(root);
		for (int i = 0; i < history.size(); i++) {
			Element element = createNode(document, history.get(i).getWord(),
					history.get(i).getMean());
			root.appendChild(element);
		}
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer trans = transFactory.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
				"4");
		trans.transform(new DOMSource(document), new StreamResult(
				new FileOutputStream(file, false)));

	}

	public void write(String file, String word, String mean, String sRoot)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {
		File f = new File(file);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = null;
		Element root = null;
		if (f.exists()) {
			document = builder.parse(f);
			root = document.getDocumentElement();

		} else {
			document = builder.newDocument();
			root = document.createElement(sRoot);
			document.appendChild(root);
		}
		root.appendChild(createNode(document, word, mean));
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer trans = transFactory.newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
				"4");
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

}
