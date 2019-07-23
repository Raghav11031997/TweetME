package com.khoros.tweetme;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }


    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(helloWorldConfiguration.getConsumerKey());
        cb.setOAuthConsumerSecret(helloWorldConfiguration.getConsumerSecret());
        cb.setOAuthAccessToken(helloWorldConfiguration.getAccessToken());
        cb.setOAuthAccessTokenSecret(helloWorldConfiguration.getAccessTokenSecret());

        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        Twitter twitter = twitterFactory.getInstance();
        final HelloWorldResource hl = new HelloWorldResource(twitter);
        environment.jersey().register(hl);

    }
}
