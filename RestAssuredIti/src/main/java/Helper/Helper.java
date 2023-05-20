package Helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

    public static String getObjectAsString(Object object)
    {
        // map objects to string " serialization" to use in body request
        ObjectMapper objectMapper= new ObjectMapper();
        String payLoad="";
        try {
            payLoad=  objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payLoad;
    }


}
