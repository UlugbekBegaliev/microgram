package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Comment;
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
public class CommentDTO {

    private String id;
    private User commenter;
    private LocalDateTime dateTime;
    private String path;
    private Publication publication;
    private String text;


    public static CommentDTO from(Comment comment) {
        return builder()
                .id(comment.getId())
                .commenter(comment.getCommenter())
                .dateTime(comment.getDateTime())
                .path(comment.getPath())
                .publication(comment.getPublication())
                .text(comment.getText())
                .build();
    }
}
