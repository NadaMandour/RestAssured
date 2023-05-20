package Requests;

import Helper.Helper;
import Helper.PropertiesLoader;
import Helper.EndPoints;
import RequestModels.CreateUserRequest;
import RequestModels.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Requests {
    static String loginEndPoint = "/login";
    //static String baseUrl = "https://reqres.in/api";
    static String usersEndPoint = "/users";
    static String singleUserEndPoint = "/users/";
    static String name = "morpheus";
    static String job = "leader";



    public  static Response loginRequest(String username, String password)
    {

      /*  LoginResponse loginResponse = RestAssured.given().log().all().contentType("application/json").body("{\n" +
                "    \"email\": \""+username+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}").post(baseUrl+loginEndPoint).as(LoginResponse.class);
        */

        LoginRequest loginRequest= new LoginRequest();
        loginRequest.email=username;
        loginRequest.password=password;

        // transfer object to string
      // String payLoad =Helper.getObjectAsString(loginRequest); // to be added in the body



        Response loginResponse = RestAssured.given().log().all().
                contentType("application/json").body(Helper.getObjectAsString(loginRequest)).
                post(PropertiesLoader.readPropertiy("baseUrl")+EndPoints.loginEndPoint);

       //loginResponse.as(LoginResponse.class);
       // Map the response to POJO instead of mapping into model so this class will have the response data
        // instead of using json path and normal Response which is model it returns object

       JsonPath loginJsonPath = loginResponse.jsonPath();
        String token = loginJsonPath.getString("token");

        return loginResponse;
    }

    public static Response listUsersRequset ( String token, String page)
    {
        Map<String ,String> headers = new HashMap<>();
        headers.put("Authorization", token);
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("page",page);
        queryParams.put("page","3");

        Response listUsersResponse = RestAssured.given().log().all().headers(headers)
                .queryParams(queryParams).get(PropertiesLoader.readPropertiy("baseUrl"),EndPoints.userEndPoint);


        return listUsersResponse;

    }
  public static Response SingleUser (String id)
  {
      Response singleUserResponse =RestAssured.given().log().all().get(PropertiesLoader.readPropertiy("baseUrl")+singleUserEndPoint+id);


      return singleUserResponse ;
  }

  public static Response CreateUser (String name , String job)
  {
      CreateUserRequest createUserRequest =new CreateUserRequest();
      createUserRequest.job=job;
      createUserRequest.name=name;

      Response createUserResponse = RestAssured.given().log().all()
              .contentType("application/json").body(Helper.getObjectAsString(createUserRequest)).post(PropertiesLoader.readPropertiy("baseUrl")+usersEndPoint);

      return  createUserResponse;
  }


}
