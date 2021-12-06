package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.LikeDTO;
import kz.attractorschool.microgram.entity.Like;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.repository.LikeRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LikeService {
    private UserRepository userRepository;
    private PublicationService publicationService;
    private LikeRepository likeRepository;

    public Page<LikeDTO> findLikes(Pageable pageable){
        return likeRepository.findAll((org.springframework.data.domain.Pageable) pageable).map(LikeDTO::from);
    }

    public Page<LikeDTO> findLikesByPublicationId(Pageable pageable, String publicationId){
        Page<Like> likes = likeRepository.findAllByPublicationId(pageable, publicationId);
        return likes.map(LikeDTO::from);
    }
}
