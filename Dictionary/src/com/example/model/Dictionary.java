package com.example.model;

import java.util.Comparator;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class Dictionary {
	private String word;
	private String mean;

	public Dictionary() {

	}

	public Dictionary(String word, String mean) {
		this.word = word;
		this.mean = mean;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

}
