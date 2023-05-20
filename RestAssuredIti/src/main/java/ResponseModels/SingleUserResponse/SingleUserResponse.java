package ResponseModels.SingleUserResponse;

import ResponseModels.ListUsersResponse.Data;
import ResponseModels.ListUsersResponse.Support;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "support"
})
public class SingleUserResponse {

    @JsonProperty("data")
    public Data data;
    @JsonProperty("support")
    public Support support;

}