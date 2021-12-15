package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {

    private String username;
    private String fullName;
    private String email;
    private String password;
    private int numOfPublications;
    private int numOfFollowers;
    private int numOfFollowings;

    public static UserDTO from(User user) {
        return builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .password(user.getPassword())
                .numOfPublications(user.getNumOfPublications())
                .numOfFollowers(user.getNumOfFollowers())
                .numOfFollowings(user.getNumOfFollowings())
                .build();
    }
}
