package io.github.marmer.protim;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.marmer.protim.model.Todo;
import io.github.marmer.protim.repositories.TodoRepository;

@Component
public class DatabasePopulator {

	@Autowired
	private TodoRepository todoRepository;

	@PostConstruct
	void init() {
		final Todo t1 = new Todo(null, "Task 1", new Date());
		final Todo t2 = new Todo(null, "Task 2", new Date());
		final Todo t3 = new Todo(null, "Task 3", new Date());
		this.todoRepository.save(Arrays.asList(t1, t2, t3));
	}
}