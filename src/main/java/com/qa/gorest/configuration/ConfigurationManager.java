package com.qa.gorest.configuration;

import com.qa.gorest.exceptions.APIFrameworkException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ConfigurationManager {

    private Properties prop;
    private FileInputStream fis;

    public Properties initProp(){
        // maven: cmd line argument
        // mvn clean install -Denv="qa"
        // mvn clean install
        prop = new Properties();
        String envName=System.getProperty("env");
        try {
            if (envName == null) {
                System.out.println("no env is given...hence running on QA env");
                fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\qa.config.properties");
            } else {
                System.out.println("running tests on env : " + envName);
                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\qa.config.properties");
                        break;

                    case "dev":
                        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\dev.config.properties");
                        break;

                    case "stage":
                        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\stage.config.properties");
                        break;

                    case "prod":
                        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
                        break;

                    default:
                        System.out.println("Please pass the right env name " + envName);
                        throw new APIFrameworkException("Wrong ENV is Given");
                }
            }
        }
            catch (Exception e){
                e.printStackTrace();
            }

        try {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}

