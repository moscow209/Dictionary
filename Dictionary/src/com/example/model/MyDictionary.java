package com.example.model;

import java.util.HashMap;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class MyDictionary<K, V> {

	private HashMap<K, V> dictionary;

	public MyDictionary() {
		dictionary = new HashMap<K, V>();
	}

	public V put(K key, V value) {
		return dictionary.put(key, value);
	}

	public V get(K key) {
		return dictionary.get(key);
	}

	public HashMap<K, V> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<K, V> dictionary) {
		this.dictionary = dictionary;
	}
}
