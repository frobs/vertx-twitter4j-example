package org.frobs.communityeverywhere.twitter;

import org.frobs.communityeverywhere.common.AppConfiguration;

public class TwitterConfiguration {
    public static final String CONSUMER_KEY = System.getenv("TWITTER_CONSUMER_KEY");
    public static final String CONSUMER_SECRET = System.getenv("TWITTER_SECRET_KEY");
    public static final String SESSION_TOKEN_SECRET = "twitterSecret";
    public static final String CALLBACK_ROUTE = "http://localhost:" + AppConfiguration.PORT + "/twitter/auth_callback";
    public static final String AUTHENTICATION_ROUTE = "/twitter/auth?username=";
}
