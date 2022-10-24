package net.mitask;

import net.mitask.http.HttpHandler;
import net.mitask.http.HttpRequest;
import net.mitask.http.HttpResponse;
import net.mitask.http.HttpStatus;

import javax.print.attribute.standard.PresentationDirection;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

import static net.mitask.Constants.*;

public class Server {
    private final HttpHandler handler;

    public Server(HttpHandler handler) {
        this.handler = handler;
    }

    public void bootstrap() throws Exception {
        System.out.println("Starting Naive...");
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(SERVER_PORT));
        System.out.println("Binding to port " + SERVER_PORT);

        while(true) {
            Future<AsynchronousSocketChannel> future = server.accept();
            handleClient(future);
        }
    }

    private void handleClient(Future<AsynchronousSocketChannel> future) throws Exception {
        System.out.println("New client connection");

        AsynchronousSocketChannel clientChannel = future.get();
        while(clientChannel != null && clientChannel.isOpen()) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            StringBuilder builder = new StringBuilder();
            boolean keepReading = true;

            while (keepReading) {
                int readResult = clientChannel.read(buffer).get();

                keepReading = readResult == BUFFER_SIZE;

                buffer.flip();
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                builder.append(charBuffer);
//                keepReading = builder.toString().endsWith(DELIMITER);

                buffer.clear();
            }

            HttpResponse response = new HttpResponse();

            if(handler != null) {
                try {
                    HttpRequest request = new HttpRequest(builder.toString());
                    this.handler.handle(request, response);

                    if(response.getHeader("Content-Type") == null) {
                        response.addHeader("Content-Type", "text/html; charset=utf-8");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setStatus(500);
                    response.addHeader("Content-Type", "text/html; charset=utf-8");
                    response.setBody("<html><body><h1>Internal Server Error!</h1></body></html>");
                }
            } else {
                response.setStatus(404);
                response.addHeader("Content-Type", "text/html; charset=utf-8");
                response.setBody("<html><body><h1>Resource not found!</h1></body></html>");
            }

            clientChannel.write(ByteBuffer.wrap(response.getBytes()));
            clientChannel.close();
        }
    }
}
