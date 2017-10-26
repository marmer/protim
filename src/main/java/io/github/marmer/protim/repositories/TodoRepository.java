package io.github.marmer.protim.repositories;

import io.github.marmer.protim.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
