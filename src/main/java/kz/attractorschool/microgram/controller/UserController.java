package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.dto.UserDTO;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.service.PublicationService;
import kz.attractorschool.microgram.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PublicationService publicationService;

    @ApiIgnore
    @GetMapping("/user/posts")
    public Page<PublicationDTO> findPostsByEmail(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return publicationService.findPublicationsByEmail(pageable, user.getEmail());
    }

    @GetMapping("/username")
    public UserDTO findUserByUsername(Authentication authentication) {
        String username = authentication.getName();
        return userService.findUserByUsername(username);
    }

    @DeleteMapping("/username")
    public ResponseEntity<Void> removeUser(Authentication authentication) {
        String username = authentication.getName();
        if (userService.removeUser(username))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/")
    public UserDTO findUserByEmail(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findUserByEmail(user.getEmail());
    }

    @GetMapping("/story")
    public List<PublicationDTO> findPublicationsBasedFollowings(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findPublicationsBasedFollowings((java.awt.print.Pageable) pageable, user.getEmail());
    }
}
