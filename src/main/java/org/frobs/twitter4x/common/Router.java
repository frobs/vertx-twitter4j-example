package org.frobs.twitter4x.common;

import io.vertx.core.Vertx;

public enum Router {
    INSTANCE;

    private io.vertx.ext.web.Router router;

    Router() {
        router = null;
    }

    public void configure(Vertx vertx){
        if(router == null){
            router = io.vertx.ext.web.Router.router(vertx);
        }
    }

    public void initialize(Routes routes){
        routes.define(router);
    }

    public io.vertx.ext.web.Router getRouter()
    {
        return router;
    }

}
