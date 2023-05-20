package Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public static String readPropertiy (String key)
    {
        Properties properties = new Properties();
        try {
            properties.load(new BufferedReader(new FileReader("src/main/resources/config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }



}
