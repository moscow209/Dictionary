package com.example.dao;

import java.util.List;

import com.example.common.MyArrayList;
import com.example.model.HistorySession;

/**
 *@version 1.0 Mar 20, 2015.
 *@author Moscow209
 */
public class HistorySessionDAO {
	
	private List<HistorySession> history;
	
	public HistorySessionDAO(){
		history = new MyArrayList<HistorySession>();
	}
	
	public void add(HistorySession session){
		int pos = 0;
		for(pos = 0; pos < history.size(); pos++){
			HistorySession temp = history.get(pos);
			if(temp.getWord().equalsIgnoreCase(session.getWord())){
				temp.setFrequency(temp.getFrequency() + 1);
			}
		}
		if(pos == history.size()){
			history.add(session);
		}
		
	}

	public List<HistorySession> getHistory() {
		return history;
	}
}
