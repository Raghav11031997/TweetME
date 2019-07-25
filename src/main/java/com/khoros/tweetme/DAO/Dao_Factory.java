package com.khoros.tweetme.DAO;

import com.khoros.tweetme.DAO.impl.Dao_Impl;

public class Dao_Factory {
    public static Dao_Serv_Inter getDao(){
        return new Dao_Impl();
    }
}
