package data;

import model.Player;

public class PlayerGenerator {
    public static Player generatePlayer(String uniqueFields) {
        return Player.builder()
                .age("17")
                .gender("male")
                .login(uniqueFields)
                .role("user")
                .screenName(uniqueFields)
                .password(uniqueFields)
                .build();
    }

    public static Player generatePlayerCustomAge(String uniqueFields, String age) {
        return Player.builder()
                .age(age)
                .gender("male")
                .login(uniqueFields)
                .role("user")
                .screenName(uniqueFields)
                .password(uniqueFields)
                .build();
    }
}
