package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Like;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {
    public static LikeDTO from(Like like){
        return builder()
                .id(like.getId())
                .user(like.getUser())
                .publication(like.getPublication())
                .dateTime(like.getDateTime())
                .build();
    }

    private String id;
    private User user;
    private Publication publication;
    private LocalDateTime dateTime;
}
