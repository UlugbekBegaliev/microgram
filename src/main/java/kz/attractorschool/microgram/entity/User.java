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

    @Id
    private String id;
    @Indexed
    private String username;
    private String fullName;
    private String email;
    private String password;
    private int numOfPublications = 0;
    private int numOfFollowers;
    private int numOfFollowings;

    public User(String fullName, String username, String email, String password) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
