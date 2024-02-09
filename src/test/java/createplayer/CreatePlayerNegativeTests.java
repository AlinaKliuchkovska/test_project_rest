package createplayer;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTest;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class CreatePlayerNegativeTests extends BaseTest {

    @Test
    @Story("Check possibility to create player with no permission")
    public void checkCreatePlayerWithNoPermissions() {
        var uniqueFields = createRandomString();
        Response response = playerController.createPlayer(generatePlayer(uniqueFields), "user");
        response.then().assertThat().statusCode(403);
    }
}