package net.mitask.http;

public interface HttpHandler {
    HttpResponse handle(HttpRequest request, HttpResponse response);
}
