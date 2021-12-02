package kz.attractorschool.microgram.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document (collection = "comments")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comment {

    @Id
    private String id;

    private Publication publication;
    private String message;
    private LocalDate localDate;

    @Indexed
    private User user;
}
