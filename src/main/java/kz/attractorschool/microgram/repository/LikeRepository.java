package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Like;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends PagingAndSortingRepository<Like, String> {
    int countByPublicationId(String publicationId);

    Page<Like> findAllByPublicationId(Pageable pageable, String publicationId);

    Page<Like> findAllByLikerEmail(Pageable pageable, String email);
}
