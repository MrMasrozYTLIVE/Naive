package net.mitask;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int SERVER_PORT = 80;
    public static final int BUFFER_SIZE = 256;

    public static final String DELIMITER = "\r\n\r\n";
    public static final String NEW_LINE = "\r\n";
    public static final String HEADER_DELIMITER = ":";

    public static final Map<Integer, String> STATUS_CODES = Collections.unmodifiableMap(
            new HashMap<>() {{
                // 1XX
                put(100, "Continue");
                put(101, "Switching Protocols");
                put(102, "Processing");
                put(103, "Early Hints");

                // 2XX
                put(200, "OK");
                put(201, "Created");
                put(202, "Accepted");
                put(203, "Non-Authoritative Information");
                put(204, "No Content");
                put(205, "Reset Content");
                put(206, "Partial Content");

                // 3XX
                put(300, "Multiple Choices");
                put(301, "Moved Permanently");
                put(302, "Found");
                put(303, "See Other");
                put(304, "Not Modified");
                put(307, "Temporary Redirect");
                put(308, "Permanent Redirect");

                // 4XX
                put(400, "Bad Request");
                put(401, "Unauthorized");
                put(403, "Forbidden");
                put(404, "Not Found");
                put(405, "Method Not Allowed");
                put(406, "Not Acceptable");
                put(407, "Proxy Authentication Required");
                put(408, "Request Timeout");
                put(409, "Conflict");
                put(410, "Gone");
                put(411, "Length Required");
                put(412, "Precondition Failed");
                put(413, "Payload Too Large");
                put(414, "URI Too Long");
                put(415, "Unsupported Media Type");
                put(416, "Range Not Satisfiable");
                put(417, "Expectation Failed");
                put(429, "Too Many Requests");

                // 5XX
                put(500, "Internal Server Error");
                put(501, "Not Implemented");
                put(502, "Bad Gateway");
                put(503, "Service Unavailable");
                put(504, "Gateway Timeout");
                put(505, "HTTP Version Not Supported");
                put(506, "Variant Also Negotiates");
                put(507, "Insufficient Storage");
                put(508, "Loop Detected");
                put(510, "Not Extended");
                put(511, "Network Authentication Required");
            }}
    );

    private static final Map<String, String> CONTENT_TYPES = Collections.unmodifiableMap(
            new HashMap<>() {{
                put("jpg", "image/jpeg");
                put("html", "text/html");
                put("json", "application/json");
                put("txt", "text/plain");
                put("", "text/plain");
            }}
    );
}
