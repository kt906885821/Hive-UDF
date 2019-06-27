package com.kt.test;

import org.apache.hadoop.hive.ql.exec.UDF;

public class ItCastUDF extends UDF{

    public String evaluate(String input){
        return input.toUpperCase();


    }
    public int evaluate(int a,int b){
        return a + b;


    }


}
