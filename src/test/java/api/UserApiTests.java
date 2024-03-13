package api;

import model.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserApiTests extends BaseApiTest {

    /**
     * Регистрирует пользователя, если пользователь уже зарегистрирован вернет "message": "User already exists"
     */
    @Test
    public void userRegistration() {
        User user = createNewUser();
        given()
                .body(user)
                .when()
                .post("/register")
                .then()
                .statusCode(201);
    }

    /**
     * Авторизует пользователя.
     */
    @Test
    public void userLogin() {
        User user = createNewUser();
        given()
                .body(user)
                .when()
                .post("/login")
                .then()
                .statusCode(200);
    }

    /**
     * Получает token. Предварительно пользователь должен быть зарегистрирован
     */
    public static String getToken() {
        User user = createNewUser();
        return given()
                .body(user)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("access_token");
    }

    private static User createNewUser() {
        return new User("IvanIvanov", "1234");
    }

}