package com.example.dao;

import com.example.common.MyArrayList;
import com.example.model.HistorySession;

/**
 * @version 1.0 Mar 20, 2015.
 * @author Moscow209
 */
public class HistorySessionDAO {

	private MyArrayList<HistorySession> history;

	public HistorySessionDAO() {
		history = new MyArrayList<HistorySession>();
	}

	public void add(HistorySession session) {
		int pos = 0;
		for (pos = 0; pos < history.size(); pos++) {
			HistorySession temp = history.get(pos);
			if (temp.getWord().equalsIgnoreCase(session.getWord())) {
				temp.setFrequency(temp.getFrequency() + 1);
				break;
			}
		}
		if (pos == history.size()) {
			history.add(session);
		}
	}

	public MyArrayList<HistorySession> getHistory() {
		return history;
	}
}
