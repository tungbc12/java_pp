package httpserver.method;

import httpserver.HTTPHandler;
import httpserver.HTTPRequest;
import httpserver.HTTPResponse;

public class GETHandler implements HTTPHandler {
    @Override
    public void handle(HTTPRequest request, HTTPResponse response) {
        response.setStatusCode(200);
        response.setBody("GET response");
        response.addHeader("Content-Type", "text/plain");
    }
}

