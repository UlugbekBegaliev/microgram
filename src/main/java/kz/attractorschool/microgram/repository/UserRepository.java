package kz.attractorschool.microgram.repository;

import kz.attractorschool.microgram.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Page<User> findAllByUsernameNotContains(Pageable pageable, String username);

    void deleteByUsername(String username);
}
