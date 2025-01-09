package com.cars;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        setting();
    }

    private static void setting() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        System.out.println("Current directory: " + System.getProperty("user.dir"));

        File yamlFile = new File("projectCars/src/resources/config.yml");
        Config config = mapper.readValue(yamlFile, Config.class);

        System.out.println("Browser name: " + config.getBrowser().getBrowser_name());
        System.out.println("Headless: " + config.getBrowser().isHeadless());
        System.out.println("App URL: " + config.getEnvironment().getTest_int().getApp_url());
    }
}
