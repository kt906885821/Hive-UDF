package com.kt.test;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
时间转换UDF自定义函数
 */

public class UDFDataFormat extends UDF {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SimpleDateFormat inputDate = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    private final SimpleDateFormat outputDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String formattedString = null;

    public Text evaluate(Text input){
        String strText = input.toString();
        if (input == null && StringUtils.isBlank(strText)){
            return null;
        }
        String all = strText.replaceAll("\"", "");

        try {
            Date parseInputDate = inputDate.parse(all);
            formattedString = outputDate.format(parseInputDate);

        } catch (Exception e) {
            logger.error("时间解析异常" + e.getMessage());

        }
        return new Text(formattedString);


    }

    public static void main(String[] args) {
        System.out.println(new UDFDataFormat().evaluate(new Text("")));

    }





}
