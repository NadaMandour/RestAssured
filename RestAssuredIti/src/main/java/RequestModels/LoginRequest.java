package RequestModels;


import com.fasterxml.jackson.annotation.*;

@JsonPropertyOrder({
        "email",
        "password"
})
@JsonIgnoreProperties(ignoreUnknown = true)//  if property not exist don't send it as a null
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {

    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;

}