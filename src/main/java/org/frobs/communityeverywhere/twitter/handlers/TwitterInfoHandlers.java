package org.frobs.communityeverywhere.twitter.handlers;

import io.vertx.ext.web.RoutingContext;
import org.frobs.communityeverywhere.twitter.TwitterClient;
import org.frobs.communityeverywhere.twitter.TwitterConfiguration;
import org.frobs.communityeverywhere.twitter.database.dao.TwitterUserDao;
import org.frobs.communityeverywhere.twitter.database.models.TwitterUserModel;
import org.frobs.communityeverywhere.twitter.helpers.RequestHelper;
import org.frobs.communityeverywhere.twitter.types.TwitterCredentials;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;

public class TwitterInfoHandlers {

    public static void timelineHandler(final RoutingContext context){
        List <Status> timelineTweets = null;
        String userName = context.request().params().get("username");
        TwitterUserModel userModel = TwitterUserDao.getByName(userName);
        TwitterCredentials credentials = null;
        if(userModel.getId() > 0){
            credentials = userModel.getCredentials();
        }else{
            RequestHelper.redirect(context, TwitterConfiguration.AUTHENTICATION_ROUTE + userName);
        }
        if(credentials != null) {
            try {
                Twitter twitter = TwitterClient.getConfiguredTwitterInstance(TwitterClient.getConfiguration(credentials));
                timelineTweets = TwitterClient.getTimeLine(twitter);
            } catch (TwitterException e) {
                RequestHelper.redirect(context, TwitterConfiguration.AUTHENTICATION_ROUTE + userName);
            }
            context.response().end(formatTimeLineTweets(timelineTweets));
        }
    }

    public static String formatTimeLineTweets(final List<Status> timelineTweets){
        StringBuilder formattedTweets = new StringBuilder();
        formattedTweets.append("[");
        for (Status status : timelineTweets) {
            formattedTweets.append("{");
            formattedTweets.append("author : ");
            formattedTweets.append(status.getUser().getScreenName());
            formattedTweets.append("},");
            formattedTweets.append("{");
            formattedTweets.append("text : ");
            formattedTweets.append(status.getText());
            formattedTweets.append("}");
        }
        formattedTweets.append("]");
        return formattedTweets.toString();
    }



}
