package httpserver;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPRequestTest {
    @Test
    public void testParseRawRequest() {
        String rawRequest = "GET / HTTP/1.1\r\nHost: localhost\r\n\r\n";
        Map<String, String> headers = new HashMap<>();
        HTTPRequest request = new HTTPRequest("GET", "/", headers, rawRequest);

        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("localhost", request.getHeaders().get("Host"));
    }
}