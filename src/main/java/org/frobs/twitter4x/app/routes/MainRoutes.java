package org.frobs.twitter4x.app.routes;


import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.frobs.twitter4x.app.handlers.MainHandler;
import org.frobs.twitter4x.common.Routes;


public class MainRoutes implements Routes {

    @Override
    public void define(Router router) {
        router.route("/public/*").handler(StaticHandler.create("webroot/public"));
        router.get("/").handler(MainHandler::indexHandler);
    }
}
