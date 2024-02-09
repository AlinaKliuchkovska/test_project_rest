package getallplayers;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.List;
import java.util.Map;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class GetAllPlayersNegativeTests extends BaseTest {

    @Test
    @Story("Check that player, that deleted before is not exist at all players list")
    public void checkThatDeletedPlayerNotExistAtAllPlayersList() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        Map<String, Integer> deletingRequestBody = Map.of("playerId", Integer.valueOf(createdPlayer.getId()));
        Response responseDelete = playerController.deletePlayer(deletingRequestBody, "supervisor");
        responseDelete.then().assertThat().statusCode(204);
        Response responseGetAll = playerController.getAllPlayers();
        List<Player> allPlayersList = responseGetAll.jsonPath().getList("players", Player.class);
        boolean isCreatedPlayerExistInAll = allPlayersList.stream().noneMatch(p -> p.getId().equals(createdPlayer.getId()));
        Assert.assertTrue(isCreatedPlayerExistInAll);
    }
}