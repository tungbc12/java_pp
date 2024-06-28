package httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class HTTPServer {

    private final String host;
    private final int port;
    private ServerSocketChannel serverSocketChannel;
    private final Map<String, HTTPHandler> handlers;

    public HTTPServer(String host, int port) {
        this.host = host;
        this.port = port;
        handlers = new HashMap<>();
    }

    public void start() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(host, port));
        System.out.println("Server started on " + host + ":" + port);

        while (true) {
            SocketChannel clientChannel = serverSocketChannel.accept();
            handleClient(clientChannel);
        }
    }

    private void handleClient(SocketChannel clientChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        clientChannel.read(buffer);
        buffer.flip();

        String request = new String(buffer.array()).trim();
        System.out.println("Received request: " + request);

        // Parse HTTP request (simplified)
        String[] requestLines = request.split("\r\n");
        if (requestLines.length > 0) {
            String requestLine = requestLines[0];
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length >= 2) {
                String method = requestParts[0];
                String path = requestParts[1];

                // Parse headers
                Map<String, String> headers = new HashMap<>();
                for (int i = 1; i < requestLines.length; i++) {
                    String[] headerParts = requestLines[i].split(": ");
                    if (headerParts.length == 2) {
                        headers.put(headerParts[0], headerParts[1]);
                    }
                }

                HTTPHandler handler = handlers.get(method + path);
                if (handler != null) {
                    HTTPRequest httpRequest = new HTTPRequest(method, path, headers, request);
                    HTTPResponse httpResponse = new HTTPResponse();
                    handler.handle(httpRequest, httpResponse);
                    clientChannel.write(ByteBuffer.wrap(httpResponse.toString().getBytes()));
                } else {
                    String response = "HTTP/1.1 404 Not Found\r\n\r\n";
                    clientChannel.write(ByteBuffer.wrap(response.getBytes()));
                }
            } else {
                String response = "HTTP/1.1 400 Bad Request\r\n\r\nInvalid Request Line";
                clientChannel.write(ByteBuffer.wrap(response.getBytes()));
            }
        } else {
            String response = "HTTP/1.1 400 Bad Request\r\n\r\nEmpty Request";
            clientChannel.write(ByteBuffer.wrap(response.getBytes()));
        }

        clientChannel.close();
    }

    public void addHandler(String method, String path, HTTPHandler handler) {
        handlers.put(method + path, handler);
    }

    public void stop() {
        try {
            if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
                serverSocketChannel.close();
                System.out.println("Server stopped");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}