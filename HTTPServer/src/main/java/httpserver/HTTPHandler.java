package httpserver;

public interface HTTPHandler {
    void handle(HTTPRequest request, HTTPResponse response);
}