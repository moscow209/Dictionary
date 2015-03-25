package com.example.model;

/**
 * @version 1.0 Mar 22, 2015.
 * @author Moscow209
 */
public class HistorySession {

	private String word;
	private String mean;
	private int frequency = 1;

	public HistorySession() {

	}

	public HistorySession(String word, String mean) {
		this.word = word;
		this.mean = mean;
	}

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

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

}
