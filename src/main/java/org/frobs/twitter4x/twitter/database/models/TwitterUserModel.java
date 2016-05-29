package org.frobs.twitter4x.twitter.database.models;

import org.frobs.twitter4x.common.database.Model;
import org.frobs.twitter4x.twitter.types.TwitterCredentials;

public class TwitterUserModel extends Model {
    private int id;
    private String name;
    private TwitterCredentials credentials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwitterCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(TwitterCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "TwitterUserModel{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", credentials = '" + credentials.toString() + '\'' +
                '}';
    }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"credentials\": {" +
                ", \"key\":\"" + credentials.getKey() + "\"" +
                ", \"secret\":\"" + credentials.getSecret() + "\"" +
                ", \"verifier\":\"" + credentials.getVerifier() + "\"" +
                "}" +
                "}";
    }
}
