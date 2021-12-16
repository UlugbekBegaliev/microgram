package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.LikeDTO;
import kz.attractorschool.microgram.entity.Like;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.LikeRepository;
import kz.attractorschool.microgram.repository.PublicationRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    public Page<LikeDTO> findLikes(Pageable pageable) {
        return likeRepository.findAll(pageable).map(LikeDTO::from);
    }

    public Page<LikeDTO> findOtherLikes(Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return likeRepository.findAllByLikerEmail(pageable, user.getEmail()).map(LikeDTO::from);
    }

    public Page<LikeDTO> findLikesByPublicationId(Pageable pageable, String publicationId) {
        Page<Like> likes = likeRepository.findAllByPublicationId(pageable, publicationId);
        return likes.map(LikeDTO::from);
    }

    public LikeDTO addLike(String publicationId, String likerUsername) {

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти пользователя с идентификатором: " + publicationId));

        User user = userRepository.findByUsername(likerUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается найти пользователя с именем: " + likerUsername));

        Like like = Like.builder()
                .id(UUID.randomUUID().toString())
                .liker(user)
                .publication(publication)
                .dateTime(LocalDateTime.now())
                .build();

        likeRepository.save(like);

        return LikeDTO.from(like);
    }

    public boolean deleteLike(String likeId) {
        likeRepository.deleteById(likeId);
        return true;
    }
}
