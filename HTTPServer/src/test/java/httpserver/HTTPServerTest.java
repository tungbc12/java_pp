package httpserver;

import httpserver.method.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    private HTTPServer server;
    private Thread serverThread;

    @BeforeEach
    public void setUp() {
        // Initialize server on localhost port 8080 for testing purposes
        server = new HTTPServer("localhost", 8080);

        server.addHandler("GET", "/", new GETHandler());

        // Start running the server
        serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    @AfterEach
    public void tearDown() {
        // Stop server
        server.stop();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGETRequest() throws IOException {
        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode);
    }
}