package kz.attractorschool.microgram.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document(collection = "likes")
@Data
public class Like {

    @Id
    private String id;
    @Indexed
    private User userLiker;
    @DBRef
    private Publication publication;
    private LocalDateTime dateTime;

    public Like(User userLiker, Publication publication) {
        this.id = UUID.randomUUID().toString();
        this.userLiker = userLiker;
        this.publication = publication;
        this.dateTime = LocalDateTime.now();
    }
}
