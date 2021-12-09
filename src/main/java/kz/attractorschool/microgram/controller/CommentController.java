package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.CommentDTO;
import kz.attractorschool.microgram.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Slice<CommentDTO> findComments(Pageable pageable) {
        return commentService.findAll(pageable);
    }

    @PostMapping(path = "/{publicationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO comment(@RequestBody CommentDTO commentDTO, @PathVariable String publicationId,
                              Authentication authentication) {
        String commenterName = authentication.getName();
        return commentService.comment(commentDTO, publicationId, commenterName);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        if (commentService.removeComment(commentId))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
