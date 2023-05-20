import Requests.Requests;
import ResponseModels.CreateUserResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Helper.PropertiesLoader;

public class CreateUser
{
    static String username = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String token = " ";
    static String name = "morpheus";
    static String job = "leader";

    @BeforeClass
    public void loginSuccess(){
        Response loginResponse = Requests.loginRequest(PropertiesLoader.readPropertiy("validUserName") ,PropertiesLoader.readPropertiy("validPassword"));

        token = loginResponse.jsonPath().getString("token");
    }
    @Test
    public void  createUser()
    {
        Response createUserResponse = Requests.CreateUser(name,job);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(createUserResponse.statusCode(),201);
    }

}
