package jenkins;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JenkinsTests {


    @Test
    public void apiTest(){
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

        // set the base url and path params
        spec.pathParams("first","booking","second",3);

        // send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData=response.as(HashMap.class);
        //System.out.println(actualData);

        Map<String,Object> bookingdates=(Map) actualData.get("bookingdates");

        assertEquals("Jim",actualData.get("firstname"));
        assertEquals("Jackson",actualData.get("lastname"));
        assertEquals(982,actualData.get("totalprice"));
        assertEquals(false,actualData.get("depositpaid"));

        assertEquals("2018-12-06",bookingdates.get("checkin"));
        assertEquals("2019-09-05",bookingdates.get("checkout"));


    }
}
