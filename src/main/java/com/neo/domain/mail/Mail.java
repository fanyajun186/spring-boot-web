package com.neo.domain.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mail {
	
	private String msgId;
	
	private String to;
	
	private String title;
	
	private String content;

}
