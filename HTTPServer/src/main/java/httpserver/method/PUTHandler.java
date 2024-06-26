package httpserver.method;

import httpserver.HTTPHandler;
import httpserver.HTTPRequest;
import httpserver.HTTPResponse;

public class PUTHandler implements HTTPHandler {
    @Override
    public void handle(HTTPRequest request, HTTPResponse response) {
        response.setStatusCode(200);
        response.setBody("PUT response: " + request.getBody());
        response.addHeader("Content-Type", "text/plain");
    }
}

