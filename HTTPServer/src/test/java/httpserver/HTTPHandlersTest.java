package httpserver;

import httpserver.method.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPHandlersTest {

    @Test
    public void testGETHandler() {
        GETHandler handler = new GETHandler();
        Map<String, String> headers = new HashMap<>();
        HTTPRequest request = new HTTPRequest("GET", "/", headers, "GET / HTTP/1.1\r\n\r\n");
        HTTPResponse response = new HTTPResponse();

        handler.handle(request, response);

        assertEquals(200, response.getStatusCode());
        assertEquals("GET response", response.getBody());
        assertEquals("text/plain", response.getHeaders().get("Content-Type"));
    }

    @Test
    public void testPOSTHandler() {
        POSTHandler handler = new POSTHandler();
        Map<String, String> headers = new HashMap<>();
        String body = "Sample POST data";
        HTTPRequest request = new HTTPRequest("POST", "/", headers, "POST / HTTP/1.1\r\n\r\n" + body);
        HTTPResponse response = new HTTPResponse();

        handler.handle(request, response);

        assertEquals(200, response.getStatusCode());
        assertEquals("POST response: " + body, response.getBody());
        assertEquals("text/plain", response.getHeaders().get("Content-Type"));
    }

    @Test
    public void testPUTHandler() {
        PUTHandler handler = new PUTHandler();
        Map<String, String> headers = new HashMap<>();
        String body = "Sample PUT data";
        HTTPRequest request = new HTTPRequest("PUT", "/", headers, "PUT / HTTP/1.1\r\n\r\n" + body);
        HTTPResponse response = new HTTPResponse();

        handler.handle(request, response);

        assertEquals(200, response.getStatusCode());
        assertEquals("PUT response: " + body, response.getBody());
        assertEquals("text/plain", response.getHeaders().get("Content-Type"));
    }

    @Test
    public void testPATCHHandler() {
        PATCHHandler handler = new PATCHHandler();
        Map<String, String> headers = new HashMap<>();
        String body = "Sample PATCH data";
        HTTPRequest request = new HTTPRequest("PATCH", "/", headers, "PATCH / HTTP/1.1\r\n\r\n" + body);
        HTTPResponse response = new HTTPResponse();

        handler.handle(request, response);

        assertEquals(200, response.getStatusCode());
        assertEquals("PATCH response: " + body, response.getBody());
        assertEquals("text/plain", response.getHeaders().get("Content-Type"));
    }

    @Test
    public void testDELETEHandler() {
        DELETEHandler handler = new DELETEHandler();
        Map<String, String> headers = new HashMap<>();
        HTTPRequest request = new HTTPRequest("DELETE", "/", headers, "DELETE / HTTP/1.1\r\n\r\n");
        HTTPResponse response = new HTTPResponse();

        handler.handle(request, response);

        assertEquals(200, response.getStatusCode());
        assertEquals("DELETE response", response.getBody());
        assertEquals("text/plain", response.getHeaders().get("Content-Type"));
    }
}