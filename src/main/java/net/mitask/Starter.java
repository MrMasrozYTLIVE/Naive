package net.mitask;

public class Starter {
    public static void main(String[] args) {
        try {
            new Server((request, response) -> {
                response.setBody("<html><body><h1>Hello, World!</h1></body></html>");
                return response;
            }).bootstrap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
