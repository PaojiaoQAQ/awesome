package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ReadFileUtils {
    public static final Logger logger = LoggerFactory.getLogger(ReadFileUtils.class);
    public static String readFileStr(String filePath){
        File file = new File(filePath);
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bs = new BufferedInputStream(fis);
            byte[] resultArray = new byte[fis.available()];
            int index = 0;
            int temp = 0;
            while((temp = bs.read()) != -1){
                resultArray[index] = (byte)temp;
                index ++;
            }
            return new String(resultArray,"utf-8");
        } catch (FileNotFoundException e) {
            logger.error("file not found!");
        } catch (IOException e) {
            logger.error("io exception while read file!");
        }
        return "something wrong";
    }
}
