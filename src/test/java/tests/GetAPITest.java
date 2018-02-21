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
    public void setUp() throws FileNotFoundException {

       serviceurl=prop.getProperty( "URL" );
        apiurl=prop.getProperty( "Service URL" );


       url=serviceurl+apiurl;



    }

    @Test
    public void getTest() throws IOException {
        TestBase testBase=new TestBase();
        RestClient restClient=new RestClient();
        String url="https://reqres.in/api/users";
        restClient.get( url );
    }




}
