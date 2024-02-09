package getplayer;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.Map;

import static utils.RandomGenerator.createRandomInteger;

public class GetPlayerNegativeTests extends BaseTest {

    @Issue("When try to get player with random Id received status code 200 without body.")
    @Test
    @Story("Check possibility to get player by random generated Id")
    public void checkGetPlayerByRandomId() {
        Map<String, Integer> getPlayerRequestBody = Map.of("playerId", createRandomInteger());
        Response response = playerController.getPlayer(getPlayerRequestBody);
        response.then().assertThat().statusCode(403);
    }
}