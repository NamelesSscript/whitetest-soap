package com.example.demo.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;

@Endpoint
public class WhitetestEndPoint {
	
	public static final String name_space ="http://www.tekup.de/soap/models/whitetest";
	
	@PayloadRoot(namespace=name_space, localPart="StudentRequest")
	@ResponsePayload
	public WhiteTestResponse getExamStatus(@RequestPayload StudentRequest studentRequest) {
		return null ;
	}

}
