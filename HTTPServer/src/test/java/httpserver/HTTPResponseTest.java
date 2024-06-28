package httpserver;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPResponseTest {
    @Test
    public void testSetStatusCode() {
        HTTPResponse response = new HTTPResponse();
        response.setStatusCode(200);
        assertEquals("HTTP/1.1 200 \r\n\r\n", response.toString());
    }

    @Test
    public void testSetBody() {
        HTTPResponse response = new HTTPResponse();
        response.setStatusCode(200);
        response.setBody("Hello, World!");
        assertEquals("HTTP/1.1 200 \r\n\r\nHello, World!", response.toString());
    }

    @Test
    public void testAddHeader() {
        HTTPResponse response = new HTTPResponse();
        response.setStatusCode(200);
        response.addHeader("Content-Type", "text/plain");
        response.setBody("Hello, World!");
        assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nHello, World!", response.toString());
    }
}