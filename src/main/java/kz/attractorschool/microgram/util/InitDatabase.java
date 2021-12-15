package kz.attractorschool.microgram.util;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@AllArgsConstructor
@Configuration
public class InitDatabase {

    private final EventRepository eventRepository;
    private final SubscriptionRepository subscriptionRepository;

    // раскомменитруйте следующие строки
    // когда создадите репозиторий пользователя
    // и сервис авторизации
//    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;

    @Bean
    public CommandLineRunner fillData() {
        return (args) -> {
            subscriptionRepository.deleteAll();
            eventRepository.deleteAll();
            eventRepository.save(Event.builder().id("e001").name("Make the shelter").description("Make it sturdy").eventDate(LocalDateTime.now().minusDays(1)).build());
            eventRepository.save(Event.builder().id("e011").name("Repair the shelter").description("Make it safe and cozy.").eventDate(LocalDateTime.now().plusDays(1)).build());
            eventRepository.save(Event.builder().id("e012").name("Gather some firewood").description("To keep the fireplace running").eventDate(LocalDateTime.now().plusDays(1)).build());
            eventRepository.save(Event.builder().id("e013").name("Winter is coming!").description("Survive the winter!").eventDate(LocalDateTime.now().plusDays(1)).build());

            // раскомменитруйте следующие строки
            // когда создадите репозиторий пользователя
            // и сервис авторизации
//            userRepository.deleteAll();
//
//            User user1 = new User();
//            user1.setEmail("test@test");
//            user1.setPassword(encoder.encode("test"));
//            userRepository.save(user1);

//            User user2 = new User();
//            user2.setEmail("guest@test");
//            user2.setPassword(encoder.encode("guest"));
//            userRepository.save(user2);

//            User user3 = new User();
//            user3.setEmail("admin@test");
//            user3.setPassword(encoder.encode("admin"));
//            userRepository.save(user3);

        };
    }
}
