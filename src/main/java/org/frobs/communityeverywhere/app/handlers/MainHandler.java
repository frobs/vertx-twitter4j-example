package org.frobs.communityeverywhere.app.handlers;

import io.vertx.ext.web.RoutingContext;


public class MainHandler {

    public static void indexHandler(RoutingContext context) {
        context.response().sendFile("webroot/public/index.html");
    }
}
