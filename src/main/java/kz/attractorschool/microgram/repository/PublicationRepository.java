package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface PublicationRepository extends MongoRepository<Publication, String> {
    int countByUserEmail(String email);

    Page<Publication> findAllByUserEmail(Pageable pageable, String email);
}
