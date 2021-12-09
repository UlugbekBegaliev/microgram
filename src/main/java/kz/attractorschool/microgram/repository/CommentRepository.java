package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface CommentRepository extends MongoRepository<Comment, String> {
    int countByPublicationId(String publicationId);

    Slice<Comment> findAllByPublicationId(Pageable pageable, String publicationId);
}
