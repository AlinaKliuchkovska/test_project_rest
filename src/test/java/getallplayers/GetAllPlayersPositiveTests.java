package getallplayers;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class GetAllPlayersPositiveTests extends BaseTest {

    @Test
    @Story("Check that created player is exist at all players list")
    public void checkThatCreatedPlayerExistAtAllPlayersList() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        Response responseGetAll = playerController.getAllPlayers();
        List<Player> allPlayersList = responseGetAll.jsonPath().getList("players", Player.class);
        List<Player> playersWithCurrentId = allPlayersList.stream().filter(p -> p.getId().equals(createdPlayer.getId())).collect(Collectors.toList());
        Assert.assertEquals(playersWithCurrentId.size(), 1);
    }
}