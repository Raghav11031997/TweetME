package com.khoros.tweetme.DAO.impl;

import com.khoros.tweetme.DAO.Dao_Serv_Inter;
import java.util.HashMap;

public class Dao_Impl implements Dao_Serv_Inter {

    private final static HashMap<String, String> map = new HashMap<>();

    public Dao_Impl(){
        map.put("Primary", "Twitter");
        map.put("Secondary", "Facebook");
    }

    public String Get(String key){
        return map.get(key);
    }

    public void Post(String key, String value){
        map.put(key,value);
    }

    public HashMap<String, String> Data(){
        return map;
    }
}
