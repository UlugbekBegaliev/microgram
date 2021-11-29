package kz.attractorschool.microgram.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "subscriptions")
@EqualsAndHashCode
public class Subscription {

    @Id
    private String id;
    private User user;
    private User toUser;
    private LocalDate date;


}
