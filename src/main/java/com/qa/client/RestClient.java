package com.qa.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestClient
{


        //1.GET Method without headers
        public CloseableHttpResponse get(String url) throws IOException
        {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet( url );//http get request
            CloseableHttpResponse closeableHttpResponse = httpClient.execute( httpGet );//hit the url
            return closeableHttpResponse;

        }



            //2.GET Method with headers

        public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws IOException
            {
                CloseableHttpClient httpClient=HttpClients.createDefault();
                HttpGet httpGet=new HttpGet( url );//http get request

                for(Map.Entry<String,String> entry :headerMap.entrySet())

                {
                    httpGet.addHeader( entry.getKey(),entry.getValue() );
                }
                CloseableHttpResponse closeableHttpResponse=httpClient.execute( httpGet );//hit the url

                return closeableHttpResponse;
            }

            //3.Post Method:request creation
        public CloseableHttpResponse post(String url,String entityString,HashMap<String,String>headerMap) throws IOException//for url
        {
            CloseableHttpClient httpClient=HttpClients.createDefault();//create a client
            HttpPost httppost=new HttpPost( url );//1.http post request.
            httppost.setEntity( new StringEntity( entityString ) );//2.to define the payload

            //for headers
            for(Map.Entry<String,String> entry :headerMap.entrySet())

            {
                httppost.addHeader( entry.getKey(),entry.getValue() );
            }
                CloseableHttpResponse closeableHttpResponse=httpClient.execute( httppost );

            return closeableHttpResponse;



        }



}
