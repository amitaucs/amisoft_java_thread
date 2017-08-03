package com.amisoft.ch2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringSuffix {

    public static void main (String[] args){

        String text = "helloehelloejdjehello";
        int strLength = text.length();
        List<String> suffixList = new ArrayList<>();

        for(int index = 0; index < strLength;index++){

            String suffix = text.substring(index,strLength);
            suffixList.add(suffix);


        }

        Collections.sort(suffixList);
        for(String suffux : suffixList){
            System.out.println(suffux);
        }
    }
}
