package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @JsonProperty
    private String age;
    @JsonProperty
    private String gender;
    @JsonProperty
    private String login;
    @JsonProperty
    private String password;
    @JsonProperty
    private String role;
    @JsonProperty
    private String screenName;
    @JsonProperty
    private String id;
}
