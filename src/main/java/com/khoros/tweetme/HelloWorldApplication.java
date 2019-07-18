package com.khoros.tweetme;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }


    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) {
        final HelloWorldResource hl = new HelloWorldResource(
                helloWorldConfiguration.getConsumerKey(),
                helloWorldConfiguration.getConsumerSecret(),
                helloWorldConfiguration.getAccessToken(),
                helloWorldConfiguration.getAccessTokenSecret()
        );
        environment.jersey().register(hl);

    }
}
