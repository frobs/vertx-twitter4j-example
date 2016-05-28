package org.frobs.communityeverywhere.twitter.routes;

import io.vertx.ext.web.Router;
import org.frobs.communityeverywhere.common.Routes;
import org.frobs.communityeverywhere.twitter.handlers.TwitterInfoHandlers;

public class TwitterInfoRoutes implements Routes {
    @Override
    public void define(Router router) {
        router.get("/twitter/timeline/*").blockingHandler(TwitterInfoHandlers::timelineHandler);
    }
}
