package httpserver.method;

import httpserver.HTTPHandler;
import httpserver.HTTPRequest;
import httpserver.HTTPResponse;

public class POSTHandler implements HTTPHandler {
    @Override
    public void handle(HTTPRequest request, HTTPResponse response) {
        response.setStatusCode(200);
        response.setBody("POST response: " + request.getBody());
        response.addHeader("Content-Type", "text/plain");
    }
}

