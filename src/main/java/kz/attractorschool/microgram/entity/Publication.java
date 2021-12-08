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
@Document(collection = "publications")
@Data
public class Publication {

    @Id
    private String id;
    private String image;
    private String description;
    private LocalDateTime dateTime;
    @Indexed
    private int numOfLikes;
    private int numOfComments;
    @DBRef
    private User user;

    public Publication(String description, User user, String image) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.dateTime = LocalDateTime.now();
        this.user = user;
        this.image = image;
    }
}
