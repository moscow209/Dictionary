package com.example.dao;

import java.util.Map;

/**
 *@version 1.0 Mar 23, 2015.
 *@author Moscow209
 */
public class SearchDAO {

	public static boolean search(Map<String, String> dictionary, String key){
		return dictionary.containsKey(key);
	}
}
