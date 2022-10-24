package net.mitask.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static net.mitask.Constants.*;

public class HttpRequest {
    private final String message;
    private final HttpMethod method;
    private final String url;
    private final Map<String, String> headers;
    private final String body;

    public HttpRequest(String message) {
        this.message = message;

        String[] parts = message.split(DELIMITER);

        String head = parts[0];
        String[] headers = head.split(NEW_LINE);

        String[] firstLine = headers[0].split(" ");
        method = HttpMethod.valueOf(firstLine[0]);
        url = firstLine[1];

        this.headers = Collections.unmodifiableMap(
                new HashMap<>() {{
                    for(int i = 1; i < headers.length; i++) {
                        String[] headerPart = headers[i].split(HEADER_DELIMITER, 2);
                        put(headerPart[0].trim(), headerPart[1].trim());
                    }
                }}
        );

        String bls = this.headers.get("Content-Length");
        int bodyLength = bls != null ? Integer.parseInt(bls) : 0;
        body = parts.length > 1 ? parts[1].trim().substring(0, bodyLength) : "";
    }

    public String getMessage() {
        return message;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
