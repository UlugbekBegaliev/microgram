package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.repository.CommentRepository;
import kz.attractorschool.microgram.repository.PublicationRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;
}
