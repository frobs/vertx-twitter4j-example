package org.frobs.twitter4x.twitter.handlers;

import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.RoutingContext;
import org.frobs.twitter4x.common.database.DefaultDatabaseConnection;
import org.frobs.twitter4x.twitter.TwitterClient;
import org.frobs.twitter4x.twitter.TwitterConfiguration;
import org.frobs.twitter4x.twitter.database.dao.TwitterUserDao;
import org.frobs.twitter4x.twitter.database.models.TwitterUserModel;
import org.frobs.twitter4x.twitter.helpers.RequestHelper;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public class TwitterAuthHandlers {

    public static void authHandler(final RoutingContext context) {
        RequestToken token = null;
        token = TwitterClient.getRequestToken();
        context.addCookie(Cookie.cookie(TwitterConfiguration.SESSION_TOKEN_SECRET, token.getTokenSecret()));
        RequestHelper.redirect(context, token.getAuthenticationURL());
    }

    public static void authCallbackHandler(final RoutingContext context) {
        Twitter twitterClientInstance = null;
        User user = null;
        TwitterCredentials credentials = new TwitterCredentials(
                context.request().params().get("oauth_token"),
                context.getCookie(TwitterConfiguration.SESSION_TOKEN_SECRET).getValue(),
                context.request().params().get("oauth_verifier")
        );
        context.removeCookie(TwitterConfiguration.SESSION_TOKEN_SECRET);

        try {
            twitterClientInstance = TwitterClient.getAuthenticatedTwitterInstance(credentials);
            user =  TwitterClient.getUser(twitterClientInstance);
            AccessToken accessToken = twitterClientInstance.getOAuthAccessToken();
            credentials.setKey(accessToken.getToken());
            credentials.setSecret(accessToken.getTokenSecret());
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        TwitterUserModel twitterUserModel = new TwitterUserModel();
        twitterUserModel.setName(user.getScreenName());
        twitterUserModel.setCredentials(credentials);

        int userId = TwitterUserDao.userExist(twitterUserModel.getName());
        if( userId > 0){
            twitterUserModel.setId(userId);
        }
        TwitterUserDao.updateUser(DefaultDatabaseConnection.get(),twitterUserModel);
        RequestHelper.redirect(context,"/");
    }
}
