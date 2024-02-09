package deleteplayer;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.Map;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class DeletePlayerPositiveTests extends BaseTest {

    @Issue("It is possible to get deleted player.")
    @Test
    @Story("Check possibility to get player, that was deleted before")
    public void checkPossibilityToGetDeletedUser() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        Map<String, Integer> requestBody = Map.of("playerId", Integer.valueOf(createdPlayer.getId()));
        Response responseDelete = playerController.deletePlayer(requestBody, "supervisor");
        responseDelete.then().assertThat().statusCode(204);
        Response responseGet = playerController.getPlayer(requestBody);
        responseGet.then().assertThat().statusCode(403);
    }
}