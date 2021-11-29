package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.repository.SubscriptionRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionService {
    private UserRepository userRepository;
    private SubscriptionRepository subscriptionRepository;
}
