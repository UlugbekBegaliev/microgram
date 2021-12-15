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
@Document(collection = "comments")
@Data
public class Comment {

    @Id
    private String id;
    @Indexed
    private String path;
    @DBRef
    private Publication publication;
    private LocalDateTime dateTime;
    private String text;
    @Indexed
    private User commenter;

    public Comment(Publication publication, User commenter, String text) {
        this.id = UUID.randomUUID().toString();
        this.publication = publication;
        this.dateTime = LocalDateTime.now();
        this.commenter = commenter;
        this.text = text;
    }

    public Comment(String path, Publication publication, User commenter, String text) {
        new Comment(publication, commenter, text);
        this.path = path;
    }
}
