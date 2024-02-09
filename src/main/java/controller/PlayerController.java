package controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Player;

import java.util.Map;

public class PlayerController {

    public Response createPlayer(Player player, String editor) {
        return RestAssured.given()
                .queryParam("age", player.getAge())
                .queryParam("gender", player.getGender())
                .queryParam("login", player.getLogin())
                .queryParam("password", player.getPassword())
                .queryParam("role", player.getRole())
                .queryParam("screenName", player.getScreenName())
                .get("/player/create/{0}", editor);
    }

    public Response deletePlayer(Map<String, Integer> request, String editor) {
        return RestAssured.given()
                .contentType("application/json")
                .body(request)
                .delete("/player/delete/{0}", editor);
    }
    public Response getPlayer(Map<String, Integer> request) {
        return RestAssured.given()
                .contentType("application/json")
                .body(request)
                .post("/player/get");
    }

    public Response getAllPlayers() {
        return RestAssured.given()
                .contentType("application/json")
                .get("/player/get/all");
    }

    public Response updatePlayer(Player player, String editor, String id) {
        return RestAssured.given()
                .contentType("application/json")
                .body(player)
                .patch("/player/update/{0}/{1}", editor, id);
    }
}
