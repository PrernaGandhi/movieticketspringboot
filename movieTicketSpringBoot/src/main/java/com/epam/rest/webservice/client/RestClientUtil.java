package com.epam.rest.webservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestClientUtil {

	@Value("${connection.port}")
	public String port;
	
	@Value("${connection.url}")
	public String url;
	
}
