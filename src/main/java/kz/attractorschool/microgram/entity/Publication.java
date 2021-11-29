package kz.attractorschool.microgram.entity;


import kz.attractorschool.microgram.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document (collection = "publications")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Publication {

    @Id
    private String id;
    private String pathPicture;
    private String description;
    private LocalDate date;
}
