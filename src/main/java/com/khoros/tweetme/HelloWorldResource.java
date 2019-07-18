package com.khoros.tweetme;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/twitter")
@Produces({ MediaType.APPLICATION_JSON})
public class HelloWorldResource {

    String consumerKey;
    String consumerSecret;
    String accessToken;
    String accessTokenSecret;

    Twitter twitter;

    public HelloWorldResource(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(this.consumerKey);
        cb.setOAuthConsumerSecret(this.consumerSecret);
        cb.setOAuthAccessToken(this.accessToken);
        cb.setOAuthAccessTokenSecret(this.accessTokenSecret);

        TwitterFactory twitterFactory = new TwitterFactory(cb.build());
        twitter = twitterFactory.getInstance();
    }

    @GET
    @Path("/get")
    public Response sayHello() throws TwitterException {

        final List<Status> statuses = twitter.getHomeTimeline();
        return Response.ok(statuses).build();
    }

    @POST
    @Path("/post")
    public Response sayHell(String twt) throws TwitterException {

        Status status = twitter.updateStatus(twt);
        return Response.ok(status).build();
    }


}