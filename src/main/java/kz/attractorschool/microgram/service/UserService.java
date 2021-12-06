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
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;
    private SubscriptionRepository subscriptionRepository;


    public Slice<UserDTO> findUsers(Pageable pageable) {
        Page<User> slice = userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        updateNumbers(User.builder().build());
        return slice.map(UserDTO::from);
    }

    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the name: " + username));
        updateNumbers(user);
        return UserDTO.from(user);
    }

    private void updateNumbers(User user) {
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the email: " + email));
        updateNumbers(user);
        return UserDTO.from(user);
    }

    public String existsUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return "There's a user with email: " + email;
        } else {
            return "There's no user with email: " + email;
        }
    }

    public Slice<UserDTO> findOtherUsers(Pageable pageable, String username) {
        Page<User> slice = userRepository.findAllByUsernameNotContains(pageable, username);
        updateNumbers(User.builder().build());
        return slice.map(UserDTO::from);
    }

    public List<PublicationDTO> findOtherPosts(Pageable pageable, String username){
        Page<User> users = userRepository.findAllByUsernameNotContains(pageable, username);
        Page<Publication> publications = publicationRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        List<Publication> newPosts = new ArrayList<>();
        for (User user : users) {
            for (Publication publication: publications) {
                if(user.getEmail().equals(publication.getUser().getEmail())){
                    newPosts.add(publication);
                }
            }
        }

        return  newPosts.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

    public List<PublicationDTO> findPublicationBasedFollowings(Pageable pageable, String email){

        Page<Publication> publications = publicationRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        Page<Subscription> subscriptions = subscriptionRepository.findAllBySubscriberEmail(pageable, email);
        List<Publication> newPosts = new ArrayList<>();

        for (Publication publication: publications) {
            for (Subscription subscription : subscriptions) {
                if (publication.getUser().getEmail().equals(subscription.getFollower().getEmail())) {
                    newPosts.add(publication);
                }
            }
        }


        return  newPosts.stream().map(PublicationDTO::from).collect(Collectors.toList());
    }

}
