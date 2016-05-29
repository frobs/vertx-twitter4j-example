package org.frobs.twitter4x.twitter;


import org.frobs.twitter4x.common.Client;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;


public class TwitterClient implements Client {

    private static ConfigurationBuilder getConfiguration(){
        ConfigurationBuilder configuration = new ConfigurationBuilder();
        configuration.setOAuthConsumerKey(TwitterConfiguration.CONSUMER_KEY);
        configuration.setOAuthConsumerSecret(TwitterConfiguration.CONSUMER_SECRET);
        return configuration;
    }

    public static ConfigurationBuilder getConfiguration(final TwitterCredentials credentials){
        ConfigurationBuilder configuration = new ConfigurationBuilder();
        configuration.setOAuthConsumerKey(TwitterConfiguration.CONSUMER_KEY);
        configuration.setOAuthConsumerSecret(TwitterConfiguration.CONSUMER_SECRET);
        configuration.setOAuthAccessToken(credentials.getKey());
        configuration.setOAuthAccessTokenSecret(credentials.getSecret());
        return configuration;
    }

    public static Twitter getConfiguredTwitterInstance(){
        Twitter twitter = new TwitterFactory(getConfiguration().build()).getInstance();
        return twitter;
    }

    public static Twitter getConfiguredTwitterInstance(final ConfigurationBuilder configuration){
        Twitter twitter = new TwitterFactory(configuration.build()).getInstance();
        return twitter;
    }

    public static RequestToken getRequestToken(){
        final Twitter twitter = getConfiguredTwitterInstance();
        RequestToken token = null;
        try {
            token = twitter.getOAuthRequestToken(TwitterConfiguration.CALLBACK_ROUTE);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static User getUser(final Twitter twitter) throws TwitterException {
        return twitter.verifyCredentials();
    };

    public static List<Status> getTimeLine(final Twitter twitter) throws TwitterException {
        return  twitter.getHomeTimeline();
    }

    public static Twitter getAuthenticatedTwitterInstance(final TwitterCredentials user) throws TwitterException {
        Twitter twitter = getConfiguredTwitterInstance();
            AccessToken accessToken = twitter.getOAuthAccessToken(new RequestToken(user.getKey(),
                    user.getSecret()), user.getVerifier());
            twitter.setOAuthAccessToken(accessToken);
        return twitter;
    }
}
