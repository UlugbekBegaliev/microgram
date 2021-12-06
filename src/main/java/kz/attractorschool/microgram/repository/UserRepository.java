package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.Comment;
import kz.attractorschool.microgram.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    Page<User> findAllByUsernameNotContains(Pageable pageable, String username);
    void deleteByUsername(String username);
}
