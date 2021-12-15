package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Publication;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;


@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    private String id;
    private String image;
    private String description;
    private int numOfLikes;
    private int numOfComments;
    private LocalDateTime dateTime;


    public static PublicationDTO from(Publication publication) {
        return builder()
                .id(publication.getId())
                .image(publication.getImage())
                .description(publication.getDescription())
                .numOfLikes(publication.getNumOfLikes())
                .numOfComments(publication.getNumOfComments())
                .dateTime(publication.getDateTime())
                .image(publication.getImage())
                .build();
    }
}
