package io.github.marmer.protim.tryouts;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.marmer.protim.model.Todo;
import io.github.marmer.protim.repositories.TodoRepository;

@RestController
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Todo create(@RequestBody final Todo todo) {
		if (todo.getCreatedOn() == null) {
			todo.setCreatedOn(new Date());
		}
		return todoRepository.save(todo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable("id") final Integer id) {
		todoRepository.delete(id);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Todo> persons() {
		return todoRepository.findAll();
	}
}