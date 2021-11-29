package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.repository.LikeRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class LikeService {
    private UserRepository userRepository;
    private PublicationService publicationService;
    private LikeRepository likeRepository;
}
