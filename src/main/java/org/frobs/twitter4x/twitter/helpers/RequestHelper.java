package org.frobs.twitter4x.twitter.helpers;

import io.vertx.ext.web.RoutingContext;

public class RequestHelper {
    public static void redirect(RoutingContext context,String url){
        context.response().setStatusCode(302);
        context.response().putHeader("Location", url);
        context.response().end();
    }
}
