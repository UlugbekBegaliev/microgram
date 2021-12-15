package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.annotation.ApiPageable;
import kz.attractorschool.microgram.dto.PublicationDTO;
import kz.attractorschool.microgram.dto.UserDTO;
import kz.attractorschool.microgram.entity.User;
import kz.attractorschool.microgram.service.PublicationService;
import kz.attractorschool.microgram.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PublicationService publicationService;

    @ApiPageable
    @GetMapping
    public Slice<UserDTO> findUsers(@ApiIgnore Pageable pageable){
        return userService.findUsers(pageable);
    }

    @ApiPageable
    @GetMapping("/user/publications")
    public Page<PublicationDTO> findPublicationsByEmail(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return publicationService.findPublicationsByEmail(pageable, user.getEmail());
    }

    @GetMapping("/username")
    public UserDTO findUserByUsername(Authentication authentication) {
        String username = authentication.getName();
        return userService.findUserByUsername(username);
    }

    @ApiPageable
    @GetMapping("/others")
    public Slice<UserDTO> findOtherUsers(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findOtherUsers(pageable, user.getUsername());
    }
    @GetMapping("/explore")
    public List<PublicationDTO> findOtherPublications(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findOtherPublications(pageable, user.getUsername());
    }

    @GetMapping("/email/")
    public UserDTO findUserByEmail(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findUserByEmail(user.getEmail());
    }

    @GetMapping("/email/exist")
    public String existUserByEmail(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return userService.existsUserByEmail(user.getEmail());
    }

    @GetMapping("/story")
    public List<PublicationDTO> findPublicationsBasedFollowings(@ApiIgnore Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.findPublicationsBasedFollowings(pageable, user.getEmail());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

    @DeleteMapping("/username")
    public ResponseEntity<Void> removeUser(Authentication authentication) {
        String username = authentication.getName();
        if (userService.removeUser(username))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
