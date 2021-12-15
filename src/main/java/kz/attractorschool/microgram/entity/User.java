package kz.attractorschool.microgram.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection = "users")
@Data
public class User {

    @Indexed
    private String username;
    private String fullName;
    @Id
    private String email;
    private String password;
    private int numOfPublications = 0;
    private int numOfFollowers;
    private int numOfFollowings;

    public User(String username, String fullName, String email, String password) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
}
