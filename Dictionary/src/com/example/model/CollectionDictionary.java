package com.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 *@version 1.0 Mar 20, 2015.
 *@author Moscow209
 */
public class CollectionDictionary <K, V>{
	
	private Map<K, V> dictionary;

	public CollectionDictionary(){
		dictionary = new HashMap<K, V>();
	}
	
	public Map<K, V> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Map<K, V> dictionary) {
		this.dictionary = dictionary;
	}
}
