package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.dto.UserDTO;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.Subscription;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.PublicationRepository;
import kz.attractorschool.microgram.repository.SubscriptionRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;
    private SubscriptionRepository subscriptionRepository;


    public Slice<UserDTO> findUsers(Pageable pageable) {
        Page<User> slice = userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
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
            user.setNumOfFollowers(subscriptionRepository.countBySubscriptionEmail(user.getEmail()));
            user.setNumOfFollowings(subscriptionRepository.countBySubscriberEmail(user.getEmail()));
        });
    }

    private void updateNumbers(User user) {
        user.setNumOfPublications(publicationRepository.countByUserEmail(user.getEmail()));
        user.setNumOfFollowers(subscriptionRepository.countBySubscriptionEmail(user.getEmail()));
        user.setNumOfFollowings(subscriptionRepository.countBySubscriberEmail(user.getEmail()));
    }

    public List<PublicationDTO> findOtherPublications(Pageable pageable, String username) {
        Page<User> users = userRepository.findAllByUsernameNotContains(pageable, username);
        Page<Publication> publications = publicationRepository.findAll((org.springframework.data.domain.Pageable) pageable);

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

        Page<Publication> publications = publicationRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        Page<Subscription> subscriptions = subscriptionRepository.findAllBySubscriberEmail(pageable, email);
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

    public boolean removeUser(String username) {
        userRepository.deleteByUsername(username);
        return true;
    }


}
