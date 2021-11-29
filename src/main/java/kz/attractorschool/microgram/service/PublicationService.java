package kz.attractorschool.microgram.service;


import kz.attractorschool.microgram.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PublicationService {
    private PublicationRepository publicationRepository;
    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
}
