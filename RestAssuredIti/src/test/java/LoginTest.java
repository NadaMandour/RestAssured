import Helper.PropertiesLoader;
import Requests.Requests;
import ResponseModels.LoginErrorResponse;
import ResponseModels.LoginResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTest {



//    static String singleUserEndPoint = "/users/2";
//    static String loginEndPoint = "/login";
//    static String baseUrl = "https://reqres.in/api";
  //  static String username = "eve.holt@reqres.in";
   // static String password = "cityslicka";



@Test
    public static void successLogin ()
{
 Response loResponse= Requests.loginRequest(PropertiesLoader.readPropertiy("validUserName"),PropertiesLoader.readPropertiy("validPassword"));
    SoftAssert softAssert =new SoftAssert();
    JsonPath loginJsonPath = loResponse.jsonPath();
    softAssert.assertEquals(loResponse.statusCode(),200," status code ");

    LoginResponse loginResponse =loResponse.as(LoginResponse.class); // using POJO class instead of jsonpath
  softAssert.assertNotNull(loginResponse.token);


  softAssert.assertNotNull(loginJsonPath.getString("token"));
    softAssert.assertAll();
}
    @Test
    public static void failLogin ()
    {
        Response loResponse= Requests.loginRequest("","password");
        SoftAssert softAssert =new SoftAssert();

        LoginErrorResponse loginErrorResponse = loResponse.as(LoginErrorResponse.class);
        softAssert.assertEquals(loginErrorResponse.error,"Missing email or username");


        JsonPath loginJsonPath = loResponse.jsonPath();
        softAssert.assertNotEquals(loResponse.statusCode(),200," status code ");
        softAssert.assertNull(loginJsonPath.getString("token"));
        softAssert.assertAll();
    }






}