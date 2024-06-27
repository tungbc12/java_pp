**HTTP Server**
- Do not use external libraries
- Implement part of HTTP 1.1 protocol using ServerSocketChannel (java.nio)
- Methods:
    - GET
    - POST
    - PUT
    - PATCH
    - Delete
- Headers (should be accesible as Map)
- Body
    - Bonus: multipart form data
- Your library should support:
    - Create and httpserver on specified host+port
    - Add listener to specific path and method
    - Access to request parameters (headers, method, etc)
    - Create and send http response back
****************
**Library description**\
\
*HTTPServer library is a lightweight Java framework that enables the implementation of an HTTP server supporting various HTTP methods (GET, POST, PUT, PATCH, DELETE). Here's a concise description:*
- HTTPServer Class: 
  - Creates an HTTP server on a specified host and port using ServerSocketChannel from java.nio.
  - Registers handlers for specific HTTP methods (GET, POST, PUT, PATCH, DELETE) and paths using a Map.
  - Accepts incoming client connections and delegates handling to registered handlers.
- HTTPHandler Interface:
    - Defines a contract (handle method) for handling HTTP requests and generating HTTP responses.
- HTTPRequest Class:
    - Parses incoming raw HTTP requests to extract method, path, headers, and body.
    - Provides methods to access parsed request details (getMethod, getPath, getHeaders, getBody).
- HTTPResponse Class:
  - Constructs HTTP responses with status codes, headers, and response bodies.
  - Provides methods to set status code (setStatusCode), response body (setBody), and add headers (addHeader).
  - Converts the response to a string format suitable for transmission over the network (toString).
    HTTP Methods Supported:

- GETHandler: Handles GET requests.
  - POSTHandler: Handles POST requests, including processing of request bodies.
  - PUTHandler: Handles PUT requests.
  - PATCHHandler: Handles PATCH requests.
  - DELETEHandler: Handles DELETE requests.
************

**Test description**\
*The HTTPServerTest class is a JUnit test suite designed to verify the functionality of the HTTPServer and its handlers using unit tests. Here's a concise description of what the test suite does:*
- testGETRequest(): Sends a GET request to / and verifies the response.
- testPOSTRequest(): Sends a POST request to /post with a request body and verifies the response.
- testPUTRequest(): Sends a PUT request to /put with a request body and verifies the response.
- testPATCHRequest(): Sends a PATCH request to /patch with a request body and verifies the response.
- testDELETERequest(): Sends a DELETE request to /delete and verifies the response.
- testInvalidRequest(): Sends an invalid request and verifies the response for a 400 Bad Request.
- testNotFoundRequest(): Sends a request to a non-existent path (/notfound) and verifies the response for 404 Not Found.
- testEmptyRequest(): Sends an empty request and verifies the response for 400 Bad Request.

**Maven test result**
