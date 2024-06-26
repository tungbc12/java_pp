package httpserver;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    private String method;
    private String path;
    private Map<String, String> headers;
    private String body;

    public HTTPRequest(String method, String path, Map<String, String> headers, String rawRequest) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        parseRawRequest(rawRequest);
    }

    private void parseRawRequest(String rawRequest) {
        String[] lines = rawRequest.split("\r\n");
        boolean isHeader = true;
        StringBuilder bodyBuilder = new StringBuilder();

        for (String line : lines) {
            if (line.isEmpty()) {
                isHeader = false;
                continue;
            }

            if (isHeader) {
                if (!line.startsWith("HTTP")) {
                    String[] headerParts = line.split(": ");
                    if (headerParts.length == 2) {
                        headers.put(headerParts[0], headerParts[1]);
                    }
                }
            } else {
                bodyBuilder.append(line).append("\n");
            }
        }
        this.body = bodyBuilder.toString().trim();
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}