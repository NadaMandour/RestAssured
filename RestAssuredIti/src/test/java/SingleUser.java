import Helper.PropertiesLoader;
import Requests.Requests;
import ResponseModels.LoginResponse;
import ResponseModels.SingleUserResponse.SingleUserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class SingleUser {

    static String username = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String token = " ";


    @BeforeClass
    public void loginSuccess(){
        Response loginResponse = Requests.loginRequest( PropertiesLoader.readPropertiy("validUserName") ,PropertiesLoader.readPropertiy("validPassword"));
        LoginResponse loginResponse1= new LoginResponse();
        token = loginResponse1.token;
    }
    @Test
    void singleUserSuccess (){
        Response singleUser =Requests.SingleUser("2");
        SingleUserResponse singleUserResponse = singleUser.as(SingleUserResponse.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(singleUserResponse.data.firstName,"");


    }

}
