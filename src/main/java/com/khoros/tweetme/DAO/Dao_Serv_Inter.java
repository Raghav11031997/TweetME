package com.khoros.tweetme.DAO;

import com.khoros.tweetme.MODELS.PojoResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Dao_Serv_Inter {
    String Get(String key);
    void Post(String key, String value);
    HashMap<String, String> Data();

    List<PojoResponse> cache = new ArrayList<>();
    void setCache(List<PojoResponse> cache);
    List<PojoResponse> getCache();

}
