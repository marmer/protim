package io.github.marmer.protim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.marmer.protim.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}