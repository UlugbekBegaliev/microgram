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
            commentList.add(new Comment(publicationList.get(0), userList.get(2), "ok"));


            List<Like> likeList = new ArrayList<>();
            List<Publication> publicationList = new ArrayList<>();
            List<Subscription> subscriptionList = new ArrayList<>();
            List<User> userList = new ArrayList<>();
        };
    }
}
