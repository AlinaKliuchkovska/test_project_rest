package createplayer;

import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import model.Player;
import org.testng.annotations.Test;
import utils.BaseTest;

import static data.PlayerGenerator.generatePlayer;
import static data.PlayerGenerator.generatePlayerCustomAge;
import static utils.RandomGenerator.createRandomString;

public class CreatePlayerPositiveTests extends BaseTest {

    @Issue("It is not possible to create user by admin.")
    @Test
    @Story("Check possibility to create player with admin permissions")
    public void checkCreatePlayerWithAdminPermissions() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response response = playerController.createPlayer(player, "admin");
        response.then().assertThat().statusCode(200);
    }

    @Issue("There is no error when creating the user with login, which already exists.")
    @Test
    @Story("Check possibility to create player with login, that existed")
    public void checkCreatePlayerWithExistedLogin() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response responseFirstCreation = playerController.createPlayer(player, "supervisor");
        responseFirstCreation.then().assertThat().statusCode(200);
        Response responseSecondCreation = playerController.createPlayer(player, "supervisor");
        responseSecondCreation.then().assertThat().statusCode(403);
    }

    @Issue("When creating new player, only login is set up, rest of fields are null.")
    @Test
    @Story("Check create player")
    public void checkCreatePlayer() {
        var uniqueFields = createRandomString();
        Player player = generatePlayer(uniqueFields);
        Response response = playerController.createPlayer(generatePlayer(uniqueFields), "supervisor");
        response.then().assertThat().statusCode(200);
        Player createdPlayer = response.as(Player.class);
        softAssert.assertEquals(createdPlayer.getAge(), player.getAge());
        softAssert.assertEquals(createdPlayer.getGender(), player.getGender());
        softAssert.assertEquals(createdPlayer.getLogin(), player.getLogin());
        softAssert.assertEquals(createdPlayer.getRole(), player.getRole());
        softAssert.assertEquals(createdPlayer.getScreenName(), player.getScreenName());
        softAssert.assertAll();
    }

    @Issue("It is not possible to create user with age 16.")
    @Test
    @Story("Check possibility to create player with age on bound of permissible")
    public void checkCreatePlayerWithAgeOnBoundOfPermissible() {
        var uniqueFields = createRandomString();
        Player player = generatePlayerCustomAge(uniqueFields, "16");
        Response response = playerController.createPlayer(player, "supervisor");
        response.then().assertThat().statusCode(200);
    }
}