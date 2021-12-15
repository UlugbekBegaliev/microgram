package kz.attractorschool.microgram.util;

import kz.attractorschool.microgram.entity.*;
import kz.attractorschool.microgram.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
public class InitDatabase {

    private final PasswordEncoder encoder;

    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final PublicationRepository publicationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner fillData(CommentRepository commentRepository,
                                      LikeRepository likeRepository,
                                      PublicationRepository publicationRepository,
                                      SubscriptionRepository subscriptionRepository,
                                      UserRepository userRepository) {
        return (args) -> {
            commentRepository.deleteAll();
            likeRepository.deleteAll();
            publicationRepository.deleteAll();;
            subscriptionRepository.deleteAll();
            userRepository.deleteAll();

            List<Comment> commentList = new ArrayList<>();
            List<Like> likeList = new ArrayList<>();
            List<Publication> publicationList = new ArrayList<>();
            List<Subscription> subscriptionList = new ArrayList<>();
            List<User> userList = new ArrayList<>();




            User user1 = new User();
            user1.setEmail("test@test");
            user1.setPassword(encoder.encode("test"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setEmail("guest@test");
            user2.setPassword(encoder.encode("guest"));
            userRepository.save(user2);

            User user3 = new User();
            user3.setEmail("admin@test");
            user3.setPassword(encoder.encode("admin"));
            userRepository.save(user3);

        };
    }
}
