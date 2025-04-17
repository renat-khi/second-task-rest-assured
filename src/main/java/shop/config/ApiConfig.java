package shop.config;

import java.io.IOException;

public interface ApiConfig {

    static void readApiConfig() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("api-config.properties"));
    }

}