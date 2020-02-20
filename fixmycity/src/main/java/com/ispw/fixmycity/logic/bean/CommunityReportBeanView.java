package com.ispw.fixmycity.logic.bean;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommunityReportBeanView extends ReportBeanView {
	private List<String> eventsBean;

	public List<String> getEvents() {
		return eventsBean;
	}

	public void setEvents(List<String> events) {
		this.eventsBean = events;
	}
	
	@Override
	public String toString() {
		return "Title: " + this.getTitle() + "\nDescription: " + this.getDescription() 
		+ "\nSubmission date: " + new SimpleDateFormat("dd-MM-yyyy").format(this.getDateSubmission())
		+" by " + this.getSubmitter() + "\nHas Event: " + !this.getEvents().isEmpty();
	}

}
