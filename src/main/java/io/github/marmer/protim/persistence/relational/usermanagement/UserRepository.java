package io.github.marmer.protim.persistence.relational.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    boolean existsByUsername(String username);

    Optional<UserDBO> findByUsername(String username);
}
