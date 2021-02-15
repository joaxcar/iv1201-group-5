package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import lombok.Value;

@Value
public class ErrorMsgBody {

	private int status;
	private String URL;
	private String msg;
	
}
