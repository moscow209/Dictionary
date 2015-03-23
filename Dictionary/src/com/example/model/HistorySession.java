package com.example.model;

/**
 *@version 1.0 Mar 22, 2015.
 *@author Moscow209
 */
public class HistorySession {
	
	private String word;
	private int frequency = 1;
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
