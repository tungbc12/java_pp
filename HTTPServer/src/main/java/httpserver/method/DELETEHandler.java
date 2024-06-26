package httpserver.method;

import httpserver.HTTPHandler;
import httpserver.HTTPRequest;
import httpserver.HTTPResponse;

public class DELETEHandler implements HTTPHandler {
    @Override
    public void handle(HTTPRequest request, HTTPResponse response) {
        response.setStatusCode(200);
        response.setBody("DELETE response");
        response.addHeader("Content-Type", "text/plain");
    }
}

