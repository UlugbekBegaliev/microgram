package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
    int countByPublicationId(String publicationId);

    Slice<Comment> findAllByPublicationId(Pageable pageable, String publicationId);
}
