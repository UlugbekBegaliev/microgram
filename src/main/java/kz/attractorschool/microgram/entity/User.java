package kz.attractorschool.microgram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    private Integer countOfPosts = 0;
    private Integer countOfFollowers = 0;
    private Integer countOfSubscribe = 0;

}
