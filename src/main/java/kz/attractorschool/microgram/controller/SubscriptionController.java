package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.SubscriptionDTO;
import kz.attractorschool.microgram.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{followingName}")
    public SubscriptionDTO subscribe(Authentication authentication, @PathVariable String followingName) {
        String followerName = authentication.getName();
        return subscriptionService.addSubscription(followerName, followingName);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> unsubscribe(@PathVariable String subscriptionId) {
        if (subscriptionService.removeSubscription(subscriptionId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
