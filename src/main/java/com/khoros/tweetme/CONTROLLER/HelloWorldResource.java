package com.khoros.tweetme.CONTROLLER;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khoros.tweetme.SERVICE.Serv_Contr_Inter;
import com.khoros.tweetme.SERVICE.Serv_Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("/twitter")
@Produces({ MediaType.APPLICATION_JSON})
public class HelloWorldResource {

    private Twitter twitter;

    private static Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

    public HelloWorldResource(Twitter twitter) {
        this.twitter = twitter;
    }

    @GET
    @Path("/get")
    public Response doGet()  {

        logger.info("GET REQUEST");

        try {
            final List<Status> statuses = twitter.getHomeTimeline();
            return Response.ok(statuses).build();
        }
        catch (TwitterException e){
            logger.error(e.getErrorMessage(), e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/post")
    public Response doPost(String twt) throws TwitterException {

        logger.info("POST REQUEST");


        try{
            Status status = twitter.updateStatus(twt);
            return Response.ok(status).build();
        }
        catch (TwitterException e){
            logger.error(e.getErrorMessage(), e);
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    @Path("/GetSocial")
    @GET
    public String getSocial(@QueryParam("key") String key) {
        Serv_Contr_Inter obj = Serv_Factory.getServ();
        return obj.GetVal(key);
    }

/*    public static class KVP {
        @JsonProperty("k") String key;
        @JsonProperty("v") String value;
    }
*/
    @Path("/PostSocial")
    @POST
    public void postSocial(@QueryParam("key") String key, @QueryParam("value") String value) {
        Serv_Contr_Inter obj = Serv_Factory.getServ();
        obj.PostVal(key, value);
    }

    @Path("/AllSocial")
    @GET
    public HashMap allSocial() {
        Serv_Contr_Inter obj = Serv_Factory.getServ();
        return obj.DataVal();
    }
}