package com.examplecom.springbootsample.demo.exception;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public abstract class RestTemplateResponseErrorHandler implements ResponseErrorHandler{
	
	public static final Series CLIENT_ERROR = null;
	public static final Series SERVER_ERROR = null;
	
	
	@Autowired
	ResourceNotFoundException resourceNotFoundException;
	
	@Override
    public boolean hasError(ClientHttpResponse httpResponse) 
      throws IOException {
 
        return (
          httpResponse.getStatusCode().series() == CLIENT_ERROR 
          || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }
 
    @Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
 
        if (httpResponse.getStatusCode()
          .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
        } else if (httpResponse.getStatusCode()
          .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IOException("System is under maintenance");
                
            }
        }
    }

}
