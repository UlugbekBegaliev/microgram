package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.LikeDTO;
import kz.attractorschool.microgram.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping
    public Slice<LikeDTO> findLikes(Pageable pageable) {
        return likeService.findLikes(pageable);
    }

    @GetMapping("/liked")
    public Slice<LikeDTO> findOtherLikes(Pageable pageable, Authentication authentication) {
        return likeService.findOtherLikes(pageable, authentication);
    }

    @PostMapping("/{publicationId}")
    public LikeDTO like(@PathVariable String publicationId,
                        Authentication authentication) {
        String likerName = authentication.getName();
        return likeService.addLike(publicationId, likerName);
    }

    @DeleteMapping("{likeId}")
    public ResponseEntity<Void> unlike(@PathVariable String likeId) {
        if (likeService.deleteLike(likeId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
