package com.khoros.tweetme.SERVICE.impl;

import com.khoros.tweetme.DAO.Dao_Factory;
import com.khoros.tweetme.DAO.Dao_Serv_Inter;
import com.khoros.tweetme.MODELS.PojoResponse;
import com.khoros.tweetme.SERVICE.Serv_Contr_Inter;
import io.dropwizard.logging.filter.NullLevelFilterFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Serv_Impl implements Serv_Contr_Inter {

    Date prevTime = null;

    Dao_Serv_Inter obj = Dao_Factory.getDao();

    public String GetVal(String key) {
        return obj.Get(key);
    }

    public void PostVal(String key, String value) {
        obj.Post(key, value);
    }

    public HashMap<String, String> DataVal() {
        return obj.Data();
    }

    public Stream<PojoResponse> getFilter(Twitter twitter) throws TwitterException {


        final List<Status> statuses = twitter.getHomeTimeline();
        final List<PojoResponse> pojoResponses = new ArrayList<>();

        for (Status status : statuses) {
            PojoResponse pojoResponse = new PojoResponse();

            pojoResponse.setName(status.getUser().getName());
            pojoResponse.setHandle(status.getUser().getScreenName());
            pojoResponse.setMessage(status.getText());
            pojoResponse.setCreatedAt(String.valueOf(status.getCreatedAt()));
            pojoResponse.setProfImageURL(status.getUser().getProfileImageURL());

            pojoResponses.add(pojoResponse);
        }

        Stream<PojoResponse> filteredPojo = pojoResponses.stream().filter(pojo -> pojo.getMessage().contains("A"));

        return filteredPojo;

    }

    public List<PojoResponse> getCachedTimeline(Twitter twitter, long cacheTime) throws TwitterException{

        Date currTime = new Date();

        if(prevTime == null){
            Dao_Serv_Inter dao_serv_inter = Dao_Factory.getDao();

            final List<Status> statuses = twitter.getHomeTimeline();
            final List<PojoResponse> pojoResponses = new ArrayList<>();

            for (Status status : statuses) {
                PojoResponse pojoResponse = new PojoResponse();

                pojoResponse.setName(status.getUser().getName());
                pojoResponse.setHandle(status.getUser().getScreenName());
                pojoResponse.setMessage(status.getText());
                pojoResponse.setCreatedAt(String.valueOf(status.getCreatedAt()));
                pojoResponse.setProfImageURL(status.getUser().getProfileImageURL());

                pojoResponses.add(pojoResponse);
            }

            dao_serv_inter.setCache(pojoResponses);
            prevTime = currTime;
            return pojoResponses;
        }

        long diffmilli = currTime.getTime() - prevTime.getTime();
        long diffsec = TimeUnit.MILLISECONDS.toSeconds(diffmilli);

        if(diffsec > cacheTime){
            Dao_Serv_Inter dao_serv_inter = Dao_Factory.getDao();

            final List<Status> statuses = twitter.getHomeTimeline();
            final List<PojoResponse> pojoResponses = new ArrayList<>();

            for (Status status : statuses) {
                PojoResponse pojoResponse = new PojoResponse();

                pojoResponse.setName(status.getUser().getName());
                pojoResponse.setHandle(status.getUser().getScreenName());
                pojoResponse.setMessage(status.getText());
                pojoResponse.setCreatedAt(String.valueOf(status.getCreatedAt()));
                pojoResponse.setProfImageURL(status.getUser().getProfileImageURL());

                pojoResponses.add(pojoResponse);
            }

            dao_serv_inter.setCache(pojoResponses);
            prevTime = currTime;
            return pojoResponses;
        }

        Dao_Serv_Inter dao_serv_inter = Dao_Factory.getDao();

        return dao_serv_inter.getCache();
    }
}
