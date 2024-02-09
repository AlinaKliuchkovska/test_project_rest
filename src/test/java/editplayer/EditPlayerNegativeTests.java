package editplayer;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.annotations.Test;
import utils.BaseTest;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class EditPlayerNegativeTests extends BaseTest {

    @Test
    @Story("Check possibility to edit player login by existed login")
    public void checkEditPlayerLoginByExistedLogin() {
        var uniqueFieldsFirstPlayer = createRandomString();
        Player firstPlayer = generatePlayer(uniqueFieldsFirstPlayer);
        var uniqueFieldsSecondPlayer = createRandomString();
        Player secondPlayer = generatePlayer(uniqueFieldsSecondPlayer);
        Response responseCreateFirstPlayer = playerController.createPlayer(firstPlayer, "supervisor");
        responseCreateFirstPlayer.then().assertThat().statusCode(200);
        Player createdFirstPlayer = responseCreateFirstPlayer.as(Player.class);
        Response responseCreateSecondPlayer = playerController.createPlayer(secondPlayer, "supervisor");
        responseCreateSecondPlayer.then().assertThat().statusCode(200);
        Player createdSecondPlayer = responseCreateSecondPlayer.as(Player.class);
        createdSecondPlayer.setLogin(createdFirstPlayer.getLogin());
        Response responseUpdate = playerController.updatePlayer(createdSecondPlayer, "supervisor", createdSecondPlayer.getId());
        responseUpdate.then().assertThat().statusCode(409);
    }

    @Issue("It is possible to edit Player Id.")
    @Test
    @Story("Check possibility to edit player Id")
    public void checkPossibilityEditPlayerId() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreateFirstPlayer = playerController.createPlayer(player, "supervisor");
        responseCreateFirstPlayer.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreateFirstPlayer.as(Player.class);
        createdPlayer.setId(createdPlayer.getId() + 1);
        Response responseUpdate = playerController.updatePlayer(createdPlayer, "supervisor", createdPlayer.getId());
        responseUpdate.then().assertThat().statusCode(403);
    }
}