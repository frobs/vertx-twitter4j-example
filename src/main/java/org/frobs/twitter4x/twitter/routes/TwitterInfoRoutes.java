package org.frobs.twitter4x.twitter.routes;

import io.vertx.ext.web.Router;
import org.frobs.twitter4x.common.Routes;
import org.frobs.twitter4x.twitter.handlers.TwitterInfoHandlers;

public class TwitterInfoRoutes implements Routes {
    @Override
    public void define(Router router) {
        router.get("/twitter/timeline/*").blockingHandler(TwitterInfoHandlers::timelineHandler);
    }
}
