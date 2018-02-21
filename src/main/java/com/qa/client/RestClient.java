package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {



    public void get(String url) throws IOException {

        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpGet httpGet=new HttpGet( url );//http get request
        CloseableHttpResponse closeableHttpResponse=httpClient.execute( httpGet );//hit the url


        //status code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code--->"+statusCode);

        //JSON String
        String responseString=EntityUtils.toString( closeableHttpResponse.getEntity(),"UTF-8" );
        JSONObject responseJson=new JSONObject( responseString );//
        System.out.println("Response JSON from API-->"+ responseJson);

        //ALL Headers
        Header[] headersArray =
                closeableHttpResponse.getAllHeaders();

        HashMap<String,String> allHeaders=new HashMap<String, String>(  );
        for(Header header:headersArray) {
            allHeaders.put( header.getName(), header.getValue() );

        }
        System.out.println("all headers are-->"+allHeaders);


    }


}
