package com.account.my.common.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src\\test\\resources\\configuration.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try{
                properties.load(reader);
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Configuration Property file not find at:" +propertyFilePath);
        }
    }

    public String getChromeDeriverPath(){
        String chromeDriverPath = properties.getProperty("chrome_driver_path");
        System.out.println("Property file value" +chromeDriverPath);
        if(chromeDriverPath != null) return chromeDriverPath;
        else throw new RuntimeException("driver path not specified");
    }

}
