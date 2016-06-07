package org.frobs.twitter4x.twitter.database.models;

import org.frobs.twitter4x.twitter.types.TwitterCredentials;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class TwitterUserModelTest {
    @Test
    public void toJson() throws Exception {
        TwitterUserModel userModel = new TwitterUserModel();
        userModel.setId(123);
        userModel.setName("one Namé");
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("A key");
        credentials.setSecret("A secret");
        credentials.setVerifier("verifier");
        userModel.setCredentials(credentials);
        Assert.assertEquals("{\"id\" : 123, \"name\" : \"one Namé\", \"credentials\" : {\"key\" : \"A key\", \"secret\" : \"A secret\", \"verifier\" : \"verifier\"}}",userModel.toJson());
    }

}