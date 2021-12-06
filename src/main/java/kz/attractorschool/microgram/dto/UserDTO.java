package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {
    public static UserDTO from(User user) {
        return builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    private String fullName;
    private String username;
    private String email;
    private String password;
    private int numOfFollowers;
    private int numOfFollowings;

}
