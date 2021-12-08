package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Subscription;
import kz.attractorschool.microgram.entity.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscriptionDTO {

    private String id;
    private User follower;
    private User following;
    private LocalDateTime dateTime;

    public static SubscriptionDTO from(Subscription subscription){
        return builder()
                .id(subscription.getId())
                .follower(subscription.getFollower())
                .following(subscription.getFollowing())
                .dateTime(subscription.getDateTime())
                .build();
    }
}
