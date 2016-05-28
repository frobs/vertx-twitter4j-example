package org.frobs.communityeverywhere.twitter.routes;

import io.vertx.ext.web.Router;
import org.frobs.communityeverywhere.common.Routes;
import org.frobs.communityeverywhere.twitter.handlers.TwitterAuthHandlers;

public class TwitterAuthRoutes implements Routes {
    @Override
    public void define(Router router) {
        router.get("/twitter/auth").blockingHandler(TwitterAuthHandlers::authHandler);
        router.get("/twitter/auth_callback").handler(TwitterAuthHandlers::authCallbackHandler);
    }
}
