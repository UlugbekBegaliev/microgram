package kz.attractorschool.microgram.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "images")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Image {

    @Id
    private String id;
}
