package httpserver;

import java.util.HashMap;
import java.util.Map;

public class HTTPResponse {
    private int statusCode;
    private String body;
    private Map<String, String> headers;

    public HTTPResponse() {
        headers = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 ").append(statusCode).append(" \r\n");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
        }
        response.append("\r\n");
        if (body != null) {
            response.append(body);
        }
        return response.toString();
    }
}
