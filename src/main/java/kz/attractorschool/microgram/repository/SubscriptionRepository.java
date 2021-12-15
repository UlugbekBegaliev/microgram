package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, String> {
    int countBySubscriptionEmail(String email);

    int countBySubscriberEmail(String email);

    Page<Subscription> findAllBySubscriberEmail(Pageable pageable, String email);
}
