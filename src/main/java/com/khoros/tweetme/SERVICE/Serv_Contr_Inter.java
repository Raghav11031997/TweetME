package com.khoros.tweetme.SERVICE;

import java.util.HashMap;

public interface Serv_Contr_Inter {
    String GetVal(String key);
    void PostVal(String key, String value);
    HashMap<String,String> DataVal();
}
