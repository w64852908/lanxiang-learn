package com.lanxiang.poi.excel;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2016/11/14.
 */
public class ReadProjectMap {

    private String baseUrl = "/Users/lanxiang/Desktop/跟踪项目";

    private String our = "/项目跟踪我方提供id2file.txt";

    private String cus = "/项目跟踪客户提供id2file.txt";

    private String mapping = "/数字100 项目-文件id对应关系";

    @Test
    public void run() throws Exception{
//        String ourString = generateMapping(baseUrl + our, baseUrl + mapping);
//        System.out.println(ourString);
        String cusString = generateMapping(baseUrl + cus, baseUrl + mapping);
        System.out.println(cusString);
    }

    private String generateMapping(String source, String map) throws Exception {
        StringBuilder result = new StringBuilder();
        BufferedReader sourceReader = new BufferedReader(new FileReader(new File(source)));
        BufferedReader mapReader = new BufferedReader(new FileReader(new File(map)));
        List<String> mapList = new ArrayList<>();
        String mapString;
        while ((mapString = mapReader.readLine()) != null) {
            mapList.add(mapString);
        }
        String sourceString;
        while ((sourceString = sourceReader.readLine()) != null) {
            for (String temp : mapList) {
                if (temp.substring(0, temp.lastIndexOf(":")).equals(sourceString)) {
                    result.append(temp).append("\r\n");
                }
            }
        }
        return result.toString();
    }
}
