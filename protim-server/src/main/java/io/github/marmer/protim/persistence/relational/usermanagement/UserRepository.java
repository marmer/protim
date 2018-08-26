package io.github.marmer.protim.persistence.relational.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    boolean existsByUsername(String username);

    Optional<UserDBO> findByUsername(String username);

    @Query("select u.username from UserDBO u order by u.username")
    List<String> findAllUsernames();
}
