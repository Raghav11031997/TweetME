package com.khoros.tweetme.SERVICE;

import com.khoros.tweetme.MODELS.PojoResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public interface Serv_Contr_Inter {
    String GetVal(String key);
    void PostVal(String key, String value);
    HashMap<String,String> DataVal();
    Stream<PojoResponse> getFilter (Twitter twitter) throws TwitterException;
    List<PojoResponse> getCachedTimeline (Twitter twitter, long cacheTime) throws TwitterException;
}
