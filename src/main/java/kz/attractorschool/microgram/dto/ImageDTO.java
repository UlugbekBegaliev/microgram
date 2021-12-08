package kz.attractorschool.microgram.dto;

import kz.attractorschool.microgram.entity.Image;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ImageDTO {

    @Id
    @Indexed
    private String imageId;

    public static ImageDTO from(Image image){
        return builder()
                .imageId(image.getId())
                .build();
    }
}
