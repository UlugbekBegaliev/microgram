package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
