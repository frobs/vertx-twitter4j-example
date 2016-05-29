package org.frobs.twitter4x;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.frobs.twitter4x.app.routes.MainRoutes;
import org.frobs.twitter4x.common.AppConfiguration;
import org.frobs.twitter4x.common.Router;
import org.frobs.twitter4x.twitter.routes.TwitterAuthRoutes;
import org.frobs.twitter4x.twitter.routes.TwitterInfoRoutes;


public class App extends AbstractVerticle {
    private void configureRouter(){
        Router.INSTANCE.configure(vertx);
    }

    private void initializeRoutes(){
        Router.INSTANCE.initialize(new MainRoutes());
        Router.INSTANCE.initialize(new TwitterAuthRoutes());
        Router.INSTANCE.initialize(new TwitterInfoRoutes());
    }

    private void configureUserCookies(){
        Router.INSTANCE.getRouter().route().handler(CookieHandler.create());
    }

    private void configureUserSession(){
        Router.INSTANCE.getRouter().route().handler(SessionHandler
            .create(LocalSessionStore.create(vertx))
            .setCookieHttpOnlyFlag(true)
            .setCookieSecureFlag(true)
            .setSessionCookieName("wsession")
        .setSessionTimeout(AppConfiguration.SESSION_TIMEOUT));
    }

    private void startServer(){
        vertx.createHttpServer().requestHandler(Router.INSTANCE.getRouter()::accept).listen(AppConfiguration.PORT);
    }

    @Override
    public void start(Future<Void> fut) {
        configureRouter();
        configureUserCookies();
        configureUserSession();
        initializeRoutes();
        startServer();
    }


}




