package com.khoros.tweetme.SERVICE;

import com.khoros.tweetme.SERVICE.impl.Serv_Impl;

public class Serv_Factory {
    public static Serv_Contr_Inter getServ(){
        return new Serv_Impl();
    }
}
