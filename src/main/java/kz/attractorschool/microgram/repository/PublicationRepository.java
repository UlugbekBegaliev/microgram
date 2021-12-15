package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Publication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PublicationRepository extends PagingAndSortingRepository<Publication, String> {
    int countByUserEmail(String email);

    Page<Publication> findAllByUserEmail(Pageable pageable, String email);
}
