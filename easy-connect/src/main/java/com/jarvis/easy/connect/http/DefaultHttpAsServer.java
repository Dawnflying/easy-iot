package com.jarvis.easy.connect.http;

/**
 * @author lixiaofei
 */

import com.jarvis.easy.connect.servers.AsServerInterface;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import lombok.Data;

import java.util.Map;

@Data
public class DefaultHttpAsServer extends AbstractVerticle implements AsServerInterface {

    private String id;

    private String name;

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");
            response.end("Hello World!");
        });

        server.listen(8080);
    }

    @Override
    public Map<String, Object> getProperties() {
        return null;
    }
}

