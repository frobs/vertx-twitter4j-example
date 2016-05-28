package org.frobs.communityeverywhere.app;

import io.vertx.core.Vertx;
import org.frobs.communityeverywhere.common.Router;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by frobs on 14/05/16.
 */
public class RouterTest {
    private Vertx vertx;

    @Test
    public void testSingleton(){
        Router.INSTANCE.configure(Vertx.vertx());
        assertTrue(Router.INSTANCE.getRouter() == Router.INSTANCE.getRouter());
    }

    @Test
    public void testNotNull(){
        Router.INSTANCE.configure(Vertx.vertx());
        assertTrue(Router.INSTANCE.getRouter() != null);
    }
}