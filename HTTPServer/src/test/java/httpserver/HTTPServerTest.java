package httpserver;

import httpserver.method.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServerTest {

    private static HTTPServer server;
    private static ExecutorService executor;

//    @BeforeAll
//    public static void setUp() throws IOException {
//        // Initialize server on localhost port 8081 for testing purposes
//        server = new HTTPServer("localhost", 8080);
//
//        // Add handlers for HTTP protocol methods.
//        server.addHandler("GET", "/", new GETHandler());
//        server.addHandler("POST", "/post", new POSTHandler());
//        server.addHandler("PUT", "/put", new PUTHandler());
//        server.addHandler("PATCH", "/patch", new PATCHHandler());
//        server.addHandler("DELETE", "/delete", new DELETEHandler());
//
//        // Start running the server in a separate thread.
//        executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> {
//            try {
//                server.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

    @AfterAll
    public static void tearDown() throws IOException {
        // Stop server and clean.
        server.stop();
        executor.shutdown();
    }

    @Test
    public void testGETRequest() throws IOException {
        String response = sendHTTPRequest("GET / HTTP/1.1\r\n\r\n");

        //assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nGET response", response);
    }

//    @Test
//    public void testPOSTRequest() throws IOException {
//        String requestBody = "This is a POST request body";
//        String request = "POST /post HTTP/1.1\r\nContent-Length: " + requestBody.length() + "\r\n\r\n" + requestBody;
//        String response = sendHTTPRequest(request);
//        assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nPOST response: " + requestBody, response);
//    }
//
//    @Test
//    public void testPUTRequest() throws IOException {
//        String requestBody = "This is a PUT request body";
//        String request = "PUT /put HTTP/1.1\r\nContent-Length: " + requestBody.length() + "\r\n\r\n" + requestBody;
//        String response = sendHTTPRequest(request);
//        assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nPUT response: " + requestBody, response);
//    }
//
//    @Test
//    public void testPATCHRequest() throws IOException {
//        String requestBody = "This is a PATCH request body";
//        String request = "PATCH /patch HTTP/1.1\r\nContent-Length: " + requestBody.length() + "\r\n\r\n" + requestBody;
//        String response = sendHTTPRequest(request);
//        assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nPATCH response: " + requestBody, response);
//    }

//    @Test
//    public void testDELETERequest() throws IOException {
//        String response = sendHTTPRequest("DELETE /delete HTTP/1.1\r\n\r\n");
//        assertEquals("HTTP/1.1 200 \r\nContent-Type: text/plain\r\n\r\nDELETE response", response);
//    }
//
//    @Test
//    public void testInvalidRequest() throws IOException {
//        String response = sendHTTPRequest("INVALID /invalid HTTP/1.1\r\n\r\n");
//        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\nInvalid Request Line", response);
//    }
//
//    @Test
//    public void testNotFoundRequest() throws IOException {
//        String response = sendHTTPRequest("GET /notfound HTTP/1.1\r\n\r\n");
//        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n", response);
//    }
//
//    @Test
//    public void testEmptyRequest() throws IOException {
//        String response = sendHTTPRequest("\r\n");
//        assertEquals("HTTP/1.1 400 Bad Request\r\n\r\nEmpty Request", response);
//    }
//
    private String sendHTTPRequest(String request) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080))) {
            ByteBuffer buffer = ByteBuffer.wrap(request.getBytes());
            socketChannel.write(buffer);

            buffer.clear();
            socketChannel.read(buffer);
            buffer.flip();

            return new String(buffer.array()).trim();
        }
    }
}