package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    public PostAPITest() throws FileNotFoundException
    {


    }
    @BeforeMethod
    public void setUp() throws FileNotFoundException {
        TestBase testBase=new TestBase();
        serviceUrl=prop.getProperty( "URL" );
        apiUrl=prop.getProperty( "serviceURL" );

        url=serviceUrl+apiUrl;


    }

    @Test
    public void postAPITest() throws IOException {
        restClient=new RestClient();
        HashMap<String,String>headerMap=new HashMap<String, String>(  );
        headerMap.put(  "Content-Type","application/json");//header maps

        //create a json for the request body(new class)

        //jackson api for marshalling an un marshalling
        ObjectMapper mapper=new ObjectMapper(  );
        Users users=new Users("Tavish","Leader");//expected users object

        //(USERS)JAVA OBJECT TO JSON
        mapper.writeValue( new File("C:\\Users\\yepurin\\IdeaProjects\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"),users);

        // OBJECT TO JSON IN STRING
        String usersJsonString=mapper.writeValueAsString( users );
        System.out.println( "usersJsonString -->" + usersJsonString );

        //passing three request type,json and URL
        closeableHttpResponse=restClient.post( url,usersJsonString,headerMap );


        //1.status code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals( statusCode,RESPONSE_STATUS_CODE_201,"does not match status code" );



        //2.JSon String verifying:fetch the json by utility

        //converting the response string to json string
        //converting json to java object

        String responseString=EntityUtils.toString( closeableHttpResponse.getEntity(),"UTF-8" );//getting the response string
        JSONObject responseJson=new JSONObject( responseString );//converting to Json string
        System.out.println( "responseJson -->" + responseJson );

        //JSON TO JAVA OBJECT
        Users usersResObj=mapper.readValue( responseString,Users.class );//actual users object
        System.out.println( usersResObj );

        Assert.assertTrue(users.getName().equals(usersResObj.getName()));
        Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));


    //comparing at object level




    }


}
