package io.github.marmer.protim.tryouts;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleController {
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String someGet() {
		return "It works without a teapot";
	}

}
