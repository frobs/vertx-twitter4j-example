package org.frobs.twitter4x.twitter.types;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwitterCredentialsTest {
    @Test
    public void toJson() throws Exception {
        TwitterCredentials credentials = new TwitterCredentials();
        credentials.setKey("A key");
        credentials.setSecret("A secret");
        credentials.setVerifier("verifier");
        Assert.assertEquals("{\"key\" : \"A key\", \"secret\" : \"A secret\", \"verifier\" : \"verifier\"}",credentials.toJson());
    }

}