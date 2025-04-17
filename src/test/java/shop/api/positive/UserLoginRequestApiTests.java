package shop.api.positive;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import shop.api.BaseApiTest;
import shop.model.UserLoginRequest;
import shop.service.MainResponseActions;
import shop.service.user.UserRequestActions;

public class UserLoginRequestApiTests extends BaseApiTest {

    @Test
    public void userRegistration() {
        UserLoginRequest userLoginRequest = createNewUser();
        Response response = UserRequestActions.registration(userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 201);
    }

    @Test
    public void userLogin() {
        UserLoginRequest userLoginRequest = createNewUser();
        Response response = UserRequestActions.login(userLoginRequest);
        MainResponseActions.shouldHaveStatus(response, 200);
    }

    public static UserLoginRequest createNewUser() {
        return new UserLoginRequest("IvanIvanov", "1234");
    }

}