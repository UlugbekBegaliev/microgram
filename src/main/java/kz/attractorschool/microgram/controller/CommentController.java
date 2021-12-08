package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.CommentDTO;
import kz.attractorschool.microgram.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Slice<CommentDTO> findComments(Pageable pageable){
        return commentService.findAll(pageable);
    }
}
