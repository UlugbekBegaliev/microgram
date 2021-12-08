package kz.attractorschool.microgram.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection = "subscriptions")
@Data
public class Subscription {

    @Id
    private String id;
    @DBRef
    private User follower;
    @DBRef
    private User following;
    private LocalDateTime dateTime;

    public Subscription(User follower, User following) {
        this.id = UUID.randomUUID().toString();
        this.follower = follower;
        this.following = following;
        this.dateTime = LocalDateTime.now();
    }
}
