package com.example.common;

import java.util.HashMap;

/**
 * @version 1.0 Mar 23, 2015.
 * @author Moscow209
 */
public class MyHashMap<K, V> {
	
	private HashMap<K, V> dictionary;

	public MyHashMap() {
		dictionary = new HashMap<K, V>();
	}

	public V put(K key, V value) {
		return dictionary.put(key, value);
	}

	public V get(K key) {
		return dictionary.get(key);
	}
	
	public int size(){
		return dictionary.size();
	}
	
	public boolean containsKey(Object key){
		return dictionary.containsKey(key);
	}

	public HashMap<K, V> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<K, V> dictionary) {
		this.dictionary = dictionary;
	}
}
