package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Image;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, String> {
}
