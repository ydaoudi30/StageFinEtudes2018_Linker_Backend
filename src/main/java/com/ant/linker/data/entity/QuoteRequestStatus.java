package com.ant.linker.data.entity;

import java.io.Serializable;

public enum QuoteRequestStatus implements Serializable {
	
	New("NEW_KEY"), 
	InProgress("PROGRESS_KEY"), 
	Answered("ANSWERED_KEY"), 
	Rectified("RECTIFIED_KEY"), 
	Accepted("ACCEPTED_KEY"), 
	Rejected("REJECTED_KEY"), 
	InPreparation("INPREPARATION_KEY");

	public String status = "";

	QuoteRequestStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public String toString() {
		return status;
	}
	
	
	
}
