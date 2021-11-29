package kz.attractorschool.microgram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document (collection = "likes")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Like {

    @Id
    private String id;
    private User user;
    private Publication publication;
    private LocalDate localDate;
}
