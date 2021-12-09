package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.SubscriptionDTO;
import kz.attractorschool.microgram.entity.Subscription;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.SubscriptionRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SubscriptionService {
    private final String SYSTEM_RES = "«Не удается найти пользователя с таким именем: ";

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public SubscriptionDTO addSubscription(String followerName, String followingName) {
        User follower = userRepository.findByUsername(followerName)
                .orElseThrow(() -> new ResourceNotFoundException(SYSTEM_RES + followerName));

        User following = userRepository.findByUsername(followingName)
                .orElseThrow(() -> new ResourceNotFoundException(SYSTEM_RES + followingName));

        Subscription subscription = Subscription
                .builder()
                .id(UUID.randomUUID().toString())
                .follower(follower)
                .following(following)
                .dateTime(LocalDateTime.now())
                .build();

        subscriptionRepository.save(subscription);

        return SubscriptionDTO.from(subscription);
    }

    public boolean removeSubscription(String subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
        return true;
    }
}
