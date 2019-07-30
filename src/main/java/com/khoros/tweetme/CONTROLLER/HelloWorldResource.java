package com.khoros.tweetme.CONTROLLER;

import com.khoros.tweetme.MODELS.PojoResponse;
import com.khoros.tweetme.SERVICE.Serv_Contr_Inter;
import com.khoros.tweetme.SERVICE.Serv_Factory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.*;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Path("/twitter")
@Produces({ MediaType.APPLICATION_JSON})
public class HelloWorldResource {

    private Twitter twitter;

    private static Logger logger = LogManager.getLogger(HelloWorldResource.class);

    long cacheTime;
    public HelloWorldResource(Twitter twitter, long cacheTime) {
        this.twitter = twitter;
        this.cacheTime = cacheTime;
    }

    @GET
    @Path("/get")
    public Response doGet()  {

        logger.info("GET REQUEST");

        try {

            final List<Status> statuses = twitter.getHomeTimeline();
            final List<PojoResponse> pojoResponses = new ArrayList<>();
            for(Status status : statuses){
                PojoResponse pojoResponse = new PojoResponse();

                pojoResponse.setName(status.getUser().getName());
                pojoResponse.setHandle(status.getUser().getScreenName());
                pojoResponse.setMessage(status.getText());
                pojoResponse.setCreatedAt(String.valueOf(status.getCreatedAt()));
                pojoResponse.setProfImageURL(status.getUser().getProfileImageURL());

                pojoResponses.add(pojoResponse);
            }

            return Response.ok(pojoResponses).build();
        }
        catch (TwitterException e){
            logger.error(e.getErrorMessage(), e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/post")
    public Response doPost(String twt) {

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

    @Path("/filter")
    @GET
    public Response doFilter() throws TwitterException {
        logger.info("GET REQUEST");
        Serv_Contr_Inter serv_contr_inter = Serv_Factory.getServ();
        Stream<PojoResponse> filteredPojo= serv_contr_inter.getFilter(twitter);
        return Response.ok(filteredPojo).build();
    }

    @Path("/cache")
    @GET
    public Response cache() throws TwitterException {

        Serv_Contr_Inter serv_contr_inter = Serv_Factory.getServ();
        List<PojoResponse> cachedResponse = serv_contr_inter.getCachedTimeline(twitter, cacheTime);
        return Response.ok(cachedResponse).build();
    }
}