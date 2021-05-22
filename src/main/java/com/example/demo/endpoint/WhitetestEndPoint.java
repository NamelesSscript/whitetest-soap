package com.example.demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.demo.service.WhitetestService;

import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Endpoint
public class WhitetestEndPoint {
	
	public static final String name_space ="http://www.tekup.de/soap/models/whitetest";
	
	@Autowired
	private WhitetestService service ;
	
	@PayloadRoot(namespace=name_space, localPart="StudentRequest")
	@ResponsePayload
	public WhiteTestResponse getExamStatus(@RequestPayload StudentRequest studentRequest) {
		return null ;
	}

}
