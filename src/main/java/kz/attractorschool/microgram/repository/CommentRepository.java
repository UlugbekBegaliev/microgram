package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
