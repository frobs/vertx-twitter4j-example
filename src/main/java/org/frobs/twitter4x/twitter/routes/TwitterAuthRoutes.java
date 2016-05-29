package org.frobs.twitter4x.twitter.routes;

import io.vertx.ext.web.Router;
import org.frobs.twitter4x.common.Routes;
import org.frobs.twitter4x.twitter.handlers.TwitterAuthHandlers;

public class TwitterAuthRoutes implements Routes {
    @Override
    public void define(Router router) {
        router.get("/twitter/auth").blockingHandler(TwitterAuthHandlers::authHandler);
        router.get("/twitter/auth_callback").handler(TwitterAuthHandlers::authCallbackHandler);
    }
}
