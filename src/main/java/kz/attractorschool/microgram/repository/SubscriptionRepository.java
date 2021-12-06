package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
    int countBySubscriptionEmail(String email);
    int countBySubscriberEmail(String email);
    Page<Subscription> findAllBySubscriberEmail(Pageable pageable, String email);
}
