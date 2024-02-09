package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String getBaseURI() {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
            return properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
