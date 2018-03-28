package io.github.marmer.protim.tryouts;

import io.github.marmer.protim.model.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@RestController
@RequestMapping("rest/sample")
public class SampleController {
	public static final String RANDOM = "Random ";
	@Autowired
	private SampleModelRepository sampleModelRepository;

	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String someGet(final HttpServletRequest request) {
		final Random random = new Random();
		final SampleModel entity = SampleModel.builder().niceProperty(RANDOM + random.nextInt()).build();
		sampleModelRepository.save(entity);
		return "It works without a teapot";
	}

	@GetMapping(path = "json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SampleModel someGetJson() {
		final Random random = new Random();
		final SampleModel entity = SampleModel.builder().niceProperty(RANDOM + random.nextInt()).build();
		sampleModelRepository.save(entity);
		return entity;
	}

	@GetMapping(path = "xml", produces = MediaType.APPLICATION_XML_VALUE)
	public SampleModel someGetXml() {
		final Random random = new Random();
		final SampleModel entity = SampleModel.builder().niceProperty(RANDOM + random.nextInt()).build();
		sampleModelRepository.save(entity);
		return entity;
	}

}
