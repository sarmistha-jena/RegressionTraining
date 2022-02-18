package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest1 {
    @Test
    public void verifyGetListOfUsers(){
        Response res = (Response) given().contentType("application/json").get("api/users?page=2");
        Assert.assertEquals(res.getStatusCode(),200);
        String jsonObject=res.body().asString();
        System.out.println(jsonObject);
        JsonPath evaluate = res.jsonPath();
        System.out.println((Integer) evaluate.get("total_pages"));
        List<String> data=evaluate.getList("data.first_name");
        for(String d : data){
            System.out.println("First name::: "+d);
        }
//        JSONObject resObj= new JSONObject(res.body().asString());
//        JSONArray resArr= resObj.getJSONArray("data");
//        System.out.println(resArr.getJSONObject(0).get("last_name"));
    }
    @Test
    public void verifyCreateUser(){
        RequestSpecification req=given();
        JSONObject requestParameter = new JSONObject();
        req.contentType("application/json");
        requestParameter.put( "name", "morpheus");
        requestParameter.put("job", "leader");
        req.body(requestParameter.toString());
        Response response=req.post("/api/users");
        System.out.println(response.body().asString());
        System.out.println(response.getStatusCode());

    }
    @Test
    public void verifyCreateUserResponseWithPOJO(){
        RequestSpecification req=given();
        JSONObject requestParameter = new JSONObject();
        req.contentType("application/json");
        requestParameter.put( "name", "morpheus");
        requestParameter.put("job", "leader");
        req.body(requestParameter.toString());
        Response response=req.post("/api/users");
        System.out.println(response.body().asString());
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        UserDetails userDetailsResponse=responseBody.as(UserDetails.class);
        System.out.println("ID  "+userDetailsResponse.id);
        System.out.println("JOB  "+userDetailsResponse.job);
        System.out.println("Name  "+userDetailsResponse.name);
        System.out.println("Created AT  "+userDetailsResponse.createdAt);
        System.out.println("Created AT method "+userDetailsResponse.getCreatedAt());

    }
    @Test
    public void verifyCreateUserRequestWithPOJO(){
        RequestSpecification req=given();
        req.contentType("application/json");
        UserDetails usdReq=new UserDetails();
        usdReq.setJob("leader1");
        usdReq.setName("marqus");
        Response response=req.body(usdReq).post("/api/users");
        System.out.println(response.body().asString());
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        UserDetails userDetailsResponse=responseBody.as(UserDetails.class);
        System.out.println("ID  "+userDetailsResponse.id+"\nJOB  "+userDetailsResponse.job+"\nName  "+userDetailsResponse.name);
        System.out.println("Created AT  "+userDetailsResponse.createdAt);
        System.out.println("Created AT method "+userDetailsResponse.getCreatedAt());

    }
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://reqres.in/";
    }
}
