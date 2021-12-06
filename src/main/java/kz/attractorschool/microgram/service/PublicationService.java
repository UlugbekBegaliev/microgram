package kz.attractorschool.microgram.service;


import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PublicationService {
    private PublicationRepository publicationRepository;
    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private ImageRepository imageRepository;

    public Page<PublicationDTO> findPosts(Pageable pageable){
        Page<Publication> posts = publicationRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        updateNumbers(Publication.builder().build());
        return  posts.map(PublicationDTO::from);
    }
    public Page<PublicationDTO> findPostsByEmail(Pageable pageable, String email){
        Page<Publication> publications = publicationRepository.findAllByUserEmail(pageable, email);
        updateNumbers((Publication) publications);
        return  publications.map(PublicationDTO::from);
    }
    public PublicationDTO findUserById(String postId) {
        Publication publication = publicationRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find post with the id: " + postId));
        updateNumbers(publication);
        return PublicationDTO.from(publication);
    }

    private void updateNumbers(Publication publication){
        publication.setNumOfLikes(likeRepository.countByPostId(publication.getId()));
        publication.setNumOfComments(commentRepository.countByPublicationId(publication.getId()));
    }
}
