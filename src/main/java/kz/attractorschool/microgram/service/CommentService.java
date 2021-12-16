package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.CommentDTO;
import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.Publication;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.exeptions.ResourceNotFoundException;
import kz.attractorschool.microgram.repository.CommentRepository;
import kz.attractorschool.microgram.repository.PublicationRepository;
import kz.attractorschool.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final String SYSTEM_RESPONSE_ID = "Не удается найти пользователя с идентификатором: ";
    private final String SYSTEM_RESPONSE_NAME = "Не удается найти пользователя с именем: ";

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;

    public Slice<CommentDTO> findAll(Pageable pageable) {
        Slice<Comment> comments = commentRepository.findAll(pageable);
        return comments.map(CommentDTO::from);
    }

    public CommentDTO comment(CommentDTO commentDTO, String publicationId, String commenterUsername) {
        Publication publication = publicationRepository
                .findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException(SYSTEM_RESPONSE_ID + publicationId));

        User user = userRepository
                .findByUsername(commenterUsername)
                .orElseThrow(() -> new ResourceNotFoundException(SYSTEM_RESPONSE_NAME + commenterUsername));

        Comment comment = Comment
                .builder()
                .id(commentDTO.getId())
                .commenter(user)
                .publication(publication)
                .text(commentDTO.getText())
                .path(commentDTO.getPath())
                .dateTime(commentDTO.getDateTime())
                .build();

        commentRepository.save(comment);

        return CommentDTO.from(comment);
    }

    public Slice<CommentDTO> findCommentByPublicationId(Pageable pageable, String publicationId) {
        Slice<Comment> comments = commentRepository.findAllByPublicationId(pageable, publicationId);
        return comments.map(CommentDTO::from);
    }

    public boolean removeComment(String commentId) {
        commentRepository.deleteById(commentId);
        return true;
    }
}
