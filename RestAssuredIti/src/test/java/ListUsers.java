import Helper.PropertiesLoader;
import Requests.Requests;
import ResponseModels.ListUsersResponse.ListUsersResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUsers {




    static String singleUserEndPoint = "/users/2";
    static String loginEndPoint = "/login";
    static String usersEndPoint = "/users";
    static String baseUrl = "https://reqres.in/api";
    static String username = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String token = " ";



     @BeforeClass
    public void loginSuccess(){
        Response loginResponse = Requests.loginRequest( PropertiesLoader.readPropertiy("validUserName") ,PropertiesLoader.readPropertiy("validPassword"));
                token = loginResponse.jsonPath().getString("token");
     }
    @Test
    public void listUsersSuccess(){

        Response listUsersResponse= Requests.listUsersRequset(token,"2");


        SoftAssert softAssert =new SoftAssert();
        softAssert.assertEquals(listUsersResponse.statusCode(),200," status code ");

        ListUsersResponse listUsersResponse1 =listUsersResponse.as(ListUsersResponse.class); // to be POJO class
        for(int i=0; i<listUsersResponse1.data.size();i++) {
            softAssert.assertNotNull(listUsersResponse1.data.get(i).firstName);
        }



        JsonPath listUsersJsonPath = listUsersResponse.jsonPath();
        List<Map<String,String>> usersList=listUsersJsonPath.getList("data");
        for(int i=0; i<usersList.size();i++)
        {
            softAssert.assertNotNull((usersList.get(i).get("first_name")));
            softAssert.assertNotNull(listUsersJsonPath.getString("data["+i+"].first_name"));

        }

        softAssert.assertEquals(listUsersJsonPath.getInt("per_page"),6," per page ");
        softAssert.assertEquals(listUsersJsonPath.getString("total"),"12"," total ");
        softAssert.assertEquals(listUsersJsonPath.getList("data").size(),6, "list size");
        softAssert.assertEquals(listUsersJsonPath.getString("data[0].id"),"7"," first id ");

        softAssert.assertAll();









    }




}
