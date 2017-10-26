package io.github.marmer.protim.tryouts;

import io.github.marmer.protim.model.Todo;
import io.github.marmer.protim.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


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
