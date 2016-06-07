package org.frobs.twitter4x.twitter.types;


import org.frobs.twitter4x.common.SerializableObject;

public class TwitterCredentials implements SerializableObject {
    private String key;
    private String secret;
    private String verifier;

    public TwitterCredentials(String key, String secret, String verifier){
        this.key = key;
        this.secret = secret;
        this.verifier = verifier;
    }
    public TwitterCredentials(){}

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    @Override
    public String toString() {
        return "TwitterCredentials{" +
            "key='" + key + '\'' +
            ", secret='" + secret + '\'' +
            ", verifier='" + verifier + '\'' +
            '}';
    }

    @Override
    public String toJson() {
        return "{" +
                "\"key\" : \"" + key + "\"" +
                ", \"secret\" : \"" + secret + "\"" +
                ", \"verifier\" : \"" + verifier + "\"" +
                "}";
    }
}
