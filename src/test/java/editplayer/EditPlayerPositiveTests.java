package editplayer;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import static data.PlayerGenerator.generatePlayer;
import static utils.RandomGenerator.createRandomString;

public class EditPlayerPositiveTests extends BaseTest {

    @Issue("Role is not updated, but response 200.")
    @Test
    @Story("Check possibility to edit player role")
    public void checkEditPlayerRole() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseCreate = playerController.createPlayer(player, "supervisor");
        responseCreate.then().assertThat().statusCode(200);
        Player createdPlayer = responseCreate.as(Player.class);
        player.setRole("admin");
        Response responseUpdate = playerController.updatePlayer(player, "supervisor", createdPlayer.getId());
        responseUpdate.then().assertThat().statusCode(200);
        Player updatedPlayer = responseUpdate.as(Player.class);
        Assert.assertEquals(updatedPlayer.getRole(), player.getRole());
    }
}