package com.knowwhatwoueat.kwue.Utils;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 19.11.2016.
 */

public class Constants {
    private static Constants instance = new Constants();

    public static String endPoint = "http://ec2-54-227-149-31.compute-1.amazonaws.com:8000/";
    public int user_id ;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public static Constants getInstance() {
        return instance;
    }


}
