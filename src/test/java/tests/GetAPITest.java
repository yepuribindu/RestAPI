package tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {
    String url;
    String serviceurl;
    String apiurl;

    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    public GetAPITest() throws FileNotFoundException {
    }

    @BeforeMethod

    public void getAPI() throws FileNotFoundException {
    TestBase testBase=new TestBase();

    serviceurl=prop.getProperty( "URL" );
    apiurl=prop.getProperty( "serviceURL" );


    url=serviceurl+apiurl;
    }

    @Test(priority = 1)
    public void getAPITestWithoutHeaders() throws IOException {

        RestClient restClient=new RestClient();
        closeableHttpResponse=restClient.get( url );

        //11a.status code
        int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code--->"+statusCode);

        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");


        //2JSON String
        String responseString= EntityUtils.toString( closeableHttpResponse.getEntity(),"UTF-8" );
        JSONObject responseJson=new JSONObject( responseString );//
        System.out.println("Response JSON from API-->"+ responseJson);


        //single value assertions
        //per_page
        String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page" );
        System.out.println("value of per page is --> "+perPageValue);
        Assert.assertEquals(Integer.parseInt( perPageValue ) ,3);

        //total
        String total=TestUtil.getValueByJPath( responseJson,"/total" );
        System.out.println( "total is -->" + total );
        Assert.assertEquals( Integer.parseInt( total ),12 );


        //get the value from JSON Array:

        String lastName=TestUtil.getValueByJPath( responseJson,"/data[0]/last_name" );
        String id=TestUtil.getValueByJPath( responseJson,"/data[0]/id" );
        String avatar=TestUtil.getValueByJPath( responseJson,"/data[0]/avatar" );
        String firstName=TestUtil.getValueByJPath( responseJson,"/data[0]/first_name" );

        System.out.println(lastName);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(firstName);

        Assert.assertEquals(lastName,"Bluth" ,"does not match" );


        //3.ALL Headers
        Header[] headersArray =
                closeableHttpResponse.getAllHeaders();

        HashMap<String,String> allHeaders=new HashMap<String, String>(  );
        for(Header header:headersArray) {
            allHeaders.put( header.getName(), header.getValue() );

        }
        System.out.println("all headers are-->"+allHeaders);


        }

    @Test(priority = 2)
    public void getAPITestWithHeaders() throws IOException {

        restClient = new RestClient();
        HashMap<String, String> headerMap = new HashMap<String, String>();

        headerMap.put( "Content-Type", "application/json" );
        closeableHttpResponse = restClient.get( url, headerMap );

        //status code verification

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals( statusCode, RESPONSE_STATUS_CODE_200 );

        //JSON String
        String responseString = EntityUtils.toString( closeableHttpResponse.getEntity(), "UTF-8" );

        JSONObject responseJson = new JSONObject( responseString );
        System.out.println( responseString );

        //Single Value Assertion:

        //per_page
        String perPageValue = TestUtil.getValueByJPath( responseJson, "/per_page" );
        Assert.assertEquals( Integer.parseInt( perPageValue ), 3 );

        //total
        String total = TestUtil.getValueByJPath( responseJson, "/total" );
        Assert.assertEquals( Integer.parseInt( total ), 12 );



        //get the value from JSON Array

        String lastName=TestUtil.getValueByJPath( responseJson,"data[0]/last_name" );
        System.out.println("lastname is-->"+lastName);

        //All Headers

    }
}
