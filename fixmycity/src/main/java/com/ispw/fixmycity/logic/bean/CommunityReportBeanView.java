package com.ispw.fixmycity.logic.bean;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommunityReportBeanView extends ReportBeanView {
	private List<String> events;

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}
	
	@Override
	public String toString() {
		return "Title: " + this.getTitle() + "\nDescription: " + this.getDescription() 
		+ "\nSubmission date: " + new SimpleDateFormat("dd-MM-yyyy").format(this.getDateSubmission())
		+" by " + this.getSubmitter() + "\nHas Event: " + !this.getEvents().isEmpty();
	}

}
