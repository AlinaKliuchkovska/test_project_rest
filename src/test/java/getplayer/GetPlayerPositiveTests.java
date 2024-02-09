package getplayer;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.util.Map;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class GetPlayerPositiveTests extends BaseTest {

    @Test
    @Story("Check get player")
    public void checkGetPlayer() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        Map<String, Integer> getCreatedPlayer = Map.of("playerId", Integer.valueOf(createdPlayer.getId()));
        Response responseGet = playerController.getPlayer(getCreatedPlayer);
        responseCreate.then().assertThat().statusCode(200);
        Player thatPlayerAfterGet = responseGet.as(Player.class);
        softAssert.assertEquals(player.getAge(), thatPlayerAfterGet.getAge());
        softAssert.assertEquals(player.getGender(), thatPlayerAfterGet.getGender());
        softAssert.assertEquals(player.getLogin(), thatPlayerAfterGet.getLogin());
        softAssert.assertEquals(player.getRole(), thatPlayerAfterGet.getRole());
        softAssert.assertEquals(player.getScreenName(), thatPlayerAfterGet.getScreenName());
        softAssert.assertAll();
    }
}