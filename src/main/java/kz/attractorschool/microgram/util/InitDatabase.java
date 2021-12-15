package kz.attractorschool.microgram.util;

import java.util.stream.Stream;

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
            publicationRepository.deleteAll();
            ;
            subscriptionRepository.deleteAll();
            userRepository.deleteAll();

            List<User> userList = new ArrayList<>();
            userList.add(new User("akai", "Akaev Askar", "aakaev@gmail.com", encoder.encode("password")));
            userList.add(new User("baks", "Bakiev Kurmanbek", "kbakiev@gmail.com", encoder.encode("password")));
            userList.add(new User("rouz", "Otunbaeva Roza", "rotunbaeva@inbox.ru", encoder.encode("password")));
            userList.add(new User("asha", "Atambaev Almazbek", "aatambaev@mail.ru", encoder.encode("password")));
            userList.add(new User("sake", "Japarov Sadyr", "sjaparov@yahoo.com", encoder.encode("password")));
            userList.add(new User("lupik", "Luparikov Mercedes", "mluparikov@yahoo.com", encoder.encode("password")));
            userList.add(new User("pila", "Pilotova Honda", "hpilotova@gmail.com", encoder.encode("password")));
            userList.add(new User("musa", "Mustangov Ford", "fmustangov@gmail.com", encoder.encode("password")));
            userList.add(new User("ilon", "Maskova Tesla", "tmaskova@inbox.ru", encoder.encode("password")));
            userList.add(new User("ulug", "Begaliev Ulugbek", "ubegaliev@gmail.com", encoder.encode("password")));

            Stream.of(userList).peek(users -> users.forEach(System.out::println)).forEach(userRepository::saveAll);

            List<Publication> publicationList = new ArrayList<>();
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(1), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(2), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(3), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(4), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(5), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(6), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(7), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(8), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(9), "image"));
            publicationList.add(new Publication(Generator.makeDescription(), userList.get(10), "image"));

            Stream.of(publicationList).peek(publications -> publications.forEach(System.out::println)).forEach(publicationRepository::saveAll);

            List<Comment> commentList = new ArrayList<>();
            commentList.add(new Comment(publicationList.get(1), userList.get(1), "comment"));
            commentList.add(new Comment(publicationList.get(2), userList.get(2), "comment"));
            commentList.add(new Comment(publicationList.get(3), userList.get(3), "comment"));
            commentList.add(new Comment(publicationList.get(4), userList.get(4), "comment"));
            commentList.add(new Comment(publicationList.get(5), userList.get(5), "comment"));
            commentList.add(new Comment(publicationList.get(6), userList.get(6), "comment"));
            commentList.add(new Comment(publicationList.get(7), userList.get(7), "comment"));
            commentList.add(new Comment(publicationList.get(8), userList.get(8), "comment"));
            commentList.add(new Comment(publicationList.get(9), userList.get(9), "comment"));
            commentList.add(new Comment(publicationList.get(10), userList.get(10), "comment"));

            Stream.of(commentList).peek(comments -> comments.forEach(System.out::println)).forEach(commentRepository::saveAll);

            List<Like> likeList = new ArrayList<>();
            likeList.add(new Like(userList.get(4), publicationList.get(1)));
            likeList.add(new Like(userList.get(9), publicationList.get(2)));
            likeList.add(new Like(userList.get(7), publicationList.get(10)));
            likeList.add(new Like(userList.get(10), publicationList.get(7)));
            likeList.add(new Like(userList.get(6), publicationList.get(9)));
            likeList.add(new Like(userList.get(8), publicationList.get(4)));
            likeList.add(new Like(userList.get(5), publicationList.get(3)));
            likeList.add(new Like(userList.get(2), publicationList.get(5)));
            likeList.add(new Like(userList.get(3), publicationList.get(6)));
            likeList.add(new Like(userList.get(1), publicationList.get(8)));

            Stream.of(likeList).peek(likes -> likes.forEach(System.out::println)).forEach(likeRepository::saveAll);

            List<Subscription> subscriptionList = new ArrayList<>();
            subscriptionList.add(new Subscription(userList.get(1), userList.get(10)));
            subscriptionList.add(new Subscription(userList.get(2), userList.get(9)));
            subscriptionList.add(new Subscription(userList.get(3), userList.get(8)));
            subscriptionList.add(new Subscription(userList.get(4), userList.get(7)));
            subscriptionList.add(new Subscription(userList.get(5), userList.get(6)));
            subscriptionList.add(new Subscription(userList.get(6), userList.get(5)));
            subscriptionList.add(new Subscription(userList.get(7), userList.get(4)));
            subscriptionList.add(new Subscription(userList.get(8), userList.get(3)));
            subscriptionList.add(new Subscription(userList.get(9), userList.get(2)));
            subscriptionList.add(new Subscription(userList.get(10), userList.get(1)));

            Stream.of(subscriptionList).peek(subscriptions -> subscriptions.forEach(System.out::println)).forEach(subscriptionRepository::saveAll);
        };
    }
}
