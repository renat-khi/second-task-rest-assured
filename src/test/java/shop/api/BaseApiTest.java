package shop.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import shop.config.ApiConfig;

import java.io.IOException;

public abstract class BaseApiTest {

    @BeforeClass
    public void prepare() throws IOException {
        initConfig();
        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле 'api-api-config.properties' отсутствует значение 'base.uri'");
        }
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.filters(new ResponseLoggingFilter());
    }

    private void initConfig() throws IOException {
        ApiConfig.readApiConfig();
    }

}