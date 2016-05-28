package org.frobs.communityeverywhere.app.routes;


import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import org.frobs.communityeverywhere.app.handlers.MainHandler;
import org.frobs.communityeverywhere.common.Routes;


public class MainRoutes implements Routes {

    @Override
    public void define(Router router) {
        router.route("/public/*").handler(StaticHandler.create("webroot/public"));
        router.get("/").handler(MainHandler::indexHandler);
    }
}
