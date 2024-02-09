package deleteplayer;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.Map;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class DeletePlayerNegativeTests extends BaseTest{

    @Test
    @Story("Check possibility to delete player, that was deleted before")
    public void checkPossibilityDeletePlayerThatWasDeletedBefore() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        Map<String, Integer> deletingRequestBody = Map.of("playerId", Integer.valueOf(createdPlayer.getId()));
        Response responseDelete = playerController.deletePlayer(deletingRequestBody, "supervisor");
        responseDelete.then().assertThat().statusCode(204);
        Response responseAfterDeletion = playerController.deletePlayer(deletingRequestBody, "supervisor");
        responseAfterDeletion.then().assertThat().statusCode(403);
    }
}