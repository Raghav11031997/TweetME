package com.khoros.tweetme.DAO;

import java.util.HashMap;

public interface Dao_Serv_Inter {
    String Get(String key);
    void Post(String key, String value);
    HashMap<String, String> Data();
}
