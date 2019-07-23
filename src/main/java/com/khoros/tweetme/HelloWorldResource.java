package com.khoros.tweetme;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
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

    private Twitter twitter;

    public HelloWorldResource(Twitter twitter) {
        this.twitter = twitter;
    }

    @GET
    @Path("/get")
    public Response doGet() throws TwitterException {

        final List<Status> statuses = twitter.getHomeTimeline();
        return Response.ok(statuses).build();
    }

    @POST
    @Path("/post")
    public Response doPost(String twt) throws TwitterException {

        Status status = twitter.updateStatus(twt);
        return Response.ok(status).build();
    }
}