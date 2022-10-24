package net.mitask.http;

import static net.mitask.Constants.*;

public class HttpStatus {
    private int statusCode;
    private String statusMessage;

    public HttpStatus(int statusCode) {
        this.statusCode = statusCode;
        this.statusMessage = STATUS_CODES.get(statusCode);
    }

    public void setStatus(int statusCode) {
        this.statusCode = statusCode;
        this.statusMessage = STATUS_CODES.get(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatus() {
        return statusCode + " " + statusMessage;
    }
}
