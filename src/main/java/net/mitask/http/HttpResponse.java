package net.mitask.http;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static net.mitask.Constants.*;

public class HttpResponse {
    private final Map<String, String> headers = new HashMap<>();
    private String body = "";
    private final HttpStatus status = new HttpStatus(200);

    public HttpResponse() {
        this.headers.put("Server", "naive");
        this.headers.put("Connection", "Close");
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public void addHeaders(Map<String, String> map) {
        this.headers.putAll(map);
    }

    public String message() {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 ")
                .append(status.getStatus())
                .append(NEW_LINE);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(NEW_LINE);
        }

        return builder.append(NEW_LINE)
                .append(body)
                .toString();
    }

    public byte[] getBytes() {
        return message().getBytes();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.headers.put("Content-Length", String.valueOf(body.length()));
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(int statusCode) {
        this.status.setStatus(statusCode);
    }

    public String getHeader(String key) {
        return headers.get(key);
    }
}
