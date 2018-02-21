package tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GetAPITest extends TestBase {
    String url;
    String serviceurl;
    String apiurl;

    public GetAPITest() throws FileNotFoundException {
    }

    @BeforeMethod
            public void getAPI() throws FileNotFoundException {
    TestBase testBase=new TestBase();

    serviceurl=prop.getProperty( "URL" );
    apiurl=prop.getProperty( "serviceURL" );


    url=serviceurl+apiurl;
    }

    @Test
    public void getTest() throws IOException {

        RestClient restClient=new RestClient();
        restClient.get( url );
    }




}
