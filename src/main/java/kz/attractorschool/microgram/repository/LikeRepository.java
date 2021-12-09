package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface LikeRepository extends MongoRepository<Like, String> {
    int countByPublicationId(String publicationId);

    Page<Like> findAllByPublicationId(Pageable pageable, String publicationId);

    Page<Like> findAllByLikerEmail(Pageable pageable, String email);
}
