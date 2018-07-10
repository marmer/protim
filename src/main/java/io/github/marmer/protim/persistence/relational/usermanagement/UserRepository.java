package io.github.marmer.protim.persistence.relational.usermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    boolean existsByUsername(String username);
}
