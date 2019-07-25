package com.khoros.tweetme.SERVICE.impl;

import com.khoros.tweetme.DAO.Dao_Factory;
import com.khoros.tweetme.DAO.Dao_Serv_Inter;
import com.khoros.tweetme.SERVICE.Serv_Contr_Inter;

import java.util.HashMap;

public class Serv_Impl implements Serv_Contr_Inter {

    Dao_Serv_Inter obj = Dao_Factory.getDao();

    public String GetVal(String key){
        return obj.Get(key);
    }

    public void PostVal(String key, String value){
        obj.Post(key, value);
    }

    public HashMap<String, String> DataVal(){
        return obj.Data();
    }

}
