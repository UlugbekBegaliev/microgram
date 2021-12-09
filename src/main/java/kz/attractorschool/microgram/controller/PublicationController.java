package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.CommentDTO;
import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.service.CommentService;
import kz.attractorschool.microgram.service.LikeService;
import kz.attractorschool.microgram.service.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@AllArgsConstructor
@RequestMapping("/publications")
public class PublicationController {

    private final PublicationService publicationService;
    private final LikeService likeService;
    private final CommentService commentService;

    @GetMapping("/{publicationId}")
    public PublicationDTO findPostById(@PathVariable String publicationId) {
        return publicationService.findUserById(publicationId);
    }

    @ApiIgnore
    @GetMapping
    public Page<PublicationDTO> findPublication(@ApiIgnore Pageable pageable) {
        return publicationService.findPublications(pageable);
    }

    @PostMapping("/publication")
    public PublicationDTO publicationDTO(@RequestParam MultipartFile poster,
                                         @RequestParam String description,
                                         Authentication authentication) {
        return publicationService.publication(poster, description, authentication);
    }

    @PostMapping("/publication")
    public PublicationDTO publication(@RequestParam MultipartFile poster,
                                      @RequestParam String description,
                                      Authentication authentication) {
        return publicationService.publication(poster, description, authentication);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO publication(@RequestBody PublicationDTO publicationDTO, Authentication authentication) {
        String username = authentication.getName();
        return publicationService.addPublication(publicationDTO, username);
    }

    @ApiIgnore
    @GetMapping("/{publicationId}/comments")
    public Slice<CommentDTO> findCommentsByPublicationId(@ApiIgnore Pageable pageable, @PathVariable String postId) {
        return commentService.findCommentByPublicationId(pageable, postId);
    }

    @DeleteMapping("{publicationId}")
    public ResponseEntity<Void> removePublication(@PathVariable String publicationId) {
        if (publicationService.removePublication(publicationId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
