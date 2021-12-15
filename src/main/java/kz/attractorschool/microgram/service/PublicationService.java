package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.entity.Image;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;

    public Page<PublicationDTO> findPublications(Pageable pageable) {
        Page<Publication> publications = publicationRepository.findAll(pageable);
        updateNumbers(publications);
        return publications.map(PublicationDTO::from);
    }

    public Page<PublicationDTO> findPublicationsByEmail(Pageable pageable, String email) {
        Page<Publication> publications = publicationRepository.findAllByUserEmail(pageable, email);
        updateNumbers(publications);
        return publications.map(PublicationDTO::from);
    }

    public PublicationDTO publication(MultipartFile poster,
                                      String description,
                                      Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String path = "/images/";
        File posterFile = new File(path + poster.getOriginalFilename());
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(posterFile);
            outputStream.write(poster.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Publication publication = Publication.builder()
                .user(user)
                .dateTime(LocalDateTime.now())
                .id(UUID.randomUUID().toString())
                .image(poster.getOriginalFilename())
                .description(description)
                .build();

        publicationRepository.save(publication);
        return PublicationDTO.from(publication);
    }

    public PublicationDTO findUserById(String publicationId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти запись с идентификатором: " + publicationId));
        updateNumbers(publication);
        return PublicationDTO.from(publication);
    }

    private void updateNumbers(Publication publication) {
        publication.setNumOfLikes(likeRepository.countByPublicationId(publication.getId()));
        publication.setNumOfComments(commentRepository.countByPublicationId(publication.getId()));
    }

    private void updateNumbers(Iterable<Publication> publications) {
        publications.forEach(publication -> {
            publication.setNumOfLikes(likeRepository.countByPublicationId(publication.getId()));
            publication.setNumOfComments(commentRepository.countByPublicationId(publication.getId()));
        });
    }

    public PublicationDTO addPublication(PublicationDTO publicationDTO, String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти запись с именем: " + username));

        Image image = imageRepository
                .findById(publicationDTO.getImage())
                .orElseThrow(() -> new ResourceNotFoundException("Опубликовать изображение с " + publicationDTO.getImage() + "не предоставляется возможным."));

        Publication publication = Publication
                .builder()
                .id(publicationDTO.getId())
                .user(user)
                .image(publicationDTO.getImage())
                .dateTime(publicationDTO.getDateTime())
                .description(publicationDTO.getDescription())
                .build();

        publicationRepository.save(publication);
        return PublicationDTO.from(publication);
    }

    public boolean removePublication(String publicationId) {
        publicationRepository.deleteById(publicationId);
        return true;
    }
}
