package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.dto.UserDTO;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.Subscription;
import kz.attractorschool.microgram.entity.User;
import org.springframework.data.domain.Pageable;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.PublicationRepository;
import kz.attractorschool.microgram.repository.SubscriptionRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder encoder;


    public Slice<UserDTO> findUsers(Pageable pageable) {
        Page<User> slice = userRepository.findAll(pageable);
        updateNumbers(slice);
        return slice.map(UserDTO::from);
    }

    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти пользователя с таким именем: " + username));
        updateNumbers(user);
        return UserDTO.from(user);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти пользователя с адресом электронной почты: " + email));
        updateNumbers(user);
        return UserDTO.from(user);
    }

    public String existsUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return "Пользователь с данной электронной почтой найден:  " + email;
        } else {
            return "Пользователь с данной электронной почтой не найден: " + email;
        }
    }

    public Slice<UserDTO> findOtherUsers(Pageable pageable, String username) {
        Page<User> slice = userRepository.findAllByUsernameNotContains(pageable, username);
        updateNumbers(slice);
        return slice.map(UserDTO::from);
    }

    private void updateNumbers(Iterable<User> users) {
        users.forEach(user -> {
            user.setNumOfPublications(publicationRepository.countByUserEmail(user.getEmail()));
            user.setNumOfFollowers(subscriptionRepository.countByFollowingEmail(user.getEmail()));
            user.setNumOfFollowings(subscriptionRepository.countByFollowerEmail(user.getEmail()));
        });
    }

    private void updateNumbers(User user) {
        user.setNumOfPublications(publicationRepository.countByUserEmail(user.getEmail()));
        user.setNumOfFollowers(subscriptionRepository.countByFollowingEmail(user.getEmail()));
        user.setNumOfFollowings(subscriptionRepository.countByFollowerEmail(user.getEmail()));
    }

    public List<PublicationDTO> findOtherPublications(Pageable pageable, String username) {
        Page<User> users = userRepository.findAllByUsernameNotContains(pageable, username);
        Page<Publication> publications = publicationRepository.findAll(pageable);

        List<Publication> newPublications = new ArrayList<>();
        for (User user : users) {
            for (Publication publication : publications) {
                if (user.getEmail().equals(publication.getUser().getEmail())) {
                    newPublications.add(publication);
                }
            }
        }

        return newPublications.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

    public List<PublicationDTO> findPublicationsBasedFollowings(Pageable pageable, String email) {

        Page<Publication> publications = publicationRepository.findAll(pageable);
        Page<Subscription> subscriptions = subscriptionRepository.findAllByFollowerEmail(pageable, email);
        List<Publication> newPublications = new ArrayList<>();

        for (Publication publication : publications) {
            for (Subscription subscription : subscriptions) {
                if (publication.getUser().getEmail().equals(subscription.getFollowing().getEmail())) {
                    newPublications.add(publication);
                }
            }
        }
        return newPublications.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

    public UserDTO register(UserDTO userDTO){
        User user = User
                .builder()
                .username(userDTO.getUsername())
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        userRepository.save(user);
        return UserDTO.from(user);
    }

    public boolean removeUser(String username) {
        userRepository.deleteByUsername(username);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("Пользователь не существует!");
        return optionalUser.get();
    }
}
