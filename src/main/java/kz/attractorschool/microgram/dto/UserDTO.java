package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {

    private String id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private int numOfFollowers;
    private int numOfFollowings;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .numOfFollowers(user.getNumOfFollowers())
                .numOfFollowings(user.getNumOfFollowings())
                .build();
    }
}
