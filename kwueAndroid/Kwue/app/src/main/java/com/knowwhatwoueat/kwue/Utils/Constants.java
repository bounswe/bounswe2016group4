package com.knowwhatwoueat.kwue.Utils;

import java.util.List;

/**
 * Created by Mehmet Akif ÇÖRDÜK on 19.11.2016.
 */

public class Constants {

    public static String endPoint = "http://ec2-54-227-149-31.compute-1.amazonaws.com:8000/";
    public static int user_id = 1;
    public static String consumptionHistorySetting = "monthly";

    public static String toJson(List<String> stringList){
        String s = "['";
        for(int i = 0 ; i < stringList.size() ; i++){
            s += stringList.get(i);
            if(i == stringList.size() - 1 ){
                s+= "'";
            }else{
                s+= "','";
            }

        }
        s += "]";

        return s ;
    }


}


