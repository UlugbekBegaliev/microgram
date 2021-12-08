package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Like;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {

    private String id;
    private User userLiker;
    private Publication publication;
    private LocalDateTime dateTime;

    public static LikeDTO from(Like like) {
        return builder()
                .id(like.getId())
                .userLiker(like.getUserLiker())
                .publication(like.getPublication())
                .dateTime(like.getDateTime())
                .build();
    }
}

