package io.github.marmer.protime.tryouts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sample")
public class SampleController {
	@GetMapping(path = "someGet", produces = "text/plain")
	public String someGet() {
		return "It works without a teapot";
	}

}
