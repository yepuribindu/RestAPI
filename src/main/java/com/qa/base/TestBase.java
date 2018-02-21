package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public Properties prop;

    public TestBase() throws FileNotFoundException {
        prop=new Properties(  );
        FileInputStream ip=new FileInputStream( "C:\\Users\\yepurin\\IdeaProjects\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties" );
        try {
            prop.load( ip );
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
